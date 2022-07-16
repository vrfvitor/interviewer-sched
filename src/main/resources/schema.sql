CREATE TABLE IF NOT EXISTS participants
(
    email          VARCHAR(320) PRIMARY KEY,
    first_name     VARCHAR(64) NOT NULL,
    last_name      VARCHAR(64) NOT NULL,
    is_interviewer BOOLEAN     NOT NULL DEFAULT FALSE,
    CONSTRAINT ck_email_lowercase
        CHECK (email = lower(email))
);

CREATE TABLE IF NOT EXISTS availability_slots
(
    id                BIGSERIAL PRIMARY KEY,
    date              DATE         NOT NULL,
    start_time        TIME         NOT NULL,
    participant_email VARCHAR(320) NOT NULL,
    CONSTRAINT fk_availability_participant
        FOREIGN KEY (participant_email)
            REFERENCES participants (email)
            ON DELETE CASCADE
);
