package com.example.post.entity;

import com.example.post.dto.RequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor

public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //고유아이디
    @Column(nullable = false)
    private String password;    //비밀번호
    @Column(nullable = false)
    private String title;       //제목
    @Column(nullable = false)
    private String author;      //작성자
    @Column(nullable = false)
    private String content;     //내용

    //requestDto로 받아온 정보를 받아주고 초기화.
    //service에 있는createPost에서 repository.save를 통해서 데이터베이스에저장
    //
    public Post(RequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
    //수정할데이터를 가저온다.
    public void update(RequestDto requestDto) {
//        this.password=requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
