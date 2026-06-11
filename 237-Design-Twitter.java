// https://leetcode.com/problems/design-twitter/


// given: a Twitter class and methods postTweet, getNewsFeed, follow and unfollow
// required: implement the class and its methods

// constraints
// userId, followerId, followeeId in [1, 500]
// tweetId in [0, 10k]
// all tweets have unique IDs
// at most 30k calls will be made to all the methods
// a user cannot follow themself


class Twitter {
    private int counter;
    private Map<Integer, Set<Integer>> followees;   // user -> Set of followees user follows
    private Map<Integer, List<List<Integer>>> tweets;   // user -> List of [counter, tweetId]

    public Twitter() {
        counter = 0;
        followees = new HashMap<>();
        tweets = new HashMap<>();    
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(List.of(counter, tweetId));
        counter++;
    }
    
    // tc: O((f*n) * log(f*n)), sc: O(f*n) where f is the no. of followees of user, n is the no. of tweets from each followee
    public List<Integer> getNewsFeed(int userId) {
        // 1. Add user to its own Set of followees
        followees.putIfAbsent(userId, new HashSet<>());
        followees.get(userId).add(userId);   // Add user to its own List of followees

        // 2. Collect all the tweetIds in the user's feed
        List<List<Integer>> currTweets = new ArrayList<>();
        for(Integer followeeId: followees.get(userId)) {
            for(List<Integer> tweet: tweets.getOrDefault(followeeId, new ArrayList<>())) {
                currTweets.add(tweet);
            }
        }

        // 3. Sort the feed in descending order of counter
        Collections.sort(currTweets, (a, b) -> Integer.compare(b.get(0), a.get(0)));

        // 4. Get the 10 most recent tweetIds from the feed and return it
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < Math.min(10, currTweets.size()); i++) {
            result.add(currTweets.get(i).get(1));
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());
        followees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> currUserFollowees = followees.getOrDefault(followerId, new HashSet<>());
        currUserFollowees.remove(followeeId);
    }
}




class Twitter {
    private int counter;
    private Map<Integer, Set<Integer>> followees;   // user -> Set of followees user follows
    private Map<Integer, List<List<Integer>>> tweets;   // user -> List of [counter, tweetId]

    public Twitter() {
        counter = 0;
        followees = new HashMap<>();
        tweets = new HashMap<>();    
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(List.of(counter, tweetId));
        counter++;
    }
    
    // tc: O((f*n) * logk), sc: O(k) where f is the no. of followees of user, n is the no. of tweets from each followee and k = 10
    public List<Integer> getNewsFeed(int userId) {
        int k = 10;

        // 1. Add user to its own Set of followees
        followees.putIfAbsent(userId, new HashSet<>());
        followees.get(userId).add(userId);   // Add user to its own List of followees

        // 2. Create a PriorityQueue (Min-heap) sorted by counter in asc order
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.get(0), b.get(0))
        );
        for(Integer followeeId: followees.get(userId)) {
            for(List<Integer> tweet: tweets.getOrDefault(followeeId, new ArrayList<>())) {
                pq.offer(tweet);
                if(pq.size() > k) pq.poll();
            }
        }

        // 3. Poll tweets from pq and add to result in reverse order
        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(0, pq.poll().get(1));
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());
        followees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> currUserFollowees = followees.getOrDefault(followerId, new HashSet<>());
        currUserFollowees.remove(followeeId);
    }
}




class Twitter {
    private int counter;
    private Map<Integer, Set<Integer>> followees;   // user -> Set of followees user follows
    private Map<Integer, List<List<Integer>>> tweets;   // user -> List of [counter, tweetId]

    public Twitter() {
        counter = 0;
        followees = new HashMap<>();
        tweets = new HashMap<>();    
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(List.of(counter, tweetId));
        counter++;
    }
    
    // tc: O((f*k) * log(f*k)), sc: O(k) where f is the no. of followees of user, and k = 10
    public List<Integer> getNewsFeed(int userId) {
        int k = 10;

        // 1. Add user to its own Set of followees
        followees.putIfAbsent(userId, new HashSet<>());
        followees.get(userId).add(userId);   // Add user to its own List of followees

        // 2. Create a PriorityQueue (Min-heap) sorted by counter in asc order
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.get(0), b.get(0))
        );
        for(Integer followeeId: followees.get(userId)) {
            int itemsProcessed = 0;   // Process at most 10 tweets from each followee
            List<List<Integer>> currFolloweeTweets = tweets.getOrDefault(followeeId, new ArrayList<>());
            for(int i = currFolloweeTweets.size() - 1; i >= 0; i--) {
                pq.offer(currFolloweeTweets.get(i));
                if(pq.size() > k) pq.poll();

                itemsProcessed++;
                if(itemsProcessed == 10) break;   // Break if we have enough tweets
            }
        }

        // 3. Poll tweets from pq and add to result in reverse order
        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(0, pq.poll().get(1));
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());
        followees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> currUserFollowees = followees.getOrDefault(followerId, new HashSet<>());
        currUserFollowees.remove(followeeId);
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