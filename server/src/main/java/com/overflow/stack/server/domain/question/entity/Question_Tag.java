package com.overflow.stack.server.domain.question.entity;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question_Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Tag tag;
}
