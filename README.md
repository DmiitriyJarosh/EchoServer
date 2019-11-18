# EchoServer
Simple echo server with parallel connections

## Execution
To run server use Server.jar and command `java -cp Server.jar MainKt 5551` where `5551` - is port number for the server. You can also add the second parameter - amount of threads for server.
To run client use Client.jar and command `java -cp Client.jar client.TestClientKt 0.0.0.0 5551` where `0.0.0.0` is server IP and `5551` is server port.