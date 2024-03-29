import React, { useState } from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import QuestionItem from "../components/QuestionItem";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import { fetchQuestionList } from "../util/usefetchQuestion";
import { checkLogin } from "../util/fetchLogin";
import { questionListState, userInfoState } from "../state/atom";
import { useRecoilState } from "recoil";
import Loading from "../components/Loading";
import Pagination from "../components/Pagination";

export default function TopQuestions() {
  const [questionList, setquestionList] = useRecoilState(questionListState);
  const [userInfo, setuserInfo] = useRecoilState(userInfoState);
  const [isLoading, setIsLoading] = useState(true);

  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  useEffect(() => {
    checkLogin();
    fetchQuestionList()
      .then((data) => {
        setquestionList(data.data);
        setIsLoading(false);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  const tagfilter = questionList.filter((questions) => {
    if (questions.tag.filter((x) => userInfo.isFollowingTags.includes(x)).length !== 0) {
      return true;
    } else return false;
  });

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <Head>
            <h1>Top Questions</h1>
            <Link to="/askquestions">
              <Button text="Ask Question" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" width="103.02px" />
            </Link>
          </Head>
          <QueCount>{tagfilter.length} questions</QueCount>
          <FilterContainer>
            <Button text="Interesting" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="3px 0px 0px 3px" />
            <Button text="Bountied" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="Hot" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="Week" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px" />
            <Button text="Month" color="#525960" border="1px solid #525960" bgColor="white" hoverColor=" hsl(210,8%,95%)" activeColor="none" width="103.02px" margin="0px" bdradius="0px 3px 3px 0px" />
            <Button text="Filter" marginLeft="0px" />
          </FilterContainer>
          <hr />
          <QueContainer>
            <Question>
              {tagfilter.slice(offset, offset + limit).map((tagquetion) => {
                return <QuestionItem questions={tagquetion} key={tagquetion.questionId} />;
              })}
            </Question>
            {tagfilter.length ? <Pagination total={tagfilter.length} limit={limit} page={page} setPage={setPage} /> : ""}
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
