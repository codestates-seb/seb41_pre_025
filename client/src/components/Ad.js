import React from "react";
import styled from "styled-components";

export default function Ad() {
  return <>
    <AdContainer>
      Ad
    </AdContainer>
  </>
}

const AdContainer = styled.div`
    width: 300px;
    height: 100vh;
    border: 1px solid black;
  `;
