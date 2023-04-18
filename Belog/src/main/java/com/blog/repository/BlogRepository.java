package com.blog.repository;

import com.blog.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByOrderByModifiedAtDesc();
}