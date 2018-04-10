-- 首页展示商品列表
CREATE TABLE `home_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_id` BIGINT NOT NULL COMMENT '商品ID',
  `sequence` INT DEFAULT 0 COMMENT '商品显示顺序'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;