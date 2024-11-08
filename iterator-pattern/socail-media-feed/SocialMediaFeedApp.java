import java.util.ArrayList;
import java.util.List;

/**
 * Iterator Pattern: Design a "Social Media Feed" where the feed iterator can retrieve posts by different criteria (friends, trending, recent).
 */

// Post Class
class Post {
    private String content;
    private String type;

    public Post(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}

// Aggregate Interface
interface Feed {
    SocialIterator getIterator(String criteria);
}

// Concrete Aggregate Class
class SocialMediaFeed implements Feed {
    private List<Post> posts;

    public SocialMediaFeed() {
        posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public SocialIterator getIterator(String criteria) {
        return new FeedIterator(posts, criteria);
    }
}

// Iterator Interface
interface SocialIterator {
    boolean hasNext();
    Post next();
}

// Concrete Iterator Class
class FeedIterator implements SocialIterator {
    private List<Post> posts;
    private String criteria;
    private int position;

    public FeedIterator(List<Post> posts, String criteria) {
        this.posts = posts;
        this.criteria = criteria;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        while (position < posts.size()) {
            Post post = posts.get(position);
            if (criteria.equalsIgnoreCase("friends") && post.getType().equalsIgnoreCase("friend")) {
                return true;
            } else if (criteria.equalsIgnoreCase("trending") && post.getType().equalsIgnoreCase("trending")) {
                return true;
            } else if (criteria.equalsIgnoreCase("recent")) {
                return true;
            } else {
                position++;
            }
        }
        return false;
    }

    @Override
    public Post next() {
        if (this.hasNext()) {
            return posts.get(position++);
        }
        return null;
    }
}

// Client Code
public class SocialMediaFeedApp {
    public static void main(String[] args) {
        SocialMediaFeed feed = new SocialMediaFeed();
        feed.addPost(new Post("Friend's post #1", "friend"));
        feed.addPost(new Post("Trending post #1", "trending"));
        feed.addPost(new Post("Recent post #1", "recent"));
        feed.addPost(new Post("Friend's post #2", "friend"));
        feed.addPost(new Post("Trending post #2", "trending"));

        System.out.println("--- Friends' Posts ---");
        SocialIterator friendIterator = feed.getIterator("friends");
        while (friendIterator.hasNext()) {
            Post post = friendIterator.next();
            System.out.println(post.getContent());
        }

        System.out.println("\n--- Trending Posts ---");
        SocialIterator trendingIterator = feed.getIterator("trending");
        while (trendingIterator.hasNext()) {
            Post post = trendingIterator.next();
            System.out.println(post.getContent());
        }

        System.out.println("\n--- Recent Posts ---");
        SocialIterator recentIterator = feed.getIterator("recent");
        while (recentIterator.hasNext()) {
            Post post = recentIterator.next();
            System.out.println(post.getContent());
        }
    }
}

/**
Explanation:
1. `Post` is a simple class that represents a post in the social media feed with content and type.
2. `Feed` is the aggregate interface that defines a method `getIterator()` for getting an iterator based on a specific criteria.
3. `SocialMediaFeed` is the concrete aggregate class that contains a list of posts and provides an iterator to iterate over them.
4. `Iterator` is the iterator interface that defines methods `hasNext()` and `next()` for iteration.
5. `FeedIterator` is the concrete iterator class that iterates over the posts in the social media feed based on the given criteria (friends, trending, recent).
6. `SocialMediaFeedApp` is the client that creates a social media feed, adds posts to it, and iterates over them using different criteria.
*/
