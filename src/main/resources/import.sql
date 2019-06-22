-- 系统权限添加语句

insert  into `tb_role`(`id`,`create_time`,`description`,`role_key`,`name`,`status`,`update_time`) values (1,'2019-06-20 17:25:30','超级管理员','administrator','administrator',0,'2019-06-20 17:26:25');
insert  into `tb_user`(`id`,`address`,`birthday`,`create_time`,`delete_status`,`description`,`email`,`locked`,`nick_name`,`password`,`sex`,`telephone`,`update_time`,`user_name`) values (1,'湖南','2019-06-20 17:26:39','2019-06-20 17:26:41',0,'超级管理员','barry.lyj@outlook.com',0,'admin','3931MUEQD1939MQMLM4AISPVNE',1,'15000000000','2019-06-20 17:27:11','admin');
insert  into `tb_user_role`(`user_id`,`role_id`) values (1,1);

-- 系统管理
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(1,'2019-06-20 13:56:51','系统管理',NULL,0,1,'系统管理',1,'system:index','/system/index',1,'2019-06-20 13:59:01',NULL);
-- 用户、角色、资源、druid监控、swagger监控
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(2,'2019-06-20 13:56:51','用户管理',NULL,0,2,'用户管理',1,'system:user:index','/admin/user/index',1,'2019-06-20 13:59:01',1),(3,'2019-06-20 13:56:51','用户编辑',NULL,0,3,'用户编辑',1,'system:user:edit','/admin/user/edit*',2,'2019-06-20 16:26:42',2),(4,'2019-06-20 16:48:48','用户添加',NULL,0,3,'用户添加',2,'system:user:add','/admin/user/add',2,'2019-06-20 16:49:26',2),(5,'2019-06-20 16:48:48','用户删除',NULL,0,3,'用户删除',3,'system:user:deleteBatch','/admin/user/deleteBatch',2,'2019-06-20 14:11:41',2),(6,'2019-06-20 16:48:48','角色分配',NULL,0,3,'角色分配',4,'system:user:grant','/admin/user/grant/**',2,'2019-06-20 14:11:51',2);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(7,'2019-06-20 16:45:10','角色管理',NULL,0,2,'角色管理',2,'system:role:index','/admin/role/index',1,'2019-06-20 16:46:52',1),(8,'2019-06-20 16:47:02','角色编辑',NULL,0,3,'角色编辑',1,'system:role:edit','/admin/role/edit*',2,'2019-06-20 10:24:06',7),(9,'2019-06-20 16:47:23','角色添加',NULL,0,3,'角色添加',2,'system:role:add','/admin/role/add',2,'2019-06-20 16:49:16',7),(10,'2019-06-20 16:47:23','角色删除',NULL,0,3,'角色删除',3,'system:role:deleteBatch','/admin/role/deleteBatch',2,'2019-06-20 14:12:03',7),(11,'2019-06-20 16:47:23','资源分配',NULL,0,3,'资源分配',4,'system:role:grant','/admin/role/grant/**',2,'2019-06-20 14:12:11',7);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(12,'2019-06-20 11:21:12','资源管理',NULL,0,2,'资源管理',3,'system:resource:index','/admin/resource/index',1,'2019-06-20 11:21:42',1),(13,'2019-06-20 11:21:52','资源编辑',NULL,0,3,'资源编辑',1,'system:resource:edit','/admin/resource/edit*',2,'2019-06-20 11:22:36',12),(14,'2019-06-20 11:21:54','资源添加',NULL,0,3,'资源添加',2,'system:resource:add','/admin/resource/add',2,'2019-06-20 11:22:39',12),(15,'2019-06-20 11:21:54','资源删除',NULL,0,3,'资源删除',3,'system:resource:deleteBatch','/admin/resource/deleteBatch',2,'2019-06-20 14:12:31',12);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(16,'2019-06-20 11:21:54','druid监控',NULL,0,2,'druid监控',4,'system:druid:index','/druid',1,'2019-06-20 14:12:31',1);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(17,'2019-06-20 11:21:54','swagger接口文档',NULL,0,2,'swagger接口文档',5,'system:swagger:index','/swagger-ui.html',1,'2019-06-20 14:12:31',1);

insert  into `tb_role_resource`(`role_id`,`resource_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17);




-- 订单添加sql语句

insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(40,'2019-06-20 13:56:51','订单管理',NULL,0,1,'订单管理',1,'order:index','/order/index',1,'2019-06-20 13:59:01',NULL);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(41,'2019-06-20 11:21:12','订单管理',NULL,0,2,'订单管理',3,'order:list','/order/index',1,'2019-06-20 11:21:42',40),(42,'2019-06-20 11:21:52','订单编辑',NULL,0,3,'订单编辑',1,'order:edit','/order/edit*',2,'2019-06-20 11:22:36',40),(43,'2019-06-20 11:21:54','订单添加',NULL,0,3,'订单添加',2,'order:add','/order/add',2,'2019-06-20 11:22:39',40),(44,'2019-06-20 11:21:54','订单删除',NULL,0,3,'订单删除',3,'order:deleteBatch','/order/deleteBatch',2,'2019-06-20 14:12:31',40);
insert  into `tb_resource`(`id`,`create_time`,`description`,`icon`,`is_hide`,`level`,`name`,`sort`,`source_key`,`source_url`,`type`,`update_time`,`parent_id`) values(45,'2019-06-20 11:21:12','流量统计',NULL,0,2,'流量统计',4,'order:flow','javascript:window.open(\'http://www.51.la/report/main?comId=20048555\');',1,'2019-06-20 11:21:42',40);
insert  into `tb_role_resource`(`role_id`,`resource_id`) values (1,40),(1,41),(1,42),(1,43),(1,44),(1,45);
