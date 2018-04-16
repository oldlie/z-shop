-- 商品规格
CREATE TABLE `commodity_spec` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `commodity_id` BIGINT COMMENT '商品ID' COLLATE 'utf8_unicode_ci',
  `breed` VARCHAR(255) COMMENT '品种' COLLATE 'utf8_unicode_ci',
  `origin` VARCHAR(255) COMMENT '原产地' COLLATE 'utf8_unicode_ci',
  `feature` VARCHAR(255) COMMENT '特征' COLLATE 'utf8_unicode_ci',
  `spec` VARCHAR(255) COMMENT '规格' COLLATE 'utf8_unicode_ci',
  `store` VARCHAR(255) COMMENT '存储方式' COLLATE 'utf8_unicode_ci',
  `product_datetime` VARCHAR(255) COMMENT '生产日期' COLLATE 'utf8_unicode_ci',
  `price` DECIMAL(6,2) COMMENT '价格',
  `inventory` INT COMMENT '库存'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;