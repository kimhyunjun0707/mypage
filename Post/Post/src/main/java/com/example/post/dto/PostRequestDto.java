package com.example.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String author;
    private String content;
    private String password;
}
