-- 商品标签对应表
CREATE TABLE `article_tag_map` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` BIGINT NOT NULL COMMENT '文章ID',
  `tag_id` BIGINT NOT NULL COMMENT '标签ID',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;