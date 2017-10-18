package pl.codeleak.samples.junit5.gradle.support;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("smoke")
public @interface Smoke {
}
