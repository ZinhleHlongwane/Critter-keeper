package com.zinhle.critterkeeper.protocol;

/**
 * Convenience entry point. Run the Server or Client classes directly for
 * the actual application; this just prints a pointer if someone runs the
 * built jar without arguments.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Critter Keeper");
        System.out.println("Run com.zinhle.critterkeeper.server.Server to start the shelter server.");
        System.out.println("Run com.zinhle.critterkeeper.client.Client to connect as a keeper.");
    }
}
