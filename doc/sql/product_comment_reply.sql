-- 商品评论回复
CREATE TABLE `commodity_comment_reply` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `comment_id` BIGINT NOT NULL COMMENT '商品ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `user_nickname` VARCHAR(32) NOT NULL COMMENT '用户在本站的昵称',
  `comment` VARCHAR(255) NOT NULL COMMENT '评论内容',
  `create_at` DATETIME NOT NULL COMMENT '评论时间'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;