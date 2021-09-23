import * as React from "react";
import { List, TextField } from "@mui/material";
import { ListItemButton } from "@mui/material";
import { ListItemText } from "@mui/material";
import { ListItemAvatar } from "@mui/material";
import { ListItem } from "@mui/material";
import { Avatar } from "@mui/material";
import { Collapse } from "@mui/material";
import { Dialog } from "@mui/material";
import { DialogActions } from "@mui/material";
import { DialogContent } from "@mui/material";
import { DialogContentText } from "@mui/material";
import { DialogTitle } from "@mui/material";
import { Slide } from "@mui/material";
import { Fab } from "@mui/material";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import DirectionsRunIcon from "@mui/icons-material/DirectionsRun";
import FlightIcon from "@mui/icons-material/Flight";
import DeleteIcon from "@mui/icons-material/Delete";
import { ExpandLess } from "@mui/icons-material";
import { ExpandMore } from "@mui/icons-material";
import AddIcon from "@mui/icons-material/Add";
import "./css/behaviour.css";
import { Container } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import MobileDatePicker from "@mui/lab/MobileDatePicker";
import Stack from "@mui/material/Stack";
import Button from "@material-ui/core/Button";
import InputAdornment from "@mui/material/InputAdornment";
import OutlinedInput from "@mui/material/OutlinedInput";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

const useStyles = makeStyles((theme) => ({
  button: {
    display: "block",
    marginTop: theme.spacing(2),
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
}));

export default function FolderList() {
  const [state, setState] = React.useState({
    type: null,
  });
  const [show, setShow] = React.useState(false);
  const [open, setOpen] = React.useState(false);
  const [openSelect, setSelect] = React.useState(false);
  const [openClass, setClass] = React.useState(false);
  const [openWaste, setWaste] = React.useState(false);
  const [openElectric, setOpenElectric] = React.useState(false);
  const [value, setValue] = React.useState(0);
  const classes = useStyles();
  const [activity, setActivity] = React.useState("");
  const [flightClass, setFlightClass] = React.useState("");
  const [wasteType, setWasteType] = React.useState("");
  const [electricLocation, setElectricLocation] = React.useState("");
  const [date, setDate] = React.useState(new Date("2014-08-10T00:00:00"));
  const [distance, setDistance] = React.useState(0);
  const [passengers, setPassengers] = React.useState(0);
  const [roundtrip, setRoundtrip] = React.useState("no");
  const [electric, setElectric] = React.useState(false);
  const [iata1, setIATA1] = React.useState("");
  const [iata2, setIATA2] = React.useState("");
  const [iata3, setIATA3] = React.useState("");

  const handleChangeRadio = (event) => {
    setRoundtrip(event.target.value);
  };

  const handleChangeElectric = (event) => {
    setElectric(event.target.value);
  };

  const isElectric = () => {
    if (electric) {
      return true;
    }
    return false;
  };

  const handleDateChange = (newDate) => {
    setDate(newDate);
  };

  const handleChangeActivity = (event) => {
    setActivity(event.target.value);
  };

  const handleShow = () => {
    setShow(!show);
  };

  const handleClick = () => {
    setOpen(!open);
    document.getElementById("flight").style.display = "none";
    document.getElementById("waste").style.display = "none";
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleCloseSelect = () => {
    setSelect(false);
  };

  const handleOpenSelect = () => {
    setSelect(true);
  };

  const handleCloseClass = () => {
    setClass(false);
  };

  const handleOpenClass = () => {
    setClass(true);
  };

  const handleChangeClass = (event) => {
    setFlightClass(event.target.value);
  };

  const handleCloseWaste = () => {
    setWaste(false);
  };

  const handleOpenWaste = () => {
    setWaste(true);
  };

  const handleChangeWaste = (event) => {
    setWasteType(event.target.value);
  };

  const handleCloseElectric = () => {
    setElectric(false);
  };

  const handleOpenElectric = () => {
    setElectric(true);
  };

  const handleChangeElectricLocation = (event) => {
    setElectricLocation(event.target.value);
  };

  const handleChangeDistance = (event, newDistance) => {
    setDistance(newDistance);
  };

  const handleChangeIATA1 = (event) => {
    setIATA1(event.target.value.toUpperCase());
  };
  const handleChangeIATA2 = (event) => {
    setIATA2(event.target.value.toUpperCase());
  };
  const handleChangeIATA3 = (event) => {
    setIATA3(event.target.value.toUpperCase());
  };

  const car_trip = "CAR_TRIP";
  const flight = "FLIGHT";
  const recycling = "RECYCLING";
  const walk = "WALK";

  const [behaviours, setBehaviours] = React.useState([]);
  let accountId;
  fetch("/api/account").then((res) =>
    res.json().then((res) => (accountId = res))
  );
  fetch("/api/users/" + accountId + "/behaviours").then((res) =>
    res.json().then((res) => (behaviours = res))
  );
  const avatarChoice = () => {
    for (let i = 0; i < behaviours.length; i++) {
      switch (behaviours.get(i).getType()) {
        case car_trip:
          return <DirectionsCarIcon />;
        case flight:
          return <FlightIcon />;
        case recycling:
          return <DeleteIcon />;
        case walk:
          return <DirectionsRunIcon />;
        default:
          return null;
      }
    }
  };

  const enableCartrip = () => {
    enableDistance();
    document.getElementById("flight").style.display = "none";
    document.getElementById("waste").style.display = "none";
  };

  const enableWaste = () => {
    disableDistance();
    document.getElementById("flight").style.display = "none";
    document.getElementById("waste").style.display = "block";
  };

  const disableDistance = () => {
    document.getElementById("distance").style.display = "none";
  };

  const enableDistance = () => {
    document.getElementById("distance").style.display = "block";
    document.getElementById("distanceValue").value = 0;
    document.getElementById("flight").style.display = "none";
  };

  const enableFlight = () => {
    disableDistance();
    document.getElementById("flight").style.display = "block";
    document.getElementById("waste").style.display = "none";
  };

  const enableWalk = () => {
    enableDistance();
    document.getElementById("waste").style.display = "none";
    document.getElementById("distance").style.display = "block";
    document.getElementById("distanceValue").value = 0;
    document.getElementById("flight").style.display = "none";
  };

  const behaviourDescription = () => {
    for (let i = 0; i < behaviours.length; i++) {
      let item = behaviours.get(i);
      let date = item.getDate();
      switch (item.getType()) {
        case car_trip:
          return (
            <Container>
              <ListItemButton onClick={handleShow}>
                <ListItemAvatar>
                  <Avatar>{avatarChoice()}</Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary="Viaggio in macchina"
                  secondary={date.toString()}
                />
                {show ? <ExpandLess /> : <ExpandMore />}
              </ListItemButton>
              <Collapse in={show} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                  <ListItem sx={{ pl: 10 }}>
                    <ListItemText>
                      Distanza: {item.getDistance().toString()}
                    </ListItemText>
                    <ListItemText>
                      Emissioni di CO2: {item.getEmission().toString()}
                    </ListItemText>
                  </ListItem>
                </List>
              </Collapse>
            </Container>
          );
        case flight:
          return (
            <Container>
              <ListItemButton onClick={handleShow}>
                <ListItemAvatar>
                  <Avatar>{avatarChoice()}</Avatar>
                </ListItemAvatar>
                <ListItemText primary="Volo" secondary={date.toString()} />
                {show ? <ExpandLess /> : <ExpandMore />}
              </ListItemButton>
              <Collapse in={show} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                  <ListItem sx={{ pl: 10 }}>
                    <ListItemText>
                      Distanza: {item.getDistance().toString()}
                    </ListItemText>
                    <ListItemText>
                      Partenza: {item.getFrom().toString()}
                    </ListItemText>
                    <ListItemText>
                      Arrivo: {item.getTo().toString()}
                    </ListItemText>
                    <ListItemText>
                      Classe di volo: {item.getFlightClass().toString()}
                    </ListItemText>
                    <ListItemText>
                      Emissioni di CO2: {item.getEmission().toString()}
                    </ListItemText>
                  </ListItem>
                </List>
              </Collapse>
            </Container>
          );
      }
    }
  };

  return (
    <Container className="container">
      <h2>LE MIE ATTIVITA'</h2>
      <center>
        <List
          className="list"
          sx={{ width: "100%", maxWidth: 450, bgcolor: "background.paper" }}
        >
          <ListItemButton onClick={handleShow}>
            <ListItemAvatar>
              <Avatar>{avatarChoice()}</Avatar>
            </ListItemAvatar>
            <ListItemText
              primary="Viaggio in macchina"
              secondary="Jan 9, 2014"
            />
            {show ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={show} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              <ListItem sx={{ pl: 10 }}>
                <ListItemText primary="Descrizione behaviour"></ListItemText>
              </ListItem>
            </List>
          </Collapse>
          <ListItemButton>
            <ListItemAvatar>
              <Avatar>
                <avatarChoice />
              </Avatar>
            </ListItemAvatar>
            <ListItemText primary="Volo" secondary="Jan 7, 2014" />
          </ListItemButton>
          <ListItemButton>
            <ListItemAvatar>
              <Avatar>
                <avatarChoice />
              </Avatar>
            </ListItemAvatar>
            <ListItemText primary="Riciclo" secondary="July 20, 2014" />
          </ListItemButton>
          <ListItemButton>
            <ListItemAvatar>
              <Avatar>
                <avatarChoice />
              </Avatar>
            </ListItemAvatar>
            <ListItemText primary="Passeggiata" secondary="July 20, 2014" />
          </ListItemButton>
        </List>
      </center>

      <Fab
        color="primary"
        aria-label="add"
        className="fab"
        onClick={handleClick}
      >
        <AddIcon />
      </Fab>

      <Dialog
        open={open}
        TransitionComponent={Transition}
        keepMounted
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle>{"Aggiungi un'attività"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-slide-description">
            <form noValidate autoComplete="off" className="form">
              <div>
                Tipo di attività
                <div>
                  <FormControl className={classes.formControl}>
                    <Select
                      open={openSelect}
                      value={activity}
                      onClose={handleCloseSelect}
                      onOpen={handleOpenSelect}
                      onChange={handleChangeActivity}
                    >
                      <MenuItem onClick={enableCartrip} value={car_trip}>
                        Viaggio in macchina
                      </MenuItem>
                      <MenuItem onClick={enableFlight} value={flight}>
                        Volo
                      </MenuItem>
                      <MenuItem onClick={enableWaste} value={recycling}>
                        Riciclo
                      </MenuItem>
                      <MenuItem onClick={enableWalk} value={walk}>
                        Camminata
                      </MenuItem>
                    </Select>
                  </FormControl>
                </div>
                <br />
                Data
                <br />
                <LocalizationProvider
                  dateAdapter={AdapterDateFns}
                  className="date"
                >
                  <Stack spacing={1}>
                    <MobileDatePicker
                      inputFormat="dd/MM/yyyy"
                      value={date}
                      onChange={handleDateChange}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </Stack>
                </LocalizationProvider>
                <div id="distance">
                  <br />
                  Distanza percorsa
                  <br />
                  <FormControl sx={{ m: 0, width: "150px" }} variant="outlined">
                    <OutlinedInput
                      type="number"
                      value={distance}
                      onChange={handleChangeDistance}
                      id="distanceValue"
                      endAdornment={
                        <InputAdornment position="end">km</InputAdornment>
                      }
                      startAdornment=" "
                      size="small"
                    />
                  </FormControl>
                </div>
                <br />
                Hai utilizzato un veicolo elettrico/ibrido?
                <br />
                <FormControl component="fieldset">
                  <RadioGroup
                    aria-label="roundtrip"
                    name="controlled-radio-buttons-group"
                    value={electric}
                    onChange={handleChangeElectric}
                  >
                    <FormControlLabel
                      value="sì"
                      control={<Radio size="small" />}
                      label="Sì"
                    />
                    <FormControlLabel
                      value="No"
                      control={<Radio size="small" />}
                      label="No"
                      checked="checked"
                    />
                  </RadioGroup>
                </FormControl>
                <div id="electric">
                  Dove hai ricaricato il veicolo?
                  <br />
                  <FormControl className={classes.formControl}>
                    <Select
                      open={openElectric}
                      value={electricLocation}
                      onClose={handleCloseElectric}
                      onOpen={handleOpenElectric}
                      onChange={handleChangeElectricLocation}
                    >
                      <MenuItem value={"CH"}>Svizzera</MenuItem>
                      <MenuItem value={"DE"}>Germania</MenuItem>
                      <MenuItem value={"AT"}>Austria</MenuItem>
                      <MenuItem value={"CERTIFIED_GREEN"}>
                        Certificato verde
                      </MenuItem>
                      <MenuItem value={"SE"}>Svezia</MenuItem>
                      <MenuItem value={"REST"}>Altro</MenuItem>
                    </Select>
                  </FormControl>
                </div>
                <br />
                <br />
                Classe di volo
                <br />
                <FormControl className={classes.formControl}>
                  <Select
                    open={openClass}
                    value={flightClass}
                    onClose={handleCloseClass}
                    onOpen={handleOpenClass}
                    onChange={handleChangeClass}
                  >
                    <MenuItem value={"economy"}>Economy</MenuItem>
                    <MenuItem value={"first"}>Prima classe</MenuItem>
                    <MenuItem value={"business"}>Business</MenuItem>
                    <MenuItem value={"premium"}>Premium economy</MenuItem>
                  </Select>
                </FormControl>
                <div id="flight">
                  Inserire i relativi codici aeroportuali IATA
                  <br />
                  <TextField
                    className="outlined-basic"
                    variant="outlined"
                    size="small"
                    inputProps={{ maxLength: 3 }}
                    helperText="Partenza"
                    onChange={handleChangeIATA1}
                    value={iata1}
                  />{" "}
                  <TextField
                    className="outlined-basic"
                    variant="outlined"
                    size="small"
                    inputProps={{ maxLength: 3 }}
                    helperText="Arrivo"
                    onChange={handleChangeIATA2}
                    value={iata2}
                  />{" "}
                  <TextField
                    className="outlined-basic"
                    variant="outlined"
                    size="small"
                    inputProps={{ maxLength: 3 }}
                    helperText="Scalo (opzionale)"
                    onChange={handleChangeIATA3}
                    value={iata3}
                  />{" "}
                  <br />
                  <br />
                  Numero di passeggeri
                  <br />
                  <TextField
                    className="passengers"
                    variant="outlined"
                    size="small"
                    type="number"
                    value={passengers}
                  />{" "}
                  <br />
                  <br />
                  Andata e ritorno
                  <br />
                  <FormControl component="fieldset">
                    <RadioGroup
                      aria-label="roundtrip"
                      name="controlled-radio-buttons-group"
                      value={roundtrip}
                      onChange={handleChangeRadio}
                    >
                      <FormControlLabel
                        value="sì"
                        control={<Radio size="small" />}
                        label="Sì"
                      />
                      <FormControlLabel
                        value="No"
                        control={<Radio size="small" />}
                        label="No"
                        checked="checked"
                      />
                    </RadioGroup>
                  </FormControl>
                  <br />
                </div>
                <div id="waste">
                  Tipo di riciclo
                  <br />
                  <FormControl className={classes.formControl}>
                    <Select
                      open={openWaste}
                      value={wasteType}
                      onClose={handleCloseWaste}
                      onOpen={handleOpenWaste}
                      onChange={handleChangeWaste}
                    >
                      <MenuItem value={"organic"}>Organico</MenuItem>
                      <MenuItem value={"paper"}>Carta</MenuItem>
                      <MenuItem value={"plastic"}>Plastica</MenuItem>
                      <MenuItem value={"non_recyclable"}>
                        Indifferenziato
                      </MenuItem>
                    </Select>
                  </FormControl>
                </div>
              </div>
              <br />
            </form>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            variant="outlined"
            color="primary"
            size="small"
            className="create"
          >
            Crea
          </Button>

          <Button
            variant="outlined"
            color="default"
            size="small"
            className="cancel"
            onClick={handleClose}
          >
            Annulla
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
}
