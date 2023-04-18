package com.example.post.dto;

import com.example.post.entity.Post;
import com.example.post.entity.Timestamped;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostResponseDto extends Timestamped{
    private Long   id;
    private String title;
    private String author;
    private String content;

    public PostResponseDto(Post post) {
        this.id      =  post.getId();
        this.title   =  post.getTitle();
        this.author  =  post.getAuthor();
        this.content =  post.getContent();
    }
}