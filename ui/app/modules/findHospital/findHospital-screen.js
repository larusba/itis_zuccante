import { Modal, TextInput, Portal, PaperProvider, Searchbar, Searchview, List, Button, Snackbar } from 'react-native-paper';
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
  const [visibleBanana, setVisibleBanana] = React.useState(false);
  const [msg, setMsg] = useState('');
  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = { backgroundColor: 'white', padding: 15, borderRadius: 20, fontSize: 30 };
  //const [visible, setVisible] = React.useState(false);
  const onToggleSnackBar = () => setVisible(!visible);
  const onDismissSnackBar = () => setVisible(false);
  const theme = {
    dark: false,
  };

  useEffect(() => {
    (async () => {
      const response = await fetch(`${config.apiUrl}api/healthServices`, {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });

      setListHealtServices(await response.json());
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
    setServerResponse(await response.json());
    setVisibleBanana(false);
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
    setVisibleBanana(true);
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
    <PaperProvider theme={theme}>
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
      <Snackbar
        visible={visibleBanana}
        onDismiss={onDismissSnackBar}
        action={{
          label: 'Ok',
          onPress: () => {
            setVisibleBanana(false);
          },
        }}
      >
        {msg}
      </Snackbar>
    </PaperProvider>
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
    margin: '10%',
    textAlign: 'center',
    position: 'fixed',
  },
  textInput: {
    textAlign: 'center',
  },
});
