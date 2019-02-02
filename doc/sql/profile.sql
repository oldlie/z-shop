CREATE TABLE `profile` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL COMMENT '用户真实姓名' COLLATE 'utf8_unicode_ci',
  `nick_name` VARCHAR(255) NOT NULL COMMENT '用户昵称名' COLLATE 'utf8_unicode_ci',
  `gender` TINYINT COMMENT '性别',
  `birth_day` DATETIME COMMENT '出生日期',
  `email` VARCHAR(255) COMMENT '用户邮箱' COLLATE 'utf8_unicode_ci',
  `mobile_phone` VARCHAR(36) COMMENT '用户手机号码' COLLATE 'utf8_unicode_ci',
  `qq` INT COMMENT 'QQ号码',
  `info` VARCHAR(500) COMMENT '用户自我简介'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;