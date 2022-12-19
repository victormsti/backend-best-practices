create table user_app(
id varchar primary key,
name varchar not null,
role varchar not null,
email varchar not null unique,
password varchar not null,
birth_date date null,
creation_date date not null,
update_date date null
);

create table user_outbox(
id varchar primary key,
email varchar not null unique,
creation_date date not null,
update_date date null
);



INSERT INTO user_app (id, name, role, email, password, birth_date, creation_date)
VALUES ('fa85f64-5717-4562-b3fc-2c963f66a789', 'Victor', 'Software Engineer', 'victormsti@gmail.com', '$2a$12$nYyKsX7phh9JuPdktM2kcOkmrdAjBPjS8S7XlTfD8nioq3uKQNVK2', '1993-03-05', '2022-12-18');
