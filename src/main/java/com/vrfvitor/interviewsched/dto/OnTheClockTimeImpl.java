package com.vrfvitor.interviewsched.dto;

import javax.validation.*;
import java.time.*;

public class OnTheClockTimeImpl implements ConstraintValidator<OnTheClockTime, LocalTime> {

    @Override
    public void initialize(OnTheClockTime onTheClockTime) {
    }

    @Override
    public boolean isValid(LocalTime timeField, ConstraintValidatorContext cxt) {
        return timeField != null && timeField.getMinute() == 0;
    }

}