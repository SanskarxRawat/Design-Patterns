/**
 * Adapter Pattern: Create a "Media Player" that adapts different media formats (MP3, MP4, AVI) to be played in a standard media player.
 */

// Target Interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee Classes
class MP3Player {
    public void playMP3(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

class MP4Player {
    public void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

class AVIPlayer {
    public void playAVI(String fileName) {
        System.out.println("Playing AVI file: " + fileName);
    }
}

// Adapter Class
class MediaAdapter implements MediaPlayer {
    private MP3Player mp3Player;
    private MP4Player mp4Player;
    private AVIPlayer aviPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mp3Player = new MP3Player();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player = new MP4Player();
        } else if (audioType.equalsIgnoreCase("avi")) {
            aviPlayer = new AVIPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mp3Player.playMP3(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMP4(fileName);
        } else if (audioType.equalsIgnoreCase("avi")) {
            aviPlayer.playAVI(fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// Concrete Media Player
class StandardMediaPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("avi")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Unsupported media type: " + audioType);
        }
    }
}

// Client Code
public class MediaPlayerApp {
    public static void main(String[] args) {
        MediaPlayer player = new StandardMediaPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("avi", "movie.avi");
        player.play("mkv", "show.mkv");
    }
}

/**
Explanation:
1. `MediaPlayer` is the target interface that defines a `play()` method for playing different media types.
2. `MP3Player`, `MP4Player`, and `AVIPlayer` are adaptee classes that provide playback functionality for specific formats.
3. `MediaAdapter` is the adapter class that adapts the interface of the adaptees to the target `MediaPlayer` interface.
4. `StandardMediaPlayer` is the concrete media player that uses the `MediaAdapter` to play different media formats.
5. `MediaPlayerApp` is the client that uses the `StandardMediaPlayer` to play various media files.
*/
