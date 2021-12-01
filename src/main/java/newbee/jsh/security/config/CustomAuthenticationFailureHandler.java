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
import newbee.jsh.security.global.error.ErrorResponse;
import newbee.jsh.security.global.error.exception.ErrorCode;

@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    private final String signInPage;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        
        if(exception instanceof InternalAuthenticationServiceException){
            dispatcherForward(request, response, signInPage, ErrorCode.ACCOUNT_NOT_FOUND);
        }

        if(exception instanceof BadCredentialsException){
            dispatcherForward(request, response, signInPage, ErrorCode.PASSWORD_NOT_MATCH);
        }

    }

    private void dispatcherForward(HttpServletRequest request, HttpServletResponse response, String url, ErrorCode errorCode) throws IOException, ServletException{
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("errorResponse", ErrorResponse.of(errorCode));
        request.getRequestDispatcher(url).forward(request, response);
    }


    
}
