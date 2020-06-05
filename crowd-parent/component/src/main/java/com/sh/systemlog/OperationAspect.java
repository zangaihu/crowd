package com.sh.systemlog;

import com.sh.constant.CrowdConstant;
import com.sh.entity.Admin;
import com.sh.entity.Logs;
import com.sh.service.LogsService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created By Sunhu At 2020/6/3 16:47
 * 切点类
 *
 * @author Sun
 */

@Aspect
@Component
@Slf4j
public class OperationAspect {

    @Autowired
    LogsService logsService;

    //配置切点，自定的注解所在，表明方法上加了注解，就介入
    @Pointcut("@annotation(com.sh.systemlog.OpLog)")
    public void executeService() {
    }


    @AfterReturning("executeService()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        Method method=methodSignature.getMethod();

        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        Logs logs=new Logs();

        OpLog opLog=method.getAnnotation(OpLog.class);
        if (opLog!=null){

            logs.setDescription(opLog.description());

        }

        String methodName = methodSignature.getName();
        String className =joinPoint.getTarget().getClass().getSimpleName();

        logs.setOperation(className+"."+methodName+"()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            logs.setArgs(params);
        }

        logs.setIp(request.getRequestURI());

        Admin admin =(Admin) request.getSession().getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (admin==null)
        {   //未登录，不记录用户
            throw new RuntimeException();
        }else {
            logs.setUserName(admin.getLoginAcct());
        }

        logs.setOpTime(new Date());



        logsService.saveLog(logs);


    }

}
