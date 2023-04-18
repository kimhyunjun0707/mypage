package com.blog.controller;

import com.blog.dto.BlogRequestDto;

import com.blog.entity.Blog;

import com.blog.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;


    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    //create
    @PostMapping("/api/create")
    public Blog CreateBoard(@RequestBody BlogRequestDto requestDto){
        System.out.println("=========create=========");
        return blogService.createBoard(requestDto);
    }
    //read
    @PostMapping("/api/read")
    public List<Blog> getBlog(){
        System.out.println("here?");

        return blogService.getBlog();
    }
    @PutMapping("api/update/{id}")
    public Long updateblog(@PathVariable Long id,@RequestBody BlogRequestDto requestDto){
        return blogService.update(id, requestDto);
    }


    @PostMapping("/api/check_password/{id}")
    public String getPassword(@PathVariable Long id) throws JsonProcessingException {
        String password = blogService.getPasswordById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(Collections.singletonMap("result", password)));
        return objectMapper.writeValueAsString(Collections.singletonMap("result", password));
    }
    @DeleteMapping("api/delete/{id}")
    public Long deleteBlog(@PathVariable Long id){
        return blogService.deleteBlog(id);
    }




}

