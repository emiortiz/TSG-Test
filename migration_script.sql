DROP DATABASE IF EXISTS database_test;

CREATE DATABASE database_test;

USE database_test;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `creation_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO database_test.users (`username`,`password`, `email`, `creation_time`) VALUES ('usuario_signin','{bcrypt}$2a$10$JMy53Oxm904ovD9xM5g7D.th2/QJZEkZV9AOyQyKM2XfcrSPOhwmK','mail@asd.com', '2019-12-25T10:10:30');
INSERT INTO database_test.users (`username`,`password`, `email`, `creation_time`) VALUES ('usuario_update','{bcrypt}$2a$10$JMy53Oxm904ovD9xM5g7D.th2/QJZEkZV9AOyQyKM2XfcrSPOhwmK','mail@asd.com', '2019-12-25T10:10:30');
INSERT INTO database_test.users (`username`,`password`, `email`, `creation_time`) VALUES ('usuario_delete','{bcrypt}$2a$10$JMy53Oxm904ovD9xM5g7D.th2/QJZEkZV9AOyQyKM2XfcrSPOhwmK','mail@asd.com', '2019-12-25T10:10:30');


--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `id_user` BIGINT  NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `creation_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `modification_time` DATETIME ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (1,'post1_title','post_description','2019-12-25T10:10:30','2019-12-25T10:10:30');
INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (1,'post2_title','post_description','2019-12-25T10:10:30','2019-12-25T10:10:30');
INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (1,'post3_title','post_description','2019-12-25T10:10:30','2019-12-25T10:10:30');
INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (2,'post4_title','post_description','2019-12-25T10:10:30','2019-12-25T10:10:30');
INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (2,'post_udpate_title','post_update_description','2019-12-25T10:10:30','2019-12-25T10:10:30');
INSERT INTO database_test.posts (`id_user`, `title`, `description`, `creation_time`, `modification_time`) VALUES (1,'post_delete_title','post_delete_description','2019-12-25T10:10:30','2019-12-25T10:10:30');

  