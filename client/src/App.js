<<<<<<< HEAD
import React from "react";
import { createGlobalStyle, css } from "styled-components";
import {
	BrowserRouter as Router,
	Route,
	Routes,
	useLocation,
} from "react-router-dom";
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
=======

import React from 'react';
import { createGlobalStyle } from 'styled-components';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Template from './components/Template';
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import Footer from './components/Footer';
import Ad from './components/Ad';
import Section from './components/Section';
import TopQuestions from './components/TopQuestions';
import Login from './components/Login';
import SignUp from './components/SignUp';
import QuestionsList from './components/QuestionsList';
import AskQuestion from './components/AskQuestion';
import Mypage from './components/Mypage';
import Pre from './components/Pre';
import Question from './components/Question';
>>>>>>> a3c98fdb9c97d20cfe84cfca38cd99d4db6a3b2b

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
						<Route
							path="/"
							element={<TopQuestions />}></Route>
						<Route
							path="/login"
							element={<Login />}></Route>
						<Route
							path="/signup"
							element={<SignUp />}></Route>
						<Route
							path="/QuestiosList"
							element={<QuestionsList />}></Route>
						<Route
							path="/askquestions"
							element={<AskQuestion />}></Route>
						<Route
							path="/mypage"
							element={<Mypage />}></Route>
						<Route
							path="/tags"
							element={<Pre />}></Route>
						<Route
							path="/users"
							element={<Pre />}></Route>
						<Route
							path="/companies"
							element={<Pre />}></Route>
					</Routes>
				</Section>
				{ad.indexOf(pathname) === -1 && <Ad />}
			</Template>
			{footer.indexOf(pathname) === -1 && <Footer />}
		</>
	);
}

const GlobalStyle = createGlobalStyle`
* {
  box-sizing: border-box;

}

body{
      background-color:white;
      ${(props) =>
				["/askquestions"].indexOf(props.pathname) !== -1 &&
				css`
					background-color: #f1f2f3;
				`}
      }

  ol, ul, li {
	list-style: none;
  }
  
`;

<<<<<<< HEAD
=======
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

>>>>>>> a3c98fdb9c97d20cfe84cfca38cd99d4db6a3b2b
export default App;
