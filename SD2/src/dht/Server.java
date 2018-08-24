package dht;

import java.io.*;
import java.net.*;

public class Server extends Thread {
	private ServerSocket serverSocket;
	private Socket socket;
	private String entrada;
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
				//DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
				DataInputStream streamIn = new DataInputStream(socket.getInputStream());
				while(conexao) {
					entrada = streamIn.readUTF();
					System.out.println(entrada);
					if(entrada.equals("sair")) {
						conexao=false;
						System.out.println("Conexão fechada");
						streamIn.close();
						socket.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}