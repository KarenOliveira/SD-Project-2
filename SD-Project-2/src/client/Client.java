package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Client {
	private DataOutputStream streamOut;
	private Socket socket;
	private Scanner sc = new Scanner(System.in);
	public DataInputStream streamIn;
	
	public Client(){
		try {
			socket = new Socket("127.0.0.1", 9002);
			streamOut = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void join(ArrayList<Integer> lista) {
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client client = new Client();
		System.out.println("Oi");
		ArrayList<Integer> knownHostList = new ArrayList<Integer>();
		String userEnter = "";
		
		try {
			System.out.println("Digite o n�mero da porta de um n� conhecido, "
					+ "caso n�o conhe�a mais nenhum n�, digite 0:");
			userEnter = sc.nextLine();
			if(!userEnter.equals("0")) {
				//knownHostList.add(Integer.parseInt(userEnter));
				try {
					client.streamOut.writeUTF(userEnter);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}catch (NumberFormatException e) {
			System.out.println("Digite um n�mero v�lido: ");
			userEnter = sc.nextLine();
			knownHostList.add(Integer.parseInt(userEnter));
		}
	}
}
