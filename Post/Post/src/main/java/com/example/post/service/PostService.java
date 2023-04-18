package com.example.post.service;


import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;



    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다"));
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPostList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Post createPost(PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto, String password) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다"));
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        post.update(requestDto);
        return postRepository.save(post);
    }
    //Optional사용
    public String deletePost(Long id, String password) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getPassword().equals(password)) {
                postRepository.delete(post);
                return "Success";
            } else {
                return "Wrong Password";
            }
        } else {
            return "Post not found";
        }
    }

    }


