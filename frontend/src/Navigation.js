import * as React from "react";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import DashboardIcon from "@mui/icons-material/Dashboard";
import GroupsIcon from "@mui/icons-material/Groups";
import BookIcon from "@mui/icons-material/Book";
import Paper from "@mui/material/Paper";
import Home from "./Home.js";

export default function FixedBottomNavigation() {
  const [value, setValue] = React.useState(1);
  const ref = React.useRef(null);
  return (
    <Box sx={{ pb: 7 }} ref={ref}>
      <CssBaseline />
      {value === 0}
      {value === 1 && <Home />}
      {value === 2}
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
          <BottomNavigationAction label="Gruppi" icon={<GroupsIcon />} />
          <BottomNavigationAction label="Home" icon={<DashboardIcon />} />
          {<BottomNavigationAction label="Attività" icon={<BookIcon />} />}
        </BottomNavigation>
      </Paper>
    </Box>
  );
}
