package utils;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Hash {
	
    public String getHash(byte[] file) {
        String result = null;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(file);
            return bytesToHex(hash);
        }catch(Exception ex) {}
        
        return result;
    }
    

    public String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
    
}
