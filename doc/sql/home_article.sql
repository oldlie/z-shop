-- 首页展示商品列表
CREATE TABLE `home_article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` BIGINT NOT NULL COMMENT '文章ID',
  `sequence` INT DEFAULT 0 COMMENT '显示顺序'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;