package me.nrubin29.rpg.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private BufferedReader reader;
    private PrintWriter writer;
    private String userName;

    public Client(Socket socket) {
        try {
        	System.out.println("Connection Request!");
        	
        	reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            System.out.println("Waiting for userName.");

            this.userName = reader.readLine();
            
            System.out.println("Got it!");

            Thread messageListener = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            System.out.println("We got something!");

                            String in = reader.readLine();

                            if (in == null) {
                                Server.getInstance().removeClient(Client.this);
                                break;
                            }

                            Server.getInstance().sendPacket(in, Client.this);
                        }
                        catch (SocketException e) { Server.getInstance().removeClient(Client.this); break; }
                        catch (EOFException e) { Server.getInstance().removeClient(Client.this); break; }
                        catch (Exception e) { e.printStackTrace(); /* TODO: for now. */ }
                    }
                }
            });

            messageListener.start();
        }
        catch (Exception e) { e.printStackTrace(); /* TODO: for now. */ }
    }

    public void sendPacket(String packet) {
        try { writer.println(packet); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public String getUserName() {
        return userName;
    }
}