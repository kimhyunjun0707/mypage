package com.example.post.service;

import com.example.post.dto.RequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //@Transactional
    //데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터 추가, 갱신, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다.
    // 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다
    //모두조회
    @Transactional
    public List<ResponseDto> getPosts() {
        List<Post> posts =postRepository.findAllByOrderByModifiedAtDesc();  //내림차순으로받아오고
        List<ResponseDto> Posts = new ArrayList<>();                        //반환타입을 정해주고새로운 어레이리스트생성
        for (Post post : posts) {                                           //반복문돌면서 생성한곳에 넣어준다
            Posts.add(new ResponseDto(post));
        }
        return Posts;


    }
    //postRepository.findById(<<JpaRepository에 있음)
    //단일조회
    @Transactional
    public ResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지않습니다.."));

        return new ResponseDto(post);
    }
    //requestDto로 받아온정보를 엔티티의 생성자에 정보를 넣어주고 저장.
    //같은 정보를 responsDto에 인자로넣어서 반환
    //저장
    @Transactional
    public ResponseDto create(RequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new ResponseDto(post);
    }
    //수정
    public ResponseDto update(Long id,RequestDto requestDto) {
        Post post = postRepository.findById(id)//id를 통해서데이터베이스에 내가 수정할정보가있는지먼저확인한다
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지않습니다.")
                );
        if (post.getPassword().equals(requestDto.getPassword())) {  //비밀번호가 일치하는경우
            post.update(requestDto);                                //게시물을 수정
            postRepository.save(post);                              //저장
            return new ResponseDto(post);
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
    //삭제
    public String delete(Long id, RequestDto requestDto) {
        Post post = postRepository.findById(id)//id를 통해서데이터베이스에 내가 수정할정보가있는지먼저확인한다
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지않습니다.")
                );
        if (post.getPassword().equals(requestDto.getPassword())) {  //비밀번호가 일치하는경우
            postRepository.deleteById(id);                          //id맞는 게시물 삭제
            return "success";
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }
}
