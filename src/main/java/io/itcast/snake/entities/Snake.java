package io.itcast.snake.entities;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.itcast.snake.listener.SnakeListener;
import io.itcast.snake.util.Global;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by cxh on 16-7-27.
 */


public class Snake {

    //定义方向
    public static final int UP = -1;
    public static final int DOWN = 1;
    public static final int LEFT = -2;
    public static final int RIGHT = 2;

    private  int oldDirection,newDirection;

    private Point oldTail;

    //用一个集合来表示蛇的所有坐标
    private LinkedList<Point> body = new LinkedList<Point>();

    //监听器
    private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

    private boolean life;

    //构造方法（初始化蛇的身体）
    public Snake() {
        init();
    }

    public  void  init() {   //初始化方法
        int x = Global.WIDTH/2;
        int y = Global.HEIGHT/2;
        for (int i = 0; i < 3; i++) {
            body.addLast(new Point(x--,y));
        }
        oldDirection = newDirection = RIGHT;

        life = true;
    }

    public void die() {
        life = false;
    }

    public void move() {    //蛇移动的方法
        System.out.println("Snake's move");

        if (!(oldDirection + newDirection == 0)) {
            oldDirection = newDirection;
        }

        //移动的时候相当于去掉一个最后的方块和添加一个最前面的方块
        //1.去尾
        oldTail = body.removeLast();  //保存蛇的尾巴（当吃掉食物的时候直接添加上）

        //2.加头
        //计算蛇头的坐标
        int x = body.getFirst().x;
        int y = body.getFirst().y;

        System.out.println(oldDirection);

        switch (oldDirection) {    //根据方向改变蛇的坐标
            case UP:
                y--;
                if (y < 0) {
                    y = Global.HEIGHT - 1;
                }
                break;
            case DOWN:
                y++;
                if (y >= Global.HEIGHT) {
                    y = 0;
                }
                break;
            case LEFT:
                x--;
                if (x < 0) {
                    x = Global.WIDTH - 1;
                }
                break;
            case RIGHT:
                x++;
                if (x >= Global.WIDTH) {
                    x = 0;
                }
                break;
        }
        System.out.println(x + "," +
                y);
        Point newHead = new Point(x, y);

        body.addFirst(newHead);
    }

    public void changeDirection(int direction) {   //蛇改变方向的方法
        System.out.println("Snake's changeDirection");
/*
        //判断 如果方向与原方向相反，则忽略此操作
        if (!(direction + newDirection == 0)) {
            this.direction = direction;
        }
*/
        newDirection =direction;
    }


    public void eatFood() {       //蛇吃东西的方法
        System.out.println("Snake's eatBody");

        body.addLast(oldTail);    //蛇吃到食物，直接把移掉的尾巴给添上
    }


    public  boolean isEatBody() {      //判断蛇是否吃到自己的身体
        System.out.println("Snake's isEatBody");

        for (int i = 1; i < body.size(); i ++) {        //遍历
            if (body.get(i).equals(this.getHead())) {
                return true;
            }
        }
        return false;
    }

    //显示蛇身体的方法
    public void drawMe(Graphics g) {
        System.out.println("Snake's drawMe");

        //蛇的颜色
        g.setColor(Color.BLUE);

        for (Point p : body) {
            g.fill3DRect(p.x * Global.SELL_SIZE,p.y * Global.SELL_SIZE,Global.SELL_SIZE,Global.SELL_SIZE,true);  //填充矩形
        }
    }

    //得到蛇头的坐标
    public Point getHead() {
        return body.getFirst();
    }

    private class SnakeDriver implements Runnable {

        public void run() {         //重写run方法override   快捷键？
            while (life) {          //捕获异常

                move();

                //触发事件，循环所有的监听器
                for (SnakeListener l : listeners) {
                    l.snakeMoved(Snake.this);
                }

                try {             //捕获异常  try {...} catch{...}
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //启动线程方法
    public void start() {
        new Thread(new SnakeDriver()).start();
    }

    //监听器方法
    public void addSnakeListener(SnakeListener l) {
        if (l != null) {
            this.listeners.add(l);
        }
    }
}
