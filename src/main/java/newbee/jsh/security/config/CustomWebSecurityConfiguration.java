package newbee.jsh.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    private static final String SIGN_IN_PAGE = "/sign-in.html";
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider(customUserDetailsServiceImpl, bCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler(SIGN_IN_PAGE);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint(SIGN_IN_PAGE);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler(SIGN_IN_PAGE);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
            .and()
                .authorizeRequests()
                .antMatchers(
                    "/h2-console/**",
                    "/sign-in.html",
                    "/sign-up.html")
                .permitAll() //h2 console ?????? ??????
                .antMatchers(
                    "/",
                    "/user.html")
                .authenticated()
                .antMatchers(
                    "/admin.html")
                .hasRole("ADMIN")
            .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
            .and()
                .formLogin() //Form ????????? ?????? (Session ???????????? ??????)
                .loginPage("/sign-in.html") //????????? ????????? ??????
                .loginProcessingUrl("/account/sign-in") //???????????? ??????????????? ?????? URL ?????? Client????????? ?????? url??? ????????? ????????????.
                .usernameParameter("email") //????????? ??? username??? ???????????? ????????? ??????
                .passwordParameter("password") //????????? ??? password??? ???????????? ????????? ??????
                .successForwardUrl("/") //???????????? ????????? ???????????? Url ??????
                .failureHandler(authenticationFailureHandler())
            .and()
                .logout() //???????????? ?????? ??????
                .logoutUrl("/account/sign-out") //???????????? URL
                .logoutSuccessUrl("/sign-in.html"); //???????????? ????????? ????????? Url ??????
    }

}
