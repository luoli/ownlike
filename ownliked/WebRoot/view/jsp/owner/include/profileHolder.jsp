<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="FixedContainer row clearfix">
    <div class="info">
        <a href="${ownUser.image}" class="ProfileImage" target="_blank">
            <img src="${ownUser.image}" alt="Profile Picture of ${ownUser.firstName} ${ownUser.lastName}" />
        </a>
        <div class="content">
            <h1>${ownUser.firstName} ${ownUser.lastName}</h1>
                <p class="colormuted">${ownUser.about}</p>
            <ul id="ProfileLinks" class="icons">
            </ul>
        </div>
    </div>
    <div class="repins">
        <h3>Repins from</h3>
        <ul>
            <li>
                <a href="/tracyklehn/?d">
                    <img src="" alt="Profile Picture of Tracy Klehn" />
                    <strong>Tracy Klehn</strong>
                </a>
            </li>
            <li>
                <a href="/marcikdeleon/?d">
                    <img src="" alt="Profile Picture of Marci De Leon" />
                    <strong>Marci De Leon</strong>
                </a>
            </li>
            <li>
                <a href="/spycmma/?d">
                    <img src="" alt="Profile Picture of Gina Jacobs" />
                    <strong>Gina Jacobs</strong>
                </a>
            </li>
        </ul>
    </div>
</div>
