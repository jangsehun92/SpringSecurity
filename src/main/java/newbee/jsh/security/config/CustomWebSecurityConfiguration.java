package newbee.jsh.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                    "/h2-console/**",
                    "/sign-in.html",
                    "/sign-up.html").permitAll() //h2 console 접근 허용
                .antMatchers(
                    "/user.html"
                ).hasRole("USER")
                .antMatchers(
                    "/admin.html"
                ).hasRole("ADMIN")
                .and()
            .csrf().disable();
    }


    
}