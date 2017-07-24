# 创建数据库
CREATE DATABASE IF NOT EXISTS seckill;
#创建表
#创建秒杀商品表
USE seckill;
CREATE TABLE seckill (
  seckill_id     BIGINT AUTO_INCREMENT NOT NULL
  COMMENT '秒杀商品ID',
  seckill_number INT                   NOT NULL
  COMMENT '秒杀商品库存',
  seckill_name   VARCHAR(50)           NOT NULL
  COMMENT '秒杀商品名字',
  start_time     DATETIME              NOT NULL
  COMMENT '开始秒杀的时间',
  end_time       DATETIME              NOT NULL
  COMMENT '结束秒杀时间',
  create_time    DATETIME              NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY ind_create_time(create_time)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT '秒杀库存表';

#创建商品秒杀成功表
USE seckill;
CREATE TABLE success_killed (
  seckill_id  BIGINT   NOT NULL
  COMMENT '秒杀商品ID',
  user_phone  BIGINT   NOT NULL
  COMMENT '电话号码',
  state       TINYINT  NOT NULL
  COMMENT '状态标示： -1：无效 0：秒杀成功 1：已付款 2：已发货',
  create_time DATETIME NOT NULL
  COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '秒杀成功明细表';

#初始化数据
USE seckill;
INSERT INTO seckill (seckill_name, seckill_number, start_time, end_time)
VALUES
  ('100块钱秒杀笔记本', 1, '2017-4-13 16:00', '2017-4-14 00:00'),
  ('1块钱秒杀iphone7', 10, '2017-4-20 8:00', '2017-4-20 10:00'),
  ('2块钱秒杀iphone6', 50, '2017-4-21 8:00', '2017-4-21 10:00'),
  ('3块钱秒杀iphone5', 5, '2017-4-22 8:00', '2017-4-22 10:00'),
  ('4块钱秒杀小米6', 30, '2017-4-23 8:00', '2017-4-23 10:00'),
  ('5块钱秒杀小米5', 20, '2017-4-24 8:00', '2017-4-24 10:00');
