package com.example.post.controller;

import com.example.post.dto.RequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home()
    {
        return new ModelAndView("index");
    }
    //목록 전체조회(아이디별로)
    @GetMapping("/api/posts")
    public List<ResponseDto> getPosts( HttpServletRequest request){
        return postService.getPosts(request);
    }
    //목록 전체조회
    @GetMapping("/api/all-posts")
    public List<ResponseDto> getAllPosts(){
        return postService.getAllPosts();
    }
    //단일 조회한다.
    //@PathVariable를 사용하여 클라이언트에서 받아온 경로의 일부를 추출하여
    //메소드의 파라미터로전달
    @GetMapping("/api/posts/{id}")
    public ResponseDto getPost(@PathVariable Long id, HttpServletRequest request){
        return postService.getPost(id,request);
    }
    //게시글 작성
    //@RequestBody - HTTP 요청 본문에 담긴 데이터를 자바 객체로 변환하는 역할
    //HTTP 요청에 담긴 데이터를 RequestDto 객체로 변환하여 createPost() 메소드에 전달
    @PostMapping("/api/post")
    public ResponseDto createPost(@RequestBody RequestDto requestDto, HttpServletRequest request){
        System.out.println("----");

        return postService.create(requestDto, request);
    }
    //게시글수정
    //요청한 아이디를 PathVariable로 받아오고
    //내용도 받아와야하기때문에 요청한정보를 RequestBody통해 받아준다.
    @PutMapping("/api/post/{id}")
    public ResponseDto updatePost(@PathVariable Long id,@RequestBody RequestDto requestDto,HttpServletRequest request){
        System.out.println("update");
        return postService.update(id,requestDto,request);
    }
    //게시글삭제
    //아이디를 PathVariable로 받아오고
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Map<String, Object>> login(@PathVariable Long id, HttpServletRequest request){
        postService.delete(id,request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "삭제성공");
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
