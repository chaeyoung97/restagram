package com.example.restagram.domain.posts;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.Users.User;
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
    private User user;

    @Column(length = 1024)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<TagsTables> tags ;

    @OneToMany(mappedBy = "post")
    private List<LikesTables> likes ;

    @OrderBy("createdDate asc")
    @OneToMany(mappedBy = "post")
    private List<Comments> comments ;

    @Builder
    public Posts(User user, String content){
        this.user = user;
        this.content = content;
    }

//    public void addTag(Tags tag){
//        tags.add(tag);
//    }
}
