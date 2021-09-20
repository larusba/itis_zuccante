import React from "react";
import "./App.css";
import ChatIcon from "@mui/icons-material/Chat";
import ReportIcon from "@mui/icons-material/Report";
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
} from "@material-ui/core";

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
    let date = new Date();
    let dayOfWeek;
    let day = date.getDate();
    let month;
    let year = date.getFullYear();
    switch (date.getDay()) {
      case 0:
        dayOfWeek = "domenica";
        break;
      case 1:
        dayOfWeek = "lunedì";
        break;
      case 2:
        dayOfWeek = "martedì";
        break;
      case 3:
        dayOfWeek = "mercoledì";
        break;
      case 4:
        dayOfWeek = "giovedì";
        break;
      case 5:
        dayOfWeek = "venerdì";
        break;
      case 6:
        dayOfWeek = "sabato";
    }
    switch (date.getMonth()) {
      case 0:
        month = "gennaio";
        break;
      case 1:
        month = "febbraio";
        break;
      case 2:
        month = "marzo";
        break;
      case 3:
        month = "aprile";
        break;
      case 4:
        month = "maggio";
        break;
      case 5:
        month = "giugno";
        break;
      case 6:
        month = "luglio";
        break;
      case 7:
        month = "agosto";
        break;
      case 8:
        month = "settembre";
        break;
      case 9:
        month = "ottobre";
        break;
      case 10:
        month = "novembre";
        break;
      case 11:
        month = "dicembre";
        break;
    }
    return dayOfWeek + " " + day + " " + " " + month + " " + year;
  };

  return (
    <div className="home-page">
      <div className="home-text">
        <h1>Ciao bello</h1>
        <h2>Oggi è {getDate()}</h2>
      </div>
      <center>
        <CircularProgress
          className="home-progress"
          variant="determinate"
          value={50}
          size={200}
        />
      </center>
      <IconButton
        className="chat-button"
        color="primary"
        onClick={handleChatClick}
      >
        <ChatIcon />
      </IconButton>
      <IconButton
        className="report-button"
        color="secondary"
        onClick={handleReportClick}
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
    </div>
  );
}
