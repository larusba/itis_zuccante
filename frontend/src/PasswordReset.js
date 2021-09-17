import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import { Paper, TextField, IconButton } from "@material-ui/core";
import SendIcon from "@material-ui/icons/Send";

export default function PasswordReset() {
  const handleSubmit = (event) => {
    const data = new FormData(event.target);
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: data.get("email"),
    };
    fetch("/api/account/reset-password/init", option).then((res) =>
      console.log(res.json()).catch((error) => console.log(error))
    );
  };
  return (
    <div className="form-background blue">
      <Paper
        component="form"
        className="form-paper"
        elevation={2}
        onSubmit={handleSubmit}
      >
        <h1 className="form-title">Reimposta password</h1>
        <hr className="form-break" />
        <TextField
          className="pwreset-input"
          variant="outlined"
          label="Indirizzo email"
          name="email"
          type="email"
        />
        <IconButton className="pwreset-button" type="submit">
          <SendIcon />
        </IconButton>
        <p className="pwreset-text">
          Inserisci il tuo indirizzo email. Riceverai una mail con le istruzioni
          per reimpostare la password nel giro di pochi minuti.&nbsp;
          <Link to="/login">Torna al login</Link>
        </p>
      </Paper>
    </div>
  );
}
