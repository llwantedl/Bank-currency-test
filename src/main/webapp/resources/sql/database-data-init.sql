USE TEST_BANK;

INSERT INTO ROLE(id, 'KEY') VALUES (NULL, 'ADMIN');
INSERT INTO ROLE(id, 'KEY') VALUES (NULL, 'USER');

INSERT INTO 'USER'(id, login, password, email, name)
  VALUES(NULL, 'admin', '$2a$11$3sojUI/gbl995wdDXVflkuryZuS0rVkoKiP6i3.SFBsJWv0pqGdfm', 'admin@admin.com', 'admin');

INSERT INTO USER_ROLE(user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (1, 2);