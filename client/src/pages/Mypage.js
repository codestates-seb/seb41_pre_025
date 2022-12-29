import React from 'react';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import hyunGS25 from '../image/ad_hyunGS25.png';

import { checkLogin } from '../util/fetchLogin';

import { userInfoState, questionListState, answerListState } from '../state/atom';
import { useRecoilState } from 'recoil';

import Loading from '../components/Loading';
import { fetchAnswersList } from '../util/useFetchAnswer';
import { MyQueItem } from '../components/MyQueItem';
import { MyAnsItem } from '../components/MyAnsItem';

export default function Mypage() {
  const [userInfo, setuserInfo] = useRecoilState(userInfoState);
  const [isLoading, setLoading] = useState(true);
  const [questionList, setquestionList] = useRecoilState(questionListState);
  const [answerList, setanswerList] = useRecoilState(answerListState);

  useEffect(() => {
    checkLogin().then((data) => {
      setLoading(false);
    });
  }, []);

  useEffect(() => {
    fetchAnswersList()
      .then((data) => {
        setanswerList(data.data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);


  const SummaryCount = (state) => {
    let count = 0;
    state.map((el) => (el.displayName === userInfo.displayName ? count++ : ''));
    return count;
  };

  const SummaryVotes = (state) => {
    let votecount = 0;
    state.map((el) =>
      el.displayName === userInfo.displayName ? (votecount += el.voteResult) : ''
    );
    return votecount;
  };

  const MyQnA = (state) => {
    const qnalist = state.filter((el) => el.displayName === userInfo.displayName);
    return qnalist;
  };

  console.log(MyQnA(questionList));
  return (
    <MypageContainer>
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <InfoContainer>
            <Profile>
              <ProfilePic src={hyunGS25}></ProfilePic>
              <h2>{userInfo.displayName}</h2>
            </Profile>
            <SummaryContainer>
              <h3>Summary</h3>
              <SummaryContent>
                <Summary>
                  <h1>{SummaryVotes(questionList)}</h1>
                  <h3>Total Votes</h3>
                </Summary>
                <Summary>
                  <h1>{SummaryCount(questionList)}</h1>
                  <h3>Questions</h3>
                </Summary>
                <Summary>
                  <h1>{SummaryCount(answerList)}</h1>
                  <h3>Answers</h3>
                </Summary>
              </SummaryContent>
            </SummaryContainer>
          </InfoContainer>
          <QuestionsContainer>
            <h1>Questions</h1>
            <ListBlock>
              {MyQnA(questionList).map((el) => {
                return (

                  <MyQueItem
                    key={el.questionId}
                    votes={el.voteResult}
                    title={el.title}
                    id={el.questionId}></MyQueItem>

                );
              })}
            </ListBlock>
          </QuestionsContainer>
          <AnswersContainer>
            <h1>Answers</h1>
            <ListBlock>
              {MyQnA(answerList).map((el) => {
                return (
                  <MyAnsItem
                    key={el.questionId}
                    votes={el.voteResult}
                    title={el.content}
                    id={el.questionId}></MyAnsItem>

                );
              })}
            </ListBlock>
          </AnswersContainer>
        </>
      )}
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
