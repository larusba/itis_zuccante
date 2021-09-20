import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import {
  TextField,
  Button,
  Grid,
  Container,
  InputAdornment,
  IconButton,
} from "@material-ui/core";
import VpnKeyIcon from "@material-ui/icons/VpnKey";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";

export default function Register() {
  const [state, setState] = React.useState({
    email: "",
    password: "",
    username: "",
    showPassword: false,
    firstName: "",
    lastName: "",
  });
  const [passwordConfirm, setPasswordConfirm] = React.useState("");

  const handleError = () => {
    if (state.password.length === 0 || passwordConfirm.length === 0)
      return false;
    if (
      state.password.length > 0 &&
      passwordConfirm.length > 0 &&
      state.password === passwordConfirm
    )
      return false;
    return true;
  };

  const handleConfirmChange = (event) => {
    setPasswordConfirm(event.target.value);
  };

  const handleInputChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;

    setState((state) => ({ ...state, [name]: value }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        firstName: state.firstName,
        lastName: state.lastName,
        login: state.username,
        email: state.email,
        langKey: "it",
        password: state.password,
      }),
    };
    fetch("/api/register", option).then((res) =>
      res.json().then((res) => console.log(res))
    );
  };

  const handleShowPassword = () => {
    setState((state) => ({ ...state, showPassword: !state.showPassword }));
  };

  return (
    <div className="form-background register">
      <div className="form-div">
        <h1 className="form-title">Registrati</h1>
        <hr className="form-break" />
        <Container className="register-form">
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <TextField
                fullWidth
                size="small"
                required
                label="Nome"
                type="text"
                name="firstName"
                variant="outlined"
                className="register-field name"
                value={state.firstName}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                fullWidth
                size="small"
                required
                label="Cognome"
                type="text"
                name="lastName"
                variant="outlined"
                className="register-field name"
                value={state.lastName}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                size="small"
                required
                label="Nome utente"
                type="text"
                name="username"
                variant="outlined"
                className="register-field"
                value={state.username}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                size="small"
                required
                label="Email"
                type="email"
                name="email"
                variant="outlined"
                value={state.email}
                onChange={handleInputChange}
                className="register-field"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                size="small"
                required
                label="Password"
                type={state.showPassword ? "text" : "password"}
                name="password"
                variant="outlined"
                value={state.password}
                onChange={handleInputChange}
                className="register-field"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={handleShowPassword}>
                        {state.showPassword ? (
                          <Visibility />
                        ) : (
                          <VisibilityOff />
                        )}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={handleError()}
                fullWidth
                size="small"
                required
                label="Conferma password"
                type={state.showPassword ? "text" : "password"}
                variant="outlined"
                className="register-field"
                onChange={handleConfirmChange}
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={handleShowPassword}>
                        {state.showPassword ? (
                          <Visibility />
                        ) : (
                          <VisibilityOff />
                        )}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
            </Grid>
          </Grid>
        </Container>
        <br />
        <Button
          onClick={handleSubmit}
          startIcon={<VpnKeyIcon />}
          variant="contained"
          className="form-button register"
        >
          Registrati
        </Button>
        <p className="form-text register">
          Gi&agrave; registrato? <Link to="/login">Accedi</Link>
        </p>
      </div>
    </div>
  );
}
