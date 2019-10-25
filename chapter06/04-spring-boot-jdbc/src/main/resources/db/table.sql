DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL DEFAULT '',
  `user_birthday` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `article_title` varchar(255) NOT NULL DEFAULT '',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`article_id`)
) AUTO_INCREMENT=1;