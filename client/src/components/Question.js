import React, { useState } from 'react';
import styled from 'styled-components';
import { Button } from './Button';
import { GoTriangleUp, GoTriangleDown } from 'react-icons/go';

export default function QuestionsList() {
  const [voteCount, setVoteCount] = useState(0);

  return (
    <>
      <Head>
        <h1>질문 제목</h1>
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
      <MainContainer>
        <VoteContainer>
          <GoTriangleUp className="triangle" onClick={() => setVoteCount(voteCount + 1)} />
          <div>{voteCount}</div>
          <GoTriangleDown className="triangle" onClick={() => setVoteCount(voteCount - 1)} />
        </VoteContainer>
        <Maintext>질문 내용</Maintext>
      </MainContainer>
      <AnswerContainer>
        Your Answer
        <textarea />
        <Button
          text="Post Your Answer"
          color="white"
          border="1px solid #4393F7"
          bgColor="#4393F7"
          hoverColor="#2D75C6"
          activeColor="#1859A3"
          width="119.77px"
          height="54.77px"
        />
      </AnswerContainer>
    </>
  );
}

const Head = styled.div`
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const MainContainer = styled.div`
  display: flex;
  justify-content: row;
`;

const VoteContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: center;
  align-items: flex-start;
  color: #6a737c;
  cursor: pointer;

  div {
    font-size: 25px;
    /* 추후 수정사항 : text 가운데 정렬 불가 (일단 padding 사용)*/
    padding-left: 10px;
  }

  .triangle {
    font-size: 40px;
  }
`;

const Maintext = styled.article`
  background-color: red;
`;

const AnswerContainer = styled.div`
  display: flex;
  flex-direction: column;

  textarea {
    height: 200px;
  }
`;
