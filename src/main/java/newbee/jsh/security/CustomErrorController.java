package newbee.jsh.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import newbee.jsh.security.global.error.ErrorResponse;
import newbee.jsh.security.global.error.exception.ErrorCode;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
    public String error(HttpServletRequest request) {
        log.info("[ GET, POST ] /error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            ErrorCode errorCode = null;

            Integer statusCode = Integer.valueOf(status.toString());

            log.info("httpStatus = " + statusCode);

            Exception e = (Exception)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

            log.info("Exception : " + (e==null?"null":e.toString()));

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                errorCode = ErrorCode.NOT_FOUND;
            }

            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            }

            if(errorCode != null){
                request.setAttribute("errorResponse", ErrorResponse.of(errorCode));
            }
            
        }
        
        return "error";
    }
    

    
}
