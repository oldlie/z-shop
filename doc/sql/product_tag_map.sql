-- 商品标签对应表
CREATE TABLE `product_tag_map` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_id` BIGINT NOT NULL COMMENT '商品ID',
  `tag_id` BIGINT NOT NULL COMMENT '标签ID',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;