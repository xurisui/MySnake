package io.itcast.snake.game;

import io.itcast.snake.controller.Controller;
import io.itcast.snake.entities.Food;
import io.itcast.snake.entities.Ground;
import io.itcast.snake.entities.Snake;
import io.itcast.snake.util.Global;
import io.itcast.snake.view.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cxh on 16-7-27.
 */
public class Game {

    public static void main(String[] args) {

        Snake snake = new Snake();
        Food food = new Food();
        Ground ground = new Ground();
        GamePanel gamePanel = new GamePanel();
        Controller controller = new Controller(snake,food,ground,gamePanel);

        javax.swing.JFrame frame = new JFrame();   //???? javax.swing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置大小
        gamePanel.setSize(Global.WIDTH * Global.SELL_SIZE, Global.HEIGHT * Global.SELL_SIZE);
        frame.setSize(Global.WIDTH * Global.SELL_SIZE + 10, Global.HEIGHT * Global.SELL_SIZE + 35);

        frame.add(gamePanel, BorderLayout.CENTER);

        //添加监听器
        gamePanel.addKeyListener(controller);
        snake.addSnakeListener(controller);

        //给frame添加事件监听器，捕获按键输入
        frame.addKeyListener(controller);

        frame.setVisible(true);
        controller.newGame();
    }
}
