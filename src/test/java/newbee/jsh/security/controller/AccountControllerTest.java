package newbee.jsh.security.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import net.minidev.json.JSONObject;
import newbee.jsh.security.account.controller.AccountController;
import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;
import newbee.jsh.security.account.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private JSONObject jsonObject;

    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    void before(){
        this.accountController = new AccountController(accountService);
        this.jsonObject = new JSONObject();
        this.objectMapper = new ObjectMapper();

        this.mvc = MockMvcBuilders
                        .standaloneSetup(accountController)
                        .addFilters(new CharacterEncodingFilter("UTF-8", true))
                        .alwaysDo(MockMvcResultHandlers.print()).build();
    }
    
    @Test
    @DisplayName("계정 생성 요청")
    void createAccount() throws Exception{
        //given
        final String email = "email@email.com";
        final String password = "password";
        final String name = "name";
        final String role = "ROLE_USER";

        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        jsonObject.put("role", role);

        final RequestCreateAccountDto dto = objectMapper.readValue(jsonObject.toString(), RequestCreateAccountDto.class);

        //when
        final MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                                                                .post("/api/account")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(objectMapper.writeValueAsString(dto)))
                                                                .andReturn().getResponse();

        //then
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

}
