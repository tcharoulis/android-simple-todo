package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private String TASK_ITEM_KEY = MainActivity.TASK_ITEM_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        TaskItem ti = (TaskItem) getIntent().getSerializableExtra(TASK_ITEM_KEY);
        EditText tv = (EditText) findViewById(R.id.editText);
        tv.setText(ti.getText());
        tv.requestFocus();
        tv.setSelection(ti.getText().length(), ti.getText().length());
    }

    public void onSaveItem(View v) {
        EditText tv = (EditText) findViewById(R.id.editText);
        TaskItem taskItem = (TaskItem) getIntent().getSerializableExtra(TASK_ITEM_KEY);

        Intent intent = new Intent();
        TaskItem newTaskItem = new TaskItem();
        newTaskItem.setText(tv.getText().toString());
        newTaskItem.id = taskItem.id;
        intent.putExtra(TASK_ITEM_KEY, newTaskItem);
        setResult(RESULT_OK, intent);
        finish();
    }
}
