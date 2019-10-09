package com.xzsd.pc.log.annotation;

import java.lang.annotation.*;

/**
 * Title: SystemControllerLog
 * @date 2019年5月30日
 * @version V1.0
 * Description:  自定义注解，拦截service
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationServiceLog {
    String description() default "";
    String role() default "";
}
