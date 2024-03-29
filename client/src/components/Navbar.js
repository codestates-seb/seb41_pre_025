import React from "react";
import styled from "styled-components";
import logo from "../image/logo.svg";
import { MdSearch, MdAccountCircle } from "react-icons/md";
import { Button } from "./Button";
import { Link, useNavigate } from "react-router-dom";
import { loginState, searchListState, searchState, userInfoState } from "../state/atom";
import { useRecoilState } from "recoil";
import { fetchSearchQuestion } from "../util/useFetchSearch";

function Navbar() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useRecoilState(loginState);
  const [search, setSearch] = useRecoilState(searchState);
  const [searchQueList, setSearchQueList] = useRecoilState(searchListState);
  const [useInfo, setuserInfo] = useRecoilState(userInfoState);

  function searchHandler(e) {
    setSearch(e.target.value);
  }

  const logout = () => {
    sessionStorage.removeItem("access_token");
    setIsLogin(false);
    navigate("/");
    setuserInfo({});
  };
  return (
    <>
      <NavbarContainer>
        <NavbarContents>
          <Link to="/">
            <NavbarLogo src={logo} alt="logo" />
          </Link>
          <SearchBarContainer>
            <MdSearch />
            <SearchBarInput
              onChange={searchHandler}
              placeholder="Search..."
              onKeyUp={(event) => {
                if (event.key === "Enter") {
                  fetchSearchQuestion(search).then((data) => {
                    setSearchQueList(data.data);
                    navigate("/searchQuestion");
                  });
                }
              }}
            />
          </SearchBarContainer>
          <ButtonContainer>
            {isLogin ? (
              <>
                <Link to="/mypage">
                  <MdAccountCircle />
                </Link>
                <Link to="/">
                  <Button onClick={logout} text="Log Out" />
                </Link>
              </>
            ) : (
              <>
                <Link to="/login">
                  <Button text="Log In" marginLeft="0px" />
                </Link>
                <Link to="/signup">
                  <Button text="Sign Up" color="white" border="1px solid #4393F7" bgColor="#4393F7" hoverColor="#2D75C6" activeColor="#1859A3" />
                </Link>
              </>
            )}
          </ButtonContainer>
        </NavbarContents>
      </NavbarContainer>
    </>
  );
}
const NavbarContainer = styled.div`
  z-index: 1;
  position: sticky;
  top: 0;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f8f9f9;
  border-top: 3px solid #e5883e;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
`;

const NavbarContents = styled.div`
  display: flex;
  align-items: center;
  background: #f8f9f9;
  height: 44px;
  width: 1264px;
`;

const NavbarLogo = styled.img`
  width: 157px;
  padding: 0 8px;
`;

const SearchBarContainer = styled.div`
  width: 350px;
  display: flex;
  flex: 1;
  flex-direction: row;
  background: white;
  align-items: center;
  border-radius: 5px;
  border: 1px solid #bbbfc3;
  margin: 0 10px;

  svg {
    font-size: 29px;
    color: #858c94;
    padding: 3px;
    margin: 0 3px;
  }
`;

const SearchBarInput = styled.input`
  color: #000;
  border: 0.5px solid #ccc;
  font-size: 16px;
  outline: none;
  border: none;
  background-repeat: no-repeat;
  background-size: 20px 20px;
  flex: 1;
`;

const ButtonContainer = styled.div`
  display: flex;
  align-items: center;

  svg {
    margin: 5px 2px 0 0;
    font-size: 33px;
    color: #858c94;
  }
`;

export default Navbar;
