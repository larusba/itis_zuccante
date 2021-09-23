import React, { useState } from "react";
import EdiText from "react-editext";

export default function Edit() {
  const [editing] = useState(false);
  const [value, setValue] = useState("This is a sample text.");

  const handleSave = (value) => {
    console.log(value);
    setValue(value);
  };
  return (
    <div style={{ width: "50%", marginTop: 20 }}>
      {/*  Apply your changes below */}
      <EdiText
        value={value}
        type="text"
        onSave={handleSave}
        editing={editing}
      />
    </div>
  );
}
