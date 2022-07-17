package com.vrfvitor.interviewsched.dto;

import javax.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnTheClockTimeImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface OnTheClockTime {
    String message() default "Informed time is not an o'clock hour";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}