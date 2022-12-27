import React from "react";
import styled from "styled-components";
import adhyungs25 from "../image/ad_hyunGS25.png";
import adgs25 from "../image/ad_GS25.jpeg";
import adcambridge from "../image/ad_cambridge.png";
import adadobe from "../image/ad_adobe.png";

export default function Ad() {
  return (
    <>
      <AdContainer>
        <img src={adhyungs25} alt="Ad" />
        <img src={adgs25} alt="Ad" />
        <img src={adcambridge} alt="Ad" />
        <img src={adadobe} alt="Ad" />
      </AdContainer>
    </>
  );
}

const AdContainer = styled.div`
  width: 300px;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;

  img {
    width: 250px;
    margin-bottom: 10px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  }
  @media all and (max-width: 1264px) {
    display: none;
  }
`;
