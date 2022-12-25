package com.overflow.stack.server.domain.tag.entity;

import com.overflow.stack.server.domain.question.entity.Question_Tag;
import com.overflow.stack.server.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @Column(nullable = false , name = "TAG_NAME")
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private Set<Question_Tag> questionTags;

    public Tag(String tagName){
        this.tagName = tagName;
    }
    public void addQuestionTag(Question_Tag qTag){
        if(qTag.getTag()!=this){
            qTag.setTag(this);
        }
        this.questionTags.add(qTag);
    }
}
