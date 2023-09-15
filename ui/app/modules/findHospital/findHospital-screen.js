import { Searchbar, Searchview, List } from 'react-native-paper';
import React, { useEffect, useState } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import { random } from 'lodash';
export default function FindHospital() {
  const [query, setQuery] = useState('');
  const [selected, setSelected] = useState([]);
  const [results, setResults] = useState([]);
  const [listHealthServices, setListHealtServices] = useState([]);
  
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
  function miaFunzione2(stringa) {
    const lista = selected;
    lista.push(stringa.target.textContent);
    setSelected(lista);
    console.log(selected);
  }
  function miaFunzione(healthService, i) {
    return <List.Item title={healthService.name} onPress={miaFunzione2} key={i} />;
  }
  function miaFunzione4(name){
    const miaLista = selected;
    let i = miaLista.indexOf(name);
    miaLista.splice(i, i+1);
    setSelected(miaLista);
  }
  function miaFunzione3(healthService, i) {
    return <List.Item title={healthService} onPress={miaFunzione4} key={i} />;
  }
  return (
    <View>
      <Searchbar
        placeholder="Search"
        mode={'view'}
        onChangeText={query => {
          const tmp = listHealthServices.filter(item => item.name.toLowerCase().includes(query.toLowerCase()));
          setResults(tmp.slice(0, 5));
          setQuery(query);
        }}
      />

      {results.length > 0 && query.length > 0 && <View style={styles.searchResult}>{results.map(miaFunzione)}</View>}
      {selected.length > 0 && <View style={styles.searchResult}>{selected.map(miaFunzione3)}</View>}
    </View>
  );
}
const styles = StyleSheet.create({
  searchResult: {
    //marginTop: 40,
    //marginBottom: 160,
    borderColor: '#20232a',
    borderWidth: 3,
  },
  paragraph: {},
});
