import React from "react";
import { styled } from "styled-components";
import arrowUp from "@assets/icons/arrow_up.svg";
import { useUserData } from "context/UserDataContext";

const DropDonw = ({ popupRef, closePopup }) => {
  const { totalData, 유저데이터저장 } = useUserData();

  const { name: engin } = totalData.엔진;
  const { name: body } = totalData.바디;
  const { name: drive } = totalData.구동방식;
  const { name: exterior } = totalData.외장;
  const { name: interior } = totalData.내장;

  return (
    <Wrapper ref={popupRef}>
      <MainContainer>
        <ItemWrapper>
          <TrimInfo>
            <span>{engin}</span>
            <span>43,460,00원</span>
          </TrimInfo>
          <TrimInfo>
            <span>{body}</span>
          </TrimInfo>
          <TrimInfo>
            <span>{drive}</span>
          </TrimInfo>
        </ItemWrapper>
        <ItemWrapper>
          <ColorInfo>
            <span>{exterior}</span>
          </ColorInfo>
          <ColorInfo>
            <span>{interior}</span>
          </ColorInfo>
        </ItemWrapper>
        <ItemWrapper>
          <OptionInfo>
            <span>
              크리미 화이트 tksksks크리미 화이트 tksksks크리미 화이트 tksksks
            </span>
            <span>43,460,00원</span>
          </OptionInfo>
          <OptionInfo>
            <span>
              크리미 화이트 tksksks크리미 화이트 tksksks크리미 화이트 tksksks
            </span>
            <span>43,460,00원</span>
          </OptionInfo>
          <OptionInfo>
            <span>
              크리미 화이트 tksksks크리미 화이트 tksksks크리미 화이트 tksksks
            </span>
            <span>43,460,00원</span>
          </OptionInfo>
          <OptionInfo>
            <span>
              크리미 화이트 tksksks크리미 화이트 tksksks크리미 화이트 tksksks
            </span>
            <span>43,460,00원</span>
          </OptionInfo>
          <OptionInfo>
            <span>인조가죽(블랙)</span>
            <span>00원</span>
          </OptionInfo>
          <OptionInfo>
            <span>인조가죽(블랙)</span>
            <span>00원</span>
          </OptionInfo>
        </ItemWrapper>
      </MainContainer>
      <FooterContainer>
        <img src={arrowUp} onClick={closePopup} />
        <span>51,460,000원</span>
      </FooterContainer>
    </Wrapper>
  );
};
const FooterContainer = styled.div`
  height: 68px;
  width: 100%;

  display: flex;
  justify-content: flex-end;
  align-items: center;

  gap: 4px;

  span {
    color: ${({ theme }) => theme.color.grey50};
    ${({ theme }) => theme.font.Extra14};
  }
  img {
    cursor: pointer;
  }
`;

const OptionInfo = styled.div`
  display: flex;
  justify-content: space-between;

  width: 490px;

  gap: 16px;
  margin-bottom: 7px;
  margin-left: 13px;
  margin-right: 15px;

  span:nth-child(1) {
    color: ${({ theme }) => theme.color.grey300};
    ${({ theme }) => theme.font.Body4_Regular};
    width: 360px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  span:nth-child(2) {
    color: ${({ theme }) => theme.color.grey100};
    ${({ theme }) => theme.font.Body4_Medium};
  }
`;

const ColorInfo = styled.div`
  display: flex;
  justify-content: space-between;

  gap: 16px;
  margin-bottom: 7px;
  margin-left: 13px;
  margin-right: 15px;

  span:nth-child(1) {
    color: ${({ theme }) => theme.color.grey300};
    ${({ theme }) => theme.font.Body4_Regular};
    width: 90px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  span:nth-child(2) {
    color: ${({ theme }) => theme.color.grey100};
    ${({ theme }) => theme.font.Body4_Medium};
  }
`;

const TrimInfo = styled.div`
  display: flex;
  justify-content: space-between;

  gap: 16px;
  margin-bottom: 7px;
  margin-left: 13px;
  margin-right: 15px;

  span:nth-child(1) {
    color: ${({ theme }) => theme.color.grey300};
    ${({ theme }) => theme.font.Body4_Regular};
  }
  span:nth-child(2) {
    color: ${({ theme }) => theme.color.grey100};
    ${({ theme }) => theme.font.Body4_Medium};
  }
`;

const ItemWrapper = styled.div`
  height: 100%;

  border-right: 1px solid;
  border-right-color: ${({ theme }) => theme.color.grey700};

  div:nth-child(1) {
    margin-top: 20px;
  }
`;

const Wrapper = styled.div`
  height: 252px;

  padding: 0 128px;

  ::-webkit-scrollbar {
    width: 4px;
  }

  ::-webkit-scrollbar-thumb {
    background-color: ${({ theme }) => theme.color.grey700};
    border-radius: 4px;
  }

  ::-webkit-scrollbar-track {
    background-color: transparent;
  }
`;

const MainContainer = styled.div`
  display: flex;

  height: 174px;
  width: 100%;

  border-top: 1px solid;
  border-top-color: ${({ theme }) => theme.color.grey700};
  border-bottom: 1px solid;
  border-bottom-color: ${({ theme }) => theme.color.grey700};

  overflow: auto;

  div:nth-child(3) {
    flex-grow: 1;
  }
`;
export default DropDonw;
