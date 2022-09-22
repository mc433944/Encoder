import java.util.*;

class Encoder {
    
    HashMap<String, String> hashMapCache;
    String originalStr;
    
    public Encoder() {
        this.hashMapCache = new HashMap<>();
        this.originalStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    }
    
    public String encode (String plainText){
        
        if(hashMapCache.containsKey(plainText)) {
            return hashMapCache.get(plainText);
        }
        
        String encodedTextResult = "";
        
        if(plainText.length() > 0) {
            int shiftCount = originalStr.indexOf(plainText.charAt(0));
            
            for(int i = 0; i < plainText.length(); i++) {
                int validCharIndex = originalStr.indexOf(plainText.charAt(i));
                
                if(validCharIndex >= 0) {
                    int shiftedIndex = validCharIndex - shiftCount;
                    if(shiftedIndex < 0) {
                        shiftedIndex += 44;
                    }
                    
                    encodedTextResult += originalStr.charAt(shiftedIndex);
                } else {
                    encodedTextResult += plainText.charAt(i);
                }
            }
        }
        
        hashMapCache.put(plainText, encodedTextResult);
        return encodedTextResult;
    }
    
    public String decode (String encodedText){
        if(hashMapCache.containsValue(encodedText)){
            for (Map.Entry<String, String> entry : hashMapCache.entrySet()) {
                
                String key = entry.getKey();
                String value = entry.getValue();
                if(value.equals(encodedText)) {
                    return key;
                }
    
            }
        }

        return encodedText;
    }
    
}
class Main {
    
    public static void main(String[] args) {
        
        String initialString = "HELLO WORLD";
        Encoder encoder = new Encoder();
        
        String encodedText = encoder.encode(initialString);
        System.out.println(encodedText);
        
        String decodedText = encoder.decode(encodedText);
        System.out.println(decodedText);
        
    }
}