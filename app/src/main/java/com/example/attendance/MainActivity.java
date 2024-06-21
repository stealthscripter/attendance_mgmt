package com.example.attendance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;

    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<ClassItem> classItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        EditText class_edt = view.findViewById(R.id.class_edt);  // Correct view reference
        EditText subject_edt = view.findViewById(R.id.subject_edt);  // Correct view reference

        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dialog.dismiss());
        add.setOnClickListener(v -> {
            addClass(class_edt, subject_edt);
            dialog.dismiss();
        });
    }

    private void addClass(EditText class_edt, EditText subject_edt) {
        String className = class_edt.getText().toString().trim();
        String subjectName = subject_edt.getText().toString().trim();

        if (className.isEmpty() || subjectName.isEmpty()) {
            // Optionally, show a Toast message to inform the user
            // Toast.makeText(this, "Please enter both class and subject names", Toast.LENGTH_SHORT).show();
            return;
        }

        classItems.add(new ClassItem(className, subjectName));
        classAdapter.notifyDataSetChanged();
    }
}
