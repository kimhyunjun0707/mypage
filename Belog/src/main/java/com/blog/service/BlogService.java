package com.blog.service;


import com.blog.dto.BlogRequestDto;
import com.blog.entity.Blog;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public Blog createBoard(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;

    }

    @Transactional(readOnly = true)
    public List<Blog> getBlog() {

        return blogRepository.findAllByOrderByModifiedAtDesc();

    }

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () ->new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return blog.getId();
    }

    public String getPasswordById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return blog.getPassword();
    }
    public String getPassword(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
        System.out.println("password : "+blog.getPassword());
        return blog.getPassword();
    }

    public Long deleteBlog(Long id) {
        blogRepository.deleteById(id);
        return id;

    }
}
