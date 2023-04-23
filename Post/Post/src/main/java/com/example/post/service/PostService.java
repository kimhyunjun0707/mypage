package com.example.post.service;

import com.example.post.dto.RequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.entity.Post;
import com.example.post.entity.Users;
import com.example.post.jwt.JwtUtil;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {
    //의존성
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    //@Transactional
    //데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터 추가, 갱신, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다.
    // 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다
    //모든글 조회
    @Transactional
    public List<ResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();  //내림차순으로받아오고
        List<ResponseDto> Posts = new ArrayList<>();                        //반환타입을 정해주고새로운 어레이리스트생성
        for (Post post : posts) {                                           //반복문돌면서 생성한곳에 넣어준다
            Posts.add(new ResponseDto(post));
        }
        return Posts;


    }
    //작성자의 글 모두조회
    @Transactional
    public List<ResponseDto> getPosts(HttpServletRequest request) {
       Users user = checkToken(request);
            List<ResponseDto> list = new ArrayList<>();
            List<Post> postList;
            postList = postRepository.findAllByUserId(user.getId());


            for (Post post : postList) {
                list.add(new ResponseDto(post));
            }

            return list;

        }
    //postRepository.findById(<<JpaRepository에 있음)
    //글 번호로 단일조회
    @Transactional
    public ResponseDto getPost(Long id, HttpServletRequest request) {
        Users user= checkToken(request);
        Post post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않거나 접근할 수 없습니다.")
        );
        return new ResponseDto(post);
        }
    //requestDto로 받아온정보를 엔티티의 생성자에 정보를 넣어주고 저장.
    //같은 정보를 responsDto에 인자로넣어서 반환
    //게시글 작성
    @Transactional
    public ResponseDto create(RequestDto requestDto, HttpServletRequest request) {
        Users user= checkToken(request);
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestDto, user));

            return new ResponseDto(post);
        }
    //글 수정
    @Transactional
    public ResponseDto update(Long id, RequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
       Users user = checkToken(request);
            Post post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
            );
            post.update(requestDto);
            return new ResponseDto(post);

    }

    //삭제
    public void delete(Long id, HttpServletRequest request) {
        Users user = checkToken(request);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        if (post.getUser().getId().equals(user.getId())) {
            postRepository.deleteById(id);      //id맞는 게시물 삭제
        } else {
            throw new IllegalArgumentException("사용자 게시물이 아닙니다.");
        }

    }


    private Users checkToken(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
        }else{
            throw new IllegalArgumentException("Token not found");
        }
    }
}
