import React from "react";
import styled from "styled-components";
import { Button, Social } from "./Button";
import { Link } from "react-router-dom";
import icon from "../image/icon.svg";
import { FcGoogle } from "react-icons/fc";
import { VscGithub } from "react-icons/vsc";
import { AiFillFacebook } from "react-icons/ai";

export default function Login() {
	return (
		<Content>
			<Logo
				src={icon}
				alt="logo"
			/>
			<Button
				text="Log In with Google"
				color="black"
				border="1px solid #bbbfc3"
				bgColor="white"
				hoverColor="hsl(210,8%,97.5%)"
				activeColor="hsl(210,8%,97.5%)"
				width="288.445px"
				margin="6px 0px"
				boxshadow="none"
			/>
			<Button
				text="Log In with GitHub"
				color="white"
				border="none"
				bgColor="black"
				hoverColor="none"
				activeColor="hsl(210,8%,97.5%)"
				width="288.445px"
				margin="6px 0px"
				boxshadow="none"
			/>
			<Button
				text="Log In with FaceBook"
				color="white"
				border="none"
				bgColor="#385499"
				hoverColor="#314a86"
				activeColor="#2a4074"
				width="288.445px"
				margin="6px 0px"
				boxshadow="none"></Button>
			<FormContainer>
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
	height: 234.2px;

	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;

	border: none;
	background-color: #fff;
	border-radius: 7px;
	box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
		0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
	margin: 24px 0px 24px 0px;
`;

const Logo = styled.img`
	width: 30px;
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
