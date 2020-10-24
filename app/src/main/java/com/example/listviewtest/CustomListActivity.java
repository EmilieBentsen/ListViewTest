package com.example.listviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listviewtest.global.Global;
import com.example.listviewtest.model.Note;
import com.example.listviewtest.repo.Repo;

public class CustomListActivity extends AppCompatActivity implements Updatable {
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("test01");
        setContentView(R.layout.activity_custom_list);
        myAdapter = new MyAdapter(this, Repo.r().notes());
        ListView listView = findViewById(R.id.myListView2);
        listView.setAdapter(myAdapter);
        System.out.println("test02");

        listView.setOnItemClickListener((_listView, linearLayout, adapterPos, arrPos) -> {
            System.out.println("Klik på række " + arrPos);
            Intent intent = new Intent(this, DetailActivity.class);
            Global.map.put(Global.NOTE_KEY, Repo.r().notes().get((int)arrPos));
            startActivity(intent);
        });
        Repo.r().setActivity(this);

    }

    @Override
    public void update() {
        System.out.println("update() er kaldet");
        runOnUiThread(()->{
            myAdapter.notifyDataSetChanged();
        });
    }

    public void addNote(View view){
            Note note = new Note("Skriv");
            Repo.r().addNote(note); // opretter ny Note + gemmer i Firebase
    }
}