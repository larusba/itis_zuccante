import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import { Paper, TextField, Button } from "@material-ui/core";

export default function PasswordResetFinish() {
  const [password, setPassword] = React.useState("");

  const handleSubmit = (event) => {
    const url = new URL(window.location.href);
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        key: url.searchParams.get("key"),
        newPassword: password,
      }),
    };
    fetch("/api/account/reset-password/finish", option).then(console.log("Ok"));
  };
  return (
    <div className="form-background blue">
      <Paper className="form-paper" elevation={2} onSubmit={handleSubmit}>
        <h1 className="form-title">Reimposta password</h1>
        <hr className="form-break" />
        <TextField
          className="pwreset-input finish"
          variant="outlined"
          label="Nuova password"
          name="password"
          type="password"
          onChange={(event) => setPassword(event.currentTarget.value)}
        />
        <br />
        <br />
        <TextField
          className="pwreset-input finish"
          variant="outlined"
          label="Conferma password"
          name="password-confirm"
          type="password"
        />

        <br />
        <Button
          variant="contained"
          className="reset-finish-button"
          type="submit"
          onClick={handleSubmit}
        >
          Conferma
        </Button>
      </Paper>
    </div>
  );
}
