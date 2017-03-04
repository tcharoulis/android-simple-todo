package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditItemActivity extends AppCompatActivity {

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    private String TASK_ITEM_KEY = MainActivity.TASK_ITEM_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        TaskItem ti = (TaskItem) getIntent().getSerializableExtra(TASK_ITEM_KEY);
        EditText tv = (EditText) findViewById(R.id.taskDescriptionTxt);
        tv.setText(ti.getText());
        tv.requestFocus();
        tv.setSelection(ti.getText().length(), ti.getText().length());

        if (ti.getDueDate() != null) {
            EditText dateField = (EditText) findViewById(R.id.dueDateTxt);
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            dateField.setText(sdf.format(ti.getDueDate()));
        }
    }

    public void onSaveItem(View v) {
        EditText tv = (EditText) findViewById(R.id.taskDescriptionTxt);
        TaskItem taskItem = (TaskItem) getIntent().getSerializableExtra(TASK_ITEM_KEY);

        TaskItem newTaskItem = new TaskItem();
        newTaskItem.setText(tv.getText().toString());
        newTaskItem.id = taskItem.id;

        EditText dueDateTxt = (EditText) findViewById(R.id.dueDateTxt);
        if (dueDateTxt.getText() != null && dueDateTxt.getText().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            try {
                newTaskItem.setDueDate(sdf.parse(dueDateTxt.getText().toString()));
            } catch (ParseException e) {}
        }

        Intent intent = new Intent();
        intent.putExtra(TASK_ITEM_KEY, newTaskItem);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
