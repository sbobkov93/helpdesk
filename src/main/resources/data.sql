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
INSERT INTO authentication_data (id, user_name, password, enabled, role_id)
VALUES (nextval('auth_data_sequence'), 'admin', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 2),
       (nextval('auth_data_sequence'), 'maria', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 1),
       (nextval('auth_data_sequence'), 'denis', '$2y$10$42jhx1MQSbjdXhzVmlIiW.F4FQVYWIiXiZhlX/oNZMroUWcbTSqxK', true, 1);
INSERT INTO employee_details(id, phone, email)
VALUES (nextval('employee_details_sequence'), '+79876543210', 'ivanov@helpdesk.com'),
       (nextval('employee_details_sequence'), '+79272222222', 'petrova@helpdesk.com'),
       (nextval('employee_details_sequence'), '+79373333333', 'denisov@helpdesk.com');
INSERT INTO employee (id, first_name, last_name, patronymic, auth_id, details_id)
VALUES (nextval('employee_sequence'), 'Иван', 'Иванов', 'Иванович', 1, 1),
        (nextval('employee_sequence'), 'Мария', 'Петрова', 'Сергеевна', 2, 2),
        (nextval('employee_sequence'), 'Денис', 'Денисов', 'Денисович', 3, 3);
INSERT INTO ticket (id, title, description, client_id, creator_id, owner_id, created_at, last_modified, status_id)
VALUES (nextval('ticket_sequence'), 'Не включается компьютер', 'После отключения электроенергии не включается компьютер', 1, 2, 3, '2012-09-17 18:47:52.069', '2012-09-17 18:47:52.069', 3),
        (nextval('ticket_sequence'), 'Нет сети', 'Компьютер не доступен в сети', 2, 1, 3, '2013-10-30 11:25:52.069', '2013-10-30 11:25:52.069', 1),
        (nextval('ticket_sequence'), 'Проблемы с ЭЦП', 'Не работает электронная подпись', 3, 2, 2, '2015-01-23 16:10:52.069', '2015-01-23 16:10:52.069', 2);
INSERT INTO note (id, ticket_id, comment, creator_id, owner_id, created_at, last_modified, status_id)
VALUES (nextval('note_sequence'), 1, 'Вышел из строя блок питания, поставил подменный, выставить счёт', 2, 3, '2012-09-18 10:25:52.069', '2012-09-18 10:25:52.069', 1),
       (nextval('note_sequence'), 2, 'Переобжал сетевой кабель, заработало', 3, 3, '2013-10-30 12:40:52.069', '2013-10-30 12:40:52.069', 2),
       (nextval('note_sequence'), 3, 'Срочно нужен компьютер для работы, перезвонит позже', 2, 2, '2015-01-23 16:20:52.069', '2015-01-23 16:20:52.069', 1),
       (nextval('note_sequence'), 3, 'Вышел срок действия сертификата, завтра принесёт новую ЭЦП, перезвонит', 2, 2, '2015-01-23 18:20:52.069', '2015-01-23 18:20:52.069', 1),
       (nextval('note_sequence'), 3, 'Установил и настроил новую эцп. Проверил, всё работает', 2, 2, '2015-01-24 12:20:52.069', '2015-01-24 12:20:52.069', 2);