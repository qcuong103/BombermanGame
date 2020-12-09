package bomberman.entities;

import bomberman.entities.blocks.Brick;
import bomberman.entities.blocks.Grass;
import bomberman.entities.blocks.Portal;
import bomberman.entities.blocks.Wall;
import bomberman.entities.enemy.*;
import bomberman.view.GameViewManager;
import bomberman.view.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CreateMap {

    public static void createMapByLevel(int level) {
        bomberman.entities.EntityArr.clearArr();
        bomberman.entities.EntityArr.bomberman = new bomberman.entities.Bomber(1, 1, Sprite.player_right.getFxImage());
        bomberman.entities.EntityArr.bombers.add(bomberman.entities.EntityArr.bomberman);
        try {
            String path = "resources/view/levels/Level" + level + ".txt";
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = buffReader.readLine().trim();
            String[] str = line.split(" ");
            GameViewManager.HEIGHT = Integer.parseInt(str[1]);
            GameViewManager.WIDTH = Integer.parseInt(str[2]);
            char [][] maps = new char[GameViewManager.HEIGHT][GameViewManager.WIDTH];

            for (int i = 0; i < GameViewManager.HEIGHT; ++i) {
                line = buffReader.readLine();
                for (int j = 0; j < GameViewManager.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < GameViewManager.WIDTH; ++i) {
                for (int j = 0 ; j < GameViewManager.HEIGHT; ++j) {
                    Brick brick;
                    bomberman.entities.Entity object;
                    Balloom balloom;
                    Oneal oneal;
                    Doll doll;
//                    Minvo minvo;
//                    Kondoria kondoria;
                    Ovape ovape;
//                    Pass pass;
                    // create wall and grass
                    if (j == 0 || j == GameViewManager.HEIGHT - 1 || i == 0 || i == GameViewManager.WIDTH - 1 || maps[j][i] == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        bomberman.entities.EntityArr.walls.add(object);
                    } else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        bomberman.entities.EntityArr.grasses.add(object);
                    }
                    // create portal
                    if (maps[j][i] == 'x') {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        bomberman.entities.EntityArr.portals.add(object);
                    }
                    // create brick
                    if (maps[j][i] == 'x' || maps[j][i] == '*') {
                        brick = new Brick(i, j, Sprite.brick.getFxImage());
                        bomberman.entities.EntityArr.bricks.add(brick);
                    } else if (maps[j][i] == '1') {
                        balloom = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        bomberman.entities.EntityArr.enemies.add(balloom);
                    } else if (maps[j][i] == '2') {
                        oneal = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        bomberman.entities.EntityArr.enemies.add(oneal);
                    } else if (maps[j][i] == '3') {
                        doll = new Doll(i, j, Sprite.doll_left2.getFxImage());
                        bomberman.entities.EntityArr.enemies.add(doll);
                    } else if (maps[j][i] == '6') {
                        ovape = new Ovape(i, j, Sprite.ovape_right1.getFxImage());
                        bomberman.entities.EntityArr.enemies.add(ovape);
                    }
                }
            }
            fileReader.close();
            buffReader.close();
            GameViewManager.TIME = 200;
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}