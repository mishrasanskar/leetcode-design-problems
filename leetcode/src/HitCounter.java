import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class HitCounter {

    private Map<Integer,Integer> counter;
    public HitCounter() {
        counter = new ConcurrentHashMap<>();
    }


    public void hit(int timestamp) {

    }

    public int getHits(int timestamp) {
        return 1;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */