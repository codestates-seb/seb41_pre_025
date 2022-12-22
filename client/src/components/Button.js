import React from "react";
import styled from "styled-components";

const ButtonTemplate = styled.button`
	width: 59.46px;
	height: 33px;
	padding: 7px;
	margin-left: ${(props) => props.marginLeft || "4px"};
	color: ${(props) => props.color || "#487299"};
	background-color: ${(props) => props.bgColor || "#E3ECF3"};
	align-items: center;
	text-align: center;
	font-size: 12px;
	border-radius: 4px;
	border: ${(props) => props.border || "1px solid #83A6C4"};
	outline: none;
	cursor: pointer;
	box-shadow: inset 0px 1px white;
	&:hover {
		background-color: ${(props) => props.hoverColor || "#b9d3e8"};
	}
	&:active {
		background-color: ${(props) => props.activeColor || "#9ac8ed"};
	}
`;

export default function Button(props) {
	const { text, marginLeft, color, border, bgColor, hoverColor, activeColor } =
		props;
	return (
		<ButtonTemplate
			marginLeft={marginLeft}
			color={color}
			bgColor={bgColor}
			border={border}
			hoverColor={hoverColor}
			activeColor={activeColor}>
			{text}
		</ButtonTemplate>
	);
}
