import React, { useRef, useState } from "react";
import { styled, css } from "styled-components";
import AdditionalOption from "./AdditionalOption";
import DefaultOption from "./DefaultOption";
import Button from "../../../Components/Common/Button/Button";
import useButtonNavigation from "../../../hooks/useButtonNavigation";
import useOnClickPopUp from "../../../hooks/useOnClickPopUp";
import OptionPopup from "./OptionPopup";
import OverlaidPopup from "../../../Components/Common/OverlaidPopup";

const OptionPage = () => {
  const [selectedTab, setSelectedTab] = useState("추가 옵션");

  const optionPopupRef = useRef();
  const { isPopupOpen, openPopup, closePopup } =
    useOnClickPopUp(optionPopupRef);

  const isOptionPage = () => {
    if (selectedTab === "추가 옵션")
      return <AdditionalOption openPopup={openPopup} />;
    if (selectedTab === "기본 포함 옵션")
      return <DefaultOption openPopup={openPopup} />;
  };

  const move = useButtonNavigation();

  return (
    <Wrapper>
      {isPopupOpen && (
        <OverlaidPopup
          component={
            <OptionPopup popupRef={optionPopupRef} closePopup={closePopup} />
          }
        />
      )}
      <TabWrapper>
        <TabItem
          selected={selectedTab === "추가 옵션"}
          onClick={() => setSelectedTab("추가 옵션")}
        >
          추가 옵션
        </TabItem>
        <TabItem
          selected={selectedTab === "기본 포함 옵션"}
          onClick={() => setSelectedTab("기본 포함 옵션")}
        >
          기본 포함 옵션
        </TabItem>
      </TabWrapper>
      {isOptionPage()}
      <ButtonContainer>
        <Button
          text="색상선택"
          style={BtnStyle1}
          onClick={() => move("/custom/color")}
        />
        <Button
          text="견적내기"
          style={BtnStyle2}
          onClick={() => move("/result")}
        />
      </ButtonContainer>
    </Wrapper>
  );
};

const BtnStyle2 = css`
  width: 298px;
  height: 52px;

  color: ${({ theme }) => theme.color.grey1000};
  background-color: ${({ theme }) => theme.color.primary_default};
  ${({ theme }) => theme.font.Body3_Medium};

  border: 1px solid ${({ theme }) => theme.color.primary_default};
  border-radius: 6px;
`;

const BtnStyle1 = css`
  width: 298px;
  height: 52px;

  color: ${({ theme }) => theme.color.grey50};
  background-color: ${({ theme }) => theme.color.grey1000};
  ${({ theme }) => theme.font.Body3_Medium};

  border: 1px solid ${({ theme }) => theme.color.grey600};
  border-radius: 6px;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;

  margin-top: 50px;
  gap: 12px;
  width: 100%;
`;

const TabItem = styled.div`
  ${({ selected }) =>
    !selected
      ? css`
          color: ${({ theme }) => theme.color.grey700};
        `
      : css`
          color: ${({ theme }) => theme.color.grey200};
          border-bottom: 1.5px solid;
          border-bottom-color: ${({ theme }) => theme.color.grey200};
        `};

  ${({ theme }) => theme.font.Head2};
  cursor: pointer;
  padding-bottom: 8px;
`;

const TabWrapper = styled.div`
  display: flex;

  gap: 40px;
  margin-top: 15px;
  padding: 0px 128px;

  border-bottom: 1px solid;
  border-bottom-color: ${({ theme }) => theme.color.grey700};
`;

const Wrapper = styled.div`
  height: 1070px;
`;

export default OptionPage;
