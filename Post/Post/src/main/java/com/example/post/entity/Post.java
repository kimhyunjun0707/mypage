package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;            //고유번호
    @Column(nullable = false)
    private String password;    //비밀번호
    @Column(nullable = false)
    private String author;      //작성자
    @Column(nullable = false)
    private String title;       //제목
    @Column(nullable = false)
    private String content;     //내용

    public Post(PostRequestDto requestDto){
        this.author   = getAuthor();
        this.title    = getTitle();
        this.content  = getTitle();
        this.password = getPassword();
    }


    public void update(PostRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getAuthor() != null) {
            this.author = requestDto.getAuthor();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
        if (requestDto.getPassword() != null) {
            this.password = requestDto.getPassword();
        }
    }
}
