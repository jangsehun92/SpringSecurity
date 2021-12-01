package newbee.jsh.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ViewController {

    @RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.POST})
    public String indexPage() {
        log.info("[ GET, POST ] /");
        return "index";
    }

    @GetMapping("/sign-up.html")
    public String signUpPage(){
        log.info("GET /sign-up.html");
        return "sign-up";
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
