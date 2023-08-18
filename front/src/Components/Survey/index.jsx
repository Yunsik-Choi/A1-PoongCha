import React, { useEffect } from "react";
import { styled } from "styled-components";
import useRadio from "../../hooks/useRadio";

const Survey = ({
  questionnaire,
  label,
  options,
  newStateHandler,
  initialState,
  style,
}) => {
  const { selectedItem, handleSelectItem } = useRadio(initialState);
  useEffect(() => {
    newStateHandler(selectedItem);
  }, [selectedItem]);
  return (
    <Wrapper $style={style.wrapper}>
      <Title $style={style.title}>{questionnaire}</Title>
      <Options $style={style.options}>
        {options.map((option, index) => (
          <div key={index}>{label(option, selectedItem, handleSelectItem)}</div>
        ))}
      </Options>
    </Wrapper>
  );
};

const Title = styled.div`
  ${({ $style }) => $style}
`;
const Options = styled.div`
  ${({ $style }) => $style}
`;
const Wrapper = styled.div`
  ${({ $style }) => $style}
`;

export default Survey;
