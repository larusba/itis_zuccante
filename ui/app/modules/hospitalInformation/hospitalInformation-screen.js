import React, { useState, useEffect } from 'react';
import { Platform, View, StyleSheet } from 'react-native';
import { Avatar, Button, Card, Text, Badge } from 'react-native-paper';
import config from './../../config/app-config.js';

export default function HospitaInformation() {
  const LeftContent = props => <Avatar.Icon {...props} icon="folder" />;
  const [listHAndI, setListAAndI] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`${config.apiUrl}api/FindNumberOfInterventionByHospitalAll`, {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });
      setListAAndI(await response.json());
    };
    fetchData();
    const timer = setInterval(fetchData, 1000);
    return () => {
      clear(timer);
    };
  }, []);

  function miaFunzione(hospitalIntervetion, i) {
    return (
      <Card key={i}>
        <Card.Content>
          <Text variant="titleLarge">{hospitalIntervetion.hospital.name}</Text>
          <Text variant="bodyMedium">{hospitalIntervetion.hospital.address}</Text>
        </Card.Content>
        <Card.Cover
          source={{
            uri: 'https://asugi.sanita.fvg.it/export/sites/aas1/it/news/img/ambulanza/IMG-20230808-WA0008.jpg',
          }}
        />
        <Card.Actions>
          <Button>Mostra interventi </Button>
          <Badge size={40}>{hospitalIntervetion.countIntervention}</Badge>
        </Card.Actions>
      </Card>
    );
  }

  return <View> {listHAndI.length > 0 && <>{listHAndI.map(miaFunzione)}</>}</View>;
}
