package com.example.post.dto;

import lombok.Getter;

@Getter
public class RequestDto {
    private Long id;
    private String title;
    private String content;

    public RequestDto(RequestDto requestDto) {
        this.title = requestDto.title;
        this.content = requestDto.content;
    }
    public RequestDto() {
    }
}
