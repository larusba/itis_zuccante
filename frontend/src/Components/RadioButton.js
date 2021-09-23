import React from "react";
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import "./css/textTitle.css";
import "./css/radio.css";

export default function FormControlLabelPlacement() {
  return (
    <FormControl component="fieldset">
      <div className="background">
        <p className="text">STILE DI VITA</p>
      </div>
      <br />
      <RadioGroup
        className="radio"
        aria-label="position"
        name="position"
        defaultValue="top"
      >
        <FormControlLabel
          value="1"
          control={<Radio color="primary" />}
          label="Nessuno di questi"
          labelPlacement="start"
        />
        <FormControlLabel
          value="2"
          control={<Radio color="primary" />}
          label="Vegano"
          labelPlacement="start"
        />
        <FormControlLabel
          value="3"
          control={<Radio color="primary" />}
          label="Vegetariano"
          labelPlacement="start"
        />
      </RadioGroup>
    </FormControl>
  );
}
