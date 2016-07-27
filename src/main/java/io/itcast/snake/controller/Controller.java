package io.itcast.snake.controller;

import io.itcast.snake.entities.Food;
import io.itcast.snake.entities.Ground;
import io.itcast.snake.entities.Snake;
import io.itcast.snake.listener.SnakeListener;
//import io.itcast.snake.util.Global;
import io.itcast.snake.view.GamePanel;

//import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.Random;

/**
 * Created by cxh on 16-7-27.
 */
public class Controller extends KeyAdapter implements SnakeListener {

    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;

    //使用字段创建 构造方法   //快捷键alt+ins  选constructor
    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    public void keyPressed(KeyEvent e) {
        //e.getKeyCode();          //和键盘一一对应

        switch (e.getKeyCode()) {   //根据键盘输入情况（上下左右）
            case KeyEvent.VK_UP:    //向上
                snake.changeDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:  //向下
                snake.changeDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:  //向左
                snake.changeDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT: //向右
                snake.changeDirection(Snake.RIGHT);
                break;
        }
    }

    //实现接口（SnakeListener）方法(snakeMoved)
    public void snakeMoved(Snake snake) {

        if (food.isSnakeEatFood(snake)) {         //蛇是否吃掉食物
            snake.eatFood();                      //蛇吃掉食物
            food.newFood(ground.getPoint());      //产生新的食物
        }

        if (ground.isSnakeEatRock(snake)) {       //蛇吃到石头
            snake.die();                          //蛇死
        }

        if (snake.isEatBody()) {                  //蛇吃到自己的身体
            snake.die();                          //蛇死
        }

        gamePanel.display(snake,food,ground);
    }

/*
    //随机产生食物坐标
    public Point getPoint() {
        Random random = new Random();
        int x = random.nextInt(Global.WIDTH);
        int y = random.nextInt(Global.HEIGHT);
        return new Point(x,y);
    }
*/

    //开始新游戏的方法
    public void newGame() {
        snake.start();   //蛇开始动
        food.newFood(ground.getPoint());  //随机丢食物  （ getPoint() 随机坐标 ）
    }

    
}
