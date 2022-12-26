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
      <QueCount>23,435 questions</QueCount>
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
        <Button text="Filter" marginLeft="0px" />
      </FilterContainer>
      <hr />
      {/* 받아온 데이터 list 형식으로 나열 */}
      <QueContainer>
        <Question>
          <Summary>
            <Vote>1 votes</Vote>
            <Answer>2 answers</Answer>
            <View>10 views</View>
          </Summary>
          <Content>
            <h3>Spring Abstract Class No Creators Deserializer issue</h3>
            <p>
              There are many variations of passages of Lorem Ipsum available, but the majority have
              suffered alteration in some form, by injected humour, or randomised words which don't
              look even slightly believable. If you are going to use a passage of Lorem Ipsum, you
              need to be sure there isn't anything embarrassing hidden in the middle of text. All
              the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as
              necessary, making this the first true generator on the Internet. It uses a dictionary
              of over 200 Latin words, combined with a handful of model sentence structures, to
              generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore
              always free from repetition, injected humour, or non-characteristic words etc.
            </p>
            <TagBox>
              <Tag>JavaScript</Tag>
              <Tag>HTML</Tag>
              <Tag>CSS</Tag>
            </TagBox>
          </Content>
          <div className="user">Mohamed Sameem 7asked 53 secs ago</div>
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

const QueContainer = styled.ul``;

const Question = styled.li`
  display: flex;
  flex-direction: column;
  overflow: hidden;
  > .user {
    align-self: flex-end;
    font-size: 12px;
  }
`;
const Summary = styled.div`
  display: flex;
`;
const Vote = styled.div`
  padding-right: 10px;
`;
const Answer = styled.div`
  padding-right: 10px;
`;
const View = styled.div``;

const Content = styled.div`
  > p {
    background-color: pink;
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
  }
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
