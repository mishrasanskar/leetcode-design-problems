import java.util.*;

class RangeFreqQuery {

    private Map<Integer, List<Integer>> m;

    public RangeFreqQuery(int[] arr) {
        m = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(m.containsKey(arr[i])){
                m.get(arr[i]).add(i);
            }
            else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                m.put(arr[i], list);
            }
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> list = m.get(value);
        if(list == null){return 0;}

        int st = Collections.binarySearch(list, left);
        if (st < 0) st = -st - 1;

        int en = Collections.binarySearch(list, right);
        if (en < 0) en = -en - 2;

        return Math.max(en - st + 1, 0);


    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */