import React, { useState, useEffect } from 'react';
import { Platform, View, StyleSheet } from 'react-native';
import { Avatar, Button, Card, Text, Badge } from 'react-native-paper';
import config from './../../config/app-config.js';

export default function HospitaInformation() {
  const LeftContent = props => <Avatar.Icon {...props} icon="folder" />;
  const [listHAndI, setListAAndI] = useState([]);
  const images = [
    '',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321ac733b36fa00b9ca1c25',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=63b6c124e94ef1008fb5c7d6',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321a95fd88c160098806917',
  ];

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
      <Card key={i} mode="contained">
        <Card.Content>
          <Text variant="titleLarge">{hospitalIntervetion.hospital.name}</Text>
          <Text variant="bodyMedium">{hospitalIntervetion.hospital.address}</Text>
        </Card.Content>
        <Card.Cover
          source={{
            uri: images[hospitalIntervetion.hospital.id],
          }}
          style={styles.cardCover}
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
const styles = StyleSheet.create({
  cardCover: {
    height: 400,
  },
});
