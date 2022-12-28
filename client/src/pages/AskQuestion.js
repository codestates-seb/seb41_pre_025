import React from "react";
import styled from "styled-components";
import { Button } from "../components/Button";
import { useState, useEffect } from "react";
import { fetchCreateQuestion } from "../util/usefetchQuestion";
import { checkLogin } from "../util/fetchLogin";

export default function AskQuestion() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const initialtag = ["Javascript"];
  const [tag, setTag] = useState(initialtag);

  function titleHandler(e) {
    setTitle(e.target.value);
  }
  function contentHandler(e) {
    setContent(e.target.value);
  }

  const removetag = (indexToRemove) => {
    setTag(tag.filter((_, index) => index !== indexToRemove));
  };

  const addtag = (event) => {
    const filtered = tag.filter((el) => el === event.target.value);
    if (event.target.value !== "" && filtered.length === 0) {
      setTag([...tag, event.target.value]);
      event.target.value = "";
    }
  };

  const postQuestion = async () => {
    await fetchCreateQuestion({ title, content, tag }).then((data) => {
      console.log(data.data.questionId);
      window.location.href = `questionDetail/${data.data.questionId}`;
    });
  };

  useEffect(() => {
    checkLogin();
  }, []);

  return (
    <>
      <Content>
        <h1>Ask a public question</h1>
        <HelpBubbleGoodQuestion>
          <h2>Writing a good question</h2>
          <p>You’re ready to ask a programming-related question and this form will help guide you through the process. Looking to ask a non-programming question? See the topics here to find a relevant site.</p>
          <h5>Steps</h5>
          <ul>
            <li>Summarize your problem in a one-line title.</li>
            <li>Describe your problem in more detail.</li>
            <li>Describe what you tried and what you expected to happen.</li>
            <li>Add “tags” which help surface your question to members of the community.</li>
            <li>Review your question and post it to the site.</li>
          </ul>
        </HelpBubbleGoodQuestion>
        <TitleInputContainer>
          <h5>Title</h5>
          <span>Be specific and imagine you’re asking a question to another person.</span>
          <LabelTextInput onChange={titleHandler} id="title" placeholder="e.g. Is there an R function for finding the index of an element in a vector?" />
        </TitleInputContainer>
        <ContextInputContainer>
          <h5>What are the details of your problem?</h5>
          <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
          <ContextInputTextArea onChange={contentHandler} />
        </ContextInputContainer>
        <TagsContainer>
          <span className="tagLabel">Tags</span>
          <TagsInput>
            <ul id="tags">
              {tag.map((tag, index) => (
                <li key={index} className="tag">
                  <span className="tag-title">{tag}</span>
                  <span className="tag-close-icon" onClick={() => removetag(index)}>
                    x
                  </span>
                </li>
              ))}
            </ul>
            <input
              className="tag-input"
              type="text"
              onKeyUp={(event) => {
                if (event.key === "Enter") {
                  addtag(event);
                }
              }}
              placeholder="Press enter to add tags"
              onSubmit="return false;"
            />
          </TagsInput>
        </TagsContainer>
        <Button onClick={postQuestion} margin="0" text="Post your question" width="130px" color="white" border="1px solid #4393F7" bgColor="#3F96F7" hoverColor="#2D75C6" activeColor="#1859A3" />
      </Content>
    </>
  );
}

const Content = styled.div`
  width: 1264px;
  background-color: #f7f8f8;
  padding: 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const HelpBubbleGoodQuestion = styled.section`
  width: 831.01px;
  padding: 24px;
  margin: 16px 0;
  background-color: #edf4fa;
  display: flex;
  flex-direction: column;
  border: 1px solid #aeceea;
  border-radius: 3px;
  font-size: 15px;

  h2 {
    margin: 0;
    font-weight: 400;
    font-size: 21px;
  }

  h5 {
    font-size: 13px;
    font-weight: 600;
    margin: 0;
    margin-bottom: 8px;
  }

  p {
    margin: 8px 0 15px 0;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  li {
    margin-left: 30px;
    list-style: inside;
    font-size: 13px;
  }
`;

const TitleInputContainer = styled.div`
  width: 831.01px;
  margin-bottom: 16px;
  padding: 24px;
  background-color: white;
  display: flex;
  flex-direction: column;
  border: 1px solid #d7d9dc;
  border-radius: 3px;
  font-size: 15px;
`;

const InputContainer = styled.div`
  width: 240.45px;
  margin: 6px 0;
`;

const Input = styled.input`
  width: 781.029px;
  height: 32.59px;

  padding: 7.8px 9.1px;
  border: 1px solid #bbbfc3;
  border-radius: 3px;
  outline: none;

  &:focus-within {
    border: 1px solid blue;
  }
`;

const Label = styled.label`
  display: block;
  margin: 6px 0;
  font-size: 16px;
  font-weight: bold;
`;

const LabelTextInput = ({ id = "", placeholder = "", onChange = (e) => {} }) => {
  return (
    <InputContainer>
      <Label htmlFor={id}></Label>
      <Input name={id} placeholder={placeholder} onChange={onChange} />
    </InputContainer>
  );
};

const ContextInputContainer = styled.div`
  margin-bottom: 16px;
  padding: 24px;
  background-color: white;
  display: flex;
  flex-direction: column;
  border: 1px solid #d7d9dc;
  border-radius: 3px;
  font-size: 15px;
`;

const ContextInputTextArea = styled.textarea`
  width: 781.029px;
  height: 32.59px;

  padding: 7.8px 9.1px;
  border: 1px solid #bbbfc3;
  border-radius: 3px;
  outline: none;

  &:focus-within {
    border: 1px solid blue;
  }
`;

const TagsContainer = styled.div`
  margin-bottom: 16px;
  padding: 24px;
  background-color: white;
  display: flex;
  flex-direction: column;
  border: 1px solid #d7d9dc;
  border-radius: 3px;
  font-size: 15px;

  .tagLabel {
    display: block;
    margin: 6px 0;
    font-size: 16px;
    font-weight: bold;
  }
`;

const TagsInput = styled.div`
  width: 781.029px;
  min-height: 48px;
  margin: 6px 0;
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  padding: 0 8px;
  border: 1px solid rgb(214, 216, 218);
  border-radius: 6px;

  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 8px 0 0 0;

    > .tag {
      border: 1px solid black;
      width: auto;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: black;
      padding: 0 8px;
      font-size: 14px;
      list-style: none;
      border-radius: 6px;
      margin: 0 8px 8px 0;
      background: var(--coz-purple-600);
      > .tag-close-icon {
        display: block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        font-size: 14px;
        margin-left: 8px;
        color: var(--coz-purple-600);
        border-radius: 50%;
        background: #fff;
        cursor: pointer;
      }
    }
  }

  > input {
    flex: 1;
    border: none;
    height: 46px;
    font-size: 14px;
    padding: 4px 0 0 0;
    :focus {
      outline: transparent;
    }
  }

  &:focus-within {
    border: 1px solid blue;
  }
`;
