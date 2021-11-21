package newbee.jsh.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import net.minidev.json.JSONObject;
import newbee.jsh.security.account.controller.AccountController;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    private MockMvc mvc;

    private AccountController accountController;

    private JSONObject jsonObject;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before(){
        // this.mvc = MockMvcBuilders
        //                 .standaloneSetup(accountController)
        //                 .addFilters(new CharacterEncodingFilter("UTF-8"), true))
        this.accountController = new AccountController();
        this.jsonObject = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }
    
}
