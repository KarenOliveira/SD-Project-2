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
				DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
				DataInputStream streamIn = new DataInputStream(socket.getInputStream());
				while(conexao) {
					entrada = streamIn.readUTF();
					System.out.println(entrada);
					if(entrada.equals("sair")) {
						conexao=false;
						closeConection(streamIn, streamOut, socket);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeConection(DataInputStream sIn, DataOutputStream sOut, Socket s) throws IOException{
		System.out.println("Conexão fechada");
		sIn.close();
		sOut.close();
		socket.close();
	}
}