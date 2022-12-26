package com.overflow.stack.server.domain.question.entity;

import com.overflow.stack.server.domain.member.entity.Member;
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
public class Question_Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;

    public Question_Vote(Question question, Member member) {
        this.question = question;
        this.member = member;
    }
}
