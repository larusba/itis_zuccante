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
import Fab from "@mui/material/Fab";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import RefreshIcon from "@mui/icons-material/Refresh";
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
import SentimentVeryDissatisfiedIcon from "@mui/icons-material/SentimentVeryDissatisfied";

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
  const [show, setShow] = React.useState(false);
  const [open, setOpen] = React.useState(false);
  const [openSelect, setSelect] = React.useState(false);
  const [openClass, setClass] = React.useState(false);
  const [openWaste, setWaste] = React.useState(false);
  const [openElectric, setOpenElectric] = React.useState(false);
  const classes = useStyles();
  const [activity, setActivity] = React.useState("");
  const [flightClass, setFlightClass] = React.useState();
  const [wasteType, setWasteType] = React.useState("");
  const [electricLocation, setElectricLocation] = React.useState("");
  const [date, setDate] = React.useState(new Date("2014-08-10T00:00:00"));
  const [distance, setDistance] = React.useState(0);
  const [walkedDistance, setWalkedDistance] = React.useState(0);
  const [passengers, setPassengers] = React.useState(0);
  const [roundtrip, setRoundtrip] = React.useState("no");
  const [electric, setElectric] = React.useState("no");
  const [iata1, setIATA1] = React.useState("");
  const [iata2, setIATA2] = React.useState("");
  const [iata3, setIATA3] = React.useState("");
  const [bags, setBags] = React.useState(0);
  const [behaviours, setBehaviours] = React.useState([]);
  const [accountId, setAccountId] = React.useState();

  React.useEffect(() => {
    const options = {
      headers: {
        Authorization: "Bearer " + window.localStorage.getItem("token"),
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    };
    fetch("/api/account", options).then((res) =>
      res.json().then((res) => {
        setAccountId(res.id);
        console.log(res.id);
      })
    );
    //fetch("/api/users/" + accountId + "/behaviours").then((res) =>
    //  res.json().then((res) => setBehaviours(res.behaviours))
    //);
  }, []);

  const createBehaviour = () => {
    handleSubmit();
    handleClose();
  };

  const updateBehaviours = () => {
    const options = {
      headers: {
        Authorization: "Bearer " + window.localStorage.getItem("token"),
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    };

    fetch("/api/users/" + accountId + "/behaviours", options).then((res) =>
      res.json().then((res) => {
        setBehaviours(res);
        console.log(res);
      })
    );
    //behaviourDescription();
    console.log("list of behaviours updated");
  };

  const behaviourDescription = (item) => {
    console.log("item ->", item);
    let date = item.date;
    console.log("=======> ", item);
    switch (item.type) {
      case car_trip:
        return (
          <Container>
            <ListItemButton onClick={handleShow}>
              <ListItemAvatar>
                <Avatar>{avatarChoice(item)}</Avatar>
              </ListItemAvatar>
              <ListItemText
                primary="Viaggio in macchina"
                secondary={new Date(date).toLocaleDateString()}
              />
              {show ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={show} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                <ListItem sx={{ pl: 10 }}>
                  <ListItemText>
                    Distanza percorsa: {item.distance}
                  </ListItemText>
                  <ListItemText>Tipo di veicolo: {item.carType}</ListItemText>
                  <ListItemText>Emissioni di CO2: {item.emission}</ListItemText>
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
                <Avatar>{avatarChoice(item)}</Avatar>
              </ListItemAvatar>
              <ListItemText
                primary="Volo"
                secondary={new Date(date).toLocaleDateString()}
              />
              {show ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={show} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                <ListItem sx={{ pl: 10 }}>
                  <ListItemText>
                    Distanza percorsa: {item.distance}
                  </ListItemText>
                  <ListItemText>Partenza: {item.from}</ListItemText>
                  <ListItemText>Arrivo: {item.to}</ListItemText>
                  <ListItemText>
                    Classe di volo: {item.flightClass}
                  </ListItemText>
                  <ListItemText>Emissioni di CO2: {item.emission}</ListItemText>
                </ListItem>
              </List>
            </Collapse>
          </Container>
        );
      case recycling:
        return (
          <Container>
            <ListItemButton onClick={handleShow}>
              <ListItemAvatar>
                <Avatar>{avatarChoice(item)}</Avatar>
              </ListItemAvatar>
              <ListItemText
                primary="Riciclo"
                secondary={new Date(date).toLocaleDateString()}
              />
              {show ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={show} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                <ListItem sx={{ pl: 10 }}>
                  <ListItemText>Tipo di riciclo: {item.wasteType}</ListItemText>
                </ListItem>
              </List>
            </Collapse>
          </Container>
        );
      case walk:
        return (
          <Container>
            <ListItemButton onClick={handleShow}>
              <ListItemAvatar>
                <Avatar>{avatarChoice(item)}</Avatar>
              </ListItemAvatar>
              <ListItemText
                primary="Camminata"
                secondary={new Date(date).toLocaleDateString()}
              />
              {show ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={show} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                <ListItem sx={{ pl: 10 }}>
                  <ListItemText>
                    Distanza percorsa: {item.distance}
                  </ListItemText>
                </ListItem>
              </List>
            </Collapse>
          </Container>
        );
      default:
        return null;
    }
  };

  /*
        return (
        <Container>
          <ListItemButton className="buttonSad" onClick={handleShow}>
            <ListItemAvatar className="avatarSad">
              <SentimentVeryDissatisfiedIcon />
            </ListItemAvatar>
            <ListItemText primary="Nessuna attività registrata. Puoi aggiornare la lista delle attività con il pulsante qui a fianco. Per registrare una nuova attività, premere il pulsante sottostante." />
          </ListItemButton>
        </Container>
      );
      */

  const handleSubmit = () => {
    let submit = {
      headers: {
        Authorization: "Bearer " + window.localStorage.getItem("token"),
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        type: activity,
        date: date,
        distance: distance,
        electricLocation: electricLocation,
        from: iata1,
        to: iata2,
        via: iata3,
        passengers: passengers,
        roundtrip: roundtrip,
        flightClass: flightClass,
        wasteType: wasteType,
        walkedDistance: walkedDistance,
        bags: bags,
      }),
    };
    fetch("/api/" + accountId + "/behaviours", submit).then(
      console.log("behaviour saved")
    );
  };

  const handleChangeRadio = (event) => {
    setRoundtrip(event.target.value);
  };

  const handleChangeElectric = (event) => {
    setElectric(event.target.value);
    console.log(event.target.value);
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
    setActivity();
    setDate(new Date("2020-08-10T00:00:00"));
    setBags(0);
    setIATA1();
    setIATA2();
    setIATA3();
    setPassengers(0);
    setWasteType();
    setElectricLocation(null);
    setDistance(0);
    setWalkedDistance(0);
    setRoundtrip(false);
    setElectric(false);
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

  const handleChangeBags = (event) => {
    setBags(event.target.value);
  };

  const handleCloseElectric = () => {
    setOpenElectric(false);
  };

  const handleOpenElectric = () => {
    setOpenElectric(true);
  };

  const handleChangeElectricLocation = (event) => {
    setElectricLocation(event.target.value);
  };

  const handleChangeDistance = (event, newDistance) => {
    setDistance(newDistance);
  };

  const handleChangeWalkedDistance = (event) => {
    console.log("distance", event.target.value);
    setWalkedDistance(event.target.value);
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

  const avatarChoice = (item) => {
    switch (item.type) {
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
  };

  return (
    <Container className="container">
      <div className="activity">
        <h2>LE MIE ATTIVITA'</h2>
      </div>
      <br />
      <center>
        <List
          className="list"
          sx={{ width: "100%", maxWidth: 450, bgcolor: "background.paper" }}
        >
          {console.log({ behaviours })}
          {behaviours !== undefined &&
            behaviours !== null &&
            behaviours.length > 0 &&
            behaviours.map((item) => behaviourDescription(item))}
        </List>
      </center>
      <div className="update">
        <IconButton
          color="default"
          aria-label="update"
          onClick={updateBehaviours}
        >
          <RefreshIcon />
        </IconButton>
      </div>
      <Box sx={{ "& > :not(style)": { m: 1 } }} className="add">
        <Fab color="primary" aria-label="add" onClick={handleClick}>
          <AddIcon />
        </Fab>
      </Box>
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
                      id="select"
                      open={openSelect}
                      value={activity}
                      onClose={handleCloseSelect}
                      onOpen={handleOpenSelect}
                      onChange={handleChangeActivity}
                    >
                      <MenuItem value={car_trip}>Viaggio in macchina</MenuItem>
                      <MenuItem value={flight}>Volo</MenuItem>
                      <MenuItem value={recycling}>Riciclo</MenuItem>
                      <MenuItem value={walk}>Camminata</MenuItem>
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
                {activity === car_trip && (
                  <div id="distance">
                    <br />
                    Distanza percorsa
                    <br />
                    <FormControl
                      sx={{ m: 0, width: "150px" }}
                      variant="outlined"
                    >
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
                )}
                {activity === walk && (
                  <div id="walkedDistance">
                    <br />
                    Distanza percorsa
                    <br />
                    <FormControl
                      sx={{ m: 0, width: "150px" }}
                      variant="outlined"
                    >
                      <OutlinedInput
                        type="number"
                        value={walkedDistance}
                        onChange={handleChangeWalkedDistance}
                        id="distanceValue"
                        endAdornment={
                          <InputAdornment position="end">km</InputAdornment>
                        }
                        startAdornment=" "
                        size="small"
                      />
                    </FormControl>
                  </div>
                )}
                <br />
                {activity === car_trip && (
                  <div id="electric">
                    Hai utilizzato un veicolo elettrico/ibrido?
                    <br />
                    <FormControl component="fieldset">
                      <RadioGroup
                        aria-label="roundtrip"
                        name="controlled-radio-buttons-group"
                        value={electric}
                        defaultValue="no"
                        onChange={handleChangeElectric}
                      >
                        <FormControlLabel
                          value="sì"
                          control={<Radio size="small" />}
                          label="Sì"
                        />
                        <FormControlLabel
                          value="no"
                          control={<Radio size="small" />}
                          label="No"
                        />
                      </RadioGroup>
                    </FormControl>
                    {electric === "sì" && (
                      <div id="electricLocation">
                        <br />
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
                    )}
                  </div>
                )}
                {activity === flight && (
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
                        defaultValue="no"
                        onChange={handleChangeRadio}
                      >
                        <FormControlLabel
                          value="sì"
                          control={<Radio size="small" />}
                          label="Sì"
                        />
                        <FormControlLabel
                          value="no"
                          control={<Radio size="small" />}
                          label="No"
                        />
                      </RadioGroup>
                    </FormControl>
                    <br />
                    <div className="flightClass">
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
                    </div>
                  </div>
                )}
                {activity === recycling && (
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
                        <MenuItem value={"ORGANIC"}>Organico</MenuItem>
                        <MenuItem value={"PAPER"}>Carta</MenuItem>
                        <MenuItem value={"PLASTIC"}>Plastica</MenuItem>
                        <MenuItem value={"NONRECYCLABLE"}>
                          Indifferenziato
                        </MenuItem>
                      </Select>
                    </FormControl>
                    {wasteType === "non_recyclable" && (
                      <div id="bags">
                        <br />
                        Numero di sacchetti smaltiti
                        <br />
                        <FormControl
                          sx={{ m: 0, width: "80px" }}
                          variant="outlined"
                        >
                          <OutlinedInput
                            type="number"
                            value={bags}
                            onChange={handleChangeBags}
                            startAdornment=" "
                            size="small"
                          />
                        </FormControl>
                      </div>
                    )}
                  </div>
                )}
              </div>
            </form>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            variant="outlined"
            color="primary"
            size="small"
            className="create"
            onClick={createBehaviour}
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
