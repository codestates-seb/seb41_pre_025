import React from "react";
import styled from "styled-components";

const TemplateContainer = styled.div`
	display: flex;
	align-items: center;
	justify-content: center;
`;

const TemplateBlock = styled.div`
	display: flex;
	width: 1264px;
	background: white;
	flex-direction: row;

	@media all and (max-width: 1264px) {
		width: 100%;
	}
`;

export default function Template({ children }) {
	return (
		<TemplateContainer>
			<TemplateBlock>{children}</TemplateBlock>
		</TemplateContainer>
	);
}
