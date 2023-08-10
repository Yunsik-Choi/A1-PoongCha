import React from "react";
import BackgroundOverlay from "./BackgroundOverlay";
import { styled } from "styled-components";

const OverlaidPopup = ({ component }) => {
  return (
    <PopupWrapper>
      <BackgroundOverlay />
      {component}
    </PopupWrapper>
  );
};
const PopupWrapper = styled.div`
  display: flex;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;

  width: 100%;
  z-index: 12;
  margin-top: 120px;
`;
export default OverlaidPopup;
