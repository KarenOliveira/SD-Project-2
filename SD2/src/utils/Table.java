package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;

public class Table {

	private Hashtable<Integer, byte[]> tabela;

	public Table() {
		tabela = new Hashtable<>();
	}
	
	public byte[] getValue() throws IOException {
		
		String path = "";
        Scanner sn = new Scanner(System.in);
		
		System.out.print("Please enter the file's path:");
    	path = sn.nextLine();
    	File file = new File(path);
        
    	byte[] bytesArray = new byte[(int) file.length()];     	  
    	  
    	FileInputStream fis;

 		fis = new FileInputStream(file);
 		fis.read(bytesArray); //read file into bytes[]
 		fis.close();
 		
 		return bytesArray;
	}
	
	public int getKey(byte[] value) {
		Hash hs = new Hash();
		   
	    String hash = hs.getHash(value);
	        
	    BigInteger num = new BigInteger(hash,16);
	    BigInteger key = num.remainder(new BigInteger("199"));
	    
	    return key.intValue();
	}
	
	
	
	
	public Hashtable<Integer, byte[]> getTabela() {
		return tabela;
	}
	
	
}
