package me.nrubin29.rpg.core.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import me.nrubin29.rpg.core.server.packet.handler.PacketHandlerManager;
import me.nrubin29.rpg.core.server.packet.packet.Packet;

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

    public void initConnection(String ip) {
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
                            
                            PacketHandlerManager.getInstance().handle(packet);
                        }
                        catch (EOFException e) { System.exit(0); }
                        catch (Exception e) { e.printStackTrace(); }
                    }
                }
            });

            listener.start();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
    
    public void sendPacket(Packet packet) {
    	try {
            System.out.println("Writing " + packet.asString());
    		writer.println(packet.asString());
    	}
    	catch (Exception e) { e.printStackTrace(); }
    }
}