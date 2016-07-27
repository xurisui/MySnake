package io.itcast.snake.entities;

import io.itcast.snake.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * Created by cxh on 16-7-27.
 */
public class Ground {

    private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];


    //构造方法
    public Ground() {
        for (int x = 0; x < Global.WIDTH; x++) {
            rocks[x][0] = 1;
            rocks[x][Global.HEIGHT - 1] = 1;
        }
    }

    public boolean isSnakeEatRock(Snake snake) {          //是否吃到石头
        System.out.println("Ground's isSnakeREatRock");

        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] ==1 && x == snake.getHead().x && y == snake.getHead().y) {
                    return true;
                }
            }
        }

        return  false;
    }

    //随机产生食物坐标
    public Point getPoint() {
        Random random = new Random();

        int x = 0, y = 0;
        do {
            x = random.nextInt(Global.WIDTH);
            y = random.nextInt(Global.HEIGHT);
        } while (rocks[x][y] == 1);

        return new Point(x,y);
    }

    public void drawMe(Graphics g) {
        System.out.println("Ground's drawMe");

        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1) {
                    g.fill3DRect(x * Global.SELL_SIZE, y * Global.SELL_SIZE, Global.SELL_SIZE, Global.SELL_SIZE, true);

                    g.setColor(Color.gray);
                }
            }
        }
    }
}
