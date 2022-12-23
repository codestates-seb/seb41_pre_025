import React from "react";
import styled from "styled-components";
import { Button } from "./Button";
import { Link } from "react-router-dom";
import { RiQuestionnaireLine } from "react-icons/ri";
import { HiOutlineChevronUpDown } from "react-icons/hi2";
import { AiFillTags } from "react-icons/ai";
import { GiAchievement } from "react-icons/gi";
import { useState } from "react";

export default function Login() {
	const initialTags = ["Javascript"];

	const [tags, setTags] = useState(initialTags);

	const removeTags = (indexToRemove) => {
		setTags(tags.filter((_, index) => index !== indexToRemove));
	};

	const addTags = (event) => {
		const filtered = tags.filter((el) => el === event.target.value);
		if (event.target.value !== "" && filtered.length === 0) {
			setTags([...tags, event.target.value]);
			event.target.value = "";
		}
	};

	return (
		<Content>
			<TextContent>
				<h1>Join the Stack Overflow community</h1>
				<InnerTextContainer>
					<RiQuestionnaireLine />
					Get unstuck — ask a question
				</InnerTextContainer>
				<InnerTextContainer>
					<HiOutlineChevronUpDown />
					Unlock new privileges like voting and commenting
				</InnerTextContainer>
				<InnerTextContainer>
					<AiFillTags />
					Save your favorite tags, filters, and jobs
				</InnerTextContainer>
				<InnerTextContainer>
					<GiAchievement />
					Earn reputation and badges
				</InnerTextContainer>
				<div>
					<span>
						Collaborate and share knowledge with a private group for FREE.
					</span>
					<span className="blueText">
						<br />
						Get Stack Overflow for Teams free for up to 50 users.
					</span>
				</div>
			</TextContent>

			<FormAndSignUpContainer>
				<Button
					text="Sign up with Google"
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
					text="Sign up with GitHub"
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
					text="Sign up with FaceBook"
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
						id="displayname"
						text="Display Name"
						placeholder="Please enter your display name"
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
					<TagsContainer>
						<span className="tagLabel">Tags</span>
						<TagsInput>
							<ul id="tags">
								{tags.map((tag, index) => (
									<li
										key={index}
										className="tag">
										<span className="tag-title">{tag}</span>
										<span
											className="tag-close-icon"
											onClick={() => removeTags(index)}>
											x
										</span>
									</li>
								))}
							</ul>
							<input
								className="tag-input"
								type="text"
								onKeyUp={(event) => {
									if (event.key === "Enter") {
										addTags(event);
									}
								}}
								placeholder="Press enter to add tags"
							/>
						</TagsInput>
					</TagsContainer>

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
					Already have an account?
					<Link to="/login">Log in</Link>
				</SignUpText>
			</FormAndSignUpContainer>
		</Content>
	);
}

const Content = styled.div`
	width: 100%;
	height: 100%;
	background-color: #f1f2f3;
	display: flex;
	align-items: center;
	justify-content: center;
`;

const TextContent = styled.div`
	width: 410.879px;
	height: 284.971px;
	margin-right: 48px;
	margin-bottom: 128px;
	display: flex;
	flex-direction: column;
	justify-content: center;

	svg {
		color: #4393f7;
		font-size: 26px;
		margin-right: 8px;
	}

	h1 {
		margin-top: 0;
		margin-bottom: 32px;
		font-size: 27px;
		font-weight: 400;
	}

	span {
		font-size: 13px;
	}

	.blueText {
		color: #3172c6;
	}
`;

const InnerTextContainer = styled.div`
	width: 410.879px;
	height: 24.004px;
	margin-bottom: 24px;
	display: flex;
	align-items: center;
	font-size: 15px;
`;

const FormAndSignUpContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`;

const FormContainer = styled.form`
	width: 288.445px;
	height: 460px;
	margin-top: 16px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);

	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;

	background-color: #fff;
	border-radius: 7px;
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

	&:focus-within {
		border: 1px solid blue;
	}
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

const TagsContainer = styled.div`
	.tagLabel {
		display: block;
		margin: 6px 0;
		font-size: 16px;
		font-weight: bold;
	}
`;

const TagsInput = styled.div`
	width: 240.45px;
	min-height: 48px;
	margin: 6px 0;
	display: flex;
	align-items: flex-start;
	flex-wrap: wrap;
	padding: 0 8px;
	border: 1px solid rgb(214, 216, 218);
	border-radius: 6px;

	> ul {
		display: flex;
		flex-wrap: wrap;
		padding: 0;
		margin: 8px 0 0 0;

		> .tag {
			border: 1px solid black;
			width: auto;
			height: 32px;
			display: flex;
			align-items: center;
			justify-content: center;
			color: black;
			padding: 0 8px;
			font-size: 14px;
			list-style: none;
			border-radius: 6px;
			margin: 0 8px 8px 0;
			background: var(--coz-purple-600);
			> .tag-close-icon {
				display: block;
				width: 16px;
				height: 16px;
				line-height: 16px;
				text-align: center;
				font-size: 14px;
				margin-left: 8px;
				color: var(--coz-purple-600);
				border-radius: 50%;
				background: #fff;
				cursor: pointer;
			}
		}
	}

	> input {
		flex: 1;
		border: none;
		height: 46px;
		font-size: 14px;
		padding: 4px 0 0 0;
		:focus {
			outline: transparent;
		}
	}

	&:focus-within {
		border: 1px solid blue;
	}
`;
