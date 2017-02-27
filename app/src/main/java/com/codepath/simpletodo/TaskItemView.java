package com.codepath.simpletodo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by paperspace on 2/27/2017.
 */

public class TaskItemView extends RelativeLayout {

    private TextView taskItemTxt;

    public TaskItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_todo, this, true);
        setupChildren();
    }

    public TaskItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_todo, this, true);
        setupChildren();
    }

    private void setupChildren() {
        taskItemTxt = (TextView) findViewById(R.id.taskItemName);
    }

    public static TaskItemView inflate(ViewGroup parent) {
        return (TaskItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view, parent, false);
    }

    public void setTaskItem(TaskItem taskItem) {
        taskItemTxt.setText( taskItem.getText());
    }
}
