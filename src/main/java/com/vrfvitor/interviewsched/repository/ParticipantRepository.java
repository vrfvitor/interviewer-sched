package com.vrfvitor.interviewsched.repository;

import com.vrfvitor.interviewsched.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Optional<Participant> findByIdAndInterviewer(UUID id, boolean isInterviewer);

}
