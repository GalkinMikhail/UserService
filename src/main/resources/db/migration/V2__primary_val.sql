insert into users (id,username,password)
values (1,'admin','$2a$12$uTLqiyjLSaYecnGY4hLcyeZIhlgbn.4Uc8d/sPNcsKaFCcSUR.akC');
insert into users(id,username,password)
values (2,'user','$2a$12$KlKTkCW7d4Q04PfmT69VSODTsFsz8Dgr46xrzQ9H1WrFK8IZEF3aW');


insert into roles(id,name)
values (1,'ROLE_ADMIN');
insert into roles(id,name)
values (2,'ROLE_USER');


insert into user_roles(user_id,role_id)
values (1,1);
insert into user_roles(user_id,role_id)
values (2,2);

