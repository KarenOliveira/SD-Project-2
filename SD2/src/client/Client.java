package client;

import java.net.*;
import java.util.*;
import java.io.*;


public class Client implements Runnable{
	
	private DataOutputStream streamOut;
	//private DataInputStream streamIn;
	private Socket socket;
	private boolean running = true;
	private static Scanner sc = new Scanner(System.in);
	private ArrayList<Integer> listaPortas = new ArrayList<Integer>();
	
	
	public void init() {
		Integer porta;
		do {
			System.out.println("Digite o número da porta, digite o número 0,"
					+ "caso já tenha digitado todas as portas: ");
			porta = (Integer) Integer.parseInt(sc.nextLine());
			listaPortas.add(porta);
		}while(porta!=0);
		join(listaPortas);
	}
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Digite sua mensagem: ");
			String mensagem = sc.nextLine();
			try {
				streamOut.writeUTF(mensagem);
			} catch (IOException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void conectarNode(int porta) throws IOException{
			try {
				System.out.println("Testando Porta: "+porta);
				socket = new Socket("127.0.0.1", porta);
				streamOut = new DataOutputStream(socket.getOutputStream());

			} catch (UnknownHostException e) {
				System.out.println("Host não encontrado");
			} 
	}
	
	public void join(ArrayList<Integer> lista) {
		for(int i = 0;i<lista.size();i++) {
			try {
				conectarNode(lista.get(i));
				System.out.println("Conectado na Porta: " + lista.get(i) + "\n");
				run();
			} catch (IOException e) {
				System.out.println("Porta " + lista.get(i) + " não responde\n");
			}
		}
	}
	
	
	public static void main(String[] args) {
		Client client = new Client();
		client.init();	
	}
}


	