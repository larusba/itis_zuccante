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
import Profile from "./Components/Profile";
import { WindowSharp } from "@mui/icons-material";

function Suggestions() {
  const [activeStep, setActiveStep] = React.useState(0);
  const [suggestions, setSuggestions] = React.useState([]);

  React.useEffect(() => {
    const getSuggestions = async (event) => {
      const res = await fetch("/api/suggestions", {
        headers: {
          Authorization: "Bearer " + window.localStorage.getItem("token"),
        },
      });
      const json = await res.json();
      console.log(json);
      if (json.status === 401) {
        console.log("unauto");
        window.location.href = "/login";
      } else console.log("auto");
      setSuggestions(json);
    };
    getSuggestions();
  }, []);

  const handleNext = () => {
    setActiveStep((activeStep) => activeStep + 1);
  };

  const handleBack = () => {
    setActiveStep((activeStep) => activeStep - 1);
  };

  return (
    <>
      {suggestions && suggestions.length > 0 && (
        <Box className="suggestions">
          <Box className="suggestions-text">
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
              <Button
                size="small"
                onClick={handleBack}
                disabled={activeStep === 0}
              >
                <KeyboardArrowLeft />
                Back
              </Button>
            }
          />
        </Box>
      )}
    </>
  );
}

export default function Home() {
  const [account, setAccount] = React.useState();
  const [behaviours, setBehaviours] = React.useState();
  const [behaviourValues, setBehaviourValues] = React.useState();
  const [drawerOpen, setDrawerOpen] = React.useState(false);
  const [reportOpen, setReportOpen] = React.useState(false);
  const [profileOpen, setProfileOpen] = React.useState(false);

  React.useEffect(() => {
    let accountTmp;
    const getAccount = async (event) => {
      const res = await fetch("/api/account", {
        headers: {
          Authorization: "Bearer " + window.localStorage.getItem("token"),
        },
      });
      const json = await res.json();
      if (json.status === 401) {
        console.log("unauto");
        window.location.href = "/login";
      } //else if (!json.profile) window.location.href = "/set-profile";
      else console.log("auto");
      setAccount(json);
      accountTmp = json;
      console.log(accountTmp.id);
    };
    getAccount();
    const getBehaviours = async (event, id) => {
      console.log(id);
      const res = await fetch("/api/users/" + id + "/behaviours", {
        headers: {
          Authorization: "Bearer " + window.localStorage.getItem("token"),
        },
      });
      const json = await res.json();
      console.log(json);
      if (json.status === 401) {
        console.log("unauto");
        window.location.href = "/login";
      } else console.log("auto");
      setBehaviours(json);
    };
    getBehaviours(accountTmp.id);
  }, []);

  const handleChatClick = () => {
    setDrawerOpen(true);
  };

  const handleReportClick = () => {
    setReportOpen(true);
  };

  const handleProfileClick = () => {
    setProfileOpen(true);
  };

  const handleChatClose = () => {
    setDrawerOpen(false);
  };

  const handleReportClose = () => {
    setReportOpen(false);
  };

  const handleProfileClose = () => {
    setProfileOpen(false);
  };

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
        <h1>Ciao {account && account.login}</h1>
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
          <CircularProgressbarWithChildren
            value={behaviours && (behaviours.bags / 365) * 100}
          >
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
      <Dialog open={reportOpen} onClose={handleReportClose}>
        <DialogTitle>Invia segnalazione</DialogTitle>
        <DialogContent>
          Invia una segnalazione di comportamenti dannosi all'ambiente
        </DialogContent>
      </Dialog>
      <Dialog open={profileOpen} scroll="paper" onClose={handleProfileClose}>
        <DialogContent>
          <Profile />
        </DialogContent>
      </Dialog>
    </Box>
  );
}
