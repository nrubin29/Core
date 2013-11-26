package me.nrubin29.rpg.server;

import java.net.BindException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Server {

    private Server() {
        new Thread(new Runnable() {
            public void run() {
                Scanner s = new Scanner(System.in);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("stop")) System.exit(0);
                }
            }
        }).start();
    }

    private static Server instance = new Server();

    public static Server getInstance() {
        return instance;
    }

    private ServerSocket server;
    private ArrayList<Client> users = new ArrayList<Client>();

    public void start() {
        try {
            server = getOpenSocket();
            
            System.out.println("Port: " + server.getLocalPort());

            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try { new Client(server.accept()); }
                        catch (Exception e) { e.printStackTrace(); /* TODO: for now. */ }
                    }
                }
            }).start();
        }
        catch (Exception e) { e.printStackTrace(); /* TODO: for now. */ }
    }

    private ServerSocket getOpenSocket() {
        try {
            Random r = new Random();
            int rPort = r.nextInt(65535);
            if (rPort < 1000) return getOpenSocket();
            return new ServerSocket(rPort, 100);
        }
        catch (StackOverflowError e) { System.exit(1); }
        catch (BindException e) { return getOpenSocket(); }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public void sendPacket(String packet, Client sender) {
    	System.out.println("Sending " + packet + " from " + sender.getUserName());
    	for (Client client : users) {
    		if (client != sender) client.sendPacket(packet);
    	}
    }

    public Client getClient(String name) {
        for (Client c : users) {
            if (c.getUserName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public void addClient(Client client) {
        users.add(client);
    }

    public void removeClient(Client client) {
        users.remove(client);
    }

    public ArrayList<Client> getUsers() {
        return users;
    }

    public static void main(String[] args) {
        Server.getInstance().start();
    }
}