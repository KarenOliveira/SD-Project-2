package Pct;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Node2 implements Runnable{

	private DataInputStream streamIn;
	private Socket socket;
	private DataOutputStream streamOut;
	public  ServerSocket serverSocket;
	private boolean running=true;
	
	public Node2(){	
		
	}
	
	public void run() {
		while(running) {
			try {
				//System.out.println("Lululu");
				System.out.println(this.streamIn.readUTF());
				//running = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void abrirServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(9815);
			System.out.println("Server iniciado...Aguardando Cliente");
			
			socket = serverSocket.accept();
			streamIn = new DataInputStream(socket.getInputStream());
			streamOut = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erro Batata");
		}
	}
	
	public void send(String mensagem) {
		
		DataOutputStream sendStreamOut;
		Socket sendSocket;
		Scanner sc = new Scanner(System.in);
		
		try {
			sendSocket = new Socket("127.0.0.1", 9815);
			sendStreamOut = new DataOutputStream(sendSocket.getOutputStream());
			mensagem = "Karen é doida";
			sendStreamOut.writeUTF(mensagem);
			sendStreamOut.close();
			sendSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
