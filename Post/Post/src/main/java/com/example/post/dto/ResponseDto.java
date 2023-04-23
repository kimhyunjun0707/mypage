package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;

@Getter
public class ResponseDto {
    //클라이언트에게 보내줄 정보들

    private Long postId;

    private String username;
    private String title;
    private String content;

    private String createdAt;
    private String modifiedAt;


    public ResponseDto(Post post) {
        this.createdAt = String.valueOf(post.getCreatedAt());   //문자열로변환
        this.modifiedAt = String.valueOf(post.getModifiedAt()); //문자열로변환
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
    }



}
