# Tic Tac Toe Project

## What's new

### `2022.02.07`

- `DBManager` classes added to the main package
- `models` package is created to include the `Game` and the `Player` models
- `Game` object represents a current playing game between two players
  - The only class that contains and perform action on the game including adding new moves, converting the moves array to json string to store in the database and storing the winner
- `Player` object carries the data of the current signed in player

## Notes

- At the begining of every game, `Game` object is created and pushed to the current games array `currentPlayingGames`
- At the end of every game, `Game` object is removed from the current games array `currentPlayingGames` and is recored in the database

---

### Mohamed Ahmed Mohamed Ali Shehata

...

### Ahmed Mohamed Hafez Abdelhamid

...

### Khloud elsaid abbas mohamed

- `insertNewGame` insert new game into the `game` table

### Yara Mohammed Samir

- `getPlayersSortedByStatus` select players from the `player` table, sorted by status... (is_online = true) first then (is_online = false)
- `getPlayersSortedByScore` select players from the `player` table, sorted by score... higher score first

### Amr Abdalaziz Abdallah

- `registerNewPlayer` insert new player, When the user signs up
- `signInPlayer` validate name and password... check if the name and password matches any record in the databaes... set playerId to the player id from the database or return -1 if can't signin
