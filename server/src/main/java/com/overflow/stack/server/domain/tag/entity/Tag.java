package com.overflow.stack.server.domain.tag.entity;

import com.overflow.stack.server.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Tag(String tagName){
        this.tagName = tagName;
    }

}
