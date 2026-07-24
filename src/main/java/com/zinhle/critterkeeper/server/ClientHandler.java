package com.zinhle.critterkeeper.server;

import com.zinhle.critterkeeper.protocol.JsonParser;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Handles one client connection: reads a line of JSON, hands it to the
 * ShelterManager, writes the JSON response back, and repeats until the
 * client disconnects.
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ShelterManager manager;

    public ClientHandler(Socket socket, ShelterManager manager) {
        this.socket = socket;
        this.manager = manager;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                Response response;
                try {
                    Request request = JsonParser.parseRequest(line);
                    response = manager.handle(request);
                } catch (Exception e) {
                    response = Response.error("could not parse request: " + e.getMessage());
                }
                out.println(JsonParser.toJson(response));
            }
        } catch (IOException e) {
            // client disconnected unexpectedly - nothing more to do
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
                // socket already closed
            }
        }
    }
}
