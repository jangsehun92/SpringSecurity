package newbee.jsh.security.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    //계정생성
    @PostMapping(value="/api/account")
    public ResponseEntity<HttpStatus> createAccount(@RequestBody RequestCreateAccountDto dto) {
        log.info("POST /account {}", dto.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //계정 정보 가져오기
    @GetMapping(value="/api/accounts/{id}")
    public ResponseEntity<HttpStatus> getAccount(@PathVariable("id") Long id){
        log.info("GET /accounts/{}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    


    
}
