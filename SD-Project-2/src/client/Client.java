package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> knownHostList = new ArrayList<Integer>();
		String userEnter = null;
		Socket socket;
		
		try {
			System.out.println("Digite o n�mero da porta de um n� conhecido, "
					+ "caso n�o conhe�a mais nenhum n�, digite 0:");
			userEnter = sc.nextLine();
			if(!userEnter.equals("0")) {
				knownHostList.add(Integer.parseInt(userEnter));
			}
		}catch (NumberFormatException e) {
			System.out.println("Digite um n�mero v�lido: ");
			userEnter = sc.nextLine();
			knownHostList.add(Integer.parseInt(userEnter));
		}finally {
			
		}
	}
	public void join(ArrayList<Integer> lista) {
		
	}
	public void openSocket() {
		
	}

}
