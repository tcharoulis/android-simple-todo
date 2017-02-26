package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private String TASK_ITEM_KEY = "task.item";

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
        TaskItem ti = (TaskItem) getIntent().getSerializableExtra(TASK_ITEM_KEY);

        Intent i = new Intent();
        TaskItem nti = new TaskItem();
        nti.setText(tv.getText().toString());
        nti.setPosition(ti.getPosition());
        i.putExtra(TASK_ITEM_KEY, nti);
        setResult(RESULT_OK, i);
        finish();
    }
}
