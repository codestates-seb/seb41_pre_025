import React, { useState } from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import QuestionItem from "../components/QuestionItem";
import { Link } from "react-router-dom";

export default function QuestionsList({ questions }) {
	return (
		<>
			<Head>
				<h1>All Questions</h1>
				<Link to="/askquestions">
					<Button
						text="Ask Question"
						color="white"
						border="1px solid #4393F7"
						bgColor="#4393F7"
						hoverColor="#2D75C6"
						activeColor="#1859A3"
						width="103.02px"
					/>
				</Link>
			</Head>
			<QueCount>{questions.data.length} questions</QueCount>
			<FilterContainer>
				<Button
					text="Newest"
					color="#525960"
					border="1px solid #525960"
					bgColor="white"
					hoverColor=" hsl(210,8%,95%)"
					activeColor="none"
					width="103.02px"
					margin="0px"
					bdradius="0px"
				/>
				<Button
					text="Newest"
					color="#525960"
					border="1px solid #525960"
					bgColor="white"
					hoverColor=" hsl(210,8%,95%)"
					activeColor="none"
					width="103.02px"
					margin="0px"
					bdradius="0px"
				/>
				<Button
					text="Newest"
					color="#525960"
					border="1px solid #525960"
					bgColor="white"
					hoverColor=" hsl(210,8%,95%)"
					activeColor="none"
					width="103.02px"
					margin="0px"
					bdradius="0px"
				/>
				<Button
					text="Filter"
					marginLeft="0px"
				/>
			</FilterContainer>
			<hr />
			{/* 받아온 데이터 list 형식으로 나열 */}
			<QueContainer>
				<Question>
					{questions.data.map((questions) => {
						return (
							<QuestionItem
								questions={questions}
								key={questions.questionId}
							/>
						);
					})}
				</Question>
			</QueContainer>
			<hr />
		</>
	);
}

const Head = styled.div`
	padding: 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
`;
const QueCount = styled.div`
	padding-left: 20px;
`;

const FilterContainer = styled.div`
	display: flex;
	justify-content: end;
	align-items: center;
`;

const QueContainer = styled.ul`
	margin: 0;
	padding: 0;
`;

const Question = styled.li`
	display: flex;
	flex-direction: column;
	overflow: hidden;
	> .user {
		align-self: flex-end;
		font-size: 12px;
	}
`;
