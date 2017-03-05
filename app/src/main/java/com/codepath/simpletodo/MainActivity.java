package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements EditTaskItemDialogFragment.SaveTaskItemListener {

    private int EDIT_TASK_REQUEST_CODE = 20;
    static String TASK_ITEM_KEY = "task.item";

    List<TaskItem> items;
    private TaskItemAdapter itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TaskItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskItem taskItem = items.get(position);
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                taskItem.delete();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskItem item = items.get(position);
                showEditDialog(item);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == EDIT_TASK_REQUEST_CODE) {
            TaskItem editedTaskItem = (TaskItem) data.getSerializableExtra(TASK_ITEM_KEY);
            TaskItem itemInList = getItem(editedTaskItem.id);
            itemInList.setText(editedTaskItem.getText());
            itemInList.setDueDate(editedTaskItem.getDueDate());
            itemsAdapter.notifyDataSetChanged();
            itemInList.update();
        }
    }

    private TaskItem getItem(int id) {
        for (Iterator<TaskItem> it = items.iterator();it.hasNext();) {
            TaskItem item = it.next();
            if (item.id == id) {
                return item;
            }
        }

        return null;
    }

    public void onAddItem(View v) {
        TaskItem item = new TaskItem();
        showEditDialog(item);
    }

    private void readItems() {
        items = SQLite.select().from(TaskItem.class).queryList();
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    private void showEditDialog(TaskItem taskItem) {
        FragmentManager fm = getSupportFragmentManager();
        String dialogTitle = taskItem.id > 0 ? "Edit" : "New";
        EditTaskItemDialogFragment editNameDialogFragment = EditTaskItemDialogFragment.newInstance(taskItem);
        editNameDialogFragment.show(fm, "activity_edit_item");
    }

    @Override
    public void onSave(TaskItem taskItem) {
        if (taskItem.id > 0) {
            taskItem.update();
            itemsAdapter.notifyDataSetChanged();
        } else {
            taskItem.save();
            itemsAdapter.add(taskItem);
        }
    }
}
