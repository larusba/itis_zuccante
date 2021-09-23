import { Paper } from "@mui/material";
import React from "react";
import "./App.css";
import Survey from "./Components/Survey.js";

export default function ProfileSurvey() {
  return (
    <Paper className="survey-paper">
      <Survey />
    </Paper>
  );
}
