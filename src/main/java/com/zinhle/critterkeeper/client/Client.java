package com.zinhle.critterkeeper.client;

import com.zinhle.critterkeeper.protocol.JsonParser;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A minimal command-line client for talking to the shelter server. Type
 * commands like "forward 2", "adopt puppy Rex", "state", "quit". Responses
 * come back over the wire as JSON, but are rendered here in plain,
 * human-readable text.
 */
public class Client {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String DIM = "\u001B[2m";

    private final String keeperName;
    private final String host;
    private final int port;

    public Client(String keeperName, String host, int port) {
        this.keeperName = keeperName;
        this.host = host;
        this.port = port;
    }

    public void run() {
        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                Scanner keyboard = new Scanner(System.in)
        ) {
            System.out.println("Connected to shelter at " + host + ":" + port + " as " + keeperName);
            System.out.println("Type HELP for a list of commands.");
            while (true) {
                System.out.print("> ");
                if (!keyboard.hasNextLine()) {
                    break;
                }
                String line = keyboard.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                String commandWord = parts[0];
                List<String> arguments = Arrays.asList(parts).subList(1, parts.length);

                Request request = new Request(keeperName, commandWord, arguments);
                out.println(JsonParser.toJson(request));

                String responseLine = in.readLine();
                if (responseLine == null) {
                    System.out.println("Server closed the connection.");
                    break;
                }
                printResponse(responseLine);

                if ("quit".equalsIgnoreCase(commandWord)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Could not connect to shelter server: " + e.getMessage());
        }
    }

    /** Turns a raw JSON response line into readable, coloured console output. */
    private void printResponse(String responseLine) {
        Response response;
        try {
            response = JsonParser.parseResponse(responseLine);
        } catch (Exception e) {
            // Fall back to the raw line if it's not valid JSON for any reason.
            System.out.println(responseLine);
            return;
        }
        if (response == null) {
            System.out.println(responseLine);
            return;
        }

        String tag = response.isOk() ? GREEN + "OK" + RESET : RED + "ERROR" + RESET;
        System.out.println("[" + tag + "] " + response.getMessage());

        State state = response.getState();
        if (state != null) {
            System.out.println(DIM + formatState(state) + RESET);
        }
    }

    private String formatState(State state) {
        StringBuilder line = new StringBuilder();
        line.append("  position ").append('[').append(state.getX()).append(',').append(state.getY()).append(']')
                .append("  facing ").append(state.getFacing());
        if (!"none".equalsIgnoreCase(state.getSpecies())) {
            line.append("  |  ").append(state.getSpecies())
                    .append(": hunger ").append(state.getHunger())
                    .append(", happiness ").append(state.getHappiness())
                    .append(", energy ").append(state.getEnergy());
        }
        return line.toString();
    }

    public static void main(String[] args) {
        String keeperName = args.length >= 1 ? args[0] : "keeper1";
        String host = args.length >= 2 ? args[1] : "localhost";
        int port = args.length >= 3 ? Integer.parseInt(args[2]) : 5050;
        new Client(keeperName, host, port).run();
    }
}
