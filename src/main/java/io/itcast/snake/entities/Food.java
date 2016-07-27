package io.itcast.snake.entities;

import io.itcast.snake.util.Global;

import java.awt.*;

/**
 * Created by cxh on 16-7-27.
 */
public class Food extends Point {

    public void newFood(Point p) {
        this.setLocation(p);
    }

    public boolean isSnakeEatFood(Snake snake) {   //蛇是否吃到了食物
        System.out.println("Food's isSnakeEatFood");

        return this.equals(snake.getHead());  //蛇头的坐标是否与食物的坐标相等

//        return false;
    }

    public void draMe(Graphics g) {
        System.out.println("Food's drawMe");

        //显示食物
        g.fill3DRect(x * Global.SELL_SIZE, y * Global.SELL_SIZE, Global.SELL_SIZE, Global.SELL_SIZE, true);
    }
}
