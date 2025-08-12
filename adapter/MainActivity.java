package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Sample data
        List<String> sampleData = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");

        // Set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with data and set it to RecyclerView
        adapter = new MyAdapter(sampleData);
        recyclerView.setAdapter(adapter);
    }
}
