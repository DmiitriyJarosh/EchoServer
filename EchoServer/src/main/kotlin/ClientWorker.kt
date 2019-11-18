import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.system.exitProcess

class ClientWorker : Runnable {
    private val queue = ConcurrentLinkedQueue<Socket>()
    private val socketStreams = HashMap<Socket, Pair<PrintWriter, BufferedReader>>()
    private var stopFlag = false

    override fun run() {
        while (!stopFlag) {
            queue.forEach {
                val (output, input) = socketStreams[it] ?: exitProcess(-3)
                var counter = 0
                while (input.ready() && counter < 5) {
                    val line = input.readLine()
                    println("Received message: $line from $it")
                    output.println(line)
                    counter++
                }
            }
        }
    }

    public fun stop() {
        stopFlag = true
    }

    public fun addClient(socket: Socket) {
        socketStreams[socket] =
            PrintWriter(socket.getOutputStream(), true) to
                    BufferedReader(InputStreamReader(socket.getInputStream()))
        queue.add(socket)
    }

    public fun amountOfClients(): Int = queue.size
}