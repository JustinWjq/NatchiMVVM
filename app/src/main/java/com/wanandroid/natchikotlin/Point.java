package com.wanandroid.natchikotlin;

/**
 * Created by JustinWjq
 *
 * @date 2020-03-06.
 * description：
 */
public class Point {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
