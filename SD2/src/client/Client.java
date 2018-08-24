package client;

import java.net.*;
import java.util.*;

import dht.Node;

import java.io.*;


public class Client extends Thread{
	
	private DataOutputStream streamOut;
	private DataInputStream streamIn;
	private Socket socket;
	private boolean running = true;
	private boolean conexao;
	private static Scanner sc = new Scanner(System.in);
	private ArrayList<Integer> listaPortas = new ArrayList<Integer>();
	
	
	public void init() {
		Integer porta;
		do {
			System.out.println("Digite o número da porta, digite o número 0,"
					+ "caso já tenha digitado todas as portas: ");
			porta = (Integer) Integer.parseInt(sc.nextLine());
				if(porta!=0) {
					listaPortas.add(porta);
				}
		}while(porta!=0);
		join(listaPortas);
	}
	
	@Override
	public void run() {
		while(running) {
			while(conexao) {
			System.out.println("Digite sua mensagem: ");
			String mensagem = sc.nextLine();
				try {
					streamOut.writeUTF(mensagem);
					if(mensagem.equals("sair")) {
						conexao = false;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch(NullPointerException e) {
					e.printStackTrace();
				}
			}
		closeConection();
		init();
		}
	}
	
	public void conectarNode(int porta) throws IOException{
			try {
				System.out.println("Testando Porta: "+porta);
				socket = new Socket("127.0.0.1", porta);
				streamOut = new DataOutputStream(socket.getOutputStream());
				streamIn = new DataInputStream(socket.getInputStream());

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
	System.out.println("Nenhum nó encontrado...\nCriando nova DHT\nGerando nó");
	Node node = new Node(16);
	System.out.println("Nó gerado");
	
		try {
			System.out.println("Conectando nó");
			conectarNode(9016);
			System.out.println("Nó conectado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	run();
	}
	public void closeConection(){
		try {
			socket.close();
			streamIn.close();
			streamOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		Client client = new Client();
		client.init();	
	}
}


	