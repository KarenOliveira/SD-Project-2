package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

public class Hash {
	
    public String getHash(byte[] file) {

    	 /*
      	  byte[] bytesArray = new byte[(int) file.length()];     	  
      	  
      	  FileInputStream fis;
   		try {
   			fis = new FileInputStream(file);
   			fis.read(bytesArray); //read file into bytes[]
   			fis.close();
   		}
      	 catch (IOException e) { e.printStackTrace(); }
    	*/
    	
        String result = null;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(file);
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    

    public String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
    
}
