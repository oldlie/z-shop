-- 标签
CREATE TABLE `tag` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `parent_id` BIGINT DEFAULT 0 COMMENT '上级标签ID'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;