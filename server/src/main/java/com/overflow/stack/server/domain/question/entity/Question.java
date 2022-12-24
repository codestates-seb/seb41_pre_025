package com.overflow.stack.server.domain.question.entity;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private Long voteResult;
    @ManyToOne
    @JoinColumn(name = "MEMEBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Answer> answers= new ArrayList<>();

}
