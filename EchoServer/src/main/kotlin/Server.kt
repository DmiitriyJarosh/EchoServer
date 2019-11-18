import java.net.ServerSocket
import kotlin.system.exitProcess

class Server(threadCount: Int, private val port: Int) {
    private val workerList = Array(threadCount) { ClientWorker() }

    public fun start() {
        val serverSocket = ServerSocket(port)
        println("Started server on port $port")
        workerList.forEach { Thread(it).start() }
        serverSocket.use {
            while (true) {
                val clientSocket = serverSocket.accept()
                println("Socket connected: $clientSocket")
                val worker = workerList.minBy { it.amountOfClients() } ?: exitProcess(-2)
                worker.addClient(clientSocket)
            }
        }
    }
}