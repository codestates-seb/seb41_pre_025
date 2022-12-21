package com.overflow.stack.server.domain.member.entity;

import com.overflow.stack.server.global.audit.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(nullable = false, unique = true, length = 20)
    private String email;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 20)
    private String fullName;
    @Column(nullable = false, length = 20)
    private String displayName;
    @Column( length = 50)
    @ColumnDefault("''")
    private String location;
    @Column(length = 30)
    @ColumnDefault("''")
    private String aboutMeTitle;
    @Lob
    @ColumnDefault("''")
    private String aboutMe;
    @Column(length = 100)
    @ColumnDefault("''")
    private String githubLink;

    @Column( length = 100)
    @ColumnDefault("''")
    private String twitterLink;
    @Column( length = 100)
    @ColumnDefault("''")
    private String websiteLink;
    @Column(length = 100)
    @ColumnDefault("'https://cdn-icons-png.flaticon.com/512/14/14660.png'")
    private String imgUrl;


}
