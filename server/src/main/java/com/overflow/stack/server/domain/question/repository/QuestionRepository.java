package com.overflow.stack.server.domain.question.repository;

import com.overflow.stack.server.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long>{


    Page<Question> findAllByTags_Tag_TagName(String keyword , Pageable pageable);

    Page<Question> findAllByContentContaining(String keyword , Pageable pageable);

    Page<Question> findAllByMember_DisplayName(String keyword, Pageable pageable);

    Page<Question> findAllByTitleContaining(String keyword , Pageable pageable);
}
