package ex01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 클래스
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller { // 어노테이션 만드는 기법: @interface

}
