package com.example.restagram.domain.tables;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tags.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class TagsTables {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tag;

    @Builder
    public TagsTables(Posts posts, Tags tags){
        this.post = posts;
        this.tag = tags;
    }
}
