import React from 'react';
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';
import EditIcon from '@material-ui/icons/Edit';
import './css/saveButton.css';

export default function SaveButton(props) {
  const { btnSet, changeData } = props;

  const saveAll = () => {
    btnSet(true);
    //changeData();
  };

  return (
    <div>
      <Button
        variant="contained"
        color="primary"
        size="large"
        className="save"
        startIcon={<SaveIcon />}
        onClick={saveAll}
      >
        Save
      </Button>
      <Button
        variant="contained"
        color="secondary"
        className="edit"
        startIcon={<EditIcon />}
        onClick={() => {
          btnSet(false);
          console.log('pp');
        }}
      >
        Edit
      </Button>
    </div>
  );
}
