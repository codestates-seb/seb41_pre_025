package com.overflow.stack.server.domain.member.utils;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberExpectedAction {
   public static ResultActions expectedResponse(ResultActions resultActions, MemberDto.Response response) throws Exception {
      return resultActions
              .andExpect(status().isOk())
              .andExpect(jsonPath("data.memberId").value(response.getMemberId()))
              .andExpect(jsonPath("data.email").value(response.getEmail()))
              .andExpect(jsonPath("data.fullName").value(response.getFullName()))
              .andExpect(jsonPath("data.displayName").value(response.getDisplayName()))
              .andExpect(jsonPath("data.aboutMe").value(response.getAboutMe()))
              .andExpect(jsonPath("data.aboutMeTitle").value(response.getAboutMeTitle()))
              .andExpect(jsonPath("data.twitterLink").value(response.getTwitterLink()))
              .andExpect(jsonPath("data.githubLink").value(response.getGithubLink()))
              .andExpect(jsonPath("data.websiteLink").value(response.getWebsiteLink()))
              .andExpect(jsonPath("data.location").value(response.getLocation()))
              .andExpect(jsonPath("data.imgUrl").value(response.getImgUrl()))
              .andExpect(jsonPath("data.isFollowingTags").isArray())
              .andExpect(jsonPath("data.isFollowingTags[0]").value(response.getIsFollowingTags().get(0)))
              .andExpect(jsonPath("data.isUnFollowingTags").isArray());
   }
}
