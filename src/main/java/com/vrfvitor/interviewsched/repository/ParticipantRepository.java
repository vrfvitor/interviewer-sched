package com.vrfvitor.interviewsched.repository;

import com.vrfvitor.interviewsched.model.*;
import org.springframework.data.jpa.repository.*;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
}
