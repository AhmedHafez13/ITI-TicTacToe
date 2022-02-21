
CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `total_score` int DEFAULT '0',
  `is_online` tinyint(1) DEFAULT '0',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- ----------------------------------------------------------- --

CREATE TABLE `game` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_x_id` int NOT NULL,
  `player_o_id` int NOT NULL,
  `winner` enum('x','o') NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `moves` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `player_x_id` (`player_x_id`),
  KEY `player_o_id` (`player_o_id`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`player_x_id`) REFERENCES `player` (`id`),
  CONSTRAINT `game_ibfk_2` FOREIGN KEY (`player_o_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
