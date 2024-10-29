package kr.ac.mjc.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/session/create")
    public ResponseEntity<User> createSession(@RequestParam("id") String id, HttpSession session){
        System.out.println(id);
        session.setAttribute("id",id);

        User user=new User();
        user.setName("진용화");
        user.setId("1234");
        return ResponseEntity.ok(user);
    }
    @GetMapping("/session/get")
    public ResponseEntity<User> getSession(HttpSession session){
        String id= (String) session.getAttribute("id");
        User user=new User();
        user.setId(id);
        return ResponseEntity.ok(user);

    }
}
