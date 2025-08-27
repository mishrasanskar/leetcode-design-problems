import java.util.ArrayList;
import java.util.TreeSet;
class ExamRoom {

    private TreeSet<Integer> seats;
    private int sz;
    public ExamRoom(int n) {
        seats = new TreeSet<>();
        sz = n;
    }

    public int seat() {
        if(seats.isEmpty()){
            seats.add(0);
            return 0;
        }

        int dist = seats.first();
        int id = 0;
        Integer prev = null;

        for(Integer i : seats){

            if(prev !=null){
                int d = (i-prev)/2;
                if(d > dist){
                    dist = d;
                    id = prev + d;
                }
            }

            prev = i;

        }

        if(sz-1-seats.last() > dist){
            id = sz-1;
        }

        seats.add(id);
        return id;

    }

    public void leave(int p) {
        seats.remove(p);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */