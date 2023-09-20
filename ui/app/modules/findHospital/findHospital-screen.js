import { Modal, TextInput, Portal, ActivityIndicator, MD2Colors, Searchbar, Searchview, List, Button, Snackbar } from 'react-native-paper';
import { faker } from '@faker-js/faker';
import React, { useEffect, useState } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import * as Location from 'expo-location';
import config from './../../config/app-config.js';
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

  const findHospitalHandle = async () => {
    //console.log('bau');
    const response = await fetch(healthServiceStringChain(), {
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    const json = await response.json();
    setServerResponse(json);
    setVisibleSnackbar(false);
    setVisible(true);
    console.log('nearestHospital pijato', json);
  };

  const imageMap = async () => {
    const response = await fetch(
      'https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/Routes?wp.0=45.5033742,12.2362233&wp.1=45.4224654,12.0655365&key=AgAOc6viEwsi16q0TRSkotHwE8lxjz_pY3dlRqpSxmmdV3rZ635LfgIjoeHhChlt&mapSize=1000, 1000'
    );
  };

  const createIntervention = async () => {
    const data = {
      nomePaziente: name,
      cognomePaziente: surname,
      numeroAmbulanza: ambulanceNumber,
      luogoIntervento: address,
      latitude: location.coords.latitude,
      longitude: location.coords.longitude,
      tempoPercorrenza: Math.trunc(serverResponse.duration),
      nomeOspedale: serverResponse.hospitalName,
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
          <Modal visible={!location || listHealthServices.length === 0} dismissable={false}>
            <ActivityIndicator animating={true} color={MD2Colors.red800} />
          </Modal>
        </Portal>
        <Portal>
          <Modal visible={visible} onDismiss={hideModal} contentContainerStyle={containerStyle} style={styles.modal}>
            <TextInput label="Hospital" value={serverResponse.hospitalName} disabled={true} mode={'outlined'} style={styles.textInput} />
            <TextInput
              label="duration (min)"
              value={Math.trunc(serverResponse.duration / 60)}
              disabled={true}
              mode={'outlined'}
              style={styles.textInput}
            />
            <TextInput
              label="distance (km)"
              value={Math.trunc(serverResponse.distance)}
              disabled={true}
              mode={'outlined'}
              style={styles.textInput}
            />
            <TextInput label="congestion" value={serverResponse.congestion} disabled={true} mode={'outlined'} style={styles.textInput} />
            <TextInput label="name" value={name} mode={'outlined'} onChangeText={e => setName(e)} style={styles.textInput} />
            <TextInput label="surname" value={surname} mode={'outlined'} onChangeText={e => setSurname(e)} style={styles.textInput} />
            <TextInput
              label="ambulance number🥵🍆🥶"
              value={ambulanceNumber}
              onChangeText={e => setAmbulanceNumber(e)}
              mode={'outlined'}
              style={styles.textInput}
            />
            <TextInput label="address" value={address} onChangeText={e => setAddress(e)} mode={'outlined'} style={styles.textInput} />
            {location?.coords && (
              <View style={styles.mapPos}>
                <img
                  height={444}
                  width={444}
                  src={`https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/Routes?wp.0=${location?.coords?.latitude},${location?.coords?.longitude}&wp.1=${serverResponse.latitude},${serverResponse.longitude}&key=AgAOc6viEwsi16q0TRSkotHwE8lxjz_pY3dlRqpSxmmdV3rZ635LfgIjoeHhChlt&mapSize=1000,1000`}
                  alt="mia mappa non tanto mia"
                ></img>
              </View>
            )}
            <br></br>
            <Button
              mode="elevated"
              onPress={() => {
                createIntervention();
                hideModal();
              }}
            >
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
    margin: '20%',
    textAlign: 'center',
    position: 'fixed',
  },
  textInput: {
    textAlign: 'center',
    width: 450,
  },
  mapPos: {
    borderRadius: 5,
    bottom: '29.2%',
    left: '51.2%',
    position: 'fixed',
  },
});
