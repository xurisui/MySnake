package io.itcast.snake.listener;

import io.itcast.snake.entities.Snake;

/**
 * Created by cxh on 16-7-27.
 */
public interface SnakeListener {

    void  snakeMoved(Snake snake);
}
