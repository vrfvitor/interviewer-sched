package com.vrfvitor.interviewsched.resource;

import com.vrfvitor.interviewsched.repository.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.zonky.test.db.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.*;

import java.time.*;
import java.util.*;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Sql({"/schema.sql", "/data-test.sql"})
@AutoConfigureEmbeddedDatabase(refresh = AFTER_EACH_TEST_METHOD)
@AutoConfigureWebMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AvailabilityRestControllerTest {

    @Autowired
    private AvailabilitySlotRepository repository;

    public static final String BASE_URL = "http://localhost:8081/api";
    private static final String BASE_URI = BASE_URL.concat("/availabilities");

    public static final String TEST_INTERVIEWER_ID = "af7cc534-5eb1-470c-8ccf-8d9907019c2a";
    public static final String TEST_CANDIDATE_ID = "bdeafd24-da0f-4581-96e9-07c1f01ae89e";

    @Test
    public void givenProperAvailabilityThenRespond200AndSave() throws JSONException {
        var uri = String.format("%s/candidate/%s", BASE_URI, TEST_CANDIDATE_ID);

        var participantId = UUID.fromString(TEST_CANDIDATE_ID);
        var date = LocalDate.of(2022, 7, 25);
        var eightAm = LocalTime.of(8, 0);
        var nineAm = LocalTime.of(9, 0);

        var payload = new JSONObject()
                .accumulate("startTime", "08:00")
                .accumulate("endTime", "10:00")
                .accumulate("dates", new JSONArray(List.of("2022-07-25")))
                .toString();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(uri)
                .then()
                .log().body()
                .statusCode(HttpStatus.OK.value());

        var candidateSlots = repository.findAllByParticipant_Id(participantId);

        assertEquals(2, candidateSlots.size());
        assertEquals(candidateSlots.get(0).getStartTime(), eightAm);
        assertEquals(candidateSlots.get(0).getDate(), date);
        assertEquals(candidateSlots.get(1).getStartTime(), nineAm);
        assertEquals(candidateSlots.get(1).getDate(), date);
    }

    @Test
    public void givenNonExistingIdForCandidateThenRespondWith404AndMessage() throws JSONException {
        var nonExistingId = "eb1cc534-5eb1-470c-8ccf-8d9907019c2c";
        var message = String.format("Candidate of id: %s not found", nonExistingId);

        var uri = String.format("%s/candidate/%s", BASE_URI, nonExistingId);

        var payload = new JSONObject()
                .accumulate("startTime", "08:00")
                .accumulate("endTime", "10:00")
                .accumulate("dates", new JSONArray(List.of("2022-07-25", "2022-07-31")))
                .toString();

        var response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(uri);

        JsonPath jsonPath = new JsonPath(response.asString());
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), jsonPath.getString("status"));
        assertEquals(message, jsonPath.getString("message"));
    }

    @Test
    public void givenStartTimeAfterEndTimeThenRespondWith400AndMessage() throws JSONException {
        var uri = String.format("%s/interviewer/%s", BASE_URI, TEST_INTERVIEWER_ID);

        var payload = new JSONObject()
                .accumulate("startTime", "08:00")
                .accumulate("endTime", "07:00")
                .accumulate("dates", new JSONArray(List.of("2022-07-25", "2022-07-31")))
                .toString();

        checkBadRequestWithErrorMessage(uri, payload, null, "Start time must be before end time");
    }

    @Test
    public void givenNonOnTheClockTimeRespondWith400AndMessage() throws JSONException {
        var uri = String.format("%s/interviewer/%s", BASE_URI, TEST_INTERVIEWER_ID);
        var message = "Informed time is not an o'clock hour";
        var datesArray = new JSONArray(List.of("2022-07-25", "2022-07-31"));

        var payloadStartTime = new JSONObject()
                .accumulate("startTime", "08:45")
                .accumulate("endTime", "10:00")
                .accumulate("dates", datesArray);
        checkBadRequestWithErrorMessage(uri, payloadStartTime.toString(), "startTime", message);

        var payloadEndTime = new JSONObject()
                .accumulate("startTime", "08:00")
                .accumulate("endTime", "10:15")
                .accumulate("dates", datesArray);
        checkBadRequestWithErrorMessage(uri, payloadEndTime.toString(), "endTime", message);
    }

    private void checkBadRequestWithErrorMessage(String uri, String payload, String field, String errorMessage) {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(uri)
                .then()
                .log().body()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errors[0].field", is(field))
                .body("errors[0].defaultMessage", is(errorMessage));
    }

}