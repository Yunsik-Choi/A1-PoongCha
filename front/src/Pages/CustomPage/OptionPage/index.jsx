import React, { useEffect, useState } from "react";
import { styled, css } from "styled-components";
import AllOptions from "./AllOptions";
import Button from "@Components/Common/Button/Button";
import useButtonNavigation from "@hooks/useButtonNavigation";
import { additionalOptionData, defaultOptionData } from "./optionData";
import { tags, tagSelectIcons, tagsNotSelectIcons } from "./tagIcon";
import OptionTagGroup from "@Components/Custom/OptionTagGroup";

const tabData = ["추가 옵션", "기본 포함 옵션"];
const OptionPage = () => {
  // 선택한 옵션들 상태관리 (옵션 id 값 저장)
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [selectedTag, setSelectedTag] = useState(0);
  const handleSelectOption = (id) => {
    if (checkOptionSelected(id)) {
      setSelectedOptions((prev) => prev.filter((optId) => optId !== id));
    } else {
      setSelectedOptions((prev) => [...prev, id]);
    }
  };
  const checkOptionSelected = (id) => {
    return selectedOptions.includes(id);
  };

  const [selectedTab, setSelectedTab] = useState("추가 옵션");
  const handleSelectTab = (tab) => {
    setSelectedTab(tab);
    setSelectedTag(0);
  };
  const move = useButtonNavigation();

  return (
    <Wrapper>
      <TabContainer>
        {tabData.map((tab) => (
          <TabItem
            selected={selectedTab === tab}
            onClick={() => handleSelectTab(tab)}
          >
            {tab}
          </TabItem>
        ))}
      </TabContainer>
      <OptionTagGroup />
      {/* <AllOptions
        tab={selectedTab}
        optionData={optionData}
        handleSelectOption={handleSelectOption}
        checkOptionSelected={checkOptionSelected}
      /> */}
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
const TagContainer = styled.div``;
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

const TabContainer = styled.div`
  display: flex;

  gap: 40px;
  margin-top: 15px;
  padding: 0px 128px;

  border-bottom: 1px solid;
  border-bottom-color: ${({ theme }) => theme.color.grey700};
`;

const Wrapper = styled.div`
  padding-top: 140px;
  height: 1070px;
`;

export default OptionPage;
