-- 文章附件
CREATE TABLE `article_attachment` (
  `id` BIGI NT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` BIGINT NOT NULL COMMENT '文章ID',
  `url` VARCHAR(255) NOT NULL COMMENT '附件URL' COLLATE 'utf8_unicode_ci',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;