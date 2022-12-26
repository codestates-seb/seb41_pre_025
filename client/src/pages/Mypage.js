import React from "react";
import styled, { css } from "styled-components";
import { MdAccountCircle, MdEdit, MdDelete } from "react-icons/md";

export default function Mypage() {
  return (
    <MypageContainer>
      <InfoContainer>
        <Profile>
          <MdAccountCircle />
          <h1>wihyun</h1>
        </Profile>
        <SummaryContainer>
          <h2>Summary</h2>
          <SummaryContent>
            <Summary>
              <h1>2</h1>
              <h3>Total Votes</h3>
            </Summary>
            <Summary>
              <h1>0</h1>
              <h3>Questions</h3>
            </Summary>
            <Summary>
              <h1>1</h1>
              <h3>Answers</h3>
            </Summary>
          </SummaryContent>
        </SummaryContainer>
      </InfoContainer>
      <QuestionsContainer>
        <h1>Questions</h1>
        <ListBlock>
          <ItemBlock>
            <VoteBox done>1</VoteBox>
            <Text>배고파</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
          <ItemBlock>
            <VoteBox done>2</VoteBox>
            <Text>살려줘</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
          <ItemBlock>
            <VoteBox>5</VoteBox>
            <Text>넹</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
        </ListBlock>
      </QuestionsContainer>
      <AnswersContainer>
        <h1>Answers</h1>
        <ListBlock>
          <ItemBlock>
            <VoteBox done>1</VoteBox>
            <Text>배고파</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
          <ItemBlock>
            <VoteBox done>2</VoteBox>
            <Text>살려줘</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
          <ItemBlock>
            <VoteBox>5</VoteBox>
            <Text>넹</Text>
            <Edit>
              <MdEdit />
            </Edit>
            <Remove>
              <MdDelete />
            </Remove>
          </ItemBlock>
        </ListBlock>
      </AnswersContainer>
    </MypageContainer>
  );
}

const MypageContainer = styled.div`
  display: flex;
  width: 100%;
  height: 100vh;
  border: 1px solid black;
  flex: 1;
  flex-direction: column;
  padding: 0px 30px;
`;
const InfoContainer = styled.div`
  flex: 1;
  display: flex;
`;
const Profile = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0px 70px 0px 40px;
  svg {
    font-size: 200px;
  }
  h1 {
    margin: 0px;
  }
`;
const SummaryContainer = styled.div`
  flex: 1;
`;
const SummaryContent = styled.div`
  display: flex;
  justify-content: center;
  background-color: #f1f2f3;
  padding: 10px;
  border-radius: 10px;
`;
const Summary = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 70px 0px 70px;
  h1 {
    font-size: 100px;
    margin: 0px;
  }
`;
const QuestionsContainer = styled.div`
  flex: 1;
`;
const AnswersContainer = styled.div`
  flex: 1;
`;

const ListBlock = styled.div`
  flex: 1;
  border: 1px solid black;
  border-radius: 5px;
  & > div:nth-child(n + 2) {
    border-top: 1px solid;
  }
`;
const Edit = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 30px;
  cursor: pointer;
  &:hover {
    color: #38d9a9;
  }
  display: none;
`;

const Remove = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 30px;
  cursor: pointer;
  &:hover {
    color: #ff6b6b;
  }
  display: none;
`;

const ItemBlock = styled.div`
  display: flex;
  align-items: center;
  padding-top: 12px;
  padding-bottom: 12px;
  &:hover {
    ${Remove} {
      display: initial;
    }
    ${Edit} {
      display: initial;
    }
  }
`;

const VoteBox = styled.div`

  font-size: 21px;
  margin: 0px 10px;
  padding: 5px 20px;
  border: 1px solid black;
  background: #f1f2f3;
  ${(props) =>
    props.done &&
    css`
      background-color: #5eba7d;
      color: white;
    `}
`;
const Text = styled.div`
  flex: 1;
  font-size: 21px;
  color: #495057;
`;
