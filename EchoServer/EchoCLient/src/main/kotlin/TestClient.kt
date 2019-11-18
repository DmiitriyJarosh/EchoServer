package client

import java.io.PrintWriter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.Inet4Address

public fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Wrong arguments. Usage: client.jar <server ip> <server port>")
    }
    val ip = Inet4Address.getByName(args[0]).canonicalHostName
    val clientSocket = Socket(ip, args[1].toInt())
    val inFromUser = BufferedReader(InputStreamReader(System.`in`))
    val inFromServer = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
    val outToServer = PrintWriter(clientSocket.getOutputStream(), true)
    while (true) {
        println("Enter string to send: ")
        val msgFromUser = inFromUser.readLine()
        if (msgFromUser == "exit") {
            break
        }
        outToServer.println(msgFromUser)
        val msgFromServer = inFromServer.readLine()
        println("echo: $msgFromServer")
    }
    inFromServer.close()
    inFromUser.close()
    outToServer.close()
    clientSocket.close()
}