# Oasis
A multiplayer game using libGDX and Netty.

# What is it
Firstly, the project name is just a placeholder, it will probably change later.

Currently there is only a few things implemented
  - Server/Client handshake
  - Client > Server create lobby
  - Client > Server join lobby
  - Client > Server send velocity inputs
  - Server > Client update velocity inputs
  - Disconnects
  
Currently, by default client tries to connect to "localhost:8090"
  
# TODO
  - Lobby input codes, currently default is just 9999 for every client
  - Sync player movements, you can see others move its just not synced
  - Collision in lobby
  - Remove players collision
  - Add next stage of game
  
# Whats planned
  - Playing levels with multiple people
  - Lobby support
  - etc

If anything, this will be a good source if you need to see some example code or get stuck on a problem.

# Assets, credit and stuff used
### 16x16 dungeon tiles 
seen in Lobby from [OpenGameArt](https://opengameart.org/content/dungeon-tileset-16x16-in-6-color)

Copyright/Attribution Notice: 
HorusKDI for 6 color palette.
https://opengameart.org/users/gustavo-saraiva

### Font
[OpenGameArt](https://opengameart.org/content/pixel-fonts-by-pix3m)
[DeviantArt](http://pix3m.deviantart.com/art/Bitmap-font-Alagard-381110713)

### UI
[OpenGameArt](https://opengameart.org/content/sci-fi-user-interface-elements)
[OpenGameArt Buch](https://opengameart.org/users/buch)

Heavily modified by me 
