-- 商品栏目
CREATE TABLE `commodity_menu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `parent_id` BIGINT DEFAULT 0 COMMENT '父栏目ID',
  `comment` VARCHAR(255) COMMENT '备注' COLLATE 'utf8_unicode_ci'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;