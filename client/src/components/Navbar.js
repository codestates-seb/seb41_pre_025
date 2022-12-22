import React from "react";
import styled from "styled-components";
import logo from "../image/logo.svg"
import { MdSearch } from "react-icons/md";
import Button from "./Button";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <>
      <NavbarContainer>
        <NavbarContents>
          <Link to="/"> 
            <NavbarLogo src={logo} alt="logo"/>
          </Link>
          <SearchBarContainer>
            <MdSearch/>
            <SearchBarInput placeholder="Search..."/>
          </SearchBarContainer>
          <ButtonContainer>
          <Link to="/login"> 
            <Button text="Log In"/>
          </Link>
          <Link to="/signup"> 
            <Button text="Sign Up"/>
          </Link>
          </ButtonContainer>
        </NavbarContents>
      </NavbarContainer>
    </>
  );
}
  const NavbarContainer = styled.div`
      display: flex;
      align-items: center;
      justify-content: center;
      background: red;
      height: 47px;
    `;

  const NavbarContents = styled.div`
      display: flex;
      padding: 0 5px;
      align-items: center;
      background: blue;
      height: 47px;
      width: 1264px;
    `;

  const NavbarLogo = styled.img`
    width: 164px;
  `;

  const SearchBarContainer = styled.div`
    width: 350px;
    display: flex;
    flex: 1;
    flex-direction: row;
    background: white;
    align-items: center;
    border-radius: 2px;

      svg {
        font-size: 35px;
      }
`;

  const SearchBarInput = styled.input`
    color: #000;
    border: 0.5px solid #ccc;
    padding: 0px 10px;
    font-size: 16px;
    outline: none;
	  border: none;
    background-repeat: no-repeat;
    background-size: 20px 20px;
    flex: 1;
  `;

  const ButtonContainer = styled.div`
    display: flex;

    button {
      margin: 5px;
    }
  `;

export default Navbar;