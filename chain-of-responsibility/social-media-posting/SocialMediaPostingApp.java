import java.util.ArrayList;
import java.util.List;

// Chain of Responsibility Pattern - Content Moderation
abstract class ContentModerator {
    protected ContentModerator nextModerator;

    public void setNextModerator(ContentModerator nextModerator) {
        this.nextModerator = nextModerator;
    }

    public abstract void moderate(String content);
}

// Concrete Moderator Classes
class LanguageModerator extends ContentModerator {
    @Override
    public void moderate(String content) {
        if (content.contains("badword")) {
            System.out.println("Content blocked due to inappropriate language.");
        } else if (nextModerator != null) {
            nextModerator.moderate(content);
        } else {
            System.out.println("Content approved.");
        }
    }
}

class LengthModerator extends ContentModerator {
    @Override
    public void moderate(String content) {
        if (content.length() > 280) {
            System.out.println("Content blocked due to excessive length.");
        } else if (nextModerator != null) {
            nextModerator.moderate(content);
        } else {
            System.out.println("Content approved.");
        }
    }
}

class SpamModerator extends ContentModerator {
    @Override
    public void moderate(String content) {
        if (content.contains("buy now")) {
            System.out.println("Content blocked due to potential spam.");
        } else if (nextModerator != null) {
            nextModerator.moderate(content);
        } else {
            System.out.println("Content approved.");
        }
    }
}

// Command Pattern - Scheduling Posts
interface PostCommand {
    void execute();
}

class SocialMediaPostCommand implements PostCommand {
    private String content;

    public SocialMediaPostCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute() {
        System.out.println("Posting content: " + content);
    }
}

// Scheduler Class
class PostScheduler {
    private List<PostCommand> scheduledPosts = new ArrayList<>();

    public void schedulePost(PostCommand postCommand) {
        scheduledPosts.add(postCommand);
        System.out.println("Post scheduled.");
    }

    public void executeScheduledPosts() {
        for (PostCommand post : scheduledPosts) {
            post.execute();
        }
        scheduledPosts.clear();
    }
}

// Client Code
public class SocialMediaPostingApp {
    public static void main(String[] args) {
        // Chain of Responsibility - Content Moderation
        ContentModerator languageModerator = new LanguageModerator();
        ContentModerator lengthModerator = new LengthModerator();
        ContentModerator spamModerator = new SpamModerator();

        languageModerator.setNextModerator(lengthModerator);
        lengthModerator.setNextModerator(spamModerator);

        String postContent = "This is a clean post without any bad words or spam.";
        System.out.println("Moderating content: " + postContent);
        languageModerator.moderate(postContent);

        String spamContent = "Buy now and get 50% off!";
        System.out.println("\nModerating content: " + spamContent);
        languageModerator.moderate(spamContent);

        // Command Pattern - Scheduling Posts
        PostScheduler scheduler = new PostScheduler();
        PostCommand post1 = new SocialMediaPostCommand("Hello, world!");
        PostCommand post2 = new SocialMediaPostCommand("Check out our latest product!");

        scheduler.schedulePost(post1);
        scheduler.schedulePost(post2);

        System.out.println("\nExecuting scheduled posts:");
        scheduler.executeScheduledPosts();
    }
}

/**
Explanation:
1. `ContentModerator` is the abstract base class for the Chain of Responsibility pattern that defines the `moderate()` method for content moderation.
2. `LanguageModerator`, `LengthModerator`, and `SpamModerator` are concrete classes that implement moderation rules for content.
3. `PostCommand` is the command interface that defines the `execute()` method for scheduling posts.
4. `SocialMediaPostCommand` is the concrete implementation of the `PostCommand` interface that schedules social media posts.
5. `PostScheduler` is the invoker class that schedules and executes posts.
6. `SocialMediaPostingApp` is the client code that demonstrates using both Chain of Responsibility for content moderation and Command for scheduling posts.
*/
