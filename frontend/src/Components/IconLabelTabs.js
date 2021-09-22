import React from "react";
import Paper from "@material-ui/core/Paper";
import { makeStyles } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import WeekendIcon from "@material-ui/icons/Weekend";
import AccessibilityNewIcon from "@material-ui/icons/AccessibilityNew";
import DirectionsRunIcon from "@material-ui/icons/DirectionsRun";
import { AiFillTrophy } from "react-icons/ai";
import "./css/iconLabelTabs.css";

const useStyles = makeStyles({
  root: {
    flexGrow: 1,
    maxWidth: 650,
  },
});

export default function IconLabelTabs() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Paper square className={classes.root} id="paper">
      LEVEL OF PHYSICAL ACTIVITY
      <br />
      <br />
      <Tabs
        value={value}
        onChange={handleChange}
        variant="fullwidth"
        indicatorColor="secondary"
        textColor="secondary"
        aria-label="icon label tabs example"
        id="tabs"
      >
        <Tab icon={<WeekendIcon />} label="LAZY" />
        <Tab icon={<AccessibilityNewIcon />} label="NORMAL" />
        <Tab icon={<DirectionsRunIcon />} label="ACTIVE" />
        <Tab icon={<AiFillTrophy />} label="SPORTY" />
      </Tabs>
    </Paper>
  );
}
