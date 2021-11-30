package newbee.jsh.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbee.jsh.security.global.error.ErrorResponse;
import newbee.jsh.security.global.error.exception.ErrorCode;

@Slf4j
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    private final String signInPage;
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        int httpStatus = HttpServletResponse.SC_FORBIDDEN;
        log.info("Status Code : " + httpStatus + " URL : " + request.getRequestURI());
        response.setStatus(httpStatus);

        if(request.getRequestURI().contains("api")){

            final MappingJackson2HttpMessageConverter jsonConvert = new MappingJackson2HttpMessageConverter();
            final MediaType mediaType = MediaType.APPLICATION_JSON;
            final ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);

            if(jsonConvert.canWrite(errorResponse.getClass(), mediaType)){
                jsonConvert.write(errorResponse, mediaType, new ServletServerHttpResponse(response));
            }

        }else{
            response.sendRedirect(signInPage);
        }

    }
    
}
