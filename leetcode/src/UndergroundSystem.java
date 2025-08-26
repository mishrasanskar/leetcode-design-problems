import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Schema{
    private String station;
    private int time;
    public Schema(String station, int time) {
        this.station = station;
        this.time = time;
    }
    public String getStation() {
        return station;
    }
    public int getTime() {
        return time;
    }
}

class Schema2{
    private int sum;
    private int cnt;
    public Schema2(int sum, int cnt) {
        this.sum = sum;
        this.cnt = cnt;
    }
    public int getSum() {
        return sum;
    }
    public int getCnt() {
        return cnt;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}

class UndergroundSystem {

    private Map<Integer, Schema> mp;
    private Map<String, Schema2> mp2;
    public UndergroundSystem() {
        mp = new HashMap<>();
        mp2 = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        mp.put(id, new Schema(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {

        Schema ls = mp.get(id);

        String k = ls.getStation() + "->" + stationName;
        int diff = t-ls.getTime();
        if(mp2.containsKey(k)){
            int prevSum =  mp2.get(k).getSum();
            mp2.get(k).setSum(prevSum+diff);
            int prevCnt = mp2.get(k).getCnt();
            mp2.get(k).setCnt(prevCnt+1);
        }
        else{
            mp2.put(k,new Schema2(diff,1));
        }
        mp.remove(id);

    }

    public double getAverageTime(String startStation, String endStation) {
        String k =  startStation + "->" + endStation;
        int sum = mp2.get(k).getSum();
        int cnt = mp2.get(k).getCnt();
        return (double)sum/cnt;
    }
}
