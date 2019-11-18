import kotlin.system.exitProcess

public fun main(args: Array<String>) {
    val USAGE = "Usage: server.jar <port> [amount of threads]"
    val DEFAULT_THREADS_COUNT = 2

    if (args.size > 2) {
        print("Wrong amount of args. $USAGE")
        exitProcess(-1)
    }

    var argument: Int? = null
    if (args.size == 2) {
        argument = args[1].toIntOrNull()
        if (argument == null) {
            println("Amount of threads should be number!!! $USAGE")
            exitProcess(-1)
        }
    }

    val amountOfThreads = argument ?: DEFAULT_THREADS_COUNT
    val server = Server(amountOfThreads, args[0].toInt())
    server.start()
}