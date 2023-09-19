import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Avatar, Button, Card, Text, Badge, Modal, Portal, PaperProvider } from 'react-native-paper';
import config from './../../config/app-config.js';

export default function HospitaInformation() {
  const [listHAndI, setListHAndI] = useState([]);
  const [listInterventions, setListInterventions] = useState([]);
  const images = [
    '',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321ac733b36fa00b9ca1c25',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=63b6c124e94ef1008fb5c7d6',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321a95fd88c160098806917',
  ];

  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const [visible, setVisible] = React.useState(false);
  const containerStyle = { backgroundColor: 'white', padding: 15, borderRadius: 20, fontSize: 30 };

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`${config.apiUrl}api/FindNumberOfInterventionByHospitalAll`, {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });
      setListHAndI(await response.json());
    };
    fetchData();
    const timer = setInterval(fetchData, 5000);
    return () => {
      clear(timer);
    };
  }, []);

  useEffect(() => {
    (async () => {
      const response = await fetch(`${config.apiUrl}api/findInterventionsByHospitalName?hospitalName=` + 'Ospedale%20di%20Mestre', {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
        },
      });

      setListInterventions(await response.json());
      console.log(listInterventions);
    })();
  }, []);

  function miaFunzione(hospitalIntervetion, i) {
    return (
      <Card key={i} mode="view">
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
          <Button disabled={hospitalIntervetion.countIntervention <= 0} onPress={showModal}>
            Mostra interventi{' '}
          </Button>
          <Badge size={45}>{hospitalIntervetion.countIntervention}</Badge>
        </Card.Actions>
      </Card>
    );
  }

  return (
    <View>
      <Portal>
        <Modal
          visible={visible}
          onDismiss={hideModal}
          style={styles.modalStyle}
          contentContainerStyle={containerStyle}
          theme={{ colors: { primary: 'green' } }}
        >
          <Text>Example Modal. Click outside this area to dismiss.</Text>
        </Modal>
      </Portal>
      {listHAndI.length > 0 && <View>{listHAndI.map(miaFunzione)}</View>}
    </View>
  );
}

const styles = StyleSheet.create({
  cardCover: {
    height: 400,
  },
  modalStyle: {
    position: 'fixed',
  },
});
