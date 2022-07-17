package com.vrfvitor.interviewsched.dto;

import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ScriptAssert(
        lang = "javascript",
        script = "Date.parse(_this.endTime) > Date.parse(_this.startTime)",
        message = "Start time must be before end time"
)
public class Availability {

    @NotEmpty
    public Set<LocalDate> dates;

    @NotNull
    @OnTheClockTime
    public LocalTime startTime;

    @NotNull
    @OnTheClockTime
    public LocalTime endTime;

    public long getDiffHours() {
        return startTime.until(endTime, ChronoUnit.HOURS);
    }

}
