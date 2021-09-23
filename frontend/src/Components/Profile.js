import React from 'react';
import Header from './Header.js';
import ControlledOpenSelect from './SelectField.js';
import RadioButton from './RadioButton';
import SaveButton from './SaveButton.js';
import './profile.css';
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import './css/imageStyle.css';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import './css/formField.css';
import './css/textTitle.css';
import ChangePasswordButton from './ChangePassword.js';
import Survey from './Survey';

function Profile() {
  const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
      '& > *': {
        margin: theme.spacing(1),
      },
    },
  }));
  const useStyles2 = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(1),
      },
    },
    input: {
      display: 'none',
    },
  }));
  const [disable, setDisable] = React.useState(true);
  const [btnState, btnSet] = React.useState(true);
  const [firstName, setFirstName] = React.useState('First name');
  const [lastName, setLastName] = React.useState('Last name');
  const [email, setEmail] = React.useState('E-mail');
  const [birth, setBirth] = React.useState('Birth date');
  const [locality, setLocality] = React.useState('Locality');
  const classes2 = useStyles2();
  const [img, setImg] = React.useState();

  const handleChangeFirst = (event) => {
    setFirstName(event.target.value);
  };
  const handleChangeLast = (event) => {
    setLastName(event.target.value);
  };
  const handleChangeEmail = (event) => {
    setEmail(event.target.value);
  };
  const handleChangeBirth = (event) => {
    setBirth(event.target.value);
  };
  const handleChangeLocality = (event) => {
    setLocality(event.target.value);
  };

  let imgUrl = '';

  const changePhoto = () => {
    console.log(img);
  };

  const changeData = () => {
    let change = {
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email,
      }),
    };
    fetch('/api/account/', change).then(console.log('account changed'));
  };

  return (
    <div className="profile">
      <Header />
      <Avatar src={imgUrl} id="logo" />
      <br />
      <br />
      <br />
      <br />
      <div>
        <input
          accept=".jpg, .png, .jpeg, .gif"
          className={classes2.input}
          id="contained-button-file"
          multiple
          type="file"
          value={img}
          onChange={changePhoto}
        />

        <label htmlFor="contained-button-file">
          <Button variant="contained" component="span">
            Upload new photo
          </Button>
        </label>
      </div>

      <div>
        <br />
        <br />
        <div className="background">
          <p className="text">PERSONAL DATA</p>
        </div>
        <br />
        <form noValidate autoComplete="off" className="form">
          NAME:
          <br />
          <br />
          <div>
            <div>
              <TextField
                className="outlined-basic"
                variant="outlined"
                size="small"
                disabled={disable}
                placeholder={firstName}
                onChange={handleChangeFirst}
              />{' '}
              <br />
            </div>
            <br />
            <div>
              <TextField
                className="outlined-basic"
                variant="outlined"
                size="small"
                disabled={btnState}
                placeholder={lastName}
                onChange={handleChangeLast}
              />{' '}
              <br />
            </div>
          </div>
          <br />
          E-MAIL:
          <br />
          <div>
            <div>
              <br />
              <TextField
                className="outlined-basic"
                variant="outlined"
                size="small"
                disabled={btnState}
                placeholder={email}
                onChange={handleChangeEmail}
              />{' '}
              <ChangePasswordButton />
            </div>
          </div>
          <br />
          <br />
          BIRTH DATE:
          <br />
          <div>
            <div>
              <br />
              <TextField
                className="outlined-basic"
                variant="outlined"
                size="small"
                disabled={btnState}
                placeholder={birth}
                onChange={handleChangeBirth}
              />{' '}
              <br />
            </div>
          </div>
          <br />
          LOCALITY:
          <br />
          <div>
            <div>
              <br />
              <TextField
                className="outlined-basic"
                variant="outlined"
                size="small"
                disabled={btnState}
                placeholder={locality}
                onChange={handleChangeLocality}
              />{' '}
              <br />
            </div>
          </div>
        </form>
      </div>

      <br />
      <hr className="hr" />
      <br />
      <ControlledOpenSelect />
      <br />

      <RadioButton />
      <br />
      <br />
      <br />
      <Survey />
      <SaveButton
        btnState={disable}
        btnSet={setDisable}
        changeData={changeData}
      />
      <br />
      <br />
      <br />
    </div>
  );
}

export default Profile;
