import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import {
  Paper,
  TextField,
  Button,
  InputAdornment,
  IconButton,
} from "@material-ui/core";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";

export default function PasswordResetFinish() {
  const [password, setPassword] = React.useState("");
  const [showPassword, setShowPassword] = React.useState(false);
  const [newPassword, setNewPassword] = React.useState("");

  const handleConfirmChange = (event) => {
    setNewPassword(event.target.value);
  };

  const handleError = () => {
    if (password.length === 0 || newPassword.length === 0) return false;
    if (
      password.length > 0 &&
      newPassword.length > 0 &&
      password === newPassword
    )
      return false;
    return true;
  };

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
  };

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
          type={showPassword ? "text" : "password"}
          onChange={(event) => setPassword(event.currentTarget.value)}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={handleShowPassword}>
                  {showPassword ? <Visibility /> : <VisibilityOff />}
                </IconButton>
              </InputAdornment>
            ),
          }}
        />
        <br />
        <br />
        <TextField
          error={handleError()}
          className="pwreset-input finish"
          variant="outlined"
          label="Conferma password"
          name="password-confirm"
          type={showPassword ? "text" : "password"}
          onChange={handleConfirmChange}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={handleShowPassword}>
                  {showPassword ? <Visibility /> : <VisibilityOff />}
                </IconButton>
              </InputAdornment>
            ),
          }}
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
