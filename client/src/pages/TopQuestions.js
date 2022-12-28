import React from "react";
import { useEffect, useState } from "react";

import { fetchQuestionList } from "../util/usefetchQuestion";
import { questionListState } from "../state/atom";
import { useRecoilState } from "recoil";

export default function TopQuestions() {
  const [questionList, setquestionList] = useRecoilState(questionListState);

  useEffect(() => {
    fetchQuestionList()
      .then((data) => {
        setquestionList(data.data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  console.log(questionList);

  return <div>Top Questions</div>;
}
