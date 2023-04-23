package com.example.post.controller;

import com.example.post.dto.SignupRequestDto;
import com.example.post.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
//@Controller       //사용안해도됌
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<Map<String, Object>>signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입 성공");
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Status code 200:HttpStatus.OK.value()
    //200코드는 요청이 성공적으로 처리되었음을 나타냄
    //Status code 201:HttpStatus.CREATED.value()
    //201코드는 Created상태를 나타내며 새로운 리소스가 성공적으로 생성되었음을 나타냄

    @ResponseBody
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody SignupRequestDto signupRequestDto, HttpServletResponse response){
        userService.login(signupRequestDto,response);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "로그인 성공");
        responseBody.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }
//    @PostMapping("/post")
//    public ResponseDto create(){
//
//    }


}
