package com.sh.interceptor;

import com.sh.constant.CrowdConstant;
import com.sh.exception.AccessForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created By Sunhu At 2020/6/3 14:49
 *
 * @author Sun
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (attribute==null)
        {throw  new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);}
        else {return true;}
    }
}
