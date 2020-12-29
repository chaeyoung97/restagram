package com.example.restagram.domain.posts;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.tables.LikesTables;
import com.example.restagram.domain.tables.TagsTables;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    @Column(length = 1024)
    private String content;

    @OneToMany(mappedBy = "post" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TagsTables> tags ;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LikesTables> likes ;

    @OrderBy("createdDate asc")
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comments> comments ;

    @Builder
    public Posts(String content){
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
