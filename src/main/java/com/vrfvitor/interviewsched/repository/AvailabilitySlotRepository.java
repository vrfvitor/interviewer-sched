package com.vrfvitor.interviewsched.repository;

import com.vrfvitor.interviewsched.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {

    List<AvailabilitySlot> findAllByParticipant_Id(UUID id);

}
