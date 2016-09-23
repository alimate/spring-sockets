# Fictional Trades Push Service with Spring WebSocket
It's a fictional financial WebSocket service that pushes random and fake stock updates to every open session every three seconds. It's currently accessible at `wss://spring-sockets.herokuapp.com/stocks`. There may be [wake up call][heroku-sleeping] for this app, so be patient if you're using it.

### Message format
Every three seconds, the `/stocks` endpoint pushes a random stock update with the following schema to all open sessions:

    { "symbol": "symbol_name", "value": val }
    
### How to run it locally?
Just use the following command and your service would be accessible at `ws://localhost:8080/stocks`:

    mvnw spring-boot:run

[heroku-sleeping]: https://blog.heroku.com/app_sleeping_on_heroku
