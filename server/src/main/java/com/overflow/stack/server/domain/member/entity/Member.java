package com.overflow.stack.server.domain.member.entity;

import com.overflow.stack.server.global.audit.Auditable;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    // only for mapping
    private Long memberId;
    @Column(nullable = false,updatable = false, unique = true, length = 20)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(length = 20)
    private String fullName;
    @Column(nullable = false, length = 20)
    private String displayName;
    @Column(length = 50)
    @ColumnDefault("''")
    private String location;
    @Column(length = 30)
    @ColumnDefault("''")
    private String aboutMeTitle;
    @Lob
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

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "member" , orphanRemoval = true)
    private Set<Member_Tag> tags = new HashSet<>();

    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }

}
