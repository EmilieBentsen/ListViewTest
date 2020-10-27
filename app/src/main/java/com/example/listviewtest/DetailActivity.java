package com.example.listviewtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.listviewtest.global.Global;
import com.example.listviewtest.model.Note;
import com.example.listviewtest.repo.Repo;

public class DetailActivity extends AppCompatActivity {
    private Note currentNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EditText myDetailTextView = findViewById(R.id.myDetailTextView);
        currentNote = (Note) Global.map.get(Global.NOTE_KEY);
        myDetailTextView.setText(currentNote.getTitle());

    }


    public void update(View view) {
        EditText myDetailTextView = findViewById(R.id.myDetailTextView);
        currentNote.setTitle(myDetailTextView.getText().toString());
        Repo.r().addNote(currentNote);
        finish();
    }

    public void delete(View view){
        AlertDialog diaBox = AskOption();
        diaBox.show();

    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Repo.r().delete(currentNote);
                        finish();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

}