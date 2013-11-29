package me.nrubin29.rpg.core.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.packet.handler.PacketHandlerManager;
import me.nrubin29.rpg.core.server.packet.packet.Packet;
import me.nrubin29.rpg.core.server.packet.packet.PacketJoin;
import me.nrubin29.rpg.game.Main;

import javax.swing.*;

public class ServerConnector {
	
	private ServerConnector() { }
	
	private static ServerConnector instance = new ServerConnector();
	
	public static ServerConnector getInstance() {
		return instance;
	}

	private Socket socket;
    private Thread listener;
    private BufferedReader reader;
    private PrintWriter writer;

    private boolean usingServer = false;

    public boolean initConnection(String ip) {
        try {
            socket = new Socket(ip.split(":")[0], Integer.valueOf(ip.split(":")[1]));
            
            System.out.println("Connecting to " + socket);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("Sending userName.");
            
            writer.println(Session.getInstance().getLocalPlayer().getName());
            
            System.out.println("Sent!");

            listener = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            String packet = reader.readLine();

                            System.out.println("Got packet: " + packet);
                            
                            if (packet == null) System.exit(0);
                            
                            PacketHandlerManager.getInstance().handle(packet);
                        }
                        catch (EOFException e) { System.exit(0); }
                        catch (Exception e) { e.printStackTrace(); }
                    }
                }
            });

            listener.start();
            
            sendPacket(new PacketJoin(Session.getInstance().getLocalPlayer().getName()));

            usingServer = true;
            return true;
        }
        catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Could not connect to server at given port.");
            return false;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error has occurred (" + e + ").");
            return false;
        }
    }
    
    public void sendPacket(Packet packet) {
        if (!usingServer) return;

    	try {
            System.out.println("Writing " + packet.asString());
    		writer.println(packet.asString());
    	}
    	catch (Exception e) { e.printStackTrace(); }
    }
}