CREATE TABLE player
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  password TEXT NOT NULL,
  total_score INT,
  is_online BOOLEAN,
  avatar VARCHAR(200)
);

CREATE TABLE game
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  player_x_id INT NOT NULL,
  player_o_id INT NOT NULL,
  winner ENUM('x', 'o') NOT NULL,
  moves TEXT NOT NULL,
  time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (player_x_id) REFERENCES player(id),
  FOREIGN KEY (player_o_id) REFERENCES player(id)
);

/* CREATE TABLE players_games
(
  player_id INT NOT NULL,
  game_id INT NOT NULL,
  PRIMARY KEY (player_id, game_id),
  FOREIGN KEY (player_id) REFERENCES player(id),
  FOREIGN KEY (game_id) REFERENCES game(id)
); */