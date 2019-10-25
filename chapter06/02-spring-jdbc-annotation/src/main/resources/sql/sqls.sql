DELIMITER//
CREATE FUNCTION getNameById(in_id INT)
  RETURNS VARCHAR(64)
  BEGIN
    RETURN (SELECT `name` FROM `user` WHERE `user_id` = in_id);
  END //
DELIMITER;