package com.example.christopher.simpletodo;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class DisplayToDoTaskActivity extends ActionBarActivity {

    private String ToDoTitle;
    private String ToDoText;
    private int ToDoId;

    public final static String DISPLAY_TODO_TITLE_MESSAGE = "com.example.christopher.SimpleToDo.DISPLAY_TODO_TITLE";
    public final static String DISPLAY_TODO_TEXT_MESSAGE = "com.example.christopher.SimpleToDo.DISPLAY_TODO_TEXT";
    public final static String DISPLAY_TODO_TEXT_ID = "com.example.christopher.SimpleToDo.DISPLAY_TODO_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_display_to_do_task);

        //ToDo error handling if gor some crazy reason we haven't gotten an INTENT_ID_MESSAGE
        ToDoTitle = intent.getStringExtra(MainActivity.INTENT_TITLE_MESSAGE);
        ToDoText = intent.getStringExtra(MainActivity.INTENT_TEXT_MESSAGE);
        ToDoId = intent.getIntExtra(MainActivity.INTENT_ID_MESSAGE,1);


        TextView titleTextView = (TextView)findViewById(R.id.todo_title);
        titleTextView.setText(ToDoTitle);

        TextView textTextView = (TextView)findViewById(R.id.todo_text);
        textTextView.setText(ToDoText);


    }

    /* NOT NEEDED?
    @Override
    protected void onStop() {
        super.onStop();

        EditText toDoText = (EditText) findViewById(R.id.todo_text);
        String itemText = toDoText.getText().toString();

        ContentValues values = new ContentValues();
        values.put(this.DISPLAY_TODO_TEXT,itemText);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_to_do_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getToDoTitle(){return this.ToDoTitle;}

    public String getToDoText() {return this.ToDoText;}

    public void setText(){
        EditText et = (EditText) findViewById(R.id.todo_text);
        String text = et.getText().toString();



    }

}
