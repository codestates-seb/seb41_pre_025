package com.overflow.stack.server.domain.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
//    @OneToMany(mappedBy = "question")
//    private List<Member> member = new ArrayList<>();
}
