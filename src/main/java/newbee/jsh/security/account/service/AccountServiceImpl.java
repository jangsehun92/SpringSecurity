package newbee.jsh.security.account.service;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;
import newbee.jsh.security.account.entity.Account;
import newbee.jsh.security.account.entity.Role;
import newbee.jsh.security.account.repository.AccountRepository;
import newbee.jsh.security.account.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    
    @Override
    public void createAccount(final RequestCreateAccountDto dto) {
        accountRepository.save(Account.builder()
                                    .email(dto.getEmail())
                                    .password(dto.getPassword())
                                    .name(dto.getName())
                                    .regDate(LocalDateTime.now())
                                    .roles(Collections.singleton(getRole(dto.getRole()))).build());
    }

    private Role getRole(final String roleValue){
        return roleRepository.findByValue(roleValue).orElseGet(() -> roleRepository.save(Role.builder().value(roleValue).build()));
    }
    
}
