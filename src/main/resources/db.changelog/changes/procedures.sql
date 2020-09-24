DROP PROCEDURE IF EXISTS p_user;

CREATE PROCEDURE p_user(IN login VARCHAR(100))
BEGIN
    SELECT *
    FROM users
    WHERE users.login = login;
END;