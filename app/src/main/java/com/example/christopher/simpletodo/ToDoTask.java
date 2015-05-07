package com.example.christopher.simpletodo;

import org.w3c.dom.Text;

/**
 * Created by Christopher on 2015-04-30.
 */
public class ToDoTask {
    private String taskTitle;
    private String taskText;


    public ToDoTask(){
        //nothing
    }

    public ToDoTask(String taskTitle, String taskText){
        this.taskText=taskText;
        this.taskTitle= taskTitle;
    }




    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }


    public String toString() {
        return this.taskTitle;

    }
}
