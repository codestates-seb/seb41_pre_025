import React, { useEffect } from "react";
import { createGlobalStyle, css } from "styled-components";
import { Routes, Route, useLocation } from "react-router-dom";
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
import Pre from "./pages/Pre";
import QuestionDetail from "./pages/QuestionDetail";
import EditQuestion from "./pages/EditQuestion";
import { loginState, userInfoState } from "./state/atom";
import { useRecoilState } from "recoil";
import { fetchMemberInfo } from "./util/fetchLogin";
import SearchQuestion from "./pages/SearchQuestion";

const GlobalStyle = createGlobalStyle`
   * {
      box-sizing: border-box;
      
   }
   
   ol, ul, li {
     list-style: none;
    }
    hr{
      border:0px;
   }
   
   body {
     background-color: white;
     ${(props) =>
       props.pathname === "/askquestions" || props.pathname === `/editQuestion/${props.editPathName}`
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
  const editPathName = pathname.slice(14);
  const sidebar = ["/login", "/signup", "/askquestions", `/editQuestion/${editPathName}`];
  const ad = [...sidebar, "/mypage", "/users", "/companies", "/tags"];
  const footer = ["/login", "/signup"];

  const [useInfo, setuserInfo] = useRecoilState(userInfoState);
  const [isLogin, setIsLogin] = useRecoilState(loginState);

  useEffect(() => {
    fetchMemberInfo().then((data) => {
      if (!data) {
        sessionStorage.removeItem("access_token");
        setuserInfo({});
        setIsLogin(false);
        //   window.location.href = "/";
      }
    });
  }, []);
  return (
    <>
      <GlobalStyle pathname={pathname} editPathName={editPathName} />
      <Navbar />
      <Template>
        {sidebar.indexOf(pathname) === -1 && <Sidebar />}
        <Section>
          <Routes>
            <Route path="/" element={isLogin ? <TopQuestions /> : <QuestionsList />}></Route>
            <Route path="/login" element={<Login />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="/questionslist" element={<QuestionsList />}></Route>
            <Route path="/askquestions" element={<AskQuestion />}></Route>
            <Route path="/mypage" element={<Mypage />}></Route>
            <Route path="/questionDetail/:questionId" element={<QuestionDetail />}></Route>
            <Route path="/editQuestion/:questionId" element={<EditQuestion />}></Route>
            <Route path="/searchQuestion" element={<SearchQuestion />}></Route>
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
