import logo from "./logo.svg";
import "./App.css";
import Login from "./Login.js";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";
import Register from "./Register";
import PasswordReset from "./PasswordReset";
import AccountActivation from "./AccountActivation";
import PasswordResetFinish from "./PasswordResetFinish";

function App() {
  return (
    <div className="App">
      <Router>
        {/*<header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>*/}
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/activate">
            <AccountActivation />
          </Route>
          <Route path="/password-reset/finish">
            <PasswordResetFinish />
          </Route>
          <Route path="/password-reset">
            <PasswordReset />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
