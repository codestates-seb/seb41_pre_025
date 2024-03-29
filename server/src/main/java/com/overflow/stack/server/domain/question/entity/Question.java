package com.overflow.stack.server.domain.question.entity;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.entity.Member_Tag;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(nullable = false)
    @Lob
    private String content;
    @Column
    private Long voteResult=0L;
    @Column
    private Long answerCount=0L;
    @ManyToOne
    @JoinColumn(name = "MEMEBER")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "question", orphanRemoval = true)
    private Set<Question_Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers= new ArrayList<>();

}
