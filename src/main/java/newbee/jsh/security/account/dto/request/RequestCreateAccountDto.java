package newbee.jsh.security.account.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
public class RequestCreateAccountDto {
    
    private String email;
    private String password;
    private String name;
    private String role; //USER, ADMIN

}
