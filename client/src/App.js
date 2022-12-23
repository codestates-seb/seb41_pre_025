import React from "react";
import { createGlobalStyle } from "styled-components";
import { BrowserRouter as Router, Route, Routes, useLocation } from "react-router-dom";
import Template from "./components/Template";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Footer from "./components/Footer";
import Ad from "./components/Ad";
import Section from "./components/Section";
import TopQuestions from "./components/TopQuestions";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import QuestionsList from "./components/QuestionsList";
import AskQuestion from "./components/AskQuestion";
import Mypage from "./components/Mypage";
import Pre from "./components/Pre";

const GlobalStyle = createGlobalStyle`
* {
  box-sizing: border-box;

}

  ol, ul, li {
	list-style: none;
  }
  
`;

function App() {
  const location = useLocation();
  const pathname = location.pathname;
  const sidebar = ["/login", "/signup"];
  const ad = [...sidebar, "/mypage"];
  const footer = [...sidebar];
  return (
    <>
      <GlobalStyle />
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
            <Route path="/tags" element={<Pre />}></Route>
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
