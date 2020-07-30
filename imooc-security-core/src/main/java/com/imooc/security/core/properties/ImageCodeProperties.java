package com.imooc.security.core.properties;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 23:33
 * @description：
 */
public class ImageCodeProperties extends SmsCodeProperties{

    private int width = 67;

    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}