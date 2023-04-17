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
}
