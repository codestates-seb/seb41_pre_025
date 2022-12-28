import React, { useState } from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import QuestionItem from "../components/QuestionItem";
import { Link } from "react-router-dom";
import { useEffect } from "react";

import { fetchQuestionList } from "../util/usefetchQuestion";
import { fetchMemberInfo, checkLogin } from "../util/fetchLogin";
import { questionListState, userInfoState } from "../state/atom";
import { useRecoilState } from "recoil";

export default function QuestionsList() {
  const [questionList, setquestionList] = useRecoilState(questionListState);
  const [userInfo, setuserInfo] = useRecoilState(userInfoState);

  useEffect(() => {
    fetchQuestionList()
      .then((data) => {
        setquestionList(data.data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  useEffect(() => {
    checkLogin();
  }, []);

  const tagfilter = questionList.filter((questions) => {
    if (questions.tag.filter((x) => userInfo.isFollowingTags.includes(x)).length !== 0) {
      return true;
    } else return false;
  });

  return (
    <>
      <Head>
        <h1>Top Questions</h1>
        <Link to="/askquestions">
          <Button text="Ask Question" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" width="103.02px" />
        </Link>
      </Head>
      <QueCount>{tagfilter.length} questions</QueCount>
      <FilterContainer>
        <Button text="Newest" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
        <Button text="Newest" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
        <Button text="Newest" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
        <Button text="Filter" marginLeft="0px" />
      </FilterContainer>
      <hr />
      <QueContainer>
        <Question>
          {tagfilter.map((tagquetion) => {
            return <QuestionItem questions={tagquetion} key={tagquetion.questionId} />;
          })}
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

const QueContainer = styled.ul`
  margin: 0;
  padding: 0;
`;

const Question = styled.li`
  display: flex;
  flex-direction: column;
  overflow: hidden;
  > .user {
    align-self: flex-end;
    font-size: 12px;
  }
`;
