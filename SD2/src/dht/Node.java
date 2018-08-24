package dht;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Node{
	private static ServerSocket serverSocket;
	private Socket socket;
	private int id;
	private final int port = 9000;
	private int porta;
	
	public Node(int i) {
		this.id=i;
		porta = port+id;
		try {
			ServerSocket serverSocket = new ServerSocket(porta);
			Server server = new Server(serverSocket);
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Node node = new Node(2);
	}	
}