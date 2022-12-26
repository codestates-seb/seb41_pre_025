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
							<FooterIcon
								src={icon}
								alt="icon"
							/>
						</Link>
					</FooterLogo>
					<FooterNav>
						<FooterNavCol>
							<h5>
								<a
									href="https://stackoverflow.com"
									target="_blank"
									rel="noreferrer">
									STACK OVERFLOW
								</a>
							</h5>
							<List>
								<li>
									<a href="/">Questions</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/help"
										target="_blank"
										rel="noreferrer">
										Help{" "}
									</a>
								</li>
							</List>
						</FooterNavCol>
						<FooterNavCol>
							<h5>
								<a
									href="https://stackoverflow.co/"
									target="_blank"
									rel="noreferrer">
									PRODUCTS
								</a>
							</h5>
							<List>
								<li>
									<a
										href="https://stackoverflow.co/teams"
										target="_blank"
										rel="noreferrer">
										Teams
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/advertising"
										target="_blank"
										rel="noreferrer">
										Advertising
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/collectives"
										target="_blank"
										rel="noreferrer">
										Collectives
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/talent"
										target="_blank"
										rel="noreferrer">
										Talent
									</a>
								</li>
							</List>
						</FooterNavCol>
						<FooterNavCol>
							<h5>
								<a
									href="https://stackoverflow.co/"
									target="_blank"
									rel="noreferrer">
									COMPANY
								</a>
							</h5>
							<List>
								<li>
									<a
										href="https://stackoverflow.co/"
										target="_blank"
										rel="noreferrer">
										About
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/company/press"
										target="_blank"
										rel="noreferrer">
										Press
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/company/work-here"
										target="_blank"
										rel="noreferrer">
										Work Here
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/legal"
										target="_blank"
										rel="noreferrer">
										Legal
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/legal/privacy-policy"
										target="_blank"
										rel="noreferrer">
										Privacy Policy
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/legal/terms-of-service"
										target="_blank"
										rel="noreferrer">
										Terms of Service
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.co/company/contact"
										target="_blank"
										rel="noreferrer">
										Contact Us
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/legal/cookie-policy"
										target="_blank"
										rel="noreferrer">
										Cookie Settings
									</a>
								</li>
								<li>
									<a
										href="https://stackoverflow.com/legal/cookie-policy"
										target="_blank"
										rel="noreferrer">
										Cookie Policy
									</a>
								</li>
							</List>
						</FooterNavCol>
						<FooterNavCol>
							<h5>
								<a
									href="https://stackexchange.com"
									target="_blank"
									rel="noreferrer">
									STACK EXCHANGE NETWORK
								</a>
							</h5>
							<List>
								<li>
									<a
										href="https://stackexchange.com/sites#technology"
										target="_blank"
										rel="noreferrer">
										Technology
									</a>
								</li>
								<li>
									<a
										href="https://stackexchange.com/sites#culturerecreation"
										target="_blank"
										rel="noreferrer">
										Culture & recreation
									</a>
								</li>
								<li>
									<a
										href="https://stackexchange.com/sites#lifearts"
										target="_blank"
										rel="noreferrer">
										Life & arts
									</a>
								</li>
								<li>
									<a
										href="https://stackexchange.com/sites#science"
										target="_blank"
										rel="noreferrer">
										Science
									</a>
								</li>
								<li>
									<a
										href="https://stackexchange.com/sites#professional"
										target="_blank"
										rel="noreferrer">
										Professional
									</a>
								</li>
								<li>
									<a
										href="https://stackexchange.com/sites#business"
										target="_blank"
										rel="noreferrer">
										Business
									</a>
								</li>
								<li className="md16">
									<a
										href="https://api.stackexchange.com/"
										target="_blank"
										rel="noreferrer">
										API
									</a>
								</li>
								<li>
									<a
										href="https://data.stackexchange.com/"
										target="_blank"
										rel="noreferrer">
										Data
									</a>
								</li>
							</List>
						</FooterNavCol>
					</FooterNav>
					<FooterCopyright>
						<List>
							<li>
								<a
									href="https://stackoverflow.blog?blb=1"
									target="_blank"
									rel="noreferrer">
									Blog
								</a>
							</li>
							<li>
								<a
									href="https://www.facebook.com/officialstackoverflow/"
									target="_blank"
									rel="noreferrer">
									Facebook
								</a>
							</li>
							<li>
								<a
									href="https://twitter.com/stackoverflow"
									target="_blank"
									rel="noreferrer">
									Twitter{" "}
								</a>
							</li>
							<li>
								<a
									href="https://linkedin.com/company/stack-overflow"
									target="_blank"
									rel="noreferrer">
									Linkedln
								</a>
							</li>
							<li>
								<a
									href="https://www.instagram.com/thestackoverflow"
									target="_blank"
									rel="noreferrer">
									Instagram
								</a>
							</li>
						</List>
						<p>
							Site design / logo © 2022 Stack Exchange Inc; user contributions
							licensed under CC BY-SA. rev 2022.12.21.43127
						</p>
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
