package com.example;


import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try {
            // starting server on port 54321
            ServerSocket serverSocket = new ServerSocket(54321);
            System.out.println("Server started on port 54321...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection from " + clientSocket.getInetAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream());

                // check available bytes
                int availableBytes = bis.available();
                byte[] buffer = new byte[availableBytes];
                
                // read the exact number of available bytes
                bis.read(buffer, 0, availableBytes);

                String request = new String(buffer);
                System.out.println("Received: " + request);

                // checks requests and sends the right response
                String response;
                if ("version".equalsIgnoreCase(request)) {
                    response = "Serial Server Submission System 2.0";
                    out.println(response);
                    System.out.println("Received2: " + request);
                    System.out.println(response);
                    response = validateSerial(request.substring(7)); // extracts part after "serial="
                    System.out.println(response);
                    out.println(response);
                    
                    serverSocket.close();
                } else {
                    response = "UNKNOWN COMMAND";
                }

                // Sends response
                out.println(response);

                // Closes connection
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
