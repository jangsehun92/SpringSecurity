package newbee.jsh.security.account.enums;

import lombok.Getter;

@Getter
public enum AccountRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    AccountRole(String value){
        this.value = value;
    }
    
}
