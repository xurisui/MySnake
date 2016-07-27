package io.itcast.snake.view;

import io.itcast.snake.entities.Food;
import io.itcast.snake.entities.Ground;
import io.itcast.snake.entities.Snake;
import io.itcast.snake.util.Global;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cxh on 16-7-27.
 */
public class GamePanel extends JPanel{

    private Snake snake;
    private Food food;
    private  Ground ground;

    public void display(Snake snake, Food food, Ground ground) {
        System.out.println("GamePanel's display");
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {

        //重新显示(要擦除以前的图像)
        g.setColor(new Color(0xcfcfcf));
        //g.setColor(new Color(0xcfcf));
        g.fillRect(0,0, Global.WIDTH * Global.SELL_SIZE,Global.HEIGHT * Global.SELL_SIZE);

        if (ground != null && snake != null && food != null) {
            this.ground.drawMe(g);
            this.snake.drawMe(g);
            this.food.draMe(g);
        }
    }
}
