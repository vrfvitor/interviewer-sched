package com.vrfvitor.interviewsched.service;

import com.vrfvitor.interviewsched.dto.*;
import com.vrfvitor.interviewsched.model.*;
import io.zonky.test.db.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.time.*;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
public class AvailabilitySlotServiceTest {

    @Autowired
    private AvailabilitySlotService service;

    @Test
    public void givenProperAvailabilityThenGenerateSlotsCorrectly() {
        var today = LocalDate.now();
        var fiveOClock = LocalTime.of(5, 0);
        var sevenOClock = LocalTime.of(7, 0);
        var availability = new Availability(Set.of(today, today.plusDays(2)), fiveOClock, sevenOClock);
        var participant = new Participant();

        var expectedGeneratedSlots = new ArrayList<AvailabilitySlot>() {{
            add(new AvailabilitySlot(today, fiveOClock, participant));
            add(new AvailabilitySlot(today, fiveOClock.plusHours(1), participant));
            add(new AvailabilitySlot(today.plusDays(2), fiveOClock, participant));
            add(new AvailabilitySlot(today.plusDays(2), fiveOClock.plusHours(1), participant));
        }};

        var generatedSlots = service.toAvailabilitySlots(availability, participant);

        expectedGeneratedSlots.forEach(slot -> assertThat(generatedSlots, hasItem(slot)));
    }

}
