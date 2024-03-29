import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { GoTriangleUp, GoTriangleDown } from "react-icons/go";
import { BsBookmark } from "react-icons/bs";
import { GiBackwardTime } from "react-icons/gi";
import { MdAccountCircle } from "react-icons/md";
import { Button } from "../components/Button";
import { fetchQuestionList, fetchDeleteQuestion } from "../util/usefetchQuestion";
import { questionDetailState, answersState, userInfoState } from "../state/atom";
import { useRecoilState, useRecoilValue } from "recoil";
import { useLocation, useNavigate } from "react-router-dom";
import { fetchCreateAnswer } from "../util/useFetchAnswer";
import Loading from "../components/Loading";
import moment from "moment-timezone";
import Answer from "../components/Answer";
import { fetchVoteQuestion } from "../util/useFetchVote";
import { checkLogin } from "../util/fetchLogin";

export default function QuestionsDetail() {
  // 로딩중 상태관리
  const navigate = useNavigate();
  const [isLoading, setLoading] = useState(true);
  const [content, setContent] = useState("");
  const [questionDetail, setquestionDetail] = useRecoilState(questionDetailState);
  const [answers, setAnswers] = useRecoilState(answersState);
  const userInfo = useRecoilValue(userInfoState);
  const location = useLocation();
  const pathname = location.pathname;
  const [update, setUpdate] = useState(true);

  const getQuestion = (id) => {
    fetchQuestionList(id).then((res) => {
      setquestionDetail(res.data);
      setAnswers(res.data.answers);
      setLoading(false);
    });
  };
  function contentHandler(e) {
    setContent(e.target.value);
  }

  const toEditQue = () => {
    if (userInfo.email === questionDetail.email) {
      navigate(`/editQuestion/${questionDetail.questionId}`);
    } else {
      alert("권한이 없습니다");
    }
  };

  const toAskQue = () => {
    checkLogin().then((res) => {
      if (res) {
        navigate("/askquestions");
      } else {
        navigate("/login");
      }
    });
  };

  const postAnswer = async () => {
    await fetchCreateAnswer(questionDetail.questionId, content).then((data) => {
      if (data) {
        setUpdate(true);
      }
    });
  };

  const deleteQuestion = async () => {
    await fetchDeleteQuestion(questionDetail.questionId).then((res) => {
      if (res) {
        navigate("/");
      }
    });
  };

  const QUpVote = () => {
    fetchVoteQuestion(questionDetail.questionId, true).then((data) => {
      if (data) {
        setUpdate(true);
      }
    });
  };

  const QDownVote = () => {
    fetchVoteQuestion(questionDetail.questionId, false).then((data) => {
      if (data) {
        setUpdate(true);
      }
    });
  };

  useEffect(() => {
    if (update) {
      getQuestion(pathname.slice(16));
      setContent("");
      setUpdate(false);
      moment.tz.setDefault("Asia/Seoul");
    }
  }, [update]);

  if (isLoading) {
    return <Loading />;
  } else {
    return (
      <Contents>
        <Head>
          <Title>
            <h1>{questionDetail.title}</h1>
            <Button onClick={toAskQue} text="Ask Question" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" width="103.02px" boxshadow="inset 0px 1px hsl(206,90%,69.5%)" />
          </Title>
          <Info>
            <div>
              <p>
                <span>Asked</span>
                {moment(questionDetail.createdAt).add(9, "hours").fromNow()}
              </p>
              <p>
                <span>Modified</span>
                {moment(questionDetail.modifiedAt).add(9, "hours").fromNow()}
              </p>
              <p>
                <span>Viewed</span>10 times
              </p>
            </div>
          </Info>
        </Head>
        <QuestionContainer>
          <VoteContainer>
            <GoTriangleUp className="triangle" onClick={QUpVote} />
            <div>{questionDetail.voteResult}</div>
            <GoTriangleDown className="triangle" onClick={QDownVote} />
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
                  <span onClick={toEditQue}>Edit</span>
                  <span onClick={deleteQuestion}>Delete</span>
                </CRUDText>
                <UserInfo>
                  <span>
                    asked&nbsp;
                    {moment(questionDetail.createdAt).add(9, "hours").fromNow()}
                  </span>
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
          <TotalAnswersText>{answers.length !== 0 ? `${answers.length} Answers` : "Know someone who can answer? Share a link to this question via email, Twitter, or Facebook."}</TotalAnswersText>
          {answers.map((answer) => (
            <Answer answer={answer} setUpdate={setUpdate} />
          ))}
          <h2>Your Answer</h2>
          <textarea value={content} onChange={contentHandler} />
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
    margin-right: 5px;
    color: #6c737b;
  }
`;

const MainContainer = styled.div`
  display: flex;
  border-bottom: 1px solid #e4e6e8;
  margin-top: 20px;
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

const CRUDandUserContainer = styled.div`
  margin: 16px 0;
  display: flex;
  justify-content: space-between;
`;

const CRUDText = styled.div`
  color: #6c737b;

  span {
    font-size: 13px;
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

  background-color: #e1ecf4;
  border-radius: 3px;
  width: 200px;
  height: 70px;
  align-self: end;

  span {
    font-size: 12px;
  }
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

const TotalAnswersText = styled.div`
  margin: 10px 0 20px 0;
`;
