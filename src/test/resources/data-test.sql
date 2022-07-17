INSERT INTO participants (id, email, first_name, last_name, is_interviewer)
VALUES ('59064b6c-f40d-427b-8c6f-f8ebda281eff', 'pedro.j@corp.com', 'Pedro', 'Jonas', true),
       ('af7cc534-5eb1-470c-8ccf-8d9907019c2a', 'felipe.a@corp.com', 'Felipe', 'Alvez', true),
       ('bdeafd24-da0f-4581-96e9-07c1f01ae89e', 'marconi.a@gmail.com', 'Marconi', 'Alvez', false),
       ('afaf89ff-97e3-4acb-8415-a6f492ee8770', 'vitor.f@gmail.com', 'Vitor', 'Ferreira', false)
ON CONFLICT DO NOTHING;
