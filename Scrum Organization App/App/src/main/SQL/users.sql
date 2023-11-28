insert into users(userid, role, name, currentSprintName, groupid)
	values(2625587, 1, 'Colby', 'current', 1);

insert into users(userid, role, name, currentSprintName, groupid)
	values(2648174, 1, 'Thomas', 'this', 1);

insert into users(userid, role, name, currentSprintName, groupid)
	values(2634927, 1, 'Kev', 'sprint', 1);

insert into users(userid, role, name, currentSprintName, groupid)
	values(2651660, 1, 'Guy', 'Work', 1);

insert into Subtasks(listid, name, checked)
	values
	  (1, 'Eat lunch', false),
	  (2, 'Go for a run', true),
      (2, 'Read a book', false),
      (3, 'Write a letter', false),
      (3, 'Clean the house', true),
      (4, 'Cook dinner', false),
      (4, 'Do yoga', false),
      (3, 'Buy groceries', true),
      (5, 'Call a friend', true),
      (5, 'Do laundry', false),
      (6, 'Take a walk', false),
      (6, 'Water the plants', true),
      (7, 'Study for an exam', false),
      (7, 'Do the dishes', true),
      (3, 'Figure out how to connect to database', true),
      (3, 'Make sample things in database', true),
      (4, 'Make google slides', false),
      (4, 'Blackmail Kevin into doing the presentation', true),
      (8, 'Attend a meeting', false),
      (5, 'Sprint subtask1', true),
      (5, 'Sprint subtask2', false),
      (8, 'Go shopping', true);

insert into Tasks(userid, name, backlog, sprint, sprintarchiveid)
	values(2625587, 'Complete project 3', true, false, -1),
	(2625587, 'Finish watching movie', true, false, -1),
	(2625587, 'Do SQL', false, false, 1),
	(2625587, 'Do Presentation', false, false, 1),
	(2625587, 'Sprint task1', false, true, -1),
    (2625587, 'Sprint task2', false, true, -1),
	(2651660, 'Start 260 presentation', true, false, -1),
    	(2651660, 'Eat dinner', true, false, -1),
    	(2634927, 'Register for classes', true, false, -1),
    	(2634927, 'Start end of term paper', true, false, -1),
    	(2648174, 'Play the drums', true, false, -1),
    	(2648174, 'Go to bookstore', true, false, -1);

insert into SprintArchive(userid, name, reflection)
	values(2625587, 'Complete project 3', 'I cannot wait to get started!'),
    	(2625587, 'Play video games', 'Playing video games was fun'),
    	(2651660, 'Work on Macro HW', 'Got stuck on third problem of the hw set'),
    	(2651660, 'Study for finals', 'Still need to study for econometrics'),
    	(2634927, 'Figure out how to get to the bookstore', 'Trying to navigate reamer was confusing'),
    	(2634927, 'Start packing up dorm room', 'Realized I need more boxes before I can start'),
    	(2648174, 'Figure out how SQL works', 'Started to figure it out but got stuck'),
    	(2648174, 'Go shopping for summer', 'Got more than enough new clothes');

insert into team(gname)
	values('Runtime Terror');

