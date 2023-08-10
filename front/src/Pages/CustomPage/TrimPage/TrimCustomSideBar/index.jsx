import React, { useEffect, useReducer } from "react";
import { css, styled } from "styled-components";
import Button from "../../../../Components/Common/Button/Button";
import { initialState, reducer } from "./index.reducer";
import TrimOptionGroup from "../../../../Components/Custom/TrimOptionGroup";
import ModelItemOptionGroup from "../../../../Components/Custom/ModelItemOptionGroup";
import useButtonNavigation from "../../../../hooks/useButtonNavigation";
import helpIcon from "../../../../assets/icons/help-circle.svg";

// state의 engine, body, drivetrain 바뀔 때마다 trimOptions 새로 가져와서 TrimOptionsGroup 다시 띄워줘야 함
const modelItemData = {
  engine: {
    title: "엔진",
    options: ["디젤 2.2", "가솔린 3.8"],
  },
  body: {
    title: "바디",
    options: ["7인승", "8인승"],
  },
  drivetrain: {
    title: "구동방식",
    options: ["2WD", "4WD"],
  },
};

const TrimOptions = [
  {
    name: "Exclusive",
    defaultOptions: [
      "12인치 내비게이션",
      "내비 기반 크루즈",
      "세이프티 파워 윈도우",
    ],
    information: "합리적인 당신을 위한",
    minPrice: 43460000,
  },
  {
    name: "Le Blanc",
    defaultOptions: [
      "20인치 알로이 휠",
      "12인치 클러스터",
      "서라운드 뷰 모니터",
    ],
    information: "필수적인 옵션만 모은",
    minPrice: 43460000,
  },
  {
    name: "Prestige",
    defaultOptions: [
      "12인치 내비게이션",
      "내비 기반 크루즈",
      "세이프티 파워 윈도우",
    ],
    information: "합리적인 당신을 위한",
    minPrice: 43460000,
  },
  {
    name: "Caligraphy",
    defaultOptions: [
      "12인치 내비게이션",
      "내비 기반 크루즈",
      "세이프티 파워 윈도우",
    ],
    information: "합리적인 당신을 위한",
    minPrice: 43460000,
  },
];

const TrimCustomSideBar = () => {
  const [state, dispatch] = useReducer(reducer, initialState);
  const handleOptionSelect = (questionKey, option) => {
    dispatch({
      type: "SELECT_OPTION",
      questionKey,
      option,
    });
  };
  const move = useButtonNavigation();

  return (
    <Wrapper>
      <CustomBarContent>
        <LinkBtnContainer>
          <img src={helpIcon} />
          <Button text="고르기 어렵다면?" style={LinkBtnStyle} />
        </LinkBtnContainer>
        <ModelItems>
          {Object.entries(modelItemData).map(([questionKey, data]) => (
            <ModelItemOptionGroup
              key={questionKey}
              data={data}
              handleOptionSelect={(newValue) => {
                handleOptionSelect(questionKey, newValue);
              }}
              radioGroup={questionKey}
              selectedOption={state[questionKey]}
            />
          ))}
        </ModelItems>
        <TrimOptionGroup
          options={TrimOptions}
          selectedOption={state["trim"]}
          handleOptionSelect={(newValue) => {
            handleOptionSelect("trim", newValue);
          }}
        />
        <Button
          text="색상 선택"
          style={nextBtnStyle}
          onClick={() => move("/custom/color")}
        />
      </CustomBarContent>
    </Wrapper>
  );
};

const LinkBtnContainer = styled.div`
  display: flex;
  gap: 2px;
  margin-top: 56px;
`;
const nextBtnStyle = css`
  width: 100%;
  height: 52px;

  color: ${({ theme }) => theme.color.grey1000};
  background-color: ${({ theme }) => theme.color.primary_default};
  ${({ theme }) => theme.font.Body3_Medium};

  border: 1px solid ${({ theme }) => theme.color.primary_default};
  border-radius: 6px;

  margin-top: 24px;
`;
const ModelItems = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 12px;

  border: 1px solid ${({ theme }) => theme.color.grey700};
  border-radius: 8px;

  padding: 12px 0px;
  margin-top: 16px;
`;
const LinkBtnStyle = css`
  color: ${({ theme }) => theme.color.secondary};
  background-color: ${({ theme }) => theme.color.grey1000};

  border: none;
  ${({ theme }) => theme.font.Extra4};
  background: none;
  padding: 0;
  outline: 0;
  text-decoration: underline;
  text-underline-offset: 3px;
`;

const CustomBarContent = styled.div`
  width: 309px;
  margin-right: 128px;

  box-sizing: border-box;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  flex: 0 0 auto;
  width: 473px;
  height: 1292px;
`;
export default TrimCustomSideBar;
