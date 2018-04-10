-- 商品规格
CREATE TABLE `product_spec` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `product_id` BIGINT COMMENT '商品ID',
  `breed` VARCHAR(255) COMMENT '品种',
  `origin` VARCHAR(255) COMMENT '原产地',
  `feature` VARCHAR(255) COMMENT '特征',
  `spec` VARCHAR(255) COMMENT '规格',
  `store` VARCHAR(255) COMMENT '存储方式',
  `product_datetime` VARCHAR(255) COMMENT '生产日期',
  `price` DECIMAL(6,2) COMMENT '价格',
  `inventory` INT COMMENT '库存'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;