package game;

import java.util.Random;

public class Map {
    private static int countEnemies;
    private static int countWalls;
    private static int sizeMap;
    private static int playerX;
    private static int playerY;
    private static int exitX;
    private static int exitY;
    private static char[][] map;
    private static Random random = new Random();

    public static void createMap(int enemies, int walls, int size) {
        if (size <= 0 || enemies < 0 || walls < 0 || (size * size) < (walls + enemies + 2)) {
            throw new IllegalParametersException("Invalid input");
        }
        countEnemies = enemies;
        sizeMap = size;
        countWalls = walls;
        // generateMap();
        // TO DO: generateMap
    }

    private static int check() {
        int check = 0;

        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                if (map[i][j] == 'V')
                    check++;
            }
        }
        return check;
    }

    private static void initPlayer() {
        int y = random.nextInt(sizeMap);
        int x = random.nextInt(sizeMap);

        if (map[y][x] == '.') {
            map[y][x] = 'P';
            playerY = y;
            playerX = x;
            return;
        }
        initPlayer();
    }

    private static void initEnemies() {
        int i = 0;

        while (i < countEnemies) {
            int y = random.nextInt(sizeMap);
            int x = random.nextInt(sizeMap);

            if (map[y][x] == '.' && checkPlayer(y, x, 'P')) {
                map[y][x] = 'X';
                i++;
            }
        }
    }

    private static boolean checkPlayer(int y, int x, char tmp) {
        if (y - 1 >= 0 && map[y - 1][x] == tmp)
            return false;
        else if (x - 1 >= 0 && map[y][x - 1] == tmp)
            return false;
        else if (y + 1 < sizeMap && map[y + 1][x] == tmp)
            return false;
        else if (x + 1 < sizeMap && map[y][x + 1] == tmp)
            return false;
        return true;
    }
    public static int getCountEnemies() {
        return countEnemies;
    }

    public static int getCountWalls() {
        return countWalls;
    }

    public static int getSizeMap() {
        return sizeMap;
    }

    public static int getPlayerX() {
        return playerX;
    }

    public static int getPlayerY() {
        return playerY;
    }
}
