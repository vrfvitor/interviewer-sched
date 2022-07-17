CREATE TABLE IF NOT EXISTS participants
(
    id             UUID PRIMARY KEY,
    email          VARCHAR(320) UNIQUE,
    first_name     VARCHAR(64) NOT NULL,
    last_name      VARCHAR(64) NOT NULL,
    is_interviewer BOOLEAN     NOT NULL DEFAULT FALSE,
    CONSTRAINT ck_email_lowercase
        CHECK (email = lower(email))
);

CREATE TABLE IF NOT EXISTS availability_slots
(
    id             BIGSERIAL PRIMARY KEY,
    date           DATE NOT NULL,
    start_time     TIME NOT NULL,
    participant_id UUID NOT NULL,
    CONSTRAINT fk_availability_participant
        FOREIGN KEY (participant_id)
            REFERENCES participants (id)
            ON DELETE CASCADE
);
