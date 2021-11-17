package newbee.jsh.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ViewController {

    @GetMapping("/sign-in.html")
    public String signInPage(){
        log.info("GET /sign-in");
        return "sign-in";
    }

    @GetMapping("/sign-up.html")
    public String signUpPage(){
        log.info("GET /sign-in");
        return "sign-up";
    }

    @GetMapping("/main.html")
    public String mainPage(){
        log.info("GET /main.html");
        return "main";
    }

    @GetMapping("/user.html")
    public String userPage(){
        log.info("GET /user.html");
        return "user";
    }

    @GetMapping("/admin.html")
    public String adminPage(){
        log.info("GET /admin.html");
        return "admin";
    }
    
}
