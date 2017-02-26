package com.codepath.simpletodo;

import java.io.Serializable;

/**
 * Created by paperspace on 2/26/2017.
 */

public class TaskItem implements Serializable {

    private int position;
    private String text;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
