package newbee.jsh.security.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;
import newbee.jsh.security.account.service.AccountService;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    //계정생성
    @PostMapping(value="/api/account")
    public ResponseEntity<HttpStatus> createAccount(@RequestBody RequestCreateAccountDto dto) {
        log.info("POST /account {}", dto.toString());
        accountService.createAccount(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
