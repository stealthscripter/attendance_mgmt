package com.example.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListener(position -> gotoItemActiviy(position));

        setToolBar();


    }

    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);


        title.setText("Attendace App");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);;
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActiviy(int position) { //this Method makes the class cliclable and redirect it to the StudentActivity.
        Intent intent = new Intent(this, StudentActivity.class);
        intent.putExtra("className", classItems.get(position).getClassName());
        intent.putExtra("subjectName", classItems.get(position).getSubjectName());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), MyDialog.CLASS_ADD_DIALOG);
        dialog.setListener((className, subjectName)-> addClass(className, subjectName));
    }

    private void addClass(String className, String subjectName) {
        classItems.add(new ClassItem(className, subjectName));
        classAdapter.notifyDataSetChanged();
    }
}