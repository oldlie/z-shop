-- 文章评分记录
CREATE TABLE `product_ranking` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_id` BIGINT NOT NULL COMMENT '商品ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `create_at` DATETIME NOT NULL COMMENT '打分时间'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;