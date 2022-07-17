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

    /**
     * Extract slots for the given Availability and saves
     * @param availability - availability informed by the participant
     * @param participant - the participant who declared such availability
     */
    public void processAndSave(Availability availability, Participant participant) {
        ArrayList<AvailabilitySlot> slots = toAvailabilitySlots(availability, participant);
        slotRepository.saveAll(slots);
    }

    /**
     * Fetches matching availability slots between a candidate and the intended interviewers for a possible interview
     * @param candidate - Participant searching for matches
     * @param interviewersIds - ids of the interviewers of interest
     * @return list of matching slots for given candidate and interviewers
     */
    public List<AvailabilitySlot> findMatches(Participant candidate, List<UUID> interviewersIds) {
        return slotRepository.findInterviewersAvailabilitiesMatchingOf(candidate.getId(), interviewersIds);
    }

    /**
     * Generate the slots for each hour between startTime (inclusive) and endTime (exclusive), and for each date informed
     * attaching the participant altogether
     * k = difference in hours (max.: 23)
     * n = number of days
     * f(runtime) = kn => linear time complexity
     * @param availability - availability informed by the participant
     * @param participant - the participant who declared such availability
     */
    public ArrayList<AvailabilitySlot> toAvailabilitySlots(Availability availability, Participant participant) {
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
