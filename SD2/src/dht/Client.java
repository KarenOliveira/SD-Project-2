package dht;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable{

	private DataOutputStream streamOut;
	private DataInputStream streamIn;
	
	private Socket socket;
	private boolean running = true;
	private boolean conexao;
	private static Scanner sc = new Scanner(System.in);
	
	private int portaDestino;
	
	public Client(int portaDestino){
		this.portaDestino = portaDestino;
	}

	@Override
	public void run() {
		/*
		while(running) {
			do {
				String mensagem = "JOIN";
				sendMessage(mensagem);
		}while(conexao==true);
			closeConection(streamIn, streamOut, socket);
		}*/
		
	}
	
	public void sendMessage(String mensagem) throws UnknownHostException, IOException  {
		
			socket = new Socket("127.0.0.1", portaDestino);
			streamIn = new DataInputStream(socket.getInputStream());
			streamOut = new DataOutputStream(socket.getOutputStream());
			
			streamOut.writeUTF("JOIN");
			closeConection(streamIn, streamOut, socket);
	}
	
	public void closeConection(DataInputStream sIn, DataOutputStream sOut, Socket s){
		System.out.println("Conexão fechada");
		try {
			sIn.close();
			sOut.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
