# Bank-currency-test
Java, Spring, Hibernate, JQuery, Bootstrap bank currency repository.

Created by Samsonov Vladimir Vladimirovich.

TODO:
1. Dockerfile
2. Money transfer waiting spinner

How to use:
1. If you do not have mysql installed go to step 2, otherwise to step 3
2. Install mysql and configure mysql user to access database
3. Change Bank-currency-test/src/main/webapp/WEB-INF/classes/database.properties to your database data
4. Run Bank-currency-test/sql/database-tables-init.sql and then Bank-currency-test/sql/database-data-init.sql scripts in mysql
5. Build and Run project
6. Test users:
      login: 'user', password: 'user', currency: 'UAH'
      login: 'user2', password: 'user2', currency: 'USD'
    Enter one user account, click 'Manage Wallets' button, select wallet and click "Transfer Money" button, follow transfer forms and wait for transfer result.
