package newbee.jsh.security.account.service;

import newbee.jsh.security.account.dto.request.RequestCreateAccountDto;

public interface AccountService {

    public void createAccount(final RequestCreateAccountDto dto);
    
}
