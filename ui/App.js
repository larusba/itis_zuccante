import * as React from 'react';
import { Provider } from 'react-redux';
import createStore from './app/shared/reducers';
import * as SplashScreen from 'expo-splash-screen';
import { PaperProvider } from 'react-native-paper';

import NavContainer from './app/navigation/nav-container';

const store = createStore();

const theme = {
  dark: false,
};

export default function App() {
  // prevent the splashscreen from disappearing until the redux store is completely ready (hidden in nav-container.js)
  const [displayApp, setDisplayApp] = React.useState(false);
  React.useEffect(() => {
    if (!displayApp) {
      SplashScreen.preventAutoHideAsync()
        .then(() => setDisplayApp(true))
        .catch(() => setDisplayApp(true));
    }
  }, [displayApp, setDisplayApp]);

  return displayApp ? (
    <Provider store={store}>
      <PaperProvider theme={theme}>
        <NavContainer />
      </PaperProvider>
    </Provider>
  ) : null;
}
