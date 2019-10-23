USE TESTBANK;

INSERT INTO ROLE (id, `key`) VALUES (1, 'ADMIN');
INSERT INTO ROLE (id, `key`) VALUES (2, 'USER');

INSERT INTO CURRENCY (id, `key`) VALUES
  (1, 'USD'),
  (2, 'EUR'),
  (3, 'RUR'),
  (4, 'CHF'),
  (5, 'GBP'),
  (6, 'PLZ'),
  (7, 'SEK'),
  (8, 'XAU'),
  (9, 'CAD'),
  (10, 'UAH');

INSERT INTO USER (id, `login`, `password`, `email`, `currency_id`) VALUES
  (1, 'admin', '$2a$11$3sojUI/gbl995wdDXVflkuryZuS0rVkoKiP6i3.SFBsJWv0pqGdfm', 'admin@admin.com', 1),
  (2, 'user', '$2a$11$xB4YpDQ1MqEcw1LNVpniAe8dU..p/McHM7tiDo/citXD1JXoG1hvW', 'user@user.ru', 1),
  (3, 'user2', '$2a$11$jjTN9Zkocqsd7zqqy0mAmerJnuGVhjEGcf7p3PTdOZkoa6ZQ6ioxK', 'user2@user.ru', 10);

INSERT INTO WALLET(id, `user_id`, `wallet_key`, `balance`) VALUES
  (1, 1, 'b024ba90-bc80-418f-b9f8-cab175b86a9b', 10000),
  (2, 2, '933e4f8f-447d-42b6-bf41-1673bfdb4bb5', 10000),
  (3, 3, '077488d7-c2f6-41fd-9a59-57a517ecffdd', 10000);

INSERT INTO USER_ROLE (user_id, role_id) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (3, 2);