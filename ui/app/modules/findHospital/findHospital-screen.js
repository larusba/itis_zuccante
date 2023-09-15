import { Modal, TextInput, Portal, PaperProvider, Searchbar, Searchview, List, Button } from 'react-native-paper';
import { faker } from '@faker-js/faker';
import React, { useEffect, useState } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import { random } from 'lodash';
import * as Location from 'expo-location';
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
  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = { backgroundColor: 'white', padding: 15, borderRadius: 20, fontSize: 30 };

  useEffect(() => {
    (async () => {
      const response = await fetch('http://192.168.1.212:8080/api/healthServices', {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });

      setListHealtServices(await response.json());
    })();
  }, []);

  const findHospitalHandle = async () => {
    console.log('bau');
    const response = await fetch(healthServiceStringChain(), {
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    setServerResponse(await response.json());
    setVisible(true);
  };

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
    <PaperProvider>
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
        <Button icon="ambulance" mode="elevated" onPress={findHospitalHandle}>
          Find hospital
        </Button>
        {selected.length > 0 && <View style={styles.paragraph}>{selected.map(miaFunzione3)}</View>}

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
            <Button mode="elevated" onPress={findHospitalHandle}>
              CREATE INTERVENTION
            </Button>
          </Modal>
        </Portal>
      </View>
    </PaperProvider>
  );
  function healthServiceStringChain() {
    let tmp = 'http://192.168.1.212:8080/api/findNearestHospitalByHealthService?';
    for (let i = 0; i < selected.length; i++) {
      tmp += 'healthServices=' + selected[i] + '&';
    }
    tmp += 'latitudine=' + location.coords.latitude + '&longitudine=' + location.coords.longitude;
    console.log(tmp);
    return tmp;
  }
}
const styles = StyleSheet.create({
  searchResult: {
    margin: 2,
    borderColor: '#20232a',
    borderWidth: 3,
    borderRadius: 20,
  },
  paragraph: {
    margin: 2,
    borderColor: '#20232a',
    borderWidth: 3,
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
