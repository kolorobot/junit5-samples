package pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnOwner {

    String withName() default  "";

    String withAddress() default "";

    String withPetName() default "";

}
