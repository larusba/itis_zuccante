import React from "react";
import "./App.css";
import { Link } from "react-router-dom";
import { TextField, Button } from "@material-ui/core";
import VpnKeyIcon from "@material-ui/icons/VpnKey";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { email: "", password: "" };
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
        username: this.state.email,
        password: this.state.password,
      }),
    };
    fetch("/api/authenticate", option).then((res) =>
      res.json().then((res) => console.log(res))
    );
  };

  render() {
    return (
      <div className="form-background login">
        <div className="form-div">
          <h1 className="form-title">Accedi</h1>
          <hr className="form-break" />
          <div className="login-form">
            <TextField
              label="Email"
              type="email"
              name="email"
              variant="outlined"
              value={this.state.email}
              onChange={this.handleInputChange}
              className="login-field"
            />
            <br />
            <br />
            <TextField
              label="Password"
              type="password"
              name="password"
              variant="outlined"
              value={this.state.password}
              onChange={this.handleInputChange}
              className="login-field"
            />
            <br />
          </div>
          <br />
          <Button
            onClick={this.handleSubmit}
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
}

export default Login;
