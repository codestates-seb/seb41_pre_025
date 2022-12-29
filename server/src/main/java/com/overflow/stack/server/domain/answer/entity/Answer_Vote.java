package com.overflow.stack.server.domain.answer.entity;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer_Vote extends Auditable {
    @Id
    private long AnswerVoteId;

    private boolean voteUp;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private Member member;

    public Answer_Vote(boolean voteUp, Answer answer, Member member) {
        this.voteUp = voteUp;
        this.answer = answer;
        this.member = member;
    }
}
