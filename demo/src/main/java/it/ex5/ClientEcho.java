package it.ex5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEcho {

    public static void main(String[] args) {

        try (
            Socket socket = new Socket("localhost", 5000);

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true
            );

            Scanner scanner = new Scanner(System.in)
        ) {

            while (true) {

                System.out.print("Inserisci una frase: ");
                String messaggio = scanner.nextLine();

                output.println(messaggio);

                if (messaggio.equalsIgnoreCase("exit")) {
                    break;
                }

                String risposta = input.readLine();

                System.out.println(
                    "Risposta dal Server: " + risposta
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}