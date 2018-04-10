-- 文章评论
CREATE TABLE `article_comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` BIGINT NOT NULL COMMENT '文章ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `user_nickname` VARCHAR(32) NOT NULL COMMENT '用户在本站的昵称',
  `comment` VARCHAR(255) NOT NULL COMMENT '评论内容',
  `create_at` DATETIME NOT NULL COMMENT '评论时间',
  `reply_count` INT DEFAULT 0 COMMENT '这条评论回复数',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;