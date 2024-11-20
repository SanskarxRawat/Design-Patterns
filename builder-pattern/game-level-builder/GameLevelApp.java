// Builder Pattern - Game Level Builder
class GameLevel {
    private String terrain;
    private String difficulty;
    private String enemies;

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setEnemies(String enemies) {
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        return "GameLevel [terrain=" + terrain + ", difficulty=" + difficulty + ", enemies=" + enemies + "]";
    }
}

// Builder Interface
interface LevelBuilder {
    void buildTerrain();
    void buildDifficulty();
    void buildEnemies();
    GameLevel getLevel();
}

// Concrete Builder for Forest Level
class ForestLevelBuilder implements LevelBuilder {
    private GameLevel level;

    public ForestLevelBuilder() {
        this.level = new GameLevel();
    }

    @Override
    public void buildTerrain() {
        level.setTerrain("Forest");
    }

    @Override
    public void buildDifficulty() {
        level.setDifficulty("Medium");
    }

    @Override
    public void buildEnemies() {
        level.setEnemies("Wolves and Goblins");
    }

    @Override
    public GameLevel getLevel() {
        return this.level;
    }
}

// Concrete Builder for Desert Level
class DesertLevelBuilder implements LevelBuilder {
    private GameLevel level;

    public DesertLevelBuilder() {
        this.level = new GameLevel();
    }

    @Override
    public void buildTerrain() {
        level.setTerrain("Desert");
    }

    @Override
    public void buildDifficulty() {
        level.setDifficulty("Hard");
    }

    @Override
    public void buildEnemies() {
        level.setEnemies("Scorpions and Bandits");
    }

    @Override
    public GameLevel getLevel() {
        return this.level;
    }
}

// Director Class
class LevelDirector {
    private LevelBuilder builder;

    public void setBuilder(LevelBuilder builder) {
        this.builder = builder;
    }

    public GameLevel constructLevel() {
        builder.buildTerrain();
        builder.buildDifficulty();
        builder.buildEnemies();
        return builder.getLevel();
    }
}

// Prototype Pattern - Level Prototype
interface LevelPrototype extends Cloneable {
    GameLevel cloneLevel();
}

class PrototypeGameLevel extends GameLevel implements LevelPrototype {
    @Override
    public GameLevel cloneLevel() {
        try {
            return (GameLevel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Client Code
public class GameLevelApp {
    public static void main(String[] args) {
        // Builder Pattern - Creating Levels
        LevelDirector director = new LevelDirector();

        LevelBuilder forestBuilder = new ForestLevelBuilder();
        director.setBuilder(forestBuilder);
        GameLevel forestLevel = director.constructLevel();
        System.out.println("Built Level: " + forestLevel);

        LevelBuilder desertBuilder = new DesertLevelBuilder();
        director.setBuilder(desertBuilder);
        GameLevel desertLevel = director.constructLevel();
        System.out.println("Built Level: " + desertLevel);

        // Prototype Pattern - Cloning Levels
        PrototypeGameLevel originalLevel = new PrototypeGameLevel();
        originalLevel.setTerrain("Mountain");
        originalLevel.setDifficulty("Extreme");
        originalLevel.setEnemies("Yetis and Dragons");
        System.out.println("Original Level: " + originalLevel);

        GameLevel clonedLevel = originalLevel.cloneLevel();
        System.out.println("Cloned Level: " + clonedLevel);
    }
}

/**
Explanation:
1. `GameLevel` is the product class that represents the game level with properties like terrain, difficulty, and enemies.
2. `LevelBuilder` is the builder interface that defines the steps to build a game level.
3. `ForestLevelBuilder` and `DesertLevelBuilder` are concrete builders that implement the building steps for different level types.
4. `LevelDirector` is the director class that constructs the level using a specified builder.
5. `LevelPrototype` is the prototype interface that allows cloning of levels.
6. `PrototypeGameLevel` is a concrete class that implements cloning functionality.
7. `GameLevelApp` is the client code that demonstrates creating levels using the builder pattern and cloning them using the prototype pattern.
*/
