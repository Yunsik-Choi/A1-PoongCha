import React, { useReducer, useState, useRef, useEffect } from "react";
import { css, styled } from "styled-components";
import { initialState, reducer } from "./index.reducer";
import Button from "@Components/Common/Button/Button";
import TrimOptionGroup from "@Components/Custom/TrimOptionGroup";
import useButtonNavigation from "@hooks/useButtonNavigation";
import helpIcon from "@assets/icons/help-circle.svg";
import useOnClickPopUp from "@hooks/useOnClickPopUp";
import OverlaidPopup from "@Components/Common/OverlaidPopup";
import ModelItemsDescriptionPopup from "../ModelItemsDescriptionPopup";
import TrimChangePopup from "../TrimChangePopup";
import { TrimOptions, modelItemData } from "./mockData";
import ModelItemOption from "@Components/Custom/ModelItemOptionGroup/ModelItemOption";
import Survey from "@Components/Survey";

const TrimCustomSideBar = () => {
  const move = useButtonNavigation();
  const [state, dispatch] = useReducer(reducer, initialState);
  const [clickedTrim, setClickedTrim] = useState();
  const setOptionSelect = (questionKey, option) => {
    dispatch({
      type: "SELECT_OPTION",
      questionKey,
      option,
    });
  };

  const modelItemDescriptionPopupRef = useRef();
  const {
    isPopupOpen: isModelItemDescriptionPopupOpen,
    openPopup: openModelItemDescriptionPopup,
    closePopup: closeModelItemDescriptionPopup,
  } = useOnClickPopUp(modelItemDescriptionPopupRef);

  const TrimChangePopupRef = useRef();
  const {
    isPopupOpen: isTrimChangePopupOpen,
    openPopup: openTrimChangePopup,
    closePopup: closeTrimChangePopup,
  } = useOnClickPopUp(TrimChangePopupRef);
  const handleTrimOptionChange = (newValue) => {
    if (newValue === state["trim"]) return;
    setClickedTrim(newValue);
    // 새로운 트림 옵션 선택으로 현재 선택한 색상과 옵션이 변동될 경우에 팝업 띄움
    if (true) {
      // 일단 항상 팝업 띄우도록 설정
      openTrimChangePopup();
    } else {
      setOptionSelect("trim", newValue);
    }
  };

  return (
    <Wrapper>
      {isModelItemDescriptionPopupOpen && (
        <OverlaidPopup
          component={
            <ModelItemsDescriptionPopup
              popupRef={modelItemDescriptionPopupRef}
              closePopup={closeModelItemDescriptionPopup}
            />
          }
        />
      )}
      {isTrimChangePopupOpen && (
        <OverlaidPopup
          component={
            <TrimChangePopup
              popupRef={TrimChangePopupRef}
              closePopup={closeTrimChangePopup}
              changeTrim={() => setOptionSelect("trim", clickedTrim)}
            />
          }
        />
      )}
      <CustomBarContent>
        <LinkBtnContainer>
          <img src={helpIcon} />
          <Button
            text="고르기 어렵다면?"
            style={LinkBtnStyle}
            onClick={openModelItemDescriptionPopup}
          />
        </LinkBtnContainer>
        <ModelItems>
          {Object.entries(modelItemData).map(([questionKey, data]) => (
            <Survey
              key={questionKey}
              questionnaire={data.title}
              label={ModelItemOption}
              options={data.options}
              newStateHandler={(newState) =>
                setOptionSelect(questionKey, newState)
              }
              initialState={state[questionKey]}
              style={modelItemRadioGroupStyle}
            />
          ))}
        </ModelItems>
        <TrimOptionGroup
          options={TrimOptions}
          selectedOption={state["trim"]}
          handleOptionSelect={(newValue) => {
            handleTrimOptionChange(newValue);
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

const modelItemRadioGroupStyle = {
  wrapper: css`
    position: relative;
    display: flex;
    flex-direction: column;
    margin: 0px 12px;
  `,
  title: css`
    ${({ theme }) => theme.font.Body4_Medium};
  `,
  options: css`
    position: relative;
    display: flex;
    width: 100%;
    margin-top: 4px;
    & > div {
      width: 50%;
    }
  `,
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
