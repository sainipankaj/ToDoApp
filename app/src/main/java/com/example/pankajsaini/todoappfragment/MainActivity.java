package com.example.pankajsaini.todoappfragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskListDelegate{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();

        Fragment frag = manager.findFragmentByTag("TLF");
        if(frag == null) {
            loadTaskListFragment();
        }

    }

    public void switchToAddTaskFragment() {
        Fragment atf = AddTaskFragment.newFragment();

        //get access to the fragment manager
        FragmentManager manager = getSupportFragmentManager();

        //find fragment with tag "TLF"
        Fragment frag = manager.findFragmentByTag("TLF");


        //attach the TLF as the target for the ATF, so that ATF can send intent to TLF
        atf.setTargetFragment(frag, 101);

        if(frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            transaction.add(R.id.mainLayout, atf, "ATF");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void switchToTaskDetails(Task task) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment td = new TaskDetails();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DETAIL", task);
        td.setArguments(bundle);

        //FragmentTransaction trans = manager.beginTransaction();

        Fragment frag = manager.findFragmentByTag("TLF");

        if(frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            transaction.add(R.id.mainLayout, td, "TD");
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    public void loadTaskListFragment() {
        //Fragment tlf = TaskListFragment.newFragment();

        TaskListFragment tlf = (TaskListFragment)TaskListFragment.newFragment();

        //attach the mainactivity as the delegate to the TaskListFragment
        tlf.delegate = this;
        //get access to the fragment manager
        FragmentManager manager = getSupportFragmentManager();

        //create a fragment transaction
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.mainLayout, tlf, "TLF");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
