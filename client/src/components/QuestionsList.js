import React from 'react';
import styled from 'styled-components';
import { Button } from './Button';

export default function QuestionsList() {
  return (
    <>
      <Head>
        <h1>All Questions</h1>
        <Button
          text="Ask Question"
          color="white"
          border="1px solid #4393F7"
          bgColor="#4393F7"
          hoverColor="#2D75C6"
          activeColor="#1859A3"
          width="103.02px"
        />
      </Head>
      <FilterContainer>
        23,347,700 questions
        <Button
          text="Newest"
          color="#525960"
          border="1px solid #525960"
          bgColor="E3E628"
          hoverColor=" hsl(210,8%,95%)"
          activeColor="#1859A3"
          width="103.02px"
          margin="0px"
        />
        <Button
          text="Newest"
          color="#525960"
          border="1px solid #525960"
          bgColor="white"
          hoverColor=" hsl(210,8%,95%)"
          activeColor="#1859A3"
          width="103.02px"
          margin="0px"
        />
        <Button
          text="Newest"
          color="#525960"
          border="1px solid #525960"
          bgColor="white"
          hoverColor=" hsl(210,8%,95%)"
          activeColor="#1859A3"
          width="103.02px"
          margin="0px"
        />
        <Button text="Filter" marginLeft="0px" />
      </FilterContainer>
      <QueContainer />
    </>
  );
}

const Head = styled.div`
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const FilterContainer = styled.div``;

const QueContainer = styled.ul``;
