package client;

import java.io.*;
import java.net.*;
import java.util.*;

import dht.Node;
import utils.Utils;


public class API implements Runnable{
	
	private DataOutputStream streamOut;
	private DataInputStream streamIn;
	private String entrada;
	private Socket socket;
	
	private boolean running = true;
	private static Scanner sc = new Scanner(System.in);
	
	private Node node;
	
	private ArrayList<Integer> listaPortas = new ArrayList<Integer>();
	
	
	public void init() {
		listaPortas.clear();
		
		Integer porta;
		do {
			System.out.println("Digite o número da porta, digite o número 0,"
					+ "caso já tenha digitado todas as portas: ");
			porta = (Integer) Integer.parseInt(sc.nextLine());
			listaPortas.add(porta);
		} while(porta!=0);
		
		join(listaPortas);
	}
	
	public void join(ArrayList<Integer> lista) {
		
		for(int i = 0;i<lista.size();i++) {
			try {
				conectarNode(lista.get(i));
				
				System.out.println("Conectado na Porta: " + lista.get(i) + "\n");
				streamOut.writeUTF("JOIN");
				System.out.println("JOIN enviado");
				
				entrada = streamIn.readUTF();
				String[] ok = Utils.cutString(entrada);
				
				node = new Node(Integer.parseInt(ok[1]), Integer.parseInt(ok[2]), Integer.parseInt(ok[3]), Integer.parseInt(ok[4]));
				node.start();
				run();				
			} catch (IOException e) {
				System.out.println("Porta " + lista.get(i) + " não responde\n");
			}
		}
		
		node = new Node(8,0,0,0);
		System.out.println("Nenhum nó encontrado \n Criando nova DHT");
		
		node.start();
		run();
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
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Digite o comando desejado:\n"
					+ "[procurar] Procura arquivo que esteja armazenado.\n"
					+ "[guardar] Guarda um arquivo.\n"
					+ "[sair] Desconectar\n");
			
			String mensagem = sc.nextLine();
			
			switch(mensagem) {
			case "procurar":
				retrieve();
				break;
			case "guardar":
				store();
				break;
			case "sair":
				leave();
				break;
			}
		}
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
	
	public void leave() {
		try {
			node.sendMessage("LEAVE", node.getPortSuc());
			
	        Set<Integer> keys = node.getTable().getTabela().keySet();
	        for(Integer key: keys){
	           node.sendMessage("TRANSFER " + key + " " + node.getTable().getTabela().get(key), node.getPortSuc());
	        }
	        
	        int id = node.getPortSuc() - 9000;
	        node.sendMessage("NODE_GONE " + id + " " + node.getPortSuc(), node.getPortAnt());
	        running = false;
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void store() {
		byte[] value;
		int key;
	
		try {
			value = node.getTable().getValue();
			key = node.getTable().getKey(value);
			node.sendMessage("STORE " + Arrays.toString(value) + " " + key, node.getPortSuc());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void retrieve() {
		
		try {
			System.out.println("Insira a chave de busca: ");
			int key = Integer.parseInt(sc.nextLine());
			node.sendMessage("RETRIEVE " + node.getTable().getTabela().get(key) + " " + node.getcurrentId(), node.getPortSuc());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		API api = new API();
		api.init();	
	}
}