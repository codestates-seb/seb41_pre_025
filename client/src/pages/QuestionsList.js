import React, { useState } from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import QuestionItem from "../components/QuestionItem";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

import { fetchQuestionList } from "../util/usefetchQuestion";
import { questionListState } from "../state/atom";
import { useRecoilState } from "recoil";
import Loading from "../components/Loading";
import Pagination from "../components/Pagination";
import { checkLogin } from "../util/fetchLogin";
export default function QuestionsList() {
  const [isLoading, setLoading] = useState(true);

  const navigate = useNavigate();

  const [questionList, setquestionList] = useRecoilState(questionListState);
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  useEffect(() => {
    fetchQuestionList().then((data) => {
      setLoading(false);
      setquestionList(data.data);
    });
  }, []);

  const toAskQue = () => {
    checkLogin().then((res) => {
      if (res) {
        navigate("/askquestions");
      } else {
        navigate("/login");
      }
    });
  };

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <Head>
            <h1>All Questions</h1>
            <Button onClick={toAskQue} text="Ask Question" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" width="103.02px" />
          </Head>
          <QueCount>{questionList.length} questions</QueCount>
          <FilterContainer>
            <Button text="Newest" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="3px 0px 0px 3px" />
            <Button text="Active" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="Bountied" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="Unanswered" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="More" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px 3px 3px 0px" />
            <Button text="Filter" marginLeft="0px" />
          </FilterContainer>
          <hr />
          <QueContainer>
            <Question>
              {questionList.slice(offset, offset + limit).map((questions) => {
                return <QuestionItem questions={questions} key={questions.questionId} />;
              })}
            </Question>
            <Pagination total={questionList.length} limit={limit} page={page} setPage={setPage} />
          </QueContainer>
          <hr />
        </>
      )}
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
