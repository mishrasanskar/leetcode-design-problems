package problem535;

import java.util.HashMap;
import java.util.Map;

public class Codec {

    private Map<String, String> longToShortMap = new HashMap<>();
    private Map<String, String> shortToLongMap = new HashMap<>();
    private final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String shortUrlPrefix = "https://tinyurl.com/";
    // Encodes a URL to a shortened URL.

    private String generateShortUrlCode() {
        char[] generatedCode = new char[6];
        for (int i = 0; i < 6; i++) {
            generatedCode[i] = chars.charAt((int)(Math.random() * chars.length()));
        }
        String code = new String(generatedCode);
        while(shortToLongMap.containsKey(code)) {
            generateShortUrlCode();
        }
        return code;
    }

    public String encode(String longUrl) {
        String shortUrlCode = generateShortUrlCode();
        longToShortMap.put(longUrl, shortUrlPrefix + shortUrlCode);
        shortToLongMap.put(shortUrlPrefix + shortUrlCode, longUrl);
        return shortUrlPrefix + shortUrlCode;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLongMap.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));


