import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Avatar, Button, Card, Text, Badge, Modal, Portal, Divider } from 'react-native-paper';
import config from './../../config/app-config.js';
import { find } from 'lodash';
import { ar } from '@faker-js/faker';

export default function HospitaInformation() {
  const [listHAndI, setListHAndI] = useState([]);
  const [listInterventions, setListInterventions] = useState([]);
  const [ospedale, setOspedale] = useState('');
  const images = [
    '',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321ac733b36fa00b9ca1c25',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=63b6c124e94ef1008fb5c7d6',
    'https://www.aulss3.veneto.it/myportal/AU12VE/api/content/download?id=6321a95fd88c160098806917',
  ];

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

  const findInterventions = async hospitalName => {
    const response = await fetch(`${config.apiUrl}api/findInterventionsByHospitalName?hospitalName=${hospitalName}`, {
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjk3MjgxMzMyfQ.uETEWdLIwmUwKCvc28egLtnQOnd7Tm-Ky6bxGC2oz9pS_-8dAjlbWXc-X2RU8lSv9Z2rCMvPL1SynzzMoNUraw',
      },
    });
    setListInterventions(await response.json());
    setVisible(true);
  };

  function miaFunzione(hospitalIntervetion, i) {
    return (
      <Card key={i} mode="view" style={styles.cardStyle}>
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
          <Button disabled={hospitalIntervetion.countIntervention <= 0} onPress={e => findInterventions(hospitalIntervetion.hospital.name)}>
            Show Interventions
          </Button>
          <Badge size={45}>{hospitalIntervetion.countIntervention}</Badge>
        </Card.Actions>
      </Card>
    );
  }

  function miaFunzione2(intervention, i) {
    return (
      <View key={i} style={styles.interventionsView}>
        <Text variant="titleMedium">{i + 1}° Intervention</Text>
        <Divider />
        <Text>Patient name: {intervention.nomePaziente}</Text>
        <Divider />
        <Text>Patient surname: {intervention.cognomePaziente}</Text>
        <Divider />
        <Text>Ambulance plate: {intervention.numeroAmbulanza}</Text>
        <Divider />
        <Text>Intervention's address: {intervention.luogoIntervento}</Text>
        <Divider />
        <Text>latitude: {intervention.latitude}</Text>
        <Divider />
        <Text>longitude: {intervention.longitude}</Text>
      </View>
    );
  }

  return (
    <View style={styles.cardStyle}>
      <Portal>
        <Modal
          visible={visible}
          style={styles.modalStyle}
          contentContainerStyle={containerStyle}
          theme={{ colors: { backdrop: 'transparent' } }}
        >
          <Text style={styles.interventionBoxStyle}>{listInterventions.map(miaFunzione2)}</Text>
          <Button visible={true} onPress={hideModal} mode="elevated" style={styles.closeButton}>
            close
          </Button>
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
    margin: '8%',
    position: 'fixed',
  },
  interventionsView: {
    padding: 5,
  },
  closeButton: {
    marginLeft: 'auto',
    marginRight: 'auto',
    width: 300,
  },
  interventionBoxStyle: {
    textAlign: 'center',
  },
  cardStyle: {
    backgroundColor: 'white',
  },
});
