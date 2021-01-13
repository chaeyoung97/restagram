package com.example.restagram.domain.posts;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.images.Images;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.tables.LikesTables;
import com.example.restagram.domain.tables.TagsTables;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity implements Serializable {

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

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Images> images;
    @Transient
    private Long commentsCnt = new Long(0);
    @Transient
    private Long likeCnt = new Long(0);

    @Builder
    public Posts(Users user, String content){
        this.user = user;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }

    public void addComments(){
        this.commentsCnt++;
    }

    public void removeComments(){
        this.commentsCnt--;
    }

    public void addLikes(){
        this.likeCnt++;
    }

    public void removeLikes(){
        this.likeCnt--;
    }
}
