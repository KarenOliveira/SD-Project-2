package dht;

import java.io.*;
import java.net.*;
import java.util.*;

import utils.Table;
import utils.Utils;

public class Node extends Thread {
	
	private final static int PORT = 9000;
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	private int porta;
	private int portSuc;
	private int portAnt;
	private int id;

	private String entrada;
	private boolean running = true;
	private boolean conexao = true;
	
	private DataOutputStream streamOut;
	private DataInputStream streamIn;
	
	private Table table;
	
	
	private Random gerador;
	
	public Node(int id, int portaAnt, int portaSuc) {
		this.id = id;
		this.portSuc = portaSuc;
		this.portAnt = portaAnt;
		gerador = new Random();
		
		table = new Table();
		
		porta = PORT + id;
		try {
			serverSocket = new ServerSocket(porta);
			System.out.println("Oi");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(running) {
			try {
				socket = serverSocket.accept();
				streamIn = new DataInputStream(socket.getInputStream());
				streamOut = new DataOutputStream(socket.getOutputStream());
				
				while(conexao) {
					entrada = streamIn.readUTF();
					System.out.println(entrada);
					int idNovo;
					
					String[] comando = Utils.cutString(entrada);
					int tempKey;
					
					switch(comando[0]) {
					case "JOIN":
						idNovo = gerador.nextInt(id)+portAnt-9000;
						sendMessage("NEW_NODE " + idNovo, portAnt);
						
						streamOut.writeUTF("JOIN_OK " + idNovo + " " + portAnt + " " + (9000+id));
						portAnt = idNovo+9000;
						break;
					case "JOIN_OK":
						break;
					case "NEW_NODE":
						portSuc = Integer.parseInt(comando[1]);
						break;
					case "LEAVE":
						portAnt = Integer.parseInt(comando[1]);
						break;
					case "NODE_GONE":
						portSuc = Integer.parseInt(comando[1]);
						break;
					case "STORE":
						tempKey = Integer.parseInt(comando[1]);
						if(tempKey > id) sendMessage("STORE " + tempKey + " " + comando[2], portSuc);
						else table.putFile();
						break;
					case "RETRIEVE":
						tempKey = Integer.parseInt(comando[1]);
						if(tempKey > id) sendMessage("RETRIEVE " + tempKey + " " + comando[2], portSuc);
						else {
							if(table.getTabela().get(tempKey) == null) sendMessage("NOT_FOUND", Integer.parseInt(comando[2]));
							else sendMessage("OK " + table.getTabela().get(tempKey), Integer.parseInt(comando[2]));
						}
						break;
					case "OK":
						System.out.println("Arquivo encontrado \n" + comando[1]);
						break;
					case "NOT_FOUND":
						System.out.println("Arquivo não encontrado");
						break;
					case "TRANSFER":
						//table.getTabela().put(Integer.parseInt(comando[1]), comando[2]);
						break;
					}
					
					//if(entrada.equals("sair")) {
						//conexao=false;
						//closeConection(streamIn, streamOut, socket);
					//}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String mensagem, int portaDestino) throws UnknownHostException, IOException  {
		
		socket = new Socket("127.0.0.1", portaDestino);
		streamOut = new DataOutputStream(socket.getOutputStream());
		
		//streamOut.writeUTF("JOIN");
		closeConection(streamIn, streamOut, socket);
	}
	
	
	
	public void closeConection(DataInputStream sIn, DataOutputStream sOut, Socket s) throws IOException{
		System.out.println("Conexão fechada");
		sIn.close();
		sOut.close();
		socket.close();
	}
	
	public static void main(String[] args) {
		Node node = new Node(5, 9000, 9033);
		node.run();
	}

	public int getPorta() {
		return porta;
	}

	public int getPortSuc() {
		return portSuc;
	}

	public Table getTable() {
		return table;
	}
	
	
	
}