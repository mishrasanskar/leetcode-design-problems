import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class AuthenticationManager {

    private Map<String, Integer> tokenMap;
    private int ttl;

    public AuthenticationManager(int ttl) {
        tokenMap = new HashMap<>();
        this.ttl = ttl;
    }

    private void removeExpiredTokens(int currentTime){
        List<String> expiredTokens = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : tokenMap.entrySet()) {
            if(entry.getValue()<=currentTime) {
                expiredTokens.add(entry.getKey());
            }
        }

        for(String tokenId : expiredTokens) {
            tokenMap.remove(tokenId);
        }
    }

    public void generate(String tokenId, int currentTime) {
//        removeExpiredTokens(currentTime);
        tokenMap.put(tokenId, currentTime+ttl);
    }

    public void renew(String tokenId, int currentTime) {
        removeExpiredTokens(currentTime);
        if (tokenMap.containsKey(tokenId)) {
            tokenMap.put(tokenId, currentTime+ttl);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        removeExpiredTokens(currentTime);
        return tokenMap.size();
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */