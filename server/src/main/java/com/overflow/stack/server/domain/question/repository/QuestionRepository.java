package com.overflow.stack.server.domain.question.repository;

import com.overflow.stack.server.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findAllByTags_Tag_TagName(String keyword);

    List<Question> findAllByContentContaining(String keyword);

    List<Question> findAllByMember_DisplayName(String keyword);

    List<Question> findAllByTitleContaining(String keyword);
}
