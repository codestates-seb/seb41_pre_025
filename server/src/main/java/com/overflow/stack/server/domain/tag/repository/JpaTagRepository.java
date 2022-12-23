package com.overflow.stack.server.domain.tag.repository;

import com.overflow.stack.server.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTagRepository extends JpaRepository<Tag,Long> {

    Optional<Tag> findByTagName(String tagName);
}
