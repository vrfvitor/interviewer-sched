INSERT INTO availability_slots (id, date, start_time, participant_id)
VALUES (1, '2022-07-25', '08:00', '59064b6c-f40d-427b-8c6f-f8ebda281eff'), -- I - PEDRO

       (2, '2022-07-25', '08:00', 'af7cc534-5eb1-470c-8ccf-8d9907019c2a'), -- I - FELIPE
       (3, '2022-07-26', '09:00', 'af7cc534-5eb1-470c-8ccf-8d9907019c2a'),

       (4, '2022-07-25', '08:00', 'bdeafd24-da0f-4581-96e9-07c1f01ae89e'), -- C - MARCONI
       (5, '2022-07-26', '09:00', 'bdeafd24-da0f-4581-96e9-07c1f01ae89e')
ON CONFLICT DO NOTHING;

ALTER SEQUENCE availability_slots_id_seq RESTART WITH 6;