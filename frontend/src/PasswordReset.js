import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import { Paper, TextField, IconButton, Snackbar } from "@material-ui/core";
import SendIcon from "@material-ui/icons/Send";

export default function PasswordReset() {
  const [status, setStatus] = React.useState(200);
  const [snackbarOpen, setSnackbarOpen] = React.useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.target);
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: data.get("email"),
    };
    const res = await fetch("/api/account/reset-password/init", option);
    setStatus(res.status);
    if (status === 200) setSnackbarOpen(true);
  };

  const handleResult = () => {
    if (status !== 200) return true;
    return false;
  };

  const handleClose = () => {
    setSnackbarOpen(false);
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
          error={handleResult()}
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
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={5000}
        onClose={handleClose}
        message="Email inviata"
      ></Snackbar>
    </div>
  );
}
