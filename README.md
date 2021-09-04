# poc-graal-chat

GraalVM and quarkus PoC

## chat-server
Server based on quarkus, includes websocket server and http server.

### Endpoints
`ws://server-ip:8080/chat` - websocket endpoint \
`http://server-ip:8080/chat/channels` - available channels

### Build
Building server requires `chat-api`. Go to `chat-server` directory and execute:
```shell
mvn package -Dnative  
```
after compilation application can be run with:
```shell
./target/chat-server-0.0.1-SNAPSHOT-runner 
```