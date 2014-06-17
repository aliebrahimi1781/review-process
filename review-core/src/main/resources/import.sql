insert into pm_user (username, password, firstname, lastname) values ('admin', 'admin', 'admin', 'admin');
insert into pm_user (username, password, firstname, lastname) values ('lm', 'lm', 'Line', 'Manager');
insert into pm_user (username, password, firstname, lastname, lm_id) values ('lmessi', '1234', 'Lionel', 'Messi', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('ainiesta', '1234', 'Andres', 'Iniesta', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('thenry', '1234', 'Thierry', 'Henry', 2);

insert into pm_role (name) values ('admin');
insert into pm_role (name) values ('lm');

insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'admin'), (select r.id from pm_role r where r.name = 'admin'));
insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'lm'), (select r.id from pm_role r where r.name = 'lm'));