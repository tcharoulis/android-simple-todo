package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by paperspace on 3/4/2017.
 */

public class EditTaskItemDialogFragment extends DialogFragment implements DatePickerFragment.DateSetListener {

    private TaskItem taskItem;

    public EditTaskItemDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditTaskItemDialogFragment newInstance(TaskItem item) {
        EditTaskItemDialogFragment frag = new EditTaskItemDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", item.id > 0 ? "Edit" : "New");
        frag.setArguments(args);
        frag.taskItem = item;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        setupListeners(view);
        initializeFields(view);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void setupListeners(View view) {
        Button saveBtn = (Button) view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTaskItemDialogFragment.this.onSaveItem(v);
            }
        });

        Button showDateDlgBtn = (Button) view.findViewById(R.id.selectDateBtn);
        showDateDlgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment.show(getFragmentManager(), EditTaskItemDialogFragment.this);
            }
        });
    }

    private void initializeFields(View view) {
        EditText taskItemName = (EditText) view.findViewById(R.id.taskDescriptionTxt);
        taskItemName.requestFocus();
        if (taskItem.getText() != null) {
            taskItemName.setText(taskItem.getText());
            taskItemName.setSelection(taskItem.getText().length(), taskItem.getText().length());
        }

        if (taskItem.getDueDate() != null) {
            EditText dateField = (EditText) view.findViewById(R.id.dueDateTxt);
            SimpleDateFormat sdf = new SimpleDateFormat(TaskItem.DATE_FORMAT);
            dateField.setText(sdf.format(taskItem.getDueDate()));
        }
    }

    public void onSaveItem(View v) {
        EditText tv = (EditText) getView().findViewById(R.id.taskDescriptionTxt);

        taskItem.setText(tv.getText().toString());

        EditText dueDateTxt = (EditText) getView().findViewById(R.id.dueDateTxt);
        if (dueDateTxt.getText() != null && dueDateTxt.getText().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(TaskItem.DATE_FORMAT);
            try {
                taskItem.setDueDate(sdf.parse(dueDateTxt.getText().toString()));
            } catch (ParseException e) {}
        }

        SaveTaskItemListener mainActivity = (SaveTaskItemListener) getActivity();
        mainActivity.onSave(taskItem);

        dismiss();
    }

    @Override
    public void onDateSet(Date d) {
        EditText dueDateView = (EditText) getView().findViewById(R.id.dueDateTxt);
        SimpleDateFormat sdf = new SimpleDateFormat(TaskItem.DATE_FORMAT);
        dueDateView.setText(sdf.format(d));
    }

    public interface SaveTaskItemListener {
        void onSave(TaskItem taskItem);
    }
}
