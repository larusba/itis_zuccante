import React from "react";
import "./App.css";
import { Paper, Button } from "@material-ui/core";

export default function AccountActivation() {
  const handleClick = (event) => {
    let url = new URL(window.location.href);
    fetch("/api/activate?key=" + url.searchParams.get("key")).then(
      console.log("Ok")
    );
  };

  return (
    <div className="form-background blue">
      <Paper className="form-paper">
        <h1 className="form-title">Attivazione account</h1>
        <hr className="form-break" />
        <p className="activate-text">
          Per accedere al tuo account devi prima attivarlo. <br />
          Clicca su questo bottone per poter accedere all'app!
        </p>
        <Button
          className="activate-button"
          variant="contained"
          onClick={handleClick}
        >
          Attiva
        </Button>
      </Paper>
    </div>
  );
}
