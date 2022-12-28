import React from "react";
import styled, { keyframes } from "styled-components";

export default function Loading() {
  return (
    <SpinContainer>
      <Spin />
    </SpinContainer>
  );
}

const SpinContainer = styled.div`
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const spinner = keyframes`
    from {
    transform: rotate(0deg);
  } to {
    transform: rotate(360deg);
  }
`;

const Spin = styled.div`
  margin: auto;
  width: 60px;
  height: 60px;
  border: 20px solid lightgray;
  border-top: 20px solid gray;
  border-radius: 50%;
  animation: ${spinner} 1s linear infinite;
`;
