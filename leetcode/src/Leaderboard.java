import java.util.*;

class Node {
    private int score;
    private int playerId;

    public Node(int score, int playerId) {
        this.score = score;
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getScore() {
        return score;
    }
}

class Leaderboard {


    private Map<Integer,Integer> scoreMap;
    PriorityQueue<Node> pq;
    public Leaderboard() {
        scoreMap = new HashMap<>();
        pq = new PriorityQueue<>(
                (a,b) -> b.getScore() - a.getScore()
        );
    }

    public void addScore(int playerId, int score) {
        scoreMap.put(playerId, scoreMap.getOrDefault(playerId, 0) + score);
        pq.add(new Node(scoreMap.get(playerId),playerId));
    }

    public int top(int K) {
        int cnt = 0, sum = 0;
        List<Node> temp = new ArrayList<>();
        while (cnt < K && !pq.isEmpty()) {

                Node node = pq.poll();
                if(scoreMap.get(node.getPlayerId()) == node.getScore()){
                    cnt++;
                    sum += node.getScore();
                }

                temp.add(node);

        }

        for(Node node : temp){
            pq.add(node);
        }
        return sum;
    }

    public void reset(int playerId) {
        scoreMap.put(playerId,0);
        pq.add(new Node(0,playerId));
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */