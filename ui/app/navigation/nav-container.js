import React, { useState, useEffect } from 'react';
import { AppState, Text, useWindowDimensions, View, StyleSheet } from 'react-native';
import * as Linking from 'expo-linking';
import * as SplashScreen from 'expo-splash-screen';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { createStackNavigator } from '@react-navigation/stack';
import { useReduxDevToolsExtension } from '@react-navigation/devtools';
import { connect } from 'react-redux';
import * as Location from 'expo-location';

// import screens
import GPSLocationScreen from '../modules/gpsLocation/gpsLocation-screen.js';
import HomeScreen from '../modules/home/home-screen';
import LoginScreen from '../modules/login/login-screen';
import AccountActions from '../shared/reducers/account.reducer';
import EntityStackScreen, { getEntityRoutes } from './entity-stack';
import StorybookScreen from '../../storybook';
import ChatScreen from '../modules/chat/chat-screen';
import DrawerContent from './drawer/drawer-content';
import { isReadyRef, navigationRef } from './nav-ref';
import NotFound from './not-found-screen';
import { ModalScreen } from './modal-screen';
import { DrawerButton } from './drawer/drawer-button';
import FindHospital from '../modules/findHospital/findHospital-screen.js';
import HospitaInformation from '../modules/hospitalInformation/hospitalInformation-screen.js';

export const drawerScreens = [
  {
    name: 'Home',
    component: HomeScreen,
    auth: null,
  },
  {
    name: 'Login',
    route: 'login',
    component: LoginScreen,
    auth: false,
  },
  {
    name: 'Chat',
    route: 'chat',
    component: ChatScreen,
    auth: true,
  },
  {
    name: 'GPSLocation',
    route: 'gpsLocation',
    component: GPSLocationScreen,
    auth: true,
  },
  {
    name: 'Find nearest hospital',
    route: 'findHospital',
    component: FindHospital,
    auth: true,
  },
  {
    name: 'About hospital',
    route: 'hospitalInformation',
    component: HospitaInformation,
    auth: true,
  },
];
if (__DEV__) {
  drawerScreens.push({
    name: 'Storybook',
    route: 'storybook',
    component: StorybookScreen,
    auth: false,
  });
}
export const getDrawerRoutes = () => {
  const routes = {};
  drawerScreens.forEach(screen => {
    if (screen.route) {
      routes[screen.name] = screen.route;
    }
  });
  return routes;
};

const linking = {
  prefixes: ['rnapp://', Linking.makeUrl('/')],
  config: {
    initialRouteName: 'Home',
    screens: {
      Home: {
        screens: {
          ...getDrawerRoutes(),
          EntityStack: {
            path: 'entities',
            screens: {
              ...getEntityRoutes(),
            },
          },
        },
      },
      ModalScreen: 'alert',
      NotFound: '*',
    },
  },
};

const Stack = createStackNavigator();
const Drawer = createDrawerNavigator();

const getScreens = props => {
  const isAuthed = props.account !== null;
  return drawerScreens.map((screen, index) => {
    if (screen.auth === null || screen.auth === undefined) {
      return <Drawer.Screen name={screen.name} component={screen.component} options={screen.options} key={index} />;
    } else if (screen.auth === isAuthed) {
      return <Drawer.Screen name={screen.name} component={screen.component} options={screen.options} key={index} />;
    }
    return null;
  });
};

function NavContainer(props) {
  const { loaded, getAccount } = props;
  const lastAppState = 'active';
  const [location, setLocation] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);

  useEffect(() => {
    (async () => {
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== 'granted') {
        setErrorMsg('Permission to access location was denied');
        return;
      }

      let location = await Location.getCurrentPositionAsync({});
      setLocation(location);
    })();
  }, [props.account]);

  React.useEffect(() => {
    return () => {
      isReadyRef.current = false;
    };
  }, []);

  React.useEffect(() => {
    if (loaded) {
      SplashScreen.hideAsync();
    }
  }, [loaded]);

  React.useEffect(() => {
    const handleChange = nextAppState => {
      if (lastAppState.match(/inactive|background/) && nextAppState === 'active') {
        getAccount();
      }
    };
    AppState.addEventListener('change', handleChange);
    return () => AppState.removeEventListener('change', handleChange);
  }, [getAccount]);

  useReduxDevToolsExtension(navigationRef);

  const dimensions = useWindowDimensions();
  return !loaded ? (
    <View>
      <Text>Loading...</Text>
    </View>
  ) : !props.account ? (
    <LoginScreen navigation={navigationRef} />
  ) : (
    <NavigationContainer
      style={styles.navigationBarStyle}
      linking={linking}
      ref={navigationRef}
      onReady={() => {
        isReadyRef.current = true;
      }}
    >
      <Stack.Navigator>
        <Stack.Screen name="Home" options={{ headerShown: false }}>
          {() => (
            <Drawer.Navigator
              drawerContent={p => <DrawerContent {...p} />}
              initialRouteName={drawerScreens[0].name}
              drawerType={dimensions.width >= 768 ? 'permanent' : 'front'}
              screenOptions={{ headerShown: true, headerLeft: DrawerButton }}
            >
              {getScreens(props)}
            </Drawer.Navigator>
          )}
        </Stack.Screen>
        <Stack.Screen
          name="ModalScreen"
          component={ModalScreen}
          options={{
            headerShown: false,
            cardStyle: { backgroundColor: 'transparent' },
            cardOverlayEnabled: true,
            cardStyleInterpolator: ({ current: { progress } }) => ({
              cardStyle: {
                opacity: progress.interpolate({
                  inputRange: [0, 0.5, 0.9, 1],
                  outputRange: [0, 0.25, 0.7, 1],
                }),
              },
              overlayStyle: {
                opacity: progress.interpolate({
                  inputRange: [0, 1],
                  outputRange: [0, 0.5],
                  extrapolate: 'clamp',
                }),
              },
            }),
          }}
        />
        <Stack.Screen name="NotFound" component={NotFound} options={{ title: 'Oops!' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const mapStateToProps = state => {
  return {
    loaded: state.appState.rehydrationComplete,
    account: state.account.account,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    getAccount: () => dispatch(AccountActions.accountRequest()),
  };
};
const styles = StyleSheet.create({
  navigationBarStyle: {
    position: 'fixed',
  },
});
export default connect(mapStateToProps, mapDispatchToProps)(NavContainer);
