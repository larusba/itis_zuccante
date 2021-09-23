import React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

const stepss = new Map([
  [
    'Means of transport',
    [
      'I walk or ride a bicycle, and I use public transport only occasionally.',
      'I predominately use public transport (2/3) and sometimes walk or ride a bicycle (1/3).',
      'I usually drive a car, but I also occasionally use public transport or walk.',
      'I mainly drive a car.',
    ],
  ],
  [
    'Flying',
    [
      'I never fly privately.',
      'I fly privately once or twice a year within Europe.',
      'I fly long haul privately about once a year (or about four times within Europe).',
      'I fly long haul privately two or three times a year (or regularly).',
    ],
  ],
  [
    'Food',
    [
      'I eat only vegan food.',
      'I mostly eat vegetarian food.',
      'I eat meat every other day on average.',
      'I eat meat at almost every meal.',
    ],
  ],
  [
    'Shopping, leisure, culture',
    [
      'I purchase new clothing, devices and furniture very rarely (these expenses are 60 euros a month). Expenses for leisure time, culture and health are below average.',
      'I purchase half of my clothing, devices and furniture second-hand (these expenses are 170 euros a month). Expenses for leisure time, culture and health are slightly below average.',
      'I occasionally purchase new clothing, devices and furniture (these expenses are 210 euros a month). Expenses for leisure time, culture and health are average (360 euros a month).',
      'I frequently purchase new clothing, devices and furniture (these expenses are 420 euros a month). Expenses for leisure time, culture and health are slightly above average.',
    ],
  ],
  [
    'Living space',
    [
      'I live in a small space (for example, a flat with several people).',
      'I live in a large area (e.g. detached house).',
    ],
  ],
  [
    'Construction standard',
    [
      'I live in a renovated, energy-efficient house.',
      'The house I inhabit is an older structure.',
    ],
  ],
  [
    'Heating system',
    [
      'Our home is heated using renewable energies (wood, heat pumps, etc.).',
      'Our home is heated using fossil fuels (petroleum, natural gas, etc.).',
    ],
  ],
]);

export default function Survey() {
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());

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

  return (
    <Box sx={{ width: '100%' }}>
      <Stepper activeStep={activeStep}>
        {Array.from(stepss.keys()).map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps} />
            </Step>
          );
        })}
      </Stepper>
      {activeStep === Array.from(stepss.keys()).length - 1 ? (
        <React.Fragment>
          <Typography sx={{ mt: 2, mb: 1 }}>
            Grazie per le risposte :)
          </Typography>
        </React.Fragment>
      ) : (
        <React.Fragment>
          <Typography variant="h6" sx={{ mt: 2, mb: 1 }}>
            {Array.from(stepss.keys())[activeStep]}
          </Typography>
          <RadioGroup
            aria-label="gender"
            defaultValue="female"
            name="radio-buttons-group"
          >
            {stepss.get(Array.from(stepss.keys())[activeStep]).map((q) => (
              <FormControlLabel value={q} control={<Radio />} label={q} />
            ))}
          </RadioGroup>
          <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
            <Button
              color="inherit"
              disabled={activeStep === 0}
              onClick={handleBack}
              sx={{ mr: 1 }}
            >
              Back
            </Button>
            <Box sx={{ flex: '1 1 auto' }} />
            <Button onClick={handleNext}>
              {activeStep === Array.from(stepss.keys()).length - 1
                ? 'Finish'
                : 'Next'}
            </Button>
          </Box>
        </React.Fragment>
      )}
    </Box>
  );
}
