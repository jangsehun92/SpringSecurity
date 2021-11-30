package newbee.jsh.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    private final String signInPage;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        
        if(exception instanceof InternalAuthenticationServiceException){
            dispatcherForward(request, response, signInPage, "계정을 찾을 수 없습니다.");
        }

        if(exception instanceof BadCredentialsException){
            dispatcherForward(request, response, signInPage, "비밀번호가 다릅니다.");
        }
    }

    private void dispatcherForward(HttpServletRequest request, HttpServletResponse response, String url, String message) throws IOException, ServletException{
        request.setAttribute("email", request.getAttribute("email"));
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(url).forward(request, response);
    }


    
}
