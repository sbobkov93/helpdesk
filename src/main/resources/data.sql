INSERT INTO role (id, role)
VALUES (nextval('role_sequence'), 'ROLE_USER'),
       (nextval('role_sequence'), 'ROLE_ADMIN');
INSERT INTO client (id, prefix, short_name, name)
VALUES (nextval('client_sequence'), 1, 'ЦК', 'ООО "ЦЕНТР КОНСАЛТ"'),
       (nextval('client_sequence'), 2, 'Фарфор', 'Ресторан доставки "Фарфор"'),
       (nextval('client_sequence'), 3, 'УЖХ', 'Ужх калининского района');
INSERT INTO status (id, status)
VALUES (nextval('status_sequence'), 'Открыта'),
        (nextval('status_sequence'), 'Закрыта'),
        (nextval('status_sequence'), 'Выезд');
INSERT INTO employee (id, first_name, last_name, patronymic, auth_id)
VALUES (nextval('employee_sequence'), 'Иван', 'Иванов', 'Иванович', 1),
        (nextval('employee_sequence'), 'Мария', 'Петрова', 'Сергеевна', 2),
        (nextval('employee_sequence'), 'Денис', 'Денисов', 'Денисович', 3);
INSERT INTO authentication_data (id, user_name, password, enabled, role_id)
VALUES (nextval('auth_data_sequence'), 'admin', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 2),
       (nextval('auth_data_sequence'), 'maria', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 1),
       (nextval('auth_data_sequence'), 'denis', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 1);
-- INSERT INTO ticket (id, title, description, client_id, creator_id, owner_id, created_at, last_modified, status_id)
-- VALUES (nextval('ticket_sequence'), 'Не включается компьютер', 'После отключения электроенергии не включается компьютер', 1, 2, 3, 1633251844, 1633251844, 3),
--         (nextval('ticket_sequence'), 'Нет сети', 'Компьютер не доступен в сети', 2, 1, 3, 1636243444, 1636243444, 3),
--         (nextval('ticket_sequence'), 'Проблемы с ЭЦП', 'Не работает электронная подпись', 3, 2, 2, 1636243444, 1636243444, 3)