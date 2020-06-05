package com.sh.systemlog;

import java.lang.annotation.*;

/**
 * Created By Sunhu At 2020/6/3 16:42
 *
 * @author Sun
 */

@Target({ElementType.METHOD,ElementType.TYPE})  //作用于类/接口，方法
@Retention(RetentionPolicy.RUNTIME)//运行时起作用
@Documented //生成javadoc是，本注解会一起生成
@Inherited//可以被子类继承
public @interface OpLog {

    String description() default "";
}
