create table users(
	userid INTEGER,
	role BOOLEAN,
	name char(50) not null,
	currentSprintName char(100) not null,
	groupid INTEGER
);

create table Subtasks(
	subtaskid INTEGER not null auto_increment primary key,
	listid INTEGER not null,
	name char(50) not null,
	checked BOOLEAN
);

create table Tasks(
	taskid INTEGER not null auto_increment primary key,
	userid INTEGER not null,
	name char(50) not null,
	backlog BOOLEAN,
	sprint BOOLEAN,
	sprintarchiveid INTEGER
);

create table SprintArchive(
	sprintarchiveid INTEGER not null auto_increment primary key,
	userid INTEGER not null,
	name char(50) not null,
	reflection char(255)
);

create table team(
	groupsid INTEGER not null auto_increment primary key,
	gname char(50) not null
);

