package newbee.jsh.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import newbee.jsh.security.account.entity.Account;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final String password = (String)authentication.getCredentials();

        final Account account = (Account)customUserDetailsServiceImpl.loadUserByUsername(email);
        if(account == null){
            throw new InternalAuthenticationServiceException(email);
        }
        if(!bCryptPasswordEncoder.matches(password, account.getPassword())){
            throw new BadCredentialsException(email);
        }
        //password를 넣어주지 않으면 로그인후에도 권한에 필요한 접근을 할 때 403이 뜬다. 추후 알아볼 것   
        return new UsernamePasswordAuthenticationToken(account, account.getPassword(), account.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
