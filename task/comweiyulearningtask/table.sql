use taskmonitordb;
drop table tasklist;

CREATE TABLE `tasklist` (
  `taskName` varchar(200) NOT NULL DEFAULT 'task' COMMENT 'taskName',
  `job_cron` varchar(100) DEFAULT NULL COMMENT 'job_cron',
   `multi_Instance` bool default false comment '是否多实例允许',
  `jobContent` varchar(2000) DEFAULT NULL COMMENT 'jobContent',
  `remarks` varchar(2000) DEFAULT NULL COMMENT 'remarks',
  `jobStatus` int(11) DEFAULT NULL COMMENT '0、有效',
  `workIps` varchar(5000) comment '当前运行的ip地址列表',
  `DataChange_LastTime` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间',
  PRIMARY KEY (`taskName`),
  KEY `ix_DataChange_LastTime` (`DataChange_LastTime`)
)  COMMENT='任务列表';


CREATE TABLE `taskmonitor` (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `taskName` varchar(100) NOT NULL DEFAULT 'task' COMMENT 'taskName',
  `workIp` varchar(100) DEFAULT NULL COMMENT 'workIp',
  `jobContent` varchar(2000) DEFAULT NULL COMMENT 'jobContent',
  `remarks` varchar(2000) DEFAULT NULL COMMENT 'remarks',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `jobStatus` int(11) DEFAULT NULL COMMENT '1、运行中，2、运行完成',
  PRIMARY KEY (`id`),
  KEY `ix_DataChange_LastTime` (`DataChange_LastTime`)
) COMMENT='job监控';


CREATE TABLE `taskmonitorlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `taskName` varchar(500) DEFAULT NULL COMMENT 'taskName',
  `workIp` varchar(100) DEFAULT NULL COMMENT 'workIp',
  `contents` varchar(2000) DEFAULT NULL COMMENT '内容',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_DataChange_LastTime` (`DataChange_LastTime`)
)  COMMENT='job运行日志表';




SELECT `tasklist`.`taskName`,
    `tasklist`.`job_cron`,
    `tasklist`.`multi_Instance`,
    `tasklist`.`jobContent`,
    `tasklist`.`remarks`,
    `tasklist`.`jobStatus`,
    `tasklist`.`workIps`,
    `tasklist`.`DataChange_LastTime`
FROM `taskmonitordb`.`tasklist`;

