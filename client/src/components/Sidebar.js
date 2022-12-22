import React from "react";
import styled from "styled-components";

function Sidebar() {
  return (
    <>
      <SidebarContainer>
        <ul>
          <li>Home</li>
          <li>PUBLIC</li>
          <ul>
            <li>Questions</li>
            <li>Tags</li>
            <li>Users</li>
            <li>Companies</li>
          </ul>
        </ul>
      </SidebarContainer>
    </>
  );
}

const SidebarContainer = styled.div`
  width: 167px;
  height: 100vh;
  border-right: 1px solid lightgray;
`;

export default Sidebar;
