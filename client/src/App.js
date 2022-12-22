import React from "react";
import { createGlobalStyle } from "styled-components";
import { BrowserRouter, Routes, Route } from "react-router-dom";
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
							path="/questions"
							element={<QuestionsList />}></Route>
						<Route
							path="/askquestions"
							element={<AskQuestion />}></Route>
						<Route
							path="/mypage"
							element={<Mypage />}></Route>
					</Routes>
				</Section>
				<Ad />
			</Template>
			<Footer />
		</BrowserRouter>
	);
}

export default App;
