create database db_mysql; -- Creates the new database
create user 'mysql'@'%' identified by 'ThePassword'; -- Creates the user
grant all on db_mysql.* to 'mysql'@'%'; -- Gives all privileges to the new user on the newly created database
--revoke all on db_mysql.* from 'mysql'@'%';
--grant select, insert, delete, update on db_mysql.* to 'mysql'@'%';