import * as React from "react";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import DashboardIcon from "@mui/icons-material/Dashboard";
import GroupsIcon from "@mui/icons-material/Groups";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import Paper from "@mui/material/Paper";
import FolderList from "./Components/Behaviour.js";
import Card from "./Components/Card";
import Profile from "./Components/Profile.js";
import "./Components/css/behaviour.css";

export default function FixedBottomNavigation() {
  const [value, setValue] = React.useState(0);
  const ref = React.useRef(null);

  return (
    <div className="div">
      <Box className="box" ref={ref}>
        <CssBaseline />
        {value === 0}
        {value === 1 && <Card />}
        {value === 2 && <FolderList />}
        <Paper
          sx={{ position: "fixed", bottom: 0, left: 0, right: 0 }}
          elevation={3}
        >
          <BottomNavigation
            showLabels
            value={value}
            onChange={(event, newValue) => {
              setValue(newValue);
            }}
          >
            <BottomNavigationAction label="Home" icon={<DashboardIcon />} />
            <BottomNavigationAction label="Group" icon={<GroupsIcon />} />
            {
              <BottomNavigationAction
                label="Attivita"
                icon={<AccountCircleIcon />}
              />
            }
          </BottomNavigation>
        </Paper>
      </Box>
    </div>
  );
}
