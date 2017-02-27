package com.codepath.simpletodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by paperspace on 2/27/2017.
 */

public class TaskItemAdapter extends ArrayAdapter<TaskItem> {


    public TaskItemAdapter(Context context, List<TaskItem> objects) {

        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskItemView taskItemView = (TaskItemView) convertView;
        if (taskItemView == null) {
            taskItemView = TaskItemView.inflate(parent);
        }

        TaskItem taskItem = getItem(position);
        taskItemView.setTaskItem(taskItem);
        return taskItemView;
    }

}
