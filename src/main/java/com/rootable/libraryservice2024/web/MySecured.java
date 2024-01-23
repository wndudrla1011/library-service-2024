package com.rootable.libraryservice2024.web;

import com.rootable.libraryservice2024.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MySecured {

    Role role() default Role.USER;

}
