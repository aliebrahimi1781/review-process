insert into pm_user (username, password, firstname, lastname) values ('admin', 'admin', 'admin', 'admin');
insert into pm_user (username, password, firstname, lastname) values ('lm', 'lm', 'Line', 'Manager');
insert into pm_user (username, password, firstname, lastname, lm_id) values ('mgotze', '1234', 'Mario', 'Gotze', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('lmessi', '1234', 'Lionel', 'Messi', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('arobben', '1234', 'Arjen', 'Robben', 2);

insert into pm_role (name) values ('admin');
insert into pm_role (name) values ('lm');

insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'admin'), (select r.id from pm_role r where r.name = 'admin'));
insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'lm'), (select r.id from pm_role r where r.name = 'lm'));