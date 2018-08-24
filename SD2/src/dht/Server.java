package dht;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private static ServerSocket serverSocket;
	private Socket socket;
	private boolean running = true;
	private boolean conexao = true;
	
	public Server(ServerSocket ss) {
		this.serverSocket = ss;
	}
	
	public void run() {
		while(running) {
			try {
				socket = serverSocket.accept();
				new Server(serverSocket).start();
				DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
				DataInputStream streamIn = new DataInputStream(socket.getInputStream());
				while(conexao) {
					System.out.println(streamIn.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}