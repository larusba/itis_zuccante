import { Modal, TextInput, Portal, ActivityIndicator, MD2Colors, Searchbar, Searchview, List, Button, Snackbar } from 'react-native-paper';
import { faker } from '@faker-js/faker';
import React, { useEffect, useState } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import * as Location from 'expo-location';
import config from './../../config/app-config.js';
import * as Device from 'expo-device';
console.log(config);

export default function FindHospital() {
  const [name, setName] = useState(faker.person.firstName());
  const [surname, setSurname] = useState(faker.person.lastName());
  const [ambulanceNumber, setAmbulanceNumber] = useState(
    faker.airline.airline().iataCode + faker.airline.flightNumber({ addLeadingZeros: true })
  );
  const [address, setAddress] = useState(faker.location.streetAddress());
  const [serverResponse, setServerResponse] = useState([]);
  const [query, setQuery] = useState('');
  const [selected, setSelected] = useState([]);
  const [selectedHospital, setSelectedHospital] = useState(null);
  const [results, setResults] = useState([]);
  const [listHealthServices, setListHealtServices] = useState([]);
  const [location, setLocation] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);
  const [visible, setVisible] = React.useState(false);
  const [visibleSnackbar, setVisibleSnackbar] = React.useState(false);
  const [msg, setMsg] = useState('');
  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = { backgroundColor: 'white', padding: 15, borderRadius: 20, fontSize: 30 };
  //const [visible, setVisible] = React.useState(false);
  const onToggleSnackBar = () => setVisible(!visible);
  const onDismissSnackBar = () => setVisible(false);

  useEffect(() => {
    (async () => {
      const response = await fetch(`${config.apiUrl}api/healthServices`, {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });

      setListHealtServices(await response.json());
      console.log('setListHealtServices pijati');
    })();
  }, []);

  const findHospitalAndInterventions = async () => {
    const response = await fetch(`${config.apiUrl}api/FindNumberOfInterventionByHospitalAll`, {
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    const json = await response.json();
    console.log('findHospitalAndInterventions', json);
    return json;
  };

  const findHospitalHandle = async () => {
    //console.log('bau');
    const response = await fetch(healthServiceStringChain(), {
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    const json = await response.json();
    console.log('nearestHospital pijato', json);
    const hospitalInterventions = await findHospitalAndInterventions();
    const map = new Map();
    hospitalInterventions.forEach(i => map.set(i.hospital.name, i.countIntervention));
    console.log('map', map);
    const ser = json.map(item => {
      item.interventions = map.get(item.hospitalName);
      return item;
    });
    console.log(await ser);
    setServerResponse(json);
    setVisibleSnackbar(false);
    setVisible(true);
  };

  const createIntervention = async () => {
    const data = {
      nomePaziente: name,
      cognomePaziente: surname,
      numeroAmbulanza: ambulanceNumber,
      luogoIntervento: address,
      latitude: location.coords.latitude,
      longitude: location.coords.longitude,
      tempoPercorrenza: Math.trunc(selectedHospital.duration),
      nomeOspedale: selectedHospital.hospitalName,
      nomePrestazione: selected,
    };
    const response = await fetch(`${config.apiUrl}api/createIntervention`, {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    console.log('bauzzzz');
    let json = await response.json();
    console.log(json);
    if (json.status) {
      setMsg('Error in creating the intervention');
    } else {
      setMsg('Intervention created');
    }
    setVisibleSnackbar(true);
    setSelectedHospital(null);
  };

  function healthServiceStringChain() {
    //console.log('bauz');
    let tmp = `${config.apiUrl}api/findNearestHospitalByHealthService?`;
    for (let i = 0; i < selected.length; i++) {
      tmp += 'healthServices=' + selected[i] + '&';
    }
    tmp += 'latitudine=' + location.coords.latitude + '&longitudine=' + location.coords.longitude;
    console.log(tmp);
    return tmp;
  }

  useEffect(() => {
    (async () => {
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== 'granted') {
        setErrorMsg('Permission to access location was denied');
        return;
      }

      let location = await Location.getCurrentPositionAsync({});
      setLocation(location);
      console.log('Location pijata');
    })();
  }, []);

  function miaFunzione2(stringa) {
    const lista = [...selected];
    lista.push(stringa.target.textContent);
    if (!selected.includes(stringa.target.textContent)) setSelected(lista);
  }

  function miaFunzione(healthService, i) {
    return <List.Item title={healthService.name} onPress={miaFunzione2} key={i} />;
  }

  function miaFunzione4(name) {
    const miaLista = [...selected];
    let i = miaLista.indexOf(name.target.textContent);
    miaLista.splice(i, 1);
    setSelected(miaLista);
  }

  function miaFunzione3(healthService, i) {
    return <List.Item title={healthService} onPress={miaFunzione4} key={i} />;
  }

  function hospitalSelectionHandle() {
    setServerResponse([]);
    return false;
  }
  return (
    <>
      <View>
        <Searchbar
          placeholder="Search"
          mode={'bar'}
          onChangeText={query => {
            const tmp = listHealthServices.filter(item => item.name.toLowerCase().includes(query.toLowerCase()));
            setResults(tmp.slice(0, 5));
            setQuery(query);
          }}
        />

        {results.length > 0 && query.length > 0 && <View style={styles.searchResult}>{results.map(miaFunzione)}</View>}
        <Button icon="ambulance" disabled={!selected.length > 0} mode="elevated" style={styles.paragraph} onPress={findHospitalHandle}>
          Find hospital
        </Button>
        {selected.length > 0 && <View style={styles.searchResult}>{selected.map(miaFunzione3)}</View>}
        <Portal>
          <Modal visible={serverResponse.length > 0} onDismiss={hospitalSelectionHandle} contentContainerStyle={{ boxShadow: 0 }}>
            {serverResponse.map((track, i) => (
              <Button
                onPress={e => {
                  //show motion tarcker
                  console.log('setSelectedHospital', track, e);
                  setSelectedHospital(track);
                  setServerResponse([]);
                }}
                key={i}
                style={styles.hospitalSelectionStyle}
              >
                {track.hospitalName} Intervent: {track.interventions} durations: {track.duration} distance: {track.distance}
              </Button>
            ))}
          </Modal>
        </Portal>
        <Portal>
          <Modal visible={!location || listHealthServices.length === 0} dismissable={false} contentContainerStyle={{ boxShadow: 0 }}>
            <ActivityIndicator animating={true} size={80} color={MD2Colors.red800} />
            <View style={styles.loadingTips}>si consiglia uno zoom del 100% in full screen</View>
          </Modal>
        </Portal>
        <Portal>
          <Modal
            visible={selectedHospital}
            onDismiss={() => setSelectedHospital(null)}
            contentContainerStyle={containerStyle}
            style={styles.modal}
          >
            <TextInput
              label="Hospital"
              value={selectedHospital?.hospitalName}
              disabled={true}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="duration (min)"
              value={Math.trunc(selectedHospital?.duration / 60)}
              disabled={true}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="distance (km)"
              value={Math.trunc(selectedHospital?.distance)}
              disabled={true}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="congestion"
              value={selectedHospital?.congestion}
              disabled={true}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="name"
              value={name}
              mode={'outlined'}
              onChangeText={e => setName(e)}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="surname"
              value={surname}
              mode={'outlined'}
              onChangeText={e => setSurname(e)}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="ambulance number"
              value={ambulanceNumber}
              onChangeText={e => setAmbulanceNumber(e)}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            <TextInput
              label="address"
              value={address}
              onChangeText={e => setAddress(e)}
              mode={'outlined'}
              style={Device.deviceType == 3 ? styles.textInputDesktop : styles.textInputPhone}
            />
            {location?.coords && Device.deviceType == 3 && (
              <View style={styles.mapPos}>
                <img
                  height={444}
                  width={444}
                  src={`https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/Routes?wp.0=${location?.coords?.latitude},${location?.coords?.longitude}&wp.1=${selectedHospital?.latitude},${selectedHospital?.longitude}&key=AgAOc6viEwsi16q0TRSkotHwE8lxjz_pY3dlRqpSxmmdV3rZ635LfgIjoeHhChlt&mapSize=1000,1000`}
                  alt={'mia mappa non propriamente mia'}
                ></img>
              </View>
            )}
            <br></br>
            <Button mode="elevated" onPress={() => createIntervention()}>
              CREATE INTERVENTION
            </Button>
          </Modal>
        </Portal>
      </View>
      <Portal>
        <Snackbar
          duration={1000}
          visible={visibleSnackbar}
          onDismiss={onDismissSnackBar}
          action={{
            label: 'Ok',
            onPress: () => {
              setVisibleSnackbar(false);
            },
          }}
        >
          {msg}
        </Snackbar>
      </Portal>
    </>
  );
}

const styles = StyleSheet.create({
  hospitalSelectionStyle: {
    backgroundColor: 'white',
    padding: 15,
    borderRadius: 0,
    fontSize: 30,
  },
  searchResult: {
    margin: 1,
    borderColor: '#20232a',
    borderWidth: 1,
    borderRadius: 20,
  },
  paragraph: {
    margin: 1,
    borderColor: '#20232a',
    borderRadius: 20,
  },
  modal: {
    margin: '17%',
    textAlign: 'center',
    position: 'fixed',
  },
  textInputDesktop: {
    textAlign: 'center',
    width: 450,
  },
  textInputPhone: {
    textAlign: 'center',
  },
  mapPos: {
    borderRadius: 5,
    bottom: '27%',
    left: '51.1%',
    position: 'fixed',
  },
  loadingTips: {
    textAlign: 'center',
    bottom: '-100%',
    fontSize: 50,
  },
});
