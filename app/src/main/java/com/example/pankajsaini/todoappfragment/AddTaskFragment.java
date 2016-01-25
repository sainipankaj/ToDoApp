package com.example.pankajsaini.todoappfragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    Button doneButton;
    Button cancelButton;
    EditText taskTitleEditText;
    EditText taskDescriptionEditText;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static Fragment newFragment() {
        AddTaskFragment atf = new AddTaskFragment();
        return atf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_add_task, container, false);
        doneButton = (Button) mainView.findViewById(R.id.done);
        taskTitleEditText = (EditText) mainView.findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = (EditText) mainView.findViewById(R.id.taskDescriptionEditText);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                String title = taskTitleEditText.getText().toString();
                String description = taskDescriptionEditText.getText().toString();
                Intent intent = new Intent();

                Task task = new Task();
                task.title = title;
                task.description = description;
                intent.putExtra("NEW_TASK", task);
                //getTargetFragment().onActivityResult(101, Activity.RESULT_OK, intent);

                TaskListFragment tlf = (TaskListFragment) getTargetFragment();
                tlf.addTask(task);

                getFragmentManager().popBackStack();
            }
        });

        cancelButton = (Button) mainView.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        taskTitleEditText = (EditText) mainView.findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = (EditText) mainView.findViewById(R.id.taskDescriptionEditText);
        return mainView;
    }


}
