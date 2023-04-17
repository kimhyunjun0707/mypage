package com.blog.controller;

import com.blog.dto.BlogRequestDto;

import com.blog.entity.Blog;

import com.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }
    @PostMapping("/api/create")
    public Blog CreateBoard(@RequestBody BlogRequestDto requestDto){
        System.out.println("=========create=========");
        return blogService.createBoard(requestDto);
    }
    @GetMapping("/api/memos")
    public List<Blog> getBlog(){
        System.out.println("here?");

        return blogService.getBlog();
    }


}

