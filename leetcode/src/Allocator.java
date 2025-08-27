import java.util.*;

class Allocator {


    private int sz;
    private ArrayList<Integer> arr;
    public Allocator(int n) {
        sz = n;
        arr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            arr.add(-1);
        }
    }

    public int allocate(int size, int mID) {

        int i = 0;
        int id = -1;

        while(i<sz){
            int cnt = 0;
            int j = i;
            if(arr.get(j) == -1){
                while(j<sz && cnt<size && arr.get(j) == -1){
                    cnt++;
                    j++;
                }
                if(cnt == size){
                    for(int k=i;k<j;k++){
                        arr.set(k, mID);
                    }
                    id = i;
                    return id;
                }
                i = j;
            }
            else{
                i+=1;
            }
        }
        return id;
    }

    public int freeMemory(int mID) {
        int cnt = 0;
        for(int i = 0; i<sz; i++){
            if(arr.get(i) == mID){
                arr.set(i, -1);
                cnt++;
            }
        }
        return cnt;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */