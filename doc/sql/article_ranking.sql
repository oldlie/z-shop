-- 文章评分记录
CREATE TABLE `article_ranking` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` BIGINT NOT NULL COMMENT '文章ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `create_at` DATETIME NOT NULL COMMENT '打分时间'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;