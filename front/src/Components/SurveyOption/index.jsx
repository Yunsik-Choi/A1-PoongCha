import React from "react";
import { css, styled } from "styled-components";
import RadioBtn from "../Common/RadioBtn";
import Check24BlueIcon from "../../assets/checkcircle/check-24-blue.svg";

const SurveyOption = ({ label, index, value, name, selected, onChange }) => {
  return (
    <>
      <SurveyOptionLabel htmlFor={"radioInput" + index} selected={selected}>
        {label}
        {selected && <img src={Check24BlueIcon} alt="check" />}
      </SurveyOptionLabel>
      <RadioBtn
        id={"radioInput" + index}
        onChange={onChange}
        name={name}
        value={value}
      />
    </>
  );
};

const SurveyOptionLabel = styled.label`
  width: 298px;
  height: 56px;

  display: flex;
  align-items: center;
  justify-content: space-between;

  border-radius: 6px;

  box-sizing: border-box;

  padding: 17px 12px;

  &:hover {
    cursor: pointer;
  }
  ${(props) =>
    props.selected
      ? css`
          background-color: ${({ theme }) => theme.color.grey1000};
          ${({ theme }) => theme.font.Body2_Bold};
          color: ${({ theme }) => theme.color.primary_default};
          border: 1.5px solid ${({ theme }) => theme.color.primary_default};
        `
      : css`
          background-color: ${({ theme }) => theme.color.grey800};
          ${({ theme }) => theme.font.Body2_Medium};
          color: ${({ theme }) => theme.color.grey500};
        `}
`;

export default SurveyOption;

// const [selectedOption, setSelectedOption] = useState(0);
// const handleOptionChange = (option) => {
//   setSelectedOption(option);
// };

{
  /* <form>
        <SurveyOption
          label="20대"
          index={0}
          name="age"
          selected={selectedOption === 0}
          onChange={() => handleOptionChange(0)}
        />
        <SurveyOption
          label="30대"
          index={1}
          name="age"
          selected={selectedOption === 1}
          onChange={() => handleOptionChange(1)}
        />
      </form> */
}
