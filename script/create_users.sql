insert into pm_user (username, password, firstname, lastname) values ('admin', 'admin', 'admin', 'admin');
insert into pm_user (username, password, firstname, lastname) values ('lm', 'lm', 'lm', 'lm');
insert into pm_user (username, password, firstname, lastname, lm_id) values ('emp_a', 'emp_a', 'emp_a', 'emp_a', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('emp_b', 'emp_b', 'emp_b', 'emp_b', 2);
insert into pm_user (username, password, firstname, lastname, lm_id) values ('emp_c', 'emp_c', 'emp_c', 'emp_c', 2);

insert into pm_role (name) values ('admin');
insert into pm_role (name) values ('lm');

insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'admin'), (select r.id from pm_role r where r.name = 'admin'));
insert into pm_user_to_role (user_id, role_id) values ((select u.id from pm_user u where u.username = 'lm'), (select r.id from pm_role r where r.name = 'lm'));