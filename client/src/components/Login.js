import React from "react";
import styled from "styled-components";
import Button from "./Button";
import { Link } from "react-router-dom";
import icon from "../image/icon.svg";

export default function Login() {
	return (
		<Content>
			<FormContainer>
				<Logo
					src={icon}
					alt="logo"
				/>
				<LabelTextInput
					id="email"
					text="Email"
					placeholder="Please enter your e-mail"
				/>
				<LabelTextInput
					id="password"
					text="Password"
					placeholder="Please enter your password"
				/>
				<Button
					text="Log In"
					color="white"
					border="1px solid #4393F7"
					bgColor="#4393F7"
					hoverColor="#2D75C6"
					activeColor="#1859A3"
					width="240.45px"
					margin="6px 0px"
				/>
			</FormContainer>
			<SignUpText>
				Don’t have an account?
				<Link to="/signup">Sign Up</Link>
			</SignUpText>
		</Content>
	);
}

const Content = styled.div`
	width: 100%;
	height: 100%;
	background-color: #f1f2f3;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`;

const FormContainer = styled.form`
	width: 288.445px;
	height: 570.594px;

	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;

	border: 1px solid black;
	background-color: #fff;
	border-radius: 7px;
`;

const Logo = styled.img`
	width: 70px;
	margin-bottom: 24px;
`;

const SignUpText = styled.span`
	width: 288.45px;
	height: 78px;
	padding: 16px;

	a {
		text-decoration: none;
		color: #2d75c6;
		margin-left: 5px;
	}
`;

const InputContainer = styled.div`
	width: 240.45px;
	margin: 6px 0;
`;

const Input = styled.input`
	width: 240.45px;
	height: 32.59px;

	padding: 7.8px 9.1px;
	border: 1px solid #bbbfc3;
	border-radius: 3px;
	outline: none;
`;

const Label = styled.label`
	display: block;
	margin: 6px 0;
	font-size: 16px;
	font-weight: bold;
`;

const LabelTextInput = ({
	id = "",
	text = "",
	placeholder = "",
	onChange = (e) => {},
}) => {
	return (
		<InputContainer>
			<Label htmlFor={id}>{text}</Label>
			<Input
				name={id}
				placeholder={placeholder}
				onChange={(e) => onChange(e)}
			/>
		</InputContainer>
	);
};
