-- 文章
CREATE TABLE `article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL COMMENT '标题' COLLATE 'utf8_unicode_ci',
  `summary` VARCHAR(255) NOT NULL COMMENT '文章摘要' COLLATE 'utf8_unicode_ci',
  `image_url` VARCHAR(255) NOT NULL COMMENT '文章题图' COLLATE 'utf8_unicode_ci',
  `author` VARCHAR(32) NOT NULL COMMENT '作者' COLLATE 'utf8_unicode_ci',
  `auhtor_id` BIGINT COMMENT '作者ID，网站用户写的文章有这个ID。后台发布的可以没有',
  `content` TEXT NOT NULL COMMENT '文章内容，HTML格式的' COLLATE 'utf8_unicode_ci',
  `status` INT DEFAULT 0 COMMENT '文章状态：0，初始状态/草稿状态；1，公开；2，仅自己可见',
  `view_count` INT DEFAULT 0 COMMENT '文章被查看次数',
  `agree_count` INT DEFAULT 0 COMMENT '文章被点赞次数',
  `share_count` INT DEFAULT 0 COMMENT '文章被分享次数',
  `allow_comment` TINYINT DEFAULT 1 COMMNET '是否允许评论',
  `ranking` INT DEFAULT 0 COMMENT '文章总分',
  `ranking_count` INT DEFAULT 0 COMMENT '文章评分次数',
  `create_at` DATETIME COMMENT '创建时间',
  `update_at` DATETIME COMMENT '最后更新时间',
  `publish_at` DATETIME COMMENT '发布时间',
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;