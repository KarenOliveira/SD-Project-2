package dht;

import java.io.*;
import java.net.*;

public class Node{
	private ServerSocket serverSocket;
	private int id;
	private final int port = 9000;
	private int porta;
	
	public Node(int i) {
		this.id=i;
		porta = port+id;
		try {
			serverSocket = new ServerSocket(porta);
			Server server = new Server(serverSocket);
			server.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Node node = new Node(2);
	}	
}