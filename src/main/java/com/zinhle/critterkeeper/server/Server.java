package com.zinhle.critterkeeper.server;

import com.zinhle.critterkeeper.config.ConfigLoader;
import com.zinhle.critterkeeper.world.Shelter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens for keeper connections and starts a ClientHandler thread for each.
 */
public class Server {

    private final int port;
    private final ShelterManager manager;

    public Server(int port, String configPath) {
        this.port = port;
        Shelter shelter = ConfigLoader.loadShelter(configPath);
        this.manager = new ShelterManager(shelter);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Critter Keeper shelter server listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(clientSocket, manager));
                thread.setDaemon(true);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 5050;
        String configPath = "shelter.json";
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            configPath = args[1];
        }
        new Server(port, configPath).start();
    }
}
