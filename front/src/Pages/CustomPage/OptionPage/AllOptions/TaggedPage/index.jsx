import React, { useEffect, useState } from "react";
import { css, styled } from "styled-components";
import OptionTooltip from "./OptionTooltip";
import OptionItem from "@Components/Custom/OptionItem";
import TaggedPageSampleImg from "@assets/images/tagged-page-sample.svg";
import PlusIcon from "@assets/icons/plus.svg";

const TaggedPage = ({
  tag,
  handleOpenPopup,
  handleSelectOption,
  optionData,
  checkOptionSelected,
}) => {
  const [activeOption, setActiveOption] = useState(null);
  const handlePlusBtnClick = (option) => {
    if (activeOption === null || activeOption !== option) {
      setActiveOption(option);
    } else {
      setActiveOption(null);
    }
  };
  return (
    <Wrapper>
      <SituationScreen>
        {activeOption !== null &&
          optionData.map((data) => data.option).includes(activeOption) && (
            <OptionTooltip
              tag={tag}
              data={optionData.find((elem) => elem.option === activeOption)}
              handleOpenPopup={handleOpenPopup}
            />
          )}
        <img src={TaggedPageSampleImg} />
        {optionData.map((data, index) => (
          <PlusButton
            key={index}
            $position={data.position}
            $clicked={activeOption === data.option}
            onClick={() => handlePlusBtnClick(data.option)}
          >
            <img src={PlusIcon} />
          </PlusButton>
        ))}
      </SituationScreen>
      <OptionItemsContainer>
        {optionData.map((data, index) => (
          <OptionItem
            key={index}
            data={data}
            selected={checkOptionSelected(data.option)}
            handleOpenPopup={handleOpenPopup}
            handleSelectOption={handleSelectOption}
          />
        ))}
      </OptionItemsContainer>
      <AdditionalComment>
        *상기 이미지는 이해를 돕기 위한 이미지로 실제 옵션 사진은 상세보기에서
        확인해주세요.
      </AdditionalComment>
    </Wrapper>
  );
};

const AdditionalComment = styled.div`
  ${({ theme }) => theme.font.Caption1_Regular};
  color: ${({ theme }) => theme.color.grey500};
  margin: 36px 0px;
`;
const PlusButton = styled.div`
  position: absolute;
  top: ${({ $position }) => $position.y}%;
  left: ${({ $position }) => $position.x}%;

  display: flex;
  align-items: center;
  justify-content: center;

  width: 28px;
  height: 28px;

  background-color: #acb8c8;

  border-radius: 55px;
  opacity: 0.8;

  &:hover {
    background-color: ${({ theme }) => theme.color.secondary};
    cursor: pointer;
  }
  img {
    width: 18px;
    height: 18px;
  }
  ${({ $clicked }) =>
    $clicked &&
    css`
      background-color: ${({ theme }) => theme.color.secondary};
    `}
`;
const SituationScreen = styled.div`
  position: relative;
  & > img {
    width: 100%;
  }
`;
const OptionItemsContainer = styled.div`
  display: flex;
  gap: 16px;
  width: 100%;
  margin-top: 40px;
  & > div {
    width: 244px;
    height: 314px;
  }
`;
const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 24px;
  width: 100%;
`;
export default TaggedPage;
