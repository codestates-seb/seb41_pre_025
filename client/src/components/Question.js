import React, { useState } from 'react';
import styled from 'styled-components';
import { Button } from './Button';
import { GoTriangleUp, GoTriangleDown } from 'react-icons/go';
import { BsBookmark } from 'react-icons/bs';
import { GiBackwardTime } from 'react-icons/gi';
import { MdAccountCircle } from 'react-icons/md';

export default function QuestionsList() {
  const [voteCount, setVoteCount] = useState(0);

  return (
    <Contents>
      <Head>
        <Title>
          <h1>질문 제목</h1>
          <Button
            text="Ask Question"
            color="white"
            border="1px solid #4393F7"
            bgColor="#4393F7"
            hoverColor="#2D75C6"
            activeColor="#1859A3"
            width="103.02px"
            boxshadow="inset 0px 1px hsl(206,90%,69.5%)"
          />
        </Title>
        <Info>Asked today Modified today Viewed 13 times</Info>
        <hr />
      </Head>
      <MainContainer>
        <VoteContainer>
          <GoTriangleUp className="triangle" onClick={() => setVoteCount(voteCount + 1)} />
          <div>{voteCount}</div>
          <GoTriangleDown className="triangle" onClick={() => setVoteCount(voteCount - 1)} />
          <BsBookmark className="icon" />
          <GiBackwardTime className="icon" />
        </VoteContainer>
        <Maintext>
          no Creators, like default constructor, exist): abstract types either need to be mapped to
          concrete types, have custom deserializer, or contain a dditional type information
        </Maintext>
      </MainContainer>
      <SubContainer>
        <TagBox>
          <Tag>JavaScript</Tag>
          <Tag>HTML</Tag>
          <Tag>CSS</Tag>
        </TagBox>
        <UserInfo>
          asked 59 mins ago
          <Name>
            <MdAccountCircle />
            RiOAOn
          </Name>
        </UserInfo>
      </SubContainer>
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
          boxshadow="inset 0px 1px hsl(206,90%,69.5%)"
        />
      </AnswerContainer>
    </Contents>
  );
}
const Contents = styled.div`
  padding: 20px;
`;
const Head = styled.div``;
const Title = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Info = styled.div`
  padding-bottom: 10px;
`;

const MainContainer = styled.div`
  display: flex;
  justify-content: row;
  padding: 20px 0px;
`;

const VoteContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: center;
  align-items: flex-start;
  color: #babfc4;
  cursor: pointer;

  > div {
    font-size: 20px;
    /* 추후 수정사항 : text 가운데 정렬 불가 (일단 padding 사용)*/
    padding: 5px 13px;
    color: #6a737c;
  }

  .triangle {
    font-size: 40px;
  }

  > .icon {
    font-size: 20px;
    margin: 9px;
  }
`;

const Maintext = styled.article`
  background-color: red;
  padding-left: 10px;
`;
const SubContainer = styled.div`
  display: flex;
  flex-direction: column;
`;
const TagBox = styled.div`
  display: flex;
  padding-left: 40px;
`;
const Tag = styled.div`
  background-color: #e1ecf4;
  border-radius: 3px;
  padding: 5px;
  margin: 5px;
  color: #39739d;
  font-size: 12px;
`;
const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  padding: 10px;
  font-size: 12px;
  background-color: #e1ecf4;
  border-radius: 3px;
  width: 200px;
  height: 70px;
  align-self: end;
`;
const Name = styled.div`
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #0074cc;
  font-weight: bold;

  > svg {
    color: black;
    margin: 5px 10px 0px 0px;
    font-size: 35px;
  }
`;

const AnswerContainer = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 20px;

  textarea {
    margin: 20px 5px;
    height: 200px;
  }
`;
