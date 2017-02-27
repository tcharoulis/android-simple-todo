package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by paperspace on 2/26/2017.
 */
@Table(database = MyDatabase.class)
public class TaskItem extends BaseModel implements Serializable {

    @Column
    @PrimaryKey(rowID = true)
    int id;

    @Column
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
