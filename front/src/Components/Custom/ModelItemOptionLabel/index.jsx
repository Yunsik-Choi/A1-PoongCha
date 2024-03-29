import React from "react";
import { css, styled } from "styled-components";

const ModelItemOptionLabel = ({ value, handleSelectItem, checked }) => {
  return (
    <>
      <Label selected={checked} onClick={() => handleSelectItem(value)}>
        {value.name}
      </Label>
    </>
  );
};
const Label = styled.label`
  display: flex;
  align-items: center;
  justify-content: center;

  width: 141.5px;
  height: 40px;

  color: ${({ theme }) => theme.color.primary_default};
  background-color: beige;
  ${({ theme }) => theme.font.Body4_Medium};

  border-radius: 6px;

  box-sizing: border-box;

  &:hover {
    cursor: pointer;
  }
  ${(props) =>
    props.selected
      ? css`
          background-color: ${({ theme }) => theme.color.grey1000};
          ${({ theme }) => theme.font.Body4_Medium};
          color: ${({ theme }) => theme.color.primary_default};
          border: 1.5px solid ${({ theme }) => theme.color.primary_default};
        `
      : css`
          background-color: ${({ theme }) => theme.color.grey800};
          ${({ theme }) => theme.font.Body4_Regular};
          color: ${({ theme }) => theme.color.grey500};
        `}
`;
export default ModelItemOptionLabel;
