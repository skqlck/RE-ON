package reon.app.domain.post.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import reon.app.domain.member.entity.Member;
import reon.app.global.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@DynamicInsert
@ToString
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private Scope scope;
    @Column(name = "action_path")
    private String actionPath;
    @Column(nullable = false)
    private int deleted;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> postComments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval -> null 처리시 자식을 delete
    private List<PostLike> postLikes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(Long id, String title, String content, Scope scope, String actionPath, int deleted, List<PostComment> postComments, List<PostLike> postLikes, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.scope = scope;
        this.actionPath = actionPath;
        this.deleted = deleted;
        this.postComments = postComments;
        this.postLikes = postLikes;
        this.member = member;
    }
}

    // Todo 2023.08.06 : 원본영상 연결해야할까 ?

