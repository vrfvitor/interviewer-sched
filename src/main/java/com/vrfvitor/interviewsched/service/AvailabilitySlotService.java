package com.vrfvitor.interviewsched.service;

import com.vrfvitor.interviewsched.dto.*;
import com.vrfvitor.interviewsched.model.*;
import com.vrfvitor.interviewsched.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AvailabilitySlotService {

    private final AvailabilitySlotRepository slotRepository;

    public void processAndSave(Availability availability, Participant participant) {
        ArrayList<AvailabilitySlot> slots = toAvailabilitySlots(availability, participant);
        slotRepository.saveAll(slots);
    }

    private ArrayList<AvailabilitySlot> toAvailabilitySlots(Availability availability, Participant participant) {
        var slots = new ArrayList<AvailabilitySlot>();
        var rangeSize = availability.getDiffHours();
        var hourSlot = availability.startTime;

        for (int i = 0; i < rangeSize; i++) {
            for (LocalDate date : availability.dates)
                slots.add(new AvailabilitySlot(date, hourSlot, participant));
            hourSlot = hourSlot.plusHours(1);
        }

        return slots;
    }

}
