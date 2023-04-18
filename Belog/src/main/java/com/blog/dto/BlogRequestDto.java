package com.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
    private String title;   //제목
    private String username;//작성자명
    private String contents;//작성내용
    private String password;//비밀번호

}
