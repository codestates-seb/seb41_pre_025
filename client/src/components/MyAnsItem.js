import React from 'react';
import styled, { css } from 'styled-components';

import { Link } from 'react-router-dom';

export function MyAnsItem(props) {
  const { votes, title, id } = props;
  return (
    <MyAnsItemTemplate>
      <VoteBox votes={votes}>{votes}</VoteBox>
      <Link to={`/questionDetail/${id}`}>
        <Text title={title}>{title}</Text>
      </Link>


    </MyAnsItemTemplate>
  );
}


const MyAnsItemTemplate = styled.div`
  display: flex;
  align-items: center;
  padding-top: 12px;
  padding-bottom: 12px;


  a {
    text-decoration: none;

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
