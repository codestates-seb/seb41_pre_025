import React from "react";
import styled from "styled-components";
import { Button } from "./Button";

export default function AskQuestion() {
	return (
		<>
			<Content>
				<h1>Ask a public question</h1>
				<HelpBubbleGoodQuestion>
					<h2>Writing a good question</h2>
					<p>
						You’re ready to ask a programming-related question and this form
						will help guide you through the process. Looking to ask a
						non-programming question? See the topics here to find a relevant
						site.
					</p>
					<h5>Steps</h5>
					<ul>
						<li>Summarize your problem in a one-line title.</li>
						<li>Describe your problem in more detail.</li>
						<li>Describe what you tried and what you expected to happen.</li>
						<li>
							Add “tags” which help surface your question to members of the
							community.
						</li>
						<li>Review your question and post it to the site.</li>
					</ul>
				</HelpBubbleGoodQuestion>
				<TitleInputContainer>
					<h5>Title</h5>
					<span>
						Be specific and imagine you’re asking a question to another person.
					</span>
					<LabelTextInput
						id="title"
						placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
					/>
					<Button
						text="Next"
						color="white"
						border="1px solid #4393F7"
						bgColor="#3F96F7"
						hoverColor="#2D75C6"
						activeColor="#1859A3"
					/>
				</TitleInputContainer>
				<ContextInputContainer>
					<h5>What are the details of your problem?</h5>
					<p>
						Introduce the problem and expand on what you put in the title.
						Minimum 20 characters.
					</p>
					<textarea></textarea>
					<Button
						text="Next"
						color="white"
						border="1px solid #4393F7"
						bgColor="#3F96F7"
						hoverColor="#2D75C6"
						activeColor="#1859A3"
					/>
				</ContextInputContainer>
			</Content>
		</>
	);
}

const Content = styled.div`
	width: 100%;
	height: 100%;
	background-color: #f1f2f3;
	display: flex;
	flex-direction: column;
`;

const HelpBubbleGoodQuestion = styled.section`
	width: 802.02px;
	padding: 24px;
	background-color: #edf4fa;
	display: flex;
	flex-direction: column;
	border: 1px solid #aeceea;
	border-radius: 3px;
	font-size: 15px;

	h2 {
		margin: 0;
		font-weight: 400;
		font-size: 21px;
	}

	h5 {
		font-size: 13px;
		font-weight: 600;
		margin: 0;
		margin-bottom: 8px;
	}

	p {
		margin: 8px 0 15px 0;
	}

	ul {
		padding: 0;
		margin: 0;
	}

	li {
		margin-left: 30px;
		list-style: inside;
		font-size: 13px;
	}
`;

const TitleInputContainer = styled.div`
	width: 802.02px;
	background-color: white;
	display: flex;
	flex-direction: column;
	border: 1px solid #d7d9dc;
	border-radius: 3px;
	font-size: 15px;
`;

const InputContainer = styled.div`
	width: 240.45px;
	margin: 6px 0;
`;

const Input = styled.input`
	width: 781.029px;
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
	placeholder = "",
	onChange = (e) => {},
}) => {
	return (
		<InputContainer>
			<Label htmlFor={id}></Label>
			<Input
				name={id}
				placeholder={placeholder}
				onChange={(e) => onChange(e)}
			/>
		</InputContainer>
	);
};

const ContextInputContainer = styled.div`
	width: 802.02px;
	background-color: white;
	display: flex;
	flex-direction: column;
	border: 1px solid #d7d9dc;
	border-radius: 3px;
	font-size: 15px;
`;
