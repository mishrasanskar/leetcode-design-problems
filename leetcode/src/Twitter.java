import java.util.*;

class User{
    private int userId;
    private List<Tweet> userTweets;
    private Set<Integer> followerIds;
    private Set<Integer> followingIds;

    public User(int userId) {
        this.userId = userId;
        this.userTweets = new ArrayList<>();
        this.followerIds = new HashSet<>();
        this.followingIds = new HashSet<>();
    }

    public List<Tweet> getUserTweets() {
        return userTweets;
    }

    public void postUserTweet(Tweet tweet){
        userTweets.add(tweet);
    }

    public void addFollower(int followerId){
        followerIds.add(followerId);
    }

    public void addFollowing(int followingId){
        followingIds.add(followingId);
    }

    public void removeFollowing(int followingId){
        followingIds.remove(Integer.valueOf(followingId));
    }
    public void removeFollower(int followerId){
        followerIds.remove(Integer.valueOf(followerId));
    }
    public Set<Integer> getFollowingIds() {
        return followingIds;
    }
}

class Tweet{
    private int tweetId;
    private int timestamp;
    public Tweet(int tweetId, int timestamp){
        this.tweetId = tweetId;
        this.timestamp = timestamp;
    }
    public int getTweetId() {
        return tweetId;
    }
    public int getTimestamp() {
        return timestamp;
    }
}

class Twitter {

    private Map<Integer, User> userMap;
    private static int timestamp = 0;

    public Twitter() {
        userMap = new HashMap<>();
    }

    private User getOrCreate(Integer userId){
        if(userMap.containsKey(userId)) return userMap.get(userId);
        User user = new User(userId);
        userMap.put(userId, user);
        return user;
    }

    public void postTweet(int userId, int tweetId) {

        User user = getOrCreate(userId);
        timestamp+=1;
        user.postUserTweet(new Tweet(tweetId, timestamp));
    }

    public List<Integer> getNewsFeed(int userId) {

        User user = getOrCreate(userId);
        Set<Integer> userFollowingIds = user.getFollowingIds();
        PriorityQueue<Tweet> pq = new PriorityQueue<>(
                (a,b) -> b.getTimestamp() - a.getTimestamp()
        );
        if(!user.getUserTweets().isEmpty()) pq.addAll(user.getUserTweets());
        if(!userFollowingIds.isEmpty()) {
            for (Integer followingId : userFollowingIds) {

                User followingUser = getOrCreate(followingId);
                List<Tweet> tweets = followingUser.getUserTweets();
                pq.addAll(tweets);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!pq.isEmpty() && ans.size() < 10){
            int tweetId = pq.poll().getTweetId();
            ans.add(tweetId);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if(followerId != followeeId) {
            User followee = getOrCreate(followeeId);
            followee.addFollower(followerId);
            User follower = getOrCreate(followerId);
            follower.addFollowing(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if(followerId != followeeId) {
            User follower = userMap.get(followerId);
            if(follower!=null) follower.removeFollowing(followeeId);
            User followee = userMap.get(followeeId);
            if (follower!=null) followee.removeFollower(followerId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
