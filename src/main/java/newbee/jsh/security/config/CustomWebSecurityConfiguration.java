package newbee.jsh.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider(customUserDetailsServiceImpl, bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // @Bean
    // public AuthenticationFailureHandler authenticationFailureHandler(){

    // }

    // @Bean
    // public AuthenticationEntryPoint authenticationEntryPoint(){

    // }

    // @Bean
    // public AccessDeniedHandler accessDeniedHandler(){

    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                    "/",
                    "/h2-console/**",
                    "/sign-up.html"
                    ).permitAll() //h2 console 접근 허용
                .antMatchers(
                    "/user.html"
                    ).hasRole("USER")
                .antMatchers(
                    "/admin.html"
                    ).hasRole("ADMIN")
                .and()
                    .formLogin() //Form 로그인 허용 (Session 기반으로 설정)
                    .loginPage("/") //로그인을 처리할 페이지 URL 설정
                    .loginProcessingUrl("/account/sign-in") //인증처리를 하는 URL 설정 Client단에서 해당 url로 요청을 해야한다.
                    .usernameParameter("email") //로그인 시 username에 해당하는 변수명 설정
                    .passwordParameter("password") //로그인 시 password에 해당하는 변수명 설정
                    .successForwardUrl("/") //로그인에 성공시 이동해줄 Url 설정
                    .permitAll() //인증 없이 접근 허용
                .and()
                    .logout() //로그아웃 관련 설정
                    .logoutUrl("/account/sign-out") //로그아웃 URL
                    .logoutSuccessUrl("/") //로그아웃 성공시 이동할 Url 설정
                .and()
            .csrf()
                .ignoringAntMatchers("/h2-console/**");
            
    }


    
}
