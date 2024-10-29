package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.UserJoinResponse;
import kr.ac.mjc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody User user){

        UserJoinResponse response=userService.join(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/login")
    public ResponseEntity<UserJoinResponse> login(@RequestBody User user, HttpServletRequest request){

        UserJoinResponse response=userService.login(user);

        //로그인 실패시
        if(!response.isSuccess()){
            return ResponseEntity.ok().body(response);
        }

        HttpSession session=request.getSession();
        User loginedUser=response.getUser();
        session.setAttribute("userId",loginedUser.getEmail());

        return ResponseEntity.ok().body(response);

    }
}
