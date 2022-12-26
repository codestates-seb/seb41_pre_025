import React from "react";
import { createGlobalStyle, css } from "styled-components";
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";
import Template from "./components/Template";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Footer from "./components/Footer";
import Ad from "./components/Ad";
import Section from "./components/Section";
import TopQuestions from "./pages/TopQuestions";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import QuestionsList from "./pages/QuestionsList";
import AskQuestion from "./pages/AskQuestion";
import Mypage from "./pages/Mypage";
import Pre from "./components/Pre";
import Question from "./pages/Question";

const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;

  }

  ol, ul, li {
   list-style: none;
  }
      body{
      background-color:white;
      ${(props) =>
        props.pathname === "/askquestions"
          ? css`
              background-color: #f7f8f8;
            `
          : ["/login", "/signup"].indexOf(props.pathname) !== -1 &&
            css`
              background-color: #f1f2f3;
            `}
      }
`;

function App() {
  const location = useLocation();
  const pathname = location.pathname;
  const sidebar = ["/login", "/signup", "/askquestions"];
  const ad = [...sidebar, "/mypage"];
  const footer = ["/login", "/signup"];
  return (
    <>
      <GlobalStyle pathname={pathname} />
      <Navbar />
      <Template>
        {sidebar.indexOf(pathname) === -1 && <Sidebar />}
        <Section>
          <Routes>
            <Route path="/" element={<TopQuestions />}></Route>
            <Route path="/login" element={<Login />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="/QuestiosList" element={<QuestionsList />}></Route>
            <Route path="/askquestions" element={<AskQuestion />}></Route>
            <Route path="/mypage" element={<Mypage />}></Route>
            {/* 임시로 질문상세페이지를 tags에서 볼 수 있도록 설정
            데이터 받아온 후에 다시 변경해야 함 */}
            <Route path="/tags" element={<Question />}></Route>
            <Route path="/users" element={<Pre />}></Route>
            <Route path="/companies" element={<Pre />}></Route>
          </Routes>
        </Section>
        {ad.indexOf(pathname) === -1 && <Ad />}
      </Template>
      {footer.indexOf(pathname) === -1 && <Footer />}
    </>
  );
}

export default App;
