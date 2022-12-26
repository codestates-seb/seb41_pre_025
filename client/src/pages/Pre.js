import React from "react";
import styled from "styled-components";
import underconstruction from "../image/underconstruction.svg";

export default function Pre() {
	return (
		<>
			<Container>
				<Content>
					<UnderConstruction src={underconstruction}></UnderConstruction>
					<h2>This page is under construction</h2>
					<p>Oops, Sorry! We're working on it!</p>
				</Content>
			</Container>
		</>
	);
}

const Container = styled.div`
	display: flex;
	align-items: center;
	justify-content: center;
`;

const Content = styled.div`
	margin: 100px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`;

const UnderConstruction = styled.img`
	width: 300px;
`;
