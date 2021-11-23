package newbee.jsh.security.account.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class RequestCreateAccountDto {
    
    private String email;
    private String password;
    private String name;
    private String role; //USER, ADMIN

}
