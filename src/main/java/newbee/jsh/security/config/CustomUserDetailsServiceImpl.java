package newbee.jsh.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;
import newbee.jsh.security.account.repository.AccountRepository;

@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService{

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
    }
    
}
