import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import icon from "../image/icon.svg";

export default function Section() {
  return (
    <>
      <FooterContainer>
        <FooterContents>
          <FooterLogo>
            <Link to="/">
              <FooterIcon src={icon} alt="icon" />
            </Link>
          </FooterLogo>
          <FooterNav>
            <FooterNavCol>
              <h5>
                <a href="https://stackoverflow.com">STACK OVERFLOW</a>
              </h5>
              <List>
                <li>
                  <a href="/">Questions</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/help">Help </a>
                </li>
              </List>
            </FooterNavCol>
            <FooterNavCol>
              <h5>
                <a href="https://stackoverflow.co/">PRODUCTS</a>
              </h5>
              <List>
                <li>
                  <a href="https://stackoverflow.co/teams">Teams</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/advertising">Advertising</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/collectives">Collectives</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/talent">Talent</a>
                </li>
              </List>
            </FooterNavCol>
            <FooterNavCol>
              <h5>
                <a href="https://stackoverflow.co/">COMPANY</a>
              </h5>
              <List>
                <li>
                  <a href="https://stackoverflow.co/">About</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/company/press">Press</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/company/work-here">Work Here</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/legal">Legal</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/legal/privacy-policy">Privacy Policy</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/legal/terms-of-service">Terms of Service</a>
                </li>
                <li>
                  <a href="https://stackoverflow.co/company/contact">Contact Us</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/legal/cookie-policy">Cookie Settings</a>
                </li>
                <li>
                  <a href="https://stackoverflow.com/legal/cookie-policy">Cookie Policy</a>
                </li>
              </List>
            </FooterNavCol>
            <FooterNavCol>
              <h5>
                <a href="https://stackexchange.com">STACK EXCHANGE NETWORK</a>
              </h5>
              <List>
                <li>
                  <a href="https://stackexchange.com/sites#technology">Technology</a>
                </li>
                <li>
                  <a href="https://stackexchange.com/sites#culturerecreation">Culture & recreation</a>
                </li>
                <li>
                  <a href="https://stackexchange.com/sites#lifearts">Life & arts</a>
                </li>
                <li>
                  <a href="https://stackexchange.com/sites#science">Science</a>
                </li>
                <li>
                  <a href="https://stackexchange.com/sites#professional">Professional</a>
                </li>
                <li>
                  <a href="https://stackexchange.com/sites#business">Business</a>
                </li>
                <li className="md16">
                  <a href="https://api.stackexchange.com/">API</a>
                </li>
                <li>
                  <a href="https://data.stackexchange.com/">Data</a>
                </li>
              </List>
            </FooterNavCol>
          </FooterNav>
          <FooterCopyright>
            <List>
              <li>
                <a href="https://stackoverflow.blog?blb=1">Blog</a>
              </li>
              <li>
                <a href="https://www.facebook.com/officialstackoverflow/">Facebook</a>
              </li>
              <li>
                <a href="https://twitter.com/stackoverflow">Twitter </a>
              </li>
              <li>
                <a href="https://linkedin.com/company/stack-overflow">Linkedln</a>
              </li>
              <li>
                <a href="https://www.instagram.com/thestackoverflow">Instagram</a>
              </li>
            </List>
            <p>Site design / logo © 2022 Stack Exchange Inc; user contributions licensed under CC BY-SA. rev 2022.12.21.43127</p>
          </FooterCopyright>
        </FooterContents>
      </FooterContainer>
    </>
  );
}

const FooterContainer = styled.div`
  display: flex;
  justify-content: center;
  width: 100wh;
  height: 300px;
  background-color: hsl(210, 8%, 15%);
  color: white;
`;

const FooterContents = styled.div`
  display: flex;
  padding: 32px 12px 12px 12px;
  width: 1264px;
`;

const FooterLogo = styled.div`
  padding-right: 30px;
`;
const FooterIcon = styled.img`
  width: 32px;
`;
const FooterNav = styled.div`
  display: flex;
  flex: 1;
`;
const FooterNavCol = styled.div`
  flex: 1;
  h5 {
    margin: 0;
    a {
      color: #babfc4;
      text-decoration: none;
    }
  }
  .md16 {
    margin-top: 16px;
  }
`;
const FooterCopyright = styled.div`
  width: 300px;
  font-size: 11px;
  ul {
    margin: 0;
  }
  a {
    float: left;
    margin-left: 10px;
  }
  p {
    margin-top: 202px;
    color: #9199a1;
  }
`;

const List = styled.ul`
  list-style: none;
  padding-left: 0px;
  a {
    color: #9199a1;
    text-decoration: none;
    font-size: 13px;
  }
`;
