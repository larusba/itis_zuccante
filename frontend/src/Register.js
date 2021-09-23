import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import { TextField, Button, Grid, Container } from "@material-ui/core";
import VpnKeyIcon from "@material-ui/icons/VpnKey";

class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      username: "",
      confirmPassword: "",
      firstName: "",
      lastName: "",
    };
  }

  handleInputChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;

    this.setState({ [name]: value });
  };

  handleSubmit = (event) => {
    let option = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        login: this.state.username,
        email: this.state.email,
        langKey: "it",
        password: this.state.password,
      }),
    };
    fetch("/api/register", option).then((res) =>
      res.json().then((res) => console.log(res))
    );
  };

  render() {
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
                  value={this.state.firstName}
                  onChange={this.handleInputChange}
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
                  value={this.state.lastName}
                  onChange={this.handleInputChange}
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
                  value={this.state.username}
                  onChange={this.handleInputChange}
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
                  value={this.state.email}
                  onChange={this.handleInputChange}
                  className="register-field"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  size="small"
                  required
                  label="Password"
                  type="password"
                  name="password"
                  variant="outlined"
                  value={this.state.password}
                  onChange={this.handleInputChange}
                  className="register-field"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  size="small"
                  required
                  label="Conferma password"
                  type="password"
                  variant="outlined"
                  className="register-field"
                />
              </Grid>
            </Grid>
          </Container>
          <br />
          <Button
            onClick={this.handleSubmit}
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
}

export default Register;
