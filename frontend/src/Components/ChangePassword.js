import { TextField } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import React from "react";
import "./css/changePassword.css";

export default function ChangePassword() {
  const [clicked, setClicked] = React.useState(false);
  const [oldPassword, setOld] = React.useState("");
  const [newPassword, setNew] = React.useState("");
  const [notEquals, setEquals] = React.useState(false);
  const [helperText, setHelper] = React.useState("");

  const changePassword = () => {
    if (oldPassword != newPassword) {
      let change = {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "Post",
        body: JSON.stringify({
          currentPassword: oldPassword,
          newPassword: newPassword,
        }),
      };
      fetch("/api/account/change-password", change).then(
        console.log("password changed")
      );
      setEquals(false);
      setHelper("");
    } else {
      if (oldPassword.length == 0 && newPassword.length == 0) {
        setEquals(true);
        setHelper("Empty fields.");
      } else {
        setEquals(true);
        setHelper("The two fields don't match.");
      }
    }
  };

  const cancel = () => {
    setEquals(false);
    setHelper("");
    setClicked(!clicked);
  };

  const change = () => {
    setEquals(false);
    setHelper("");
    setClicked(!clicked);
  };

  return (
    <div>
      <Button
        variant="outlined"
        size="small"
        color="secondary"
        className="changePass"
        onClick={() => change()}
      >
        Change password
      </Button>
      {clicked && (
        <div className="clickedDiv">
          <br />
          <TextField
            className="textField"
            placeholder="Old password"
            variant="outlined"
            size="small"
            onChange={() => setOld(document.getElementById("old").value)}
            id="old"
            name="oldField"
            type="password"
          />{" "}
          <TextField
            className="textField"
            placeholder="New password"
            variant="outlined"
            size="small"
            onChange={() => setNew(document.getElementById("new").value)}
            id="new"
            error={notEquals}
            helperText={helperText}
            type="password"
          />{" "}
          <div className="button">
            <Button
              variant="outlined"
              size="small"
              color="primary"
              className="confirm"
              onClick={() => changePassword()}
            >
              Confirm
            </Button>
            <Button
              variant="outlined"
              color="default"
              size="small"
              className="cancel"
              onClick={() => cancel()}
            >
              Cancel
            </Button>
          </div>
        </div>
      )}
    </div>
  );
}
