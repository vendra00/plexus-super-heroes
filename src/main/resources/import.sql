INSERT INTO user(name, email, password) VALUES('User', 'user@email.com', '{bcrypt}$2a$10$P3lNPvU2j6TuJdr2TGp9FuGc5fyBAufME.WwIIpg2JTekbO5/2sf.');
INSERT INTO user(name, email, password) VALUES('ModeraTor', 'moderator@email.com', '{bcrypt}$2a$10$P3lNPvU2j6TuJdr2TGp9FuGc5fyBAufME.WwIIpg2JTekbO5/2sf.');

INSERT INTO profile(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO profile(id, name) VALUES(2, 'ROLE_MODERATOR');

INSERT INTO user_profile(user_id, profile_id) VALUES(1, 1);
INSERT INTO user_profile(user_id, profile_id) VALUES(2, 2);

INSERT INTO super_hero VALUES (1, 'Super Man');
INSERT INTO super_hero VALUES (2, 'Ant Man');
INSERT INTO super_hero VALUES (3, 'Aqua Man');
INSERT INTO super_hero VALUES (4, 'Iron Man');
INSERT INTO super_hero VALUES (5, 'Batman');
INSERT INTO super_hero VALUES (6, 'Wonder Woman');
INSERT INTO super_hero VALUES (7, 'Halwk Eye');
INSERT INTO super_hero VALUES (8, 'Black Widow');
INSERT INTO super_hero VALUES (9, 'Silver Surfer');
INSERT INTO super_hero VALUES (10, 'Major Glory');
INSERT INTO super_hero VALUES (11, 'Robin');
INSERT INTO super_hero VALUES (12, 'Vision');
INSERT INTO super_hero VALUES (13, 'Thor');
INSERT INTO super_hero VALUES (14, 'Star Lord');
INSERT INTO super_hero VALUES (15, 'Captain America');