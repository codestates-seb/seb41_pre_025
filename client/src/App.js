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

const GlobalStyle = createGlobalStyle`
* {
  box-sizing: border-box;

}
  body {
    background: #e9ecef;
  }

  ol, ul, li {
	list-style: none;
  }
  
`;

function App() {
  return (
    <BrowserRouter>
      <GlobalStyle />
      <Navbar />
      <Template>
        <Sidebar />
        <Section>
          <Routes>
            <Route path="/" element={<TopQuestions />}></Route>
            <Route path="/login" element={<Login />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="/questions" element={<QuestionsList />}></Route>
            <Route path="/askquestions" element={<AskQuestion />}></Route>
            <Route path="/mypage" element={<Mypage />}></Route>
            {/* 임시로 질문상세페이지를 tags에서 볼 수 있도록 설정
				데이터 받아온 후에 다시 변경해야 함 */}
            <Route path="/tags" element={<Question />}></Route>
            <Route path="/users" element={<Pre />}></Route>
            <Route path="/companies" element={<Pre />}></Route>
          </Routes>
        </Section>
        <Ad />
      </Template>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
