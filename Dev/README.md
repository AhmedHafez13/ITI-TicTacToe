# Tic Tac Toe Project

✔ Register
✔ Sign in
✔ List players


✔ Sign in or Sign up as a first time player.
- After player sign in:
    - ✔ Showing Online/Offline status of players on the updated
players list after sign in.
    - 📌 Allow the player to play in single mode( i.e. play with the
    computer)
    - ✔ Player can ask any online player to play with him.
    - 📌 The application should give the user an option to record the game
    and store it at the server for replay later.
    - 📌 [GAME END] The application should give the player bonus points when the
    player wins.
    - Use Points to classify players at player list.


## What's new

### `2022.02.07`

#### Server Project

- `DBManager` classes added to the main package
- `models` package is created to include the `Game` and the `Player` models
- `Game` object represents a current playing game between two players
  - The only class that contains and perform action on the game including adding new moves, converting the moves array to json string to store in the database and storing the winner
- `Player` object carries the data of the current signed in player

## Notes

- At the begining of every game, `Game` object is created and pushed to the current games map `currentGames`
- At the end of every game, `Game` object is removed from the current games map `currentGames` and is recored in the database

---

### Mohamed Ahmed Mohamed Ali Shehata

- Game logic
  - Game end
  - Play with computer

#### Server Side

- Degsin the `leaderboard` scene with all `controller` functionality

---

### Ahmed Mohamed Hafez Abdelhamid

- Client/Server communication

#### Server Side

- Degsin the `server manager` scene with all `controller` functionality

---

### Khloud elsaid abbas mohamed

#### Server Side

- `insertNewGame` insert new game into the `game` table

#### Client Side

- Degsin the `game` scene with all `controller` functionality

---

### Yara Mohammed Samir

#### Server Side

- `getPlayersSortedByStatus` select players from the `player` table, sorted by status... (is_online = true) first then (is_online = false)
- `getPlayersSortedByScore` select players from the `player` table, sorted by score... higher score first

#### Client Side

- Degsin the `main menu` and `invitation window` scenes with all `controllers` functionality

---

### Amr Abdalaziz Abdallah

#### Server Side

- `registerNewPlayer` validate data on the client side and send to the server to insert it to the database (username is unique) then the server send the result to take and action the client side
- `signInPlayer` validate name and password... check if the name and password matches any record in the databaes... set playerId to the player id from the database or return -1 if can't signin

#### Client Side

- Degsin the `sign in` and `register` scenes with all `controllers` functionality
