import { useState, useEffect } from "react";
import styled from "styled-components";
import { Button, Social } from "../components/Button";
import { Link } from "react-router-dom";
import icon from "../image/icon.svg";
import { FcGoogle } from "react-icons/fc";
import { VscGithub } from "react-icons/vsc";
import { AiFillFacebook } from "react-icons/ai";
import { useSetRecoilState } from "recoil";
import { loginState } from "../state/atom";
import { useNavigate } from "react-router-dom";
import { fetchLogin, fetchMemberInfo } from "../util/fetchLogin";

import { userInfoState } from "../state/atom";
import { useRecoilState } from "recoil";

export default function Login() {
	const navigate = useNavigate();
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const setIsLogin = useSetRecoilState(loginState);
	const [userInfo, setuserInfo] = useRecoilState(userInfoState);

	function emailHandler(e) {
		setEmail(e.target.value);
	}
	function passwordHandler(e) {
		setPassword(e.target.value);
	}

	const login = async () => {
		const loginData = JSON.stringify({
			email: email, //test1@gmail.com
			password: password, //password
		});
		await fetchLogin(loginData).then((data) => {
			if (data.status === 200) {
				setIsLogin(true);
				fetchMemberInfo()
					.then((data) => {
						setuserInfo(data.data);
					})
					.catch((err) => {
						console.log(err.message);
					});
				navigate("/");
			}
		});
	};

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
					onChange={emailHandler}
					id="email"
					text="Email"
					placeholder="Please enter your e-mail"
				/>
				<LabelTextInput
					onChange={passwordHandler}
					id="password"
					text="Password"
					placeholder="Please enter your password"
				/>
				<Button
					onClick={login}
					type="button"
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
	height: 100vh;
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
				onChange={onChange}
			/>
		</InputContainer>
	);
};
