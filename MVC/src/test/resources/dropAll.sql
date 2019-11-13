SET SQL_SAFE_UPDATES = 0;
DELETE FROM sugartestsmvc where id > 0;
DELETE FROM device where id > 0;
DELETE FROM devicesdetails where id > 0;
DELETE FROM devicetypes where id > 0;
DELETE FROM lastid where id > 0;
DELETE FROM users_roles where Users_id > 0;
DELETE FROM users where id > 0;
DELETE FROM roles where id > 0;