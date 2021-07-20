package com.wlos.common_generator.result;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention( RUNTIME)
@Target({ TYPE, METHOD })
@Documented
/**
 *用来标记方法的返回值，是否需要包装
 */
public @interface ResponseResult {

}
