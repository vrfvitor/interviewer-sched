package com.vrfvitor.interviewsched.model;

import lombok.*;
import org.hibernate.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "availability_slots")
public class AvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public AvailabilitySlot(LocalDate date, LocalTime startTime, Participant participant) {
        this.date = date;
        this.startTime = startTime;
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilitySlot that = (AvailabilitySlot) o;
        return date.equals(that.date) && startTime.equals(that.startTime) && participant.equals(that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, participant);
    }
}
