package com.crazyxxl.jwt.annotation;

import com.crazyxxl.jwt.config.JwtAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({
        ElementType.TYPE
})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import({JwtAutoConfiguration.class})
public @interface JWT {
}
