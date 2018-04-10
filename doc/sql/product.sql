-- 商品
CREATE TABLE `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `summary` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `comment` VARCHAR(255) COMMENT '商品备注，用于备注特殊情况' COLLATE 'utf8_unicode_ci',
  `description` VARCHAR(255) COMMENT '商品描述' COLLATE 'utf8_unicode_ci',
  `status` TINYINT COMMENT '商品状态：0，商品初始；1，商品上架;2，商品下架' COLLATE 'utf8_unicode_ci',
  `create_at` DATETIME COMMENT '商品创建时间',
  `update_at` DATETIME COMMENT '商品最后更新时间',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;