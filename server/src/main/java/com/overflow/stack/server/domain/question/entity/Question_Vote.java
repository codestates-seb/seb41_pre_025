package com.overflow.stack.server.domain.question.entity;

import com.overflow.stack.server.domain.member.entity.Member;
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
public class Question_Vote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    private boolean voteUp;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;

    public Question_Vote(boolean voteUp, Question question, Member member) {
        this.voteUp = voteUp;
        this.question = question;
        this.member = member;
    }
}
