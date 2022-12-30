import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import moment from "moment-timezone";
import { MdAccountCircle } from "react-icons/md";

export default function QuestionItem({ questions }) {
  console.log(questions);
  return (
    <>
      <ContentContainer>
        <Summary>
          <Vote>{questions.voteResult} votes</Vote>
          <Answer>{questions.answerCount} answers</Answer>
          <View>10 views</View>
        </Summary>
        <ContentBox>
          <Link to={`/questionDetail/${questions.questionId}`}>
            <h3>{questions.title}</h3>
          </Link>
          <ContentText>{questions.content}</ContentText>
          <ContentInfo>
            <TagBox>
              {questions.tag.map((tag) => {
                return <Tag key={questions.questionId}>{tag}</Tag>;
              })}
            </TagBox>
            <DisplayName>
              <MdAccountCircle />
              <div>
                <span>{questions.displayName}</span>
                <span>{moment(questions.createdAt).add(9, "hours").fromNow()}</span>
              </div>
            </DisplayName>
          </ContentInfo>
        </ContentBox>
      </ContentContainer>
    </>
  );
}

const Summary = styled.div`
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: flex-end;
  justify-content: space-evenly;

  margin: 0 16px 4px 0;
  width: 108px;
`;

const Vote = styled.div`
  padding-right: 10px;
  font-size: 13px;
`;
const Answer = styled.div`
  padding-right: 10px;
  color: #6c737b;
  font-size: 13px;
`;
const View = styled.div`
  padding-right: 10px;
  color: #6c737b;
  font-size: 13px;
`;

const ContentContainer = styled.div`
  &:not(:last-child) {
    border-bottom: 1px solid #d7d9dc;
  }
  display: flex;
  flex-direction: row;
  padding: 16px;
`;

const ContentBox = styled.div`
  flex: 1;

  h3 {
    margin: 0 0 10px 0;
    padding-right: 24px;
    color: #3f96f7;
    font-size: 17px;
  }

  a {
    text-decoration: none;
  }
`;

const ContentText = styled.p`
  margin: 0 0 8px 0;
  width: 100%;
  height: 2.6em;
  overflow: hidden;
  /* 말줄임 적용 안됨 */
  text-overflow: ellipsis;
  font-size: 13px;
  display: -webkit-box;
  -webkit-line-clamp: 2; /* 라인수 */
  -webkit-box-orient: vertical;
  word-wrap: break-word;
  line-height: 1.2em;
`;

const ContentInfo = styled.div`
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
`;

const TagBox = styled.div`
  display: flex;
`;

const Tag = styled.div`
  background-color: #e1ecf4;
  border-radius: 3px;
  padding: 5px;
  margin: 5px;
  color: #39739d;
  font-size: 12px;
`;

const Time = styled.div`
  display: flex;
  align-items: center;
  font-size: 12px;
  color: #6c737b;
`;

const Text = styled.p`
  display: flex;
  align-items: center;
  font-size: 12px;
  color: #6c737b;
  margin: 0 3px;
`;

const DisplayName = styled.p`
  display: flex;
  align-items: center;
  font-size: 12px;

  svg {
    margin: 2px;
    font-size: 15px;
  }

  div {
    color: #6c737b;
    font-size: 12px;
  }

  span {
    margin: 0 3px;
  }
`;
