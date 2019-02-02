create table users(
	username varchar(50) not null primary key,
	password varchar(255) not null,
	enabled boolean not null,
	token varchar(36)
);

create table authoritieList (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authoritieList (username,authority);