import React from "react";
import styled from "styled-components";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faEarthAmericas} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

function Sidebar() {
  return <>
    <SidebarContainer>
      <ul>
			<Link to="/" >
        <li>Home</li>
			</Link>
        <li>PUBLIC</li>
		  <Link to="/QuestiosList">
        <li>
			<FontAwesomeIcon icon={faEarthAmericas} className="icon" />
          Questions
        </li>
		  </Link>
        <li>
          Tags
        </li>
        <li>
          Users
        </li>
        <li>
          Companies
        </li>
      </ul>
    </SidebarContainer>
  </>
}

const SidebarContainer = styled.div`
    width: 167px;
    height: 100vh;
    border-right: 1px solid lightgray;
	 padding-top: 20px;
	 font-size: 13px;

	ul{
   	padding-left:0px;
		color: #525960;
}
	li:nth-child(1) {
		font-size: 13px;
		font-weight: 500;
		padding: 4px 4px 4px 8px;
	}

	li:nth-child(2) {
		font-size: 11px;
		margin: 20px 0px 4px 8px;
	}

	a {
		font-weight: 500;
		padding: 8px 6px 8px 8px;
		
		>.icon {
			font-size: 16px;
			
		}
	}
	li:nth-child(n+4) {
		font-weight: 500;
		padding: 13px 4px 4px 30px;

	}
  `;


export default Sidebar;