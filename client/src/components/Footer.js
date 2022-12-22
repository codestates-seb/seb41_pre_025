import React from "react";
import styled from "styled-components";

export default function Section() {
  return <>
    <FooterContainer>
        Footer
    </FooterContainer>
  </>
}

const FooterContainer = styled.div`
    width: 100wh;
    height: 300px;
    background-color: black;
    color: white;
  `;
