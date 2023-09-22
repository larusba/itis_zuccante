import React from 'react';
import { ScrollView, Text, Image, View, Platform, StyleSheet } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { connect } from 'react-redux';
import * as Device from 'expo-device';

import LearnMoreLinks from './learn-more-links.component.js';
import { Images } from '../../shared/themes';

function HomeScreen(props) {
  const { account } = props;
  return (
    <View style={styles.viewBackroundStyle}>
      <Text style={Device.deviceType == 3 ? styles.textStyleDesktop : styles.textStylePhone}> AMBULANZA VELOCE</Text>
    </View>
  );
}
const styles = StyleSheet.create({
  viewBackroundStyle: {
    color: 'blue',
  },
  textStylePhone: {
    fontWeight: 900,
    fontFamily: 'Copperplate',
    fontSize: 42,
    color: '#38cbfc',
    textAlign: 'center',
    marginTop: '30%',
    marginRight: '15%',
    marginLeft: '15%',
    position: 'absolute',
  },
  textStyleDesktop: {
    fontWeight: 900,
    fontFamily: 'Copperplate',
    fontSize: 60,
    color: '#38cbfc',
    textAlign: 'center',
    marginTop: '20%',
    marginRight: '30%',
    marginLeft: '30%',
    position: 'absolute',
  },
});

const mapStateToProps = state => ({ account: state.account.account });
const mapDispatchToProps = dispatch => ({});
export default connect(mapStateToProps, mapDispatchToProps)(HomeScreen);
