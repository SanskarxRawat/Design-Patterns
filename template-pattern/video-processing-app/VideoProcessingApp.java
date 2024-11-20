// Template Method Pattern - Video Processing for Different Formats
abstract class VideoProcessor {
    // Template method
    public final void processVideo() {
        loadVideo();
        decodeVideo();
        applyEffects();
        saveVideo();
    }

    protected abstract void loadVideo();
    protected abstract void decodeVideo();

    protected void applyEffects() {
        System.out.println("Applying default effects.");
    }

    protected void saveVideo() {
        System.out.println("Saving processed video.");
    }
}

class MP4VideoProcessor extends VideoProcessor {
    @Override
    protected void loadVideo() {
        System.out.println("Loading MP4 video.");
    }

    @Override
    protected void decodeVideo() {
        System.out.println("Decoding MP4 video.");
    }
}

class AVIVideoProcessor extends VideoProcessor {
    @Override
    protected void loadVideo() {
        System.out.println("Loading AVI video.");
    }

    @Override
    protected void decodeVideo() {
        System.out.println("Decoding AVI video.");
    }
}

// Strategy Pattern - Applying Video Effects
interface VideoEffectStrategy {
    void applyEffect();
}

class BlackAndWhiteEffect implements VideoEffectStrategy {
    @Override
    public void applyEffect() {
        System.out.println("Applying black and white effect.");
    }
}

class SepiaEffect implements VideoEffectStrategy {
    @Override
    public void applyEffect() {
        System.out.println("Applying sepia effect.");
    }
}

class NoEffect implements VideoEffectStrategy {
    @Override
    public void applyEffect() {
        System.out.println("No special effects applied.");
    }
}

class VideoEditor {
    private VideoEffectStrategy effectStrategy;

    public void setEffectStrategy(VideoEffectStrategy effectStrategy) {
        this.effectStrategy = effectStrategy;
    }

    public void applyEffect() {
        if (effectStrategy != null) {
            effectStrategy.applyEffect();
        } else {
            System.out.println("No effect strategy set.");
        }
    }
}

// Client Code
public class VideoProcessingApp {
    public static void main(String[] args) {
        // Template Method Pattern - Processing Different Video Formats
        VideoProcessor mp4Processor = new MP4VideoProcessor();
        VideoProcessor aviProcessor = new AVIVideoProcessor();

        System.out.println("Processing MP4 Video:");
        mp4Processor.processVideo();

        System.out.println("\nProcessing AVI Video:");
        aviProcessor.processVideo();

        // Strategy Pattern - Applying Video Effects
        VideoEditor editor = new VideoEditor();
        System.out.println("\nApplying Video Effects:");

        editor.setEffectStrategy(new BlackAndWhiteEffect());
        editor.applyEffect();

        editor.setEffectStrategy(new SepiaEffect());
        editor.applyEffect();

        editor.setEffectStrategy(new NoEffect());
        editor.applyEffect();
    }
}

/**
Explanation:
1. `VideoProcessor` is the abstract class for the Template Method pattern that defines the steps for processing a video.
2. `MP4VideoProcessor` and `AVIVideoProcessor` are concrete implementations of `VideoProcessor` that specify how to load and decode different video formats.
3. `VideoEffectStrategy` is the strategy interface that defines the method `applyEffect()` for applying effects to a video.
4. `BlackAndWhiteEffect`, `SepiaEffect`, and `NoEffect` are concrete strategies that implement different video effects.
5. `VideoEditor` is the context class that maintains a reference to a `VideoEffectStrategy` and uses it to apply effects.
6. `VideoProcessingApp` is the client code that demonstrates using both the Template Method pattern for processing different video formats and the Strategy pattern for applying video effects.
*/
