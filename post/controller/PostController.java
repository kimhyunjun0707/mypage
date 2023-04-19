package com.example.post.controller;

import com.example.post.dto.RequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home()
    {
        return new ModelAndView("index");
    }
    //목록 전체조회
    @GetMapping("/api/posts")
    public List<ResponseDto> getPosts(){
        return postService.getPosts();
    }
    //단일 조회한다.
    //@PathVariable를 사용하여 클라이언트에서 받아온 경로의 일부를 추출하여
    //메소드의 파라미터로전달
    @GetMapping("/api/posts/{id}")
    public ResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }
    //게시글 작성
    //@RequestBody - HTTP 요청 본문에 담긴 데이터를 자바 객체로 변환하는 역할
    //HTTP 요청에 담긴 데이터를 RequestDto 객체로 변환하여 createPost() 메소드에 전달
    @PostMapping("/api/post")
    public ResponseDto createPost(@RequestBody RequestDto requestDto){
        return postService.create(requestDto);
    }
    //게시글수정
    //요청한 아이디를 PathVariable로 받아오고
    //내용도 받아와야하기때문에 요청한정보를 RequestBody통해 받아준다.
    @PutMapping("/api/post/{id}")
    public ResponseDto updatePost(@PathVariable Long id,@RequestBody RequestDto requestDto){
        return postService.update(id,requestDto);
    }
    //게시글삭제
    //아이디를 PathVariable로 받아오고
    @DeleteMapping("/api/post/{id}")
    public String deletePost(@PathVariable Long id,@RequestBody RequestDto requestDto){
        return postService.delete(id,requestDto);
    }


}
