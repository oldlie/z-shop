-- 网站基本信息设置
CREATE TABLE `` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `icp` VARCHAR(255) COMMENT 'ICP备案号码' COLLATE 'utf8_unicode_ci',
  `friend_links` VARCHAR(512) COMMENT '友情链接，JSON格式' COLLATE 'utf8_unicode_ci',
  `telephone` VARCHAR(32) COMMENT '系统联系电话' COLLATE 'utf8_unicode_ci',
  `address` VARCHAR(255) COMMENT '系统联系地址' COLLATE 'utf8_unicode_ci',
  `upload_dir` VARCHAR(255) COMMENT '上传文件到服务器的文件夹路径' COLLATE 'utf8_unicode_ci',
  `upload_url` VARCHAR(255) COMMENT '访问上传的文件URL根路径' COLLATE 'utf8_unicode_ci',
  `description` VARCHAR(512) COMMENT '网站简介' COLLATE 'utf8_unicode_ci',
  `key_words` VARCHAR(512) COMMENT '网站关键字' COLLATE 'utf8_unicode_ci',
  `title` VARCHAR(512) COMMENT '网站标题' COLLATE 'utf8_unicode_ci',
  `email` VARCHAR(512) COMMENT '网站邮箱，可用于给用户发送邮件' COLLATE 'utf8_unicode_ci',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;