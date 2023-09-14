import { Searchbar, Searchview, List } from 'react-native-paper';
import React, { useEffect, useState } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import { random } from 'lodash';
export default function FindHospital() {
  const [query, setQuery] = useState('');
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
  function miaFunzione(healthService, i) {
    return <List.Item title={healthService} key={i} />;
  }
  return (
    <View>
      <Searchbar placeholder="Search" mode={'view'} onChangeText={e => setQuery(e)} />

      {query.length !== 0 && <View style={styles.searchResult}>{listHealthServices.map(miaFunzione)}</View>}
      {console.log(query)}
    </View>
  );
}
const styles = StyleSheet.create({
  searchResult: {
    marginTop: 40,
    marginBottom: 160,
    borderColor: '#20232a',
    borderWidth: 3,
  },
  paragraph: {},
});
