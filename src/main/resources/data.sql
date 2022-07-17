INSERT INTO participants (id, email, first_name, last_name, is_interviewer)
VALUES ('59064b6c-f40d-427b-8c6f-f8ebda281eff', 'pedro.j@corp.com', 'Pedro', 'Jonas', true),
       ('af7cc534-5eb1-470c-8ccf-8d9907019c2a', 'felipe.a@corp.com', 'Felipe', 'Alvez', true),
       ('bdeafd24-da0f-4581-96e9-07c1f01ae89e', 'marconi.a@gmail.com', 'Marconi', 'Alvez', false),
       ('afaf89ff-97e3-4acb-8415-a6f492ee8770', 'vitor.f@gmail.com', 'Vitor', 'Ferreira', false)
ON CONFLICT DO NOTHING;

INSERT INTO availability_slots (id, date, start_time, participant_id)
VALUES (1, '2022-07-25', ' 09:00', 'af7cc534-5eb1-470c-8ccf-8d9907019c2a'),
       (2, '2022-07-26', ' 08:00', 'af7cc534-5eb1-470c-8ccf-8d9907019c2a'),
       (3, '2022-07-26', ' 09:00', 'af7cc534-5eb1-470c-8ccf-8d9907019c2a'),
       (4, '2022-07-25', ' 08:00', 'afaf89ff-97e3-4acb-8415-a6f492ee8770'),
       (6, '2022-07-26', ' 09:00', 'afaf89ff-97e3-4acb-8415-a6f492ee8770')
ON CONFLICT DO NOTHING;

ALTER SEQUENCE availability_slots_id_seq RESTART WITH 7;