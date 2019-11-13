insert into `roles` (`id`, `roleName`) values (1, 'USER');
insert into `roles` (`id`, `roleName`) values (2, 'ADMIN');

insert into `users_roles` (`users_id`, `roles_id`)
    values (1, 1);
