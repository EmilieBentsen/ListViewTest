package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.listviewtest.global.Global;
import com.example.listviewtest.model.Note;

public class DetailActivity extends AppCompatActivity {
    private Note currentNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView myDetailTextView = findViewById(R.id.myDetailTextView);
        currentNote = (Note) Global.map.get(Global.NOTE_KEY);
        myDetailTextView.setText(currentNote.getTitle());
    }
}