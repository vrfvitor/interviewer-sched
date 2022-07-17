package com.vrfvitor.interviewsched.resource;

import com.vrfvitor.interviewsched.dto.*;
import com.vrfvitor.interviewsched.model.*;
import com.vrfvitor.interviewsched.repository.*;
import com.vrfvitor.interviewsched.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import javax.validation.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/availabilities")
public class AvailabilityRestController {

    private final AvailabilitySlotService slotService;
    private final ParticipantRepository participantRepository;

    @GetMapping("/candidate/{id}/interviewers")
    public ResponseEntity<List<AvailabilitySlot>> matchesFor(@PathVariable("id") UUID id, @RequestParam("id") List<UUID> interviewersIds) {
        var candidate = participantRepository
                .findByIdAndInterviewer(id, false)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Candidate of id: %s not found", id)));

        return ResponseEntity.ok(slotService.findMatches(candidate, interviewersIds));
    }

    @PostMapping("/candidate/{id}")
    public void registerForCandidate(@PathVariable("id") UUID id, @Valid @RequestBody Availability availability) {
        var candidate = participantRepository
                .findByIdAndInterviewer(id, false)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Candidate of id: %s not found", id)));
        slotService.processAndSave(availability, candidate);
    }

    @PostMapping("/interviewer/{id}")
    public void registerForInterviewer(@PathVariable("id") UUID id, @Valid @RequestBody Availability availability) {
        var interviewer = participantRepository
                .findByIdAndInterviewer(id, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Interviewer of id: %s not found", id)));
        slotService.processAndSave(availability, interviewer);
    }

}
