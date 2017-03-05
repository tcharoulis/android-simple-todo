package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by paperspace on 2/26/2017.
 */
@Table(database = MyDatabase.class)
public class TaskItem extends BaseModel implements Serializable {

    public static final String DATE_FORMAT = "yyyy/MM/dd";

    @Column
    @PrimaryKey(rowID = true)
    int id;

    @Column
    private String text;

    @Column(typeConverter = DateConverter.class)
    private Date dueDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
