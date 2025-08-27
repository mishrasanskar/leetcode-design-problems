import java.util.ArrayList;
import java.util.PriorityQueue;

class SeatManager {

    private PriorityQueue<Integer> pq;
    private final int sz;
    public SeatManager(int n) {
        pq = new PriorityQueue<>();
        sz = n;
        for(int i=0;i<n;i++) pq.add(i+1);
    }

    public int reserve() {
        if(!pq.isEmpty()) return pq.poll();
        return -1;
    }

    public void unreserve(int seatNumber) {
        if(seatNumber>=1 && seatNumber<=sz){
            pq.offer(seatNumber);
        }
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */