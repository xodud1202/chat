package com.xodud1202.chat.support.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DataSource를 여러 개로 구성 시 DAO를 구성하는 interface를 명시적으로 지정하기 위해 만든 Custom Annotation
 * jdbc/xodud JNDI DataSource와 매핑
 * @author xodud1202
 * @since  2022.12.22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface xodudDs {

}
