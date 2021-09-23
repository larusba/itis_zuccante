import React from "react";
import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

const steps = new Map([
  [
    "Mezzi di trasporto",
    [
      {
        answer:
          "Cammino o utilizzo una bicicletta, utilizzo i mezzi pubblici solo occasionalmente.",
        value: "LOW",
      },
      {
        answer:
          "Uso soprattutto i mezzi di trasporto e a volte cammino o uso la bici.",
        value: "MEDIUM",
      },
      {
        answer:
          "Solitamente guido, ma a volte cammino o uso anche i mezzi pubblici.",
        value: "HIGH",
      },
      { answer: "Prevalentemente guido.", value: "GIANT" },
    ],
  ],
  [
    "Volo",
    [
      { answer: "Non volo privatamente.", value: "LOW" },
      {
        answer:
          "Volo privatamente una o due volte l'anno all'interno dell'Europa.",
        value: "MEDIUM",
      },
      {
        answer: "Volo privatamente a lungo raggio circa una volta l'anno.",
        value: "HIGH",
      },
      {
        answer:
          "Volo privatamente a lungo raggio due o tre volte l'anno (o regolarmente).",
        value: "GIANT",
      },
    ],
  ],
  [
    "Cibo",
    [
      { answer: "Mangio solo vegano.", value: "LOW" },
      { answer: "Mangio per lo più cibo vegetariano.", value: "MEDIUM" },
      {
        answer: "In media mangio la carne un giorno sì e un giorno no.",
        value: "HIGH",
      },
      { answer: "Mangio carne in quasi tutti i pasti.", value: "GIANT" },
    ],
  ],
  [
    "Shopping, divertimento, cultura",
    [
      {
        answer:
          "Acquisto nuovi vestiti, dispositivi e mobili molto raramente (queste spese sono 60 euro al mese). Spese per il tempo libero, cultura e salute sono sotto la media.",
        value: "LOW",
      },
      {
        answer:
          "Acquisto metà dei miei vestiti, dispositivi e mobili di seconda mano (queste spese sono 170 euro al mese). Spese per il tempo libero, cultura e salute sono leggermente sotto la media.",
        value: "MEDIUM",
      },
      {
        answer:
          "Acquisto occasionalmente vestiti, dispositivi e mobili (queste spese sono 210 euro al mese). Spese per il tempo libero, cultura e salute sono medie (360 euros a month).",
        value: "HIGH",
      },
      {
        answer:
          "Acquisto frequentemente vestiti, dispositivi e mobili (queste spese sono 420 euro al mese). Spese per il tempo libero, cultura e salute sono leggermente sopra la media.",
        value: "GIANT",
      },
    ],
  ],
  [
    "Spazio per vivere",
    [
      {
        answer:
          "Vivo in uno spazio piccolo (per esempio, un appartamento con più persone).",
        value: "LOW",
      },
      {
        answer: "Vivo in uno spazio grande (es. casa singola).",
        value: "HIGH",
      },
    ],
  ],
  [
    "Standard di costruzione",
    [
      {
        answer: "Vivo in una casa rinnovata e a basso consumo energetico",
        value: "LOW",
      },
      {
        answer: "La casa in cui abito è una struttura più vecchia",
        value: "HIGH",
      },
    ],
  ],
  [
    "Sistema di riscaldamento",
    [
      {
        answer:
          "La nostra casa è riscaldata usando fonti rinnovabili (legno, pompe di calore, ecc.).",
        value: "LOW",
      },
      {
        answer:
          "La nostra casa è riscaldata usando combustibili fossili (petrolio, gas naturale, ecc.).",
        value: "HIGH",
      },
    ],
  ],
]);

export default function Survey() {
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());
  const [personalFootprint, setPersonalFootprint] = React.useState(new Map());
  const [vehicle, setVehicle] = React.useState();
  const [recycling, setRecycling] = React.useState();

  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleFootprintChange = (event, step) => {
    console.log(step);
    const personalFootprintTmp = new Map(personalFootprint);
    personalFootprintTmp.set(step, event.target.value);
    setPersonalFootprint(personalFootprintTmp);
  };

  console.log(personalFootprint);
  return (
    <Box sx={{ width: "100%" }}>
      <Stepper activeStep={activeStep}>
        {Array.from(steps.keys()).map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps} />
            </Step>
          );
        })}
      </Stepper>
      {activeStep === Array.from(steps.keys()).length - 1 ? (
        <React.Fragment>
          <Typography sx={{ mt: 2, mb: 1 }}>
            Grazie per le risposte :)
          </Typography>
        </React.Fragment>
      ) : (
        <React.Fragment>
          <Typography variant="h6" sx={{ mt: 2, mb: 1 }}>
            {Array.from(steps.keys())[activeStep]}
          </Typography>
          <RadioGroup name="radio-buttons-group">
            {steps.get(Array.from(steps.keys())[activeStep]).map((q) => (
              <FormControlLabel
                value={q.value}
                control={<Radio />}
                label={q.answer}
                onChange={(event) => handleFootprintChange(event, activeStep)}
              />
            ))}
          </RadioGroup>
          <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
            <Button
              color="inherit"
              disabled={activeStep === 0}
              onClick={handleBack}
              variant="outlined"
              sx={{ mr: 1 }}
            >
              Back
            </Button>
            <Box sx={{ flex: "1 1 auto" }} />
            <Button onClick={handleNext} variant="contained">
              {activeStep === Array.from(steps.keys()).length - 1
                ? "Finish"
                : "Next"}
            </Button>
          </Box>
        </React.Fragment>
      )}
    </Box>
  );
}
