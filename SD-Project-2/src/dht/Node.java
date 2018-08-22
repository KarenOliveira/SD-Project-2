package dht;

import java.io.*;
import java.net.*;

public class Node {
	
	private int id;
	private DataInputStream streamIn;
	private Socket socket;
	private DataOutputStream streamOut;
	public  ServerSocket serverSocket;
	private final int port = 9000;
	private int porta;
	
	public Node(int id) {
		try {
			porta = port+id;
			ServerSocket serverSocket = new ServerSocket(porta);
			System.out.println("Server iniciado...Aguardando Cliente");

			socket = serverSocket.accept();
			streamIn = new DataInputStream(socket.getInputStream());
			streamOut = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erro Batata");
		}
	}
	public static void main(String[] args) {
		Node node = new Node(2);
	}
}
