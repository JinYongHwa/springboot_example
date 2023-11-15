package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.UserJoinResponse;
import kr.ac.mjc.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserJoinResponse join(User user){
        User checkUser=userRepository.findByEmail(user.getEmail());
        UserJoinResponse userJoinResponse=new UserJoinResponse();
        //이미 이메일을 사용하는 사용자가 있는경우
        if(checkUser!=null){

            userJoinResponse.setSuccess(false);
            userJoinResponse.setMessage("이미 사용중인 이메일 입니다");
            return userJoinResponse;
        }

        userJoinResponse.setSuccess(true);
        userJoinResponse.setMessage("회원가입이 완료되었습니다");

        User savedUser=userRepository.save(user);
        userJoinResponse.setUser(savedUser);
        return userJoinResponse;
    }

    public UserJoinResponse login(User user){
        User checkUser=userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());

        UserJoinResponse userJoinResponse=new UserJoinResponse();

        //로그인 실패시
        if(checkUser==null){
            userJoinResponse.setSuccess(false);
            userJoinResponse.setMessage("아이디 또는 패스워드가 틀렸습니다");
            return userJoinResponse;
        }

        userJoinResponse.setSuccess(true);
        userJoinResponse.setMessage("로그인 성공");
        userJoinResponse.setUser(checkUser);
        return userJoinResponse;
    }

}
