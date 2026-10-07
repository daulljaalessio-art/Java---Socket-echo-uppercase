package it.ex5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {

    public static void main(String[] args) {

        try (
            ServerSocket serverSocket = new ServerSocket(5000)
        ) {

            System.out.println("Server in ascolto sulla porta 5000...");

            try (
                Socket socket = serverSocket.accept();

                BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );

                PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
                )
            ) {

                String messaggio;

                while (true) {

                    messaggio = input.readLine();

                    if (messaggio == null) {
                        break;
                    }

                    if (messaggio.equalsIgnoreCase("exit")) {
                        break;
                    }

                    output.println(messaggio.toUpperCase());
                }
            }

            System.out.println("Server terminato.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}