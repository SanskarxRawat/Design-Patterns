import java.util.ArrayList;
import java.util.List;

// Memento Class
class GameSave {
    private int level;
    private int health;

    public GameSave(int level, int health) {
        this.level = level;
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }
}

// Originator Class
class Game {
    private int level;
    private int health;

    public void set(int level, int health) {
        this.level = level;
        this.health = health;
        System.out.println("Game state set to - Level: " + level + ", Health: " + health);
    }

    public GameSave save() {
        System.out.println("Saving game state - Level: " + level + ", Health: " + health);
        return new GameSave(level, health);
    }

    public void restore(GameSave save) {
        this.level = save.getLevel();
        this.health = save.getHealth();
        System.out.println("Game state restored - Level: " + level + ", Health: " + health);
    }
}

// Caretaker Class
class GameSaveManager {
    private List<GameSave> saveList = new ArrayList<>();

    public void addSave(GameSave save) {
        saveList.add(save);
    }

    public GameSave getSave(int index) {
        return saveList.get(index);
    }
}

// Client Code
public class GameSaveSystemApp {
    public static void main(String[] args) {
        Game game = new Game();
        GameSaveManager saveManager = new GameSaveManager();

        game.set(1, 100);
        saveManager.addSave(game.save());

        game.set(2, 80);
        saveManager.addSave(game.save());

        game.set(3, 50);
        // Restore to previous state
        game.restore(saveManager.getSave(1));

        // Restore to initial state
        game.restore(saveManager.getSave(0));
    }
}

/**
Explanation:
1. `GameSave` is the memento class that stores the game state (level and health).
2. `Game` is the originator class that can create a save (memento) and restore its state from a save.
3. `GameSaveManager` is the caretaker class that manages the list of saved game states.
4. `GameSaveSystemApp` is the client that interacts with the game, saves its state, and restores it when needed.
*/
