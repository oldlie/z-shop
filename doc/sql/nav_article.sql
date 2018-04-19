-- 文章导航设置
CREATE TABLE `nav_article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_menu_id` BIGINT NOT NULL COMMENT '文章栏目ID',
  `article_menu_title` VARCHAR(255) NOT NULL COMMENT '文章栏目标题' COLLATE 'utf8_unicode_ci',
  `sequence` INT DEFAULT 0 COMMENT '显示顺序'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;