package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
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
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @GetMapping("/api/posts")
    public List<PostResponseDto> getCrudList() {
        return postService.getPostList();
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        Post post = postService.createPost(requestDto);
        return new PostResponseDto(post);
    }

    @PostMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @RequestParam String password){
        Post post = postService.updatePost(id, requestDto, password);
        return new PostResponseDto(post);
    }
    @DeleteMapping("/api/post/{id}")
    public String deletePost(@PathVariable Long id, @PathVariable String password) {
        return postService.deletePost(id, password);
    }







}
