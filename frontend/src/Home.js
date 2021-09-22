import React from "react";
import "./App.css";
import ChatIcon from "@mui/icons-material/Chat";
import ReportIcon from "@mui/icons-material/Report";
import CloudOutlinedIcon from "@mui/icons-material/CloudOutlined";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import AccountBoxIcon from "@mui/icons-material/AccountBox";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import DirectionsRunIcon from "@mui/icons-material/DirectionsRun";
import {
  IconButton,
  Drawer,
  Box,
  Paper,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent,
  MobileStepper,
  Button,
} from "@material-ui/core";
import { CircularProgressbarWithChildren } from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import Grid from "@mui/material/Grid";

function Suggestions() {
  const [activeStep, setActiveStep] = React.useState(0);
  const [suggestions, setSuggestions] = React.useState();

  React.useEffect(() => {
    console.log("Ok 1");
    const getSuggestions = async (event) => {
      console.log("Ok");
      const res = await fetch("/api/suggestions");
      const json = await res.json();
      setSuggestions(json);
    };
    getSuggestions();
  });

  const handleNext = () => {
    setActiveStep((activeStep) => activeStep + 1);
  };

  const handleBack = () => {
    setActiveStep((activeStep) => activeStep - 1);
  };

  return (
    <Box className="suggestions">
      <Box className="suggestions-text">
        {console.log(suggestions)}
        <h3>Consigli - {suggestions && suggestions[activeStep].type}</h3>
        <p>{suggestions && suggestions[activeStep].description}</p>
      </Box>
      <MobileStepper
        variant="text"
        steps={suggestions.length}
        position="static"
        activeStep={activeStep}
        nextButton={
          <Button
            size="small"
            onClick={handleNext}
            disabled={activeStep === suggestions.length - 1}
          >
            Next
            <KeyboardArrowRight />
          </Button>
        }
        backButton={
          <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
            <KeyboardArrowLeft />
            Back
          </Button>
        }
      />
    </Box>
  );
}

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

  const handleProfileClick = () => {};

  const getDate = () => {
    return new Date().toLocaleDateString("it-IT", {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric",
    });
  };

  return (
    <Box
      style={{
        position: "relative",
        marginLeft: "30px",
        marginRight: "30px",
      }}
    >
      <div style={{ fontFamily: "Lato, sans-serif" }}>
        <h1>Ciao utente</h1>
        <h2>Oggi è {getDate()}</h2>
      </div>
      <Grid
        container
        spacing={2}
        justifyContent="center"
        alignItems="center"
        width="15%"
        style={{ margin: "auto" }}
      >
        <Grid item xs={12} style={{ textAlign: "center" }}>
          <CircularProgressbarWithChildren value={70}>
            <CloudOutlinedIcon />
          </CircularProgressbarWithChildren>
        </Grid>
        <Grid item xs={6} style={{ textAlign: "center" }}>
          <CircularProgressbarWithChildren value={80}>
            <DirectionsRunIcon />
          </CircularProgressbarWithChildren>
        </Grid>
        <Grid item xs={6} style={{ textAlign: "center" }}>
          <CircularProgressbarWithChildren value={85}>
            <DeleteOutlineOutlinedIcon />
          </CircularProgressbarWithChildren>
        </Grid>
      </Grid>
      <Suggestions />
      <IconButton
        className="home-button profile"
        color="primary"
        onClick={handleProfileClick}
      >
        <AccountBoxIcon />
      </IconButton>
      <IconButton
        className="home-button chat"
        color="primary"
        onClick={handleChatClick}
      >
        <ChatIcon />
      </IconButton>
      <IconButton
        className="home-button report"
        color="secondary"
        onClick={handleReportClick}
      >
        <ReportIcon />
      </IconButton>
      <Drawer anchor="left" open={drawerOpen} onClose={handleChatClose}>
        <Box className="chat-drawer">
          <Paper>
            <TextField variant="outlined" size="small"></TextField>
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
