import React from "react";
import styled from "styled-components";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faEarthAmericas} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

function Sidebar() {
  return <>
    <SidebarContainer>
      <ul>
        <li>
			<Link to="/" >
			Home
			</Link>
		  </li>
        <li>PUBLIC</li>
        <li>
		  <Link to="/QuestiosList">
			<FontAwesomeIcon icon={faEarthAmericas} className="icon" />
          Questions
		  </Link>
        </li>
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
		&:hover {
			color: black;
		}
	}
	
	li:nth-child(2) {
		font-size: 11px;
		margin: 25px 0px 15px 8px;
	}
	
	a {
		color: #525960;
		text-decoration: none;
		font-weight: 500;
		padding: 4px 4px 4px 8px;
		>.icon {
			font-size: 16px;
			margin: -1px 4px 0px 0px;
			
		}
		&:hover {
			color: black;
		}
	}
	li:nth-child(n+4) {
		font-weight: 500;
		padding: 20px 4px 4px 30px;

		&:hover {
			color: black;
		}
	}
  `;


export default Sidebar;

