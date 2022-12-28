import React from "react";
import { useEffect, useState } from "react";
import styled, { css } from "styled-components";
import { MdEdit, MdDelete } from "react-icons/md";
import hyunGS25 from "../image/ad_hyunGS25.png";

import { fetchMemberInfo, checkLogin } from "../util/fetchLogin";

import { userInfoState } from "../state/atom";
import { useRecoilState } from "recoil";

export default function Mypage() {
  const [userInfo, setuserInfo] = useRecoilState(userInfoState);

  useEffect(() => {
    checkLogin();
    fetchMemberInfo()
      .then((data) => {
        setuserInfo(data.data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  console.log(userInfo);

  return (
    <MypageContainer>
      <InfoContainer>
        <Profile>
          <ProfilePic src={hyunGS25}></ProfilePic>
          <h2>HYUNGS25</h2>
        </Profile>
        <SummaryContainer>
          <h3>Summary</h3>
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
  flex: 1;
  flex-direction: column;
  padding: 30px 50px;
`;

const InfoContainer = styled.div`
  flex: 1;
  display: flex;
`;

const Profile = styled.div`
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0px 70px 0px 40px;

  h2 {
    margin: 15px;
  }
`;

const ProfilePic = styled.img`
  border: 1px solid #bbbfc3;
  border-radius: 12px;
  width: 128px;
`;

const SummaryContainer = styled.div``;

const SummaryContent = styled.div`
  display: flex;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #bbbfc3;
  background-color: #f8f8f8;
`;

const Summary = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 70px 0px 70px;
  h1 {
    font-size: 50px;
    margin: 0px;
  }

  h3 {
    font-size: 10px;
  }

  &:not(:last-child) {
    border-right: 1px solid #bbbfc3;
  }
`;
const QuestionsContainer = styled.div`
  flex: 1;

  h1 {
    font-size: 20px;
  }
`;
const AnswersContainer = styled.div`
  flex: 1;
  margin-top: 20px;

  h1 {
    font-size: 20px;
  }
`;

const ListBlock = styled.div`
  flex: 1;
  border: 1px solid #bbbfc3;
  border-radius: 5px;

  & > div:nth-child(n + 2) {
    border-top: 1px solid #bbbfc3;
  }
`;
const Edit = styled.div`
  margin-right: 10px;
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
  margin-right: 10px;
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
      display: flex;
      align-items: center;
      justify-content: center;
    }
    ${Edit} {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
`;

const VoteBox = styled.div`
  font-size: 21px;
  margin: 0px 10px;
  padding: 5px 20px;
  border: 1px solid #838383;
  border-radius: 5px;
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
