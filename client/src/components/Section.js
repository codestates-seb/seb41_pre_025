import React from "react";
import styled from "styled-components";

export default function Section({ children }) {
  return (
    <>
      <SectionContainer>{children}</SectionContainer>
    </>
  );
}

const SectionContainer = styled.div`
  flex: 1;
`;
