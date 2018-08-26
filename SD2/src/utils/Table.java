package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;

public class Table {

	private Hashtable<BigInteger, File> tabela;

	public Table() {
		tabela = new Hashtable<>();
	}
	
	public void putFile() {
		String path = "";
        Scanner sn = new Scanner(System.in);
        Hash hs = new Hash();
        
        while(!path.equals("0")) {
        	
        	System.out.print("Please enter data for which SHA256 is required:");
        	path = sn.nextLine();
        	File file = new File(path);
	        
	        
	        String hash = hs.getHash(file);
	        
	        BigInteger num = new BigInteger(hash,16);
	        BigInteger key = num.remainder(new BigInteger("199"));
	        
	        System.out.println("Hash calculado: " + num);
	        System.out.println("         Chave: " + key);
	        
	        tabela.put(key, file);
	        
	        System.out.println(tabela.get(key).getPath());
        }
	}
	
	
	public Hashtable<BigInteger, File> getTabela() {
		return tabela;
	}
	
	
}
