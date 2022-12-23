package com.overflow.stack.server.domain.tag.service;

import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.domain.tag.repository.JpaTagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TagServiceImpl implements TagService {
    private final JpaTagRepository jpaTagRepository;

    public TagServiceImpl(JpaTagRepository jpaTagRepository) {
        this.jpaTagRepository = jpaTagRepository;
    }

    @Override
    public Optional<Tag> findTagByTagName(String tagName) {
        return jpaTagRepository.findByTagName(tagName);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return jpaTagRepository.save(tag);
    }
}
