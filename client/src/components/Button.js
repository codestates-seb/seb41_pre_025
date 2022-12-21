import React from "react";
import styled from "styled-components";

    const ButtonTemplate = styled.button`
        padding: 7px;
        color: black;
        align-items: center;
        text-align: center;
        font-size: 16px;
        border-radius: 2px;
        border: none;
        outline: none;
        cursor: pointer;
    `;

    export default function Button(props) {
        const { text } = props;
            return <ButtonTemplate>
                 {text}            
                </ButtonTemplate>
        }

