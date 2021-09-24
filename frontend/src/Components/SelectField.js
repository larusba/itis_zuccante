import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import "./css/selectField.css";
import "./css/textTitle.css";
import { Icon } from "@material-ui/core";

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

export default function ControlledOpenSelect() {
  const classes = useStyles();
  const [style, setStyle] = React.useState("");
  const [open, setOpen] = React.useState(false);

  const handleChange = (event) => {
    setStyle(event.target.value);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  return (
    <div id="selectField">
      <div className="background">
        <p className="text">LIVELLO DI ATTIVIT&Agrave;</p>
      </div>
      <FormControl className={classes.formControl}>
        <Select
          open={open}
          value={style}
          IconComponent={Icon}
          onClose={handleClose}
          onOpen={handleOpen}
          onChange={handleChange}
        >
          <MenuItem>
            <em>Nessuno</em>
          </MenuItem>
          <MenuItem value={0}>Sedentario</MenuItem>
          <MenuItem value={1}>Normale</MenuItem>
          <MenuItem value={2}>Attivo</MenuItem>
          <MenuItem value={3}>Sportivo</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
}
