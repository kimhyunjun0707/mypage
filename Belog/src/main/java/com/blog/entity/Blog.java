package com.blog.entity;

import com.blog.dto.BlogRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends com.example.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //사용자 고유번호
    @Column(nullable = false)
    private String password;    //비밀번호
    @Column(nullable = false)
    private String title;       // 제목
    @Column(nullable = false)
    private String username;    //작성자
    @Column(nullable = false)
    private String contents;    //내용

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();


    }
}
