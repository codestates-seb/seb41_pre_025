import React from "react";
import styled from "styled-components";

const TemplateBlock = styled.div`
  width: 1264px;
  margin: 0 auto; /* 페이지 중앙에 나타나도록 설정 */
  background: white;
  display: flex;
  flex-direction: row;
`;

export default function Template({ children }) {
  return <TemplateBlock>{children}</TemplateBlock>;

}
