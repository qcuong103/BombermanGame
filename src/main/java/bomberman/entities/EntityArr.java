package bomberman.entities;

import bomberman.entities.blocks.Brick;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.enemy.Enemy;
import bomberman.entities.item.Item;
import bomberman.view.GameViewManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityArr {

    public static List<Bomber> bombers = new ArrayList<>();

    public static List<bomberman.entities.Entity> grasses = new ArrayList<>();

    public static List<bomberman.entities.Entity> walls = new ArrayList<>();

    public static List<Brick> bricks = new ArrayList<>();

    public static List<bomberman.entities.Entity> portals = new ArrayList<>();

    public static List<Item> items = new ArrayList<>();

    public static List<Enemy> enemies = new ArrayList<>();

    public static bomberman.entities.Bomber bomberman;

    public static void removeEnemy() {
        Iterator<Enemy> enemyIterator = enemies.listIterator();
        Enemy enemy;
        while (enemyIterator.hasNext()) {
            enemy = enemyIterator.next();
            if (!enemy.isAlive()) {
                enemyIterator.remove();
//                GameViewManager.POINTS += 100;
                GameViewManager.addPoints(100);
            }
        }
    }

    public static void removeBrick() {
        bricks.removeIf(Brick::isBroken);
    }

    public static void removeBomb() {
        bomberman.bombs.removeIf(Bomb::isExploded);
    }

    public static void clearArr() {
        bombers.clear();
        grasses.clear();
        bricks.clear();
        walls.clear();
        items.clear();
        portals.clear();
        enemies.clear();
    }
}