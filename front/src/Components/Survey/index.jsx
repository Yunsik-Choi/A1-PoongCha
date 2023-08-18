import React, { useEffect } from "react";
import { styled } from "styled-components";

import useRadio from "../../hooks/useRadio";
import Title from "./Title";

const Survey = ({
  questionnaire,
  label,
  options,
  newStateHandler,
  initialState,
  style,
}) => {
  const { selected, handleSelected } = useRadio(initialState);
  useEffect(() => {
    newStateHandler(selected);
  }, [selected]);
  return (
    <Wrapper>
      <Title questionnaire={questionnaire} />
      <FlexBox $style={style}>
        {options.map((option, index) => (
          <div key={index}>{label(option, selected, handleSelected)}</div>
        ))}
      </FlexBox>
    </Wrapper>
  );
};

const FlexBox = styled.div`
  display: flex;
  flex-wrap: wrap;

  ${({ $style }) => $style}
`;

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  margin-bottom: 52px;
`;

export default Survey;
