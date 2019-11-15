package p3.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice  //标识此类用来拦截controller层产生的异常并发出通知
public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class )
    public String defaultErrorHandler(HttpServletRequest request){
        return "unauth";
    }

}
