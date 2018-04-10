-- 首页轮播图设置
CREATE TABLE `home_slideshow` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '轮播图标题' COLLATE 'utf8_unicode_ci',
  `url` VARCHAR(255) NOT NULL COMMENT '点击轮播图将要跳转的URL' COLLATE 'utf8_unicode_ci',
  `pic_url` VARCHAR(255) NOT NULL COMMENT '轮播图图片URL' COLLATE 'utf8_unicode_ci',
  `sequence` INT DEFAULT 0 COMMENT '轮播图显示顺序'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;