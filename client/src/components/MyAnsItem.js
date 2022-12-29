import React from 'react';
import styled, { css } from 'styled-components';
import { MdEdit, MdDelete } from 'react-icons/md';

export function MyAnsItem(props) {
  const { votes, title } = props;
  return (
    <MyAnsItemTemplate>
      <VoteBox votes={votes}>{votes}</VoteBox>
      <Text title={title}>{title}</Text>
      <Edit>
        <MdEdit />
      </Edit>
      <Remove>
        <MdDelete />
      </Remove>
    </MyAnsItemTemplate>
  );
}

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
const MyAnsItemTemplate = styled.div`
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
