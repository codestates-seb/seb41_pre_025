package com.overflow.stack.server.domain.answer.entity;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "voteResult")
    private Long voteResult = 0L;

    @Column
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "QUESTION")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "MEMBER")
    private Member member;

    public Answer(Question question) {
        this.question = question;
    }
}
