package com.overflow.stack.server.domain.member.entity;

import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member_Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTagId;
    @ManyToOne
    private Member member;

    @ManyToOne
    private Tag tag;

    private boolean isFollowed;

    public Member_Tag(Tag tag, boolean isFollowed) {
        this.tag = tag;
        this.isFollowed = isFollowed;
    }
}
