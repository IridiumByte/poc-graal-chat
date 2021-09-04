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

## chat-client
JavaFX client for chat, which compiles to native image thanks to GluonFX plugin.

To compile and run native image:
1. Build parent module with `mvn clean install`
2. Go to `chat-client` module
3. Build native image with `mvn gluonfx:build`
4. Run `./target/gluonfx/x86_64-linux/chat-client`

### Development
In order to compile and run in IDE, do the following steps:
1. Go to [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
2. Download JavaFX for you operating system
3. Unpack JavaFX SDK
4. Add it as a library to your IDE (in IntelliJ: File -> Project Structure -> Global libraries -> Add)
5. Add VM options in run configuration: `--module-path="<path-to-javafx>/lib" --add-modules="javafx.base,javafx.controls"`
