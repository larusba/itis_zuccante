import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import {
  TextField,
  Button,
  InputAdornment,
  IconButton,
  Container,
  Grid,
} from "@material-ui/core";
import VpnKeyIcon from "@material-ui/icons/VpnKey";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";

export default function Login() {
  const [state, setState] = React.useState({
    email: "",
    password: "",
    showPassword: false,
  });
  const [status, setStatus] = React.useState(0);

  const handleInputChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;

    setState((state) => ({ ...state, [name]: value }));
  };

  const handleShowPassword = () => {
    setState((state) => ({ ...state, showPassword: !state.showPassword }));
  };

  const handleError = () => {
    if (status === 400 || status === 401) return true;
    return false;
  };

  const handleSubmit = () => {
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        username: state.email,
        password: state.password,
      }),
    };
    fetch("/api/authenticate", option).then((res) =>
      res.json().then((res) => setStatus(res.status))
    );
  };

  return (
    <div className="form-background login">
      <div className="form-div">
        <h1 className="form-title">Accedi</h1>
        <hr className="form-break" />
        <Container className="login-form">
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                error={handleError()}
                label="Email"
                type="email"
                name="email"
                variant="outlined"
                value={state.email}
                onChange={handleInputChange}
                className="login-field"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                error={handleError()}
                label="Password"
                type={state.showPassword ? "text" : "password"}
                name="password"
                variant="outlined"
                value={state.password}
                onChange={handleInputChange}
                className="login-field"
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
              <br />
            </Grid>
          </Grid>
        </Container>
        <br />
        <Button
          onClick={handleSubmit}
          startIcon={<VpnKeyIcon />}
          variant="contained"
          className="form-button login"
        >
          Accedi
        </Button>
        <Link to="/password-reset" className="form-text" id="password-reset">
          Reimposta password
        </Link>
        <p className="form-text">
          Nuovo utente? <Link to="/register">Registrati</Link>
        </p>
      </div>
    </div>
  );
}
