package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int EDIT_TASK_REQUEST_CODE = 20;
    private String TASK_ITEM_KEY = "task.item";

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("Item 1");
        items.add("Item 2");
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                TaskItem item = new TaskItem();
                item.setPosition(position);
                item.setText(items.get(position));
                i.putExtra(TASK_ITEM_KEY, item);
                startActivityForResult(i, EDIT_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == EDIT_TASK_REQUEST_CODE) {
            TaskItem editedTaskItem = (TaskItem) data.getSerializableExtra(TASK_ITEM_KEY);
            items.remove(editedTaskItem.getPosition());
            itemsAdapter.insert(editedTaskItem.getText(), editedTaskItem.getPosition());
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    public void onAddItem(View v) {
        EditText editText = (EditText) findViewById(R.id.etNewItem);
        String itemText = editText.getText().toString();
        itemsAdapter.add(itemText);
        editText.setText("");
        writeItems();
    }

    private void readItems() {
        File fileDirs = getFilesDir();
        File todoFile = new File(fileDirs, "todos.txt");
        try {
            items = new ArrayList<>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        File fileDirs = getFilesDir();
        File todoFile = new File(fileDirs, "todos.txt");

        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
