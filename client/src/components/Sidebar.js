import React from "react";
import styled from "styled-components";
import { FaGlobeAmericas } from "react-icons/fa";
import { NavLink } from "react-router-dom";

function Sidebar() {
	return (
		<>
			<SidebarContainer>
				<div>
					<ul>
						<li>
							<NavLink
								className={({ isActive }) => (isActive ? "active" : "not")}
								to="/">
								Home
							</NavLink>
						</li>
						<li>PUBLIC</li>
						<li>
							<NavLink
								className={({ isActive }) => (isActive ? "active" : "not")}
								to="/questionslist">
								<FaGlobeAmericas className="icon" />
								Questions
							</NavLink>
						</li>
						<li>
							<NavLink
								className={({ isActive }) => (isActive ? "active" : "not")}
								to="/tags">
								Tags
							</NavLink>
						</li>
						<li>
							<NavLink
								className={({ isActive }) => (isActive ? "active" : "not")}
								to="/users">
								Users
							</NavLink>
						</li>
						<li>
							<NavLink
								className={({ isActive }) => (isActive ? "active" : "not")}
								to="/companies">
								Companies
							</NavLink>
						</li>
					</ul>
				</div>
			</SidebarContainer>
		</>
	);
}

const SidebarContainer = styled.div`
	width: 167px;
	border-right: 1px solid lightgray;
	padding-top: 20px;
	font-size: 13px;

	div {
		width: 100%;
		position: -webkit-sticky;
		position: sticky;
		top: 83px;
	}

	ul {
		padding-left: 0px;
		color: #525960;
	}

	li:nth-child(1) > a {
		padding: 8px 6px 8px 8px;
	}
	li:nth-child(2) {
		font-size: 11px;
		margin: 16px 0px 4px 8px;
	}
	li:nth-child(3) > a {
		display: flex;
		align-items: center;
		padding: 8px 6px 8px 8px;
		> .icon {
			margin-right: 4px;
			font-size: 16px;
		}
	}
	li:nth-child(n + 4) > a {
		padding: 8px 8px 8px 30px;
	}
	a {
		display: block;
		color: #525960;
		text-decoration: none;
		font-weight: 500;
		&:hover {
			color: black;
		}
		&.active {
			color: black;
			font-weight: bold;
			background: #f1f2f3;
			border-right: 3px solid #f48225;
		}
	}
`;

export default Sidebar;
