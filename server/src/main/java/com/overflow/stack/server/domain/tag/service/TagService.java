package com.overflow.stack.server.domain.tag.service;

import com.overflow.stack.server.domain.tag.entity.Tag;

import java.util.Optional;

public interface TagService {
    Optional<Tag> findTagByTagName(String tagName);
    Tag saveTag(Tag tag);
}
