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
import Navigation from "./Navigation.js";
import Profile from "./Components/Profile";
import Dashboard from "./Dashboard";
import Behaviour from "./Components/Behaviour";

function App() {
  return (
    <div className="App">
      <Router>
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
          <Route path="/">
            <Navigation />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
