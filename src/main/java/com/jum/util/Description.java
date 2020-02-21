package com.jum.util;

import java.lang.annotation.*;

/**
 * Created by hly on 2017/4/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Description {
    public String steps();
    public String expectation();
    public String priority() default "P1";
}
