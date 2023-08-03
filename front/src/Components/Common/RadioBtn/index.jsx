import React, { useState } from "react";
import { styled } from "styled-components";

const RadioBtn = ({ id, name, value, onChange }) => {
  const [selectedOption, setSelectedOption] = useState("");

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  return (
    <RadioBtnInput id={id} name={name} onChange={onChange}></RadioBtnInput>
  );
};

const RadioBtnInput = styled.input.attrs({ type: "radio" })`
  display: none;
`;

export default RadioBtn;
