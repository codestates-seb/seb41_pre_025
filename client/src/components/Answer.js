import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { GoTriangleUp, GoTriangleDown } from "react-icons/go";
import { BsBookmark } from "react-icons/bs";
import { GiBackwardTime } from "react-icons/gi";
import { MdAccountCircle } from "react-icons/md";
import { Button } from "./Button";
import { fetchDeleteAnswer, fetchModifyAnswer } from "../util/useFetchAnswer";
import { fetchVoteAnswer } from "../util/useFetchVote";
import moment from "moment";
import { userInfoState } from "../state/atom";
import { useRecoilValue } from "recoil";

export default function Answer({ answer, setUpdate }) {
  const { answerId, voteResult, content, createdAt, displayName, email } = answer;
  const [ansedit, setAnsedit] = useState(true);
  const [answerContent, setAnswerContent] = useState("");
  const userInfo = useRecoilValue(userInfoState);

  function editHandler() {
    if (email === userInfo.email) {
      setAnsedit(!ansedit);
    } else {
      alert("권한이 없습니다");
    }
  }
  function contentHandler(e) {
    setAnswerContent(e.target.value);
  }
  const editAnswer = async (answerId, content) => {
    await fetchModifyAnswer(answerId, { answerId, content }).then((data) => {
      if (data) {
        setUpdate(true);
      }
      editHandler();
    });
  };

  const deleteAnswer = async (answerId) => {
    await fetchDeleteAnswer(answerId).then((data) => {
      setUpdate(true);
    });
  };

  const upVoteAnswer = (answerId) => {
    fetchVoteAnswer(answerId, true).then((data) => {
      if (data) {
        setUpdate(true);
      }
    });
  };

  const downVoteAnswer = (answerId) => {
    fetchVoteAnswer(answerId, false).then((data) => {
      if (data) {
        setUpdate(true);
      }
    });
  };

  return ansedit ? (
    <MainContainer>
      <VoteContainer>
        <GoTriangleUp onClick={() => upVoteAnswer(answerId)} className="triangle" />
        <div>{voteResult}</div>
        <GoTriangleDown onClick={() => downVoteAnswer(answerId)} className="triangle" />
        <BsBookmark className="icon" />
        <GiBackwardTime className="icon" />
      </VoteContainer>
      <MainTextContainer>
        <Maintext>{content}</Maintext>
        <CRUDandUserContainer>
          <CRUDText>
            <span>Share</span>
            <span onClick={editHandler}>Edit</span>
            <span onClick={() => deleteAnswer(answerId)}>Delete</span>
          </CRUDText>
          <UserInfo>
            <span>
              answered&nbsp;
              {moment(createdAt).add(9, "hours").fromNow()}
            </span>
            <Name>
              <MdAccountCircle />
              {displayName}
            </Name>
          </UserInfo>
        </CRUDandUserContainer>
      </MainTextContainer>
    </MainContainer>
  ) : (
    <>
      <textarea defaultValue={content} onChange={contentHandler} />
      <Button
        onClick={() => editAnswer(answerId, answerContent)}
        text="Edit Your Answer"
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
    </>
  );
}

const MainContainer = styled.div`
  display: flex;
  border-bottom: 1px solid #e4e6e8;
  margin-top: 20px;
`;

const MainTextContainer = styled.div`
  width: 100%;
`;

const Maintext = styled.article`
  width: 100%;
  margin-bottom: 16px;
  font-size: 15px;
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
