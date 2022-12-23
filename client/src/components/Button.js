import React from "react";
import styled from "styled-components";

const ButtonTemplate = styled.button`
  width: ${(props) => props.width || "59.46px"};
  height: 33px;
  padding: 7px;
  margin: ${(props) => props.margin || "0 0 0 4px"};
  color: ${(props) => props.color || "#487299"};
  background-color: ${(props) => props.bgColor || "#E3ECF3"};
  align-items: center;
  text-align: center;
  font-size: 12px;
  border-radius: 4px;
  border: ${(props) => props.border || "1px solid #83A6C4"};
  outline: none;
  cursor: pointer;
  box-shadow: ${(props) => props.boxshadow || "inset 0px 1px white"};
  &:hover {
    background-color: ${(props) => props.hoverColor || "#b9d3e8"};
  }
  &:active {
    background-color: ${(props) => props.activeColor || "#9ac8ed"};
  }
`;

export function Button(props) {
  const { text, width, margin, color, border, bgColor, hoverColor, activeColor, boxshadow } = props;
  return (
    <ButtonTemplate type="button" margin={margin} color={color} bgColor={bgColor} border={border} hoverColor={hoverColor} activeColor={activeColor} width={width} boxshadow={boxshadow}>
      {text}
    </ButtonTemplate>
  );
}
