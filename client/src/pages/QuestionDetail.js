import React, { useState } from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import { GoTriangleUp, GoTriangleDown } from "react-icons/go";
import { BsBookmark } from "react-icons/bs";
import { GiBackwardTime } from "react-icons/gi";
import { MdAccountCircle } from "react-icons/md";
import { useEffect } from "react";
import { fetchQuestionList } from "../util/usefetchQuestion";
import { questionDetailState, answersState } from "../state/atom";
import { useRecoilState } from "recoil";
import { useLocation, Link } from "react-router-dom";
import { fetchCreateAnswer } from "../util/useFetchAnswer";
import Loading from "../components/Loading";
import { fetchDeleteQuestion } from "../util/usefetchQuestion";

export default function QuestionsDetail() {
  // 투표 수 voteResult로 바꾸고 상태관리
  const [voteCount, setVoteCount] = useState(0);
  // 로딩중 상태관리
  const [isLoading, setLoading] = useState(true);
  const [content, setContent] = useState("");
  const [questionDetail, setquestionDetail] = useRecoilState(questionDetailState);
  const [answers, setAnswers] = useRecoilState(answersState);
  const location = useLocation();
  const pathname = location.pathname;

  useEffect(() => {
    fetchQuestionList(pathname.slice(16))
      .then((data) => {
        setquestionDetail(data.data);
        setAnswers(data.data.answers);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  function contentHandler(e) {
    setContent(e.target.value);
  }
  const postAnswer = async () => {
    await fetchCreateAnswer(questionDetail.questionId, content).then((data) => {
      window.location.href = `${questionDetail.questionId}`;
    });
  };

  const deleteQuestion = async () => {
    await fetchDeleteQuestion(questionDetail.questionId).then((data) => {
      window.location.href = "/";
    });
  };
  if (isLoading) {
    return <Loading />;
  } else {
    return (
      <Contents>
        <Head>
          <Title>
            <h1>{questionDetail.title}</h1>
            <Link to="/askquestions">
              <Button text="Ask Question" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" width="103.02px" boxshadow="inset 0px 1px hsl(206,90%,69.5%)" />
            </Link>
          </Title>
          <Info>
            <div>
              <p>
                <span>Asked</span> today
              </p>
              <p>
                <span>Modified</span> today
              </p>
              <p>
                <span>Viewed</span> 13 times
              </p>
            </div>
          </Info>
        </Head>
        <QuestionContainer>
          <VoteContainer>
            <GoTriangleUp className="triangle" onClick={() => setVoteCount(voteCount + 1)} />
            <div>{voteCount}</div>
            <GoTriangleDown className="triangle" onClick={() => setVoteCount(voteCount - 1)} />
            <BsBookmark className="icon" />
            <GiBackwardTime className="icon" />
          </VoteContainer>
          <MainTextContainer>
            <Maintext>{questionDetail.content}</Maintext>
            <SubContainer>
              <TagBox>
                {questionDetail.tag.map((tag) => {
                  return <Tag key={questionDetail.questionId}>{tag}</Tag>;
                })}
              </TagBox>
              <CRUDandUserContainer>
                <CRUDText>
                  <span>Share</span>
                  <Link to={`/editQuestion/${questionDetail.questionId}`}>
                    <span>Edit</span>
                  </Link>
                  <span onClick={deleteQuestion}>Delete</span>
                </CRUDText>
                <UserInfo>
                  asked 59 mins ago
                  <Name>
                    <MdAccountCircle />
                    {questionDetail.displayName}
                  </Name>
                </UserInfo>
              </CRUDandUserContainer>
            </SubContainer>
          </MainTextContainer>
        </QuestionContainer>
        <AnswerContainer>
          <div>{answers.length} Answers</div>
          {answers.map((answer) => (
            <MainContainer>
              <VoteContainer>
                <GoTriangleUp className="triangle" onClick={() => setVoteCount(voteCount + 1)} />
                <div>{voteCount}</div>
                <GoTriangleDown className="triangle" onClick={() => setVoteCount(voteCount - 1)} />
                <BsBookmark className="icon" />
                <GiBackwardTime className="icon" />
              </VoteContainer>
              <Maintext>{answer.content}</Maintext>
            </MainContainer>
          ))}
          <h2>Your Answer</h2>
          <textarea onChange={contentHandler} />
          <Button
            onClick={postAnswer}
            text="Post Your Answer"
            color="white"
            border="1px solid #4393F7"
            bgColor="#4393F7"
            hoverColor="#2D75C6"
            activeColor="#1859A3"
            width="119.77px"
            height="54.77px"
            boxshadow="inset 0px 1px hsl(206,90%,69.5%)"
            margin="10px 0 15px 0"
          />
        </AnswerContainer>
      </Contents>
    );
  }
}

const Contents = styled.div`
  padding: 20px;
  height: 100%;
`;
const Head = styled.div``;
const Title = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  h1 {
    color: #3c4044;
    font-size: 27px;
    font-weight: 400;
  }
`;

const Info = styled.div`
  padding-bottom: 10px;
  margin-bottom: 16px;
  font-size: 13px;
  border-bottom: 1px solid #e4e6e8;

  div {
    display: flex;
  }

  p {
    margin: 0 16px 8px 0;
  }

  span {
    color: #6c737b;
  }
`;

const MainContainer = styled.div`
  display: flex;
  border-bottom: 1px solid #e4e6e8;

  div {
    padding-right: 16px;
  }
`;

const VoteContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: #babfc4;
  cursor: pointer;
  margin-right: 16px;

  div {
    font-size: 20px;
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

const QuestionContainer = styled.div`
  display: flex;
  border-bottom: 1px solid #e4e6e8;
  padding: 0 0 20px 0;
`;

const MainTextContainer = styled.div`
  width: 100%;
`;

const Maintext = styled.article`
  width: 100%;
  margin-bottom: 16px;
  font-size: 15px;
`;
const SubContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 16px 0;
`;
const TagBox = styled.div`
  display: flex;
  margin-bottom: 13px;
`;

const Tag = styled.div`
  background-color: #e1ecf4;
  border-radius: 3px;
  padding: 5px;
  margin: 0 7px 0 0;
  color: #39739d;
  font-size: 12px;
`;

const CRUDandUserContainer = styled.div`
  margin: 16px 0;
  display: flex;
  justify-content: space-between;
`;

const CRUDText = styled.div`
  color: #6c737b;
  font-size: 13px;

  span {
    cursor: pointer;
    margin: 0 7px 0 0;
  }
  a {
    text-decoration: none;
    color: #6c737b;
  }
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
  margin-top: 10px;

  h2 {
    margin: 19px 0 19px 0;
    font-size: 19px;
    font-weight: 400;
  }

  textarea {
    height: 200px;
    border-color: #bbbfc3;
    border-radius: 5px;
  }

  div {
    margin: 8px 0;
    font-size: 19px;
  }
`;
