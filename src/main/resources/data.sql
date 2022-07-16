INSERT INTO participants (email, first_name, last_name, is_interviewer)
VALUES ('pedro.j@corp.com', 'Pedro', 'Jonas', true),
       ('felipe.a@corp.com', 'Felipe', 'Alvez', true),
       ('marconi.a@gmail.com', 'Marconi', 'Alvez', false),
       ('vitor.f@gmail.com', 'Vitor', 'Ferreira', false)
ON CONFLICT DO NOTHING;

INSERT INTO availability_slots (id, date, start_time, participant_email)
VALUES (1, '2022-07-25', ' 09:00',  'felipe.a@corp.com'),
       (2, '2022-07-26', ' 08:00',  'felipe.a@corp.com'),
       (3, '2022-07-26', ' 09:00',  'felipe.a@corp.com'),
       (4, '2022-07-25', ' 08:00',  'vitor.f@gmail.com'),
       (6, '2022-07-26', ' 09:00',  'vitor.f@gmail.com')
ON CONFLICT DO NOTHING;
