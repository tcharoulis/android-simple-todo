package com.codepath.simpletodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.item_todo, null);
        }

        TaskItem taskItem = getItem(position);
        bindView(convertView, this.getContext(), taskItem);
        return convertView;
    }

    private void bindView(View view, Context context, TaskItem taskItem) {
        // Find fields to populate in inflated template
        TextView taskItemTxt = (TextView) view.findViewById(R.id.taskItemName);
        // Populate fields with extracted properties
        taskItemTxt.setText( taskItem.getText());
    }
}
