import React from 'react';
import './App.css';
import ChatIcon from '@mui/icons-material/Chat';
import ReportIcon from '@mui/icons-material/Report';
import {
  IconButton,
  Drawer,
  Box,
  Paper,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent,
  CircularProgress,
} from '@material-ui/core';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

export default function Home() {
  const [drawerOpen, setDrawerOpen] = React.useState(false);
  const [dialogOpen, setDialogOpen] = React.useState(false);

  const handleChatClick = () => {
    setDrawerOpen(true);
  };

  const handleReportClick = () => {
    setDialogOpen(true);
  };

  const handleChatClose = () => {
    setDrawerOpen(false);
  };

  const handleReportClose = () => {
    setDialogOpen(false);
  };

  const getDate = () => {
    return new Date().toLocaleDateString('it-IT', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    });
  };

  return (
    <Box
      style={{
        position: 'relative',
        width: '100%',
        marginLeft: '30px',
        marginRight: '30px',
      }}
    >
      <div style={{ fontFamily: 'Lato, sans-serif' }}>
        <h1>Ciao bello</h1>
        <h2>Oggi è {getDate()}</h2>
      </div>
      <Grid
        container
        spacing={2}
        justifyContent="center"
        alignItems="center"
        width="500px"
        style={{ margin: 'auto' }}
      >
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
        <Grid item xs={4} style={{ textAlign: 'center' }}>
          <CircularProgress variant="determinate" value={100} size={40} />
        </Grid>
      </Grid>
      <IconButton
        color="primary"
        onClick={handleChatClick}
        style={{
          position: 'fixed',
          bottom: '100px',
          left: '50px',
          transform: 'scale(3)',
        }}
      >
        <ChatIcon />
      </IconButton>
      <IconButton
        color="secondary"
        onClick={handleReportClick}
        style={{
          position: 'fixed',
          bottom: '100px',
          right: '50px',
          transform: 'scale(3)',
        }}
      >
        <ReportIcon />
      </IconButton>
      <Drawer anchor="left" open={drawerOpen} onClose={handleChatClose}>
        <Box>
          <Paper>
            <TextField variant="outlined"></TextField>
          </Paper>
        </Box>
      </Drawer>
      <Dialog open={dialogOpen} onClose={handleReportClose}>
        <DialogTitle>Invia segnalazione</DialogTitle>
        <DialogContent>
          Invia una segnalazione di comportamenti dannosi all'ambiente
        </DialogContent>
      </Dialog>
    </Box>
  );
}
