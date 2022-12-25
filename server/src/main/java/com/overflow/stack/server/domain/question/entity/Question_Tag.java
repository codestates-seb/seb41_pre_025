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

    public void addQuestion(Question question){
        this.question=question;
        if(!this.question.getTags().contains(this)) {
            this.question.addQuestionTag(this);
        }
    }
    public void addTag(Tag tag){
        this.tag=tag;
        if(!this.tag.getQuestionTags().contains(this)){
            this.tag.addQuestionTag(this);
        }
    }

    public Question_Tag(Question question, Tag tag) {
        this.question = question;
        this.tag = tag;
    }
}
