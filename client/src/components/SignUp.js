import React from "react";
import styled from "styled-components";
import Button from "./Button";
import { Link } from "react-router-dom";

export default function Login() {
	return (
		<Content>
			<Logo />
			<FormContainer>
				<SignUpForm>
					<UsernameInput></UsernameInput>
					<PasswordInput></PasswordInput>
					<Button></Button>
				</SignUpForm>
			</FormContainer>
			<Span>
				Don’t have an account?
				<Link to="/signup">Sign up</Link>
			</Span>
		</Content>
	);
}

const Content = styled.div`
	width: 100vh;
	height: 100vh;
`;

const Logo = styled.img`
	width: 30px;
`;

const FormContainer = styled.div`
	width: 70px;
`;

const SignUpForm = styled.div`
	width: 70px;
`;

const Span = styled.span`
	width: 70px;
`;

const UsernameInput = styled.input`
	background-color: #000;
	border: 1px solid black;
	font-size: 16px;
	outline: none;
	border: none;
	background-repeat: no-repeat;
	background-size: 20px 20px;
	flex: 1;
`;

const PasswordInput = styled.input`
	color: #000;
	border: 1px solid black;
	font-size: 16px;
	outline: none;
	border: none;
	background-repeat: no-repeat;
	background-size: 20px 20px;
	flex: 1;
`;
