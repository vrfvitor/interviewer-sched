package com.vrfvitor.interviewsched.repository;

import com.vrfvitor.interviewsched.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {

    List<AvailabilitySlot> findAllByParticipant_Id(UUID id);

    @Query("FROM AvailabilitySlot ai " +
            "JOIN FETCH Participant p ON p.interviewer = TRUE AND ai.participant = p " +
            "JOIN AvailabilitySlot ac ON ai.date = ac.date AND ai.startTime = ac.startTime AND ac.participant.id = :candidateId " +
            "WHERE ai.participant.id IN(:interviewersIds) ORDER BY ai.date, ai.startTime")
    List<AvailabilitySlot> findInterviewersAvailabilitiesMatchingOf(UUID candidateId, List<UUID> interviewersIds);

}
