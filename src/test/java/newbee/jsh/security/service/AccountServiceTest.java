package newbee.jsh.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.minidev.json.JSONObject;
import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;
import newbee.jsh.security.account.entity.Account;
import newbee.jsh.security.account.entity.Role;
import newbee.jsh.security.account.repository.AccountRepository;
import newbee.jsh.security.account.repository.RoleRepository;
import newbee.jsh.security.account.service.AccountService;
import newbee.jsh.security.account.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    private JSONObject jsonObject;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before(){
        this.accountService = new AccountServiceImpl(accountRepository, roleRepository);
        this.jsonObject = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("ROLE을 저장하고, Account를 저장한다.")
    void createRoleByNotFoundRole() throws Exception{
        //given
        final String roleValue = "ROLE_USER";

        jsonObject.put("role", roleValue);

        final RequestCreateAccountDto dto = objectMapper.readValue(jsonObject.toString(), RequestCreateAccountDto.class);

        given(roleRepository.findByValue(anyString())).willReturn(Optional.empty());
        given(roleRepository.save(any(Role.class))).willReturn(Role.builder().value(roleValue).build());

        //when
        accountService.createAccount(dto);        

        //then
        then(roleRepository).should(times(1)).findByValue(anyString());
        then(roleRepository).should(times(1)).save(any(Role.class));
        then(accountRepository).should(times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("ROLE을 찾고, Account를 저장한다.")
    void findRole() throws Exception{
        //given
        final String roleValue = "ROLE_USER";

        jsonObject.put("role", roleValue);

        final RequestCreateAccountDto dto = objectMapper.readValue(jsonObject.toString(), RequestCreateAccountDto.class);
        final Role role = Role.builder().id(1L).value(roleValue).build();

        given(roleRepository.findByValue(anyString())).willReturn(Optional.of(role));

        //when
        accountService.createAccount(dto);        

        //then
        then(roleRepository).should(times(1)).findByValue(anyString());
        then(roleRepository).should(times(0)).save(any(Role.class));
        then(accountRepository).should(times(1)).save(any(Account.class));
    }
    
}
