package com.example;


import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Startet den Server auf Port 54321
            ServerSocket serverSocket = new ServerSocket(54321);
            System.out.println("Server started on port 54321...");

            while (true) {
                // Wartet auf eine Verbindung vom Client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection from " + clientSocket.getInetAddress());

                // Liest die Anfrage vom Client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = in.readLine();
                System.out.println("Received: " + request);

                // Überprüft die Anfrage und sendet die entsprechende Antwort
                String response = "";
                if ("version".equalsIgnoreCase(request)) {
                    response = "Serial Server Submission System 2.0";
                } else if (request.startsWith("serial=")) {
                    response = validateSerial(request.substring(7)); // Extrahiert den Teil nach "serial="
                } else {
                    response = "UNKNOWN COMMAND";
                }

                // Sendet die Antwort zurück
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(response);

                // Schließt die Verbindung
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String validateSerial(String serial) {
        return "SERIAL VALID=1";
    }
}