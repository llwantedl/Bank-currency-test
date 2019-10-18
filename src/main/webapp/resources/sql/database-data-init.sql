USE TESTBANK;

INSERT INTO ROLE (id, `key`) VALUES (1, 'ADMIN');
INSERT INTO ROLE (id, `key`) VALUES (2, 'USER');

INSERT INTO CURRENCY (id, `key`) VALUES
  (NULL, 'USD'),
  (NULL, 'EUR'),
  (NULL, 'RUR'),
  (NULL, 'CHF'),
  (NULL, 'GBP'),
  (NULL, 'PLZ'),
  (NULL, 'SEK'),
  (NULL, 'XAU'),
  (NULL, 'CAD'),
  (NULL, 'UAH');

INSERT INTO USER (id, `login`, `password`, `email`, `currency_id`)
VALUES (1, 'admin', '$2a$11$3sojUI/gbl995wdDXVflkuryZuS0rVkoKiP6i3.SFBsJWv0pqGdfm', 'admin@admin.com', 1);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);