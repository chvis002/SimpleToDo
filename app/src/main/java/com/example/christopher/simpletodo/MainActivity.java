package com.example.christopher.simpletodo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<ToDoTask> items;
    private ArrayAdapter<ToDoTask> itemsAdapter;
    private ListView lvItems;

    private ToDoDbAdapter dbAdapter;

    public final static String INTENT_TITLE_MESSAGE = "com.example.christopher.SimpleToDo.TITLE";
    public final static String INTENT_TEXT_MESSAGE = "com.example.christopher.SimpleToDo.TEXT";
    public final static String INTENT_ID_MESSAGE = "com.example.christopher.SimpleToDo.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new ToDoDbAdapter(this);
        dbAdapter.open();

        //New stuff
        lvItems = (ListView) findViewById(R.id.lvItems); //hard parse
        items = new ArrayList<ToDoTask>();
        items = this.updateItems();
        itemsAdapter = new ArrayAdapter<ToDoTask>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);



        setupListViewListener();
    }

    /*
    @Override
    protected void onStop() {
        super.onStop();

        EditText toDoText = (EditText) findViewById(R.id.todo_text);
        String itemText = toDoText.getText().toString();

        ContentValues values = new ContentValues();
        values.put(this.DISPLAY_TODO_TEXT,itemText);

    }*/

    protected void onResume(Bundle savedInstanceState){
        //TODO was here
        //need to catch toDoText from DisplayToDoTaskAcitivity
        super.onResume();

    }

    protected ArrayList<ToDoTask> updateItems() {

        Cursor results = dbAdapter.fetchAllNotes();

        if (results.moveToFirst()) {
            do {
                ToDoTask toDoTask = new ToDoTask();
                toDoTask.setTaskTitle(results.getString(1));
                toDoTask.setTaskText(results.getString(2));
                // Adding contact to list
                items.add(toDoTask);
            } while (results.moveToNext());
        }

        return items;
    }

    private void setupListViewListener() {

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {

                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {

                /*
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                */
                showTaskDetails(item, pos);
            }
        });

    }

    public void showTaskDetails(View view, int pos) {
        // sendMessage

        Intent intent = new Intent(this, DisplayToDoTaskActivity.class);
        ToDoTask pushTask = items.get(pos);
        intent.putExtra(INTENT_TITLE_MESSAGE, pushTask.getTaskTitle());
        intent.putExtra(INTENT_TEXT_MESSAGE,pushTask.getTaskText());
        intent.putExtra(INTENT_ID_MESSAGE,pos);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        //itemsAdapter.add(new ToDoTask(itemText, "Enter text here..."));
        items.add(new ToDoTask(itemText, "Enter text here..."));
        dbAdapter.createToDo(itemText,"");
        etNewItem.setText("");

        ListView lvItems = (ListView) findViewById(R.id.lvItems);


        showTaskDetails(lvItems, (items.size()-1));

    }


}
