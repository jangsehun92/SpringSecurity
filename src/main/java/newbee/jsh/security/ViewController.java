package newbee.jsh.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import newbee.jsh.security.account.entity.Account;


@Slf4j
@Controller
public class ViewController {

    @RequestMapping("/")
    public String indexPage() {
        log.info("[ GET, POST ] /");
        return "index";
    }

    @RequestMapping("/sign-in.html")
    public String signInPage(@AuthenticationPrincipal Account account){
        log.info("[ GET, POST ] /sign-in.html");
            
        if(account != null){
            return "redirect:/";
        }
        return "sign-in";
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
