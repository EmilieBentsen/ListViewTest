package com.example.listviewtest.repo;

import com.example.listviewtest.Updatable;
import com.example.listviewtest.model.Note;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repo {

    private static Repo repo = new Repo(); // kan kun køre éen gang
    private Updatable activity;
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // test
    private static final String NOTES = "notes";
    private static final String TITLE = "title";
    private List<Note> noteList = new ArrayList<>(); //gemmer Note objekter. Kan opdateres.
    public static Repo r(){
        return repo;
    }

    public void setActivity(Updatable a){ // kaldes fra aktivitet, som skal blive opdateret
        activity = a;
        startListener();
    }

    public void addNote(Note note){
        DocumentReference ref = db.collection(NOTES).document(note.getId()); // opret nyt dokument i
        // Firebase, hvor vi selv angiver document id.
        Map<String,String> map = new HashMap<>();
        map.put(TITLE, note.getTitle()); // tilføj selv flere key-value par efter behov
        ref.set(map).addOnCompleteListener(task -> {
            if(!task.isSuccessful()) {
                System.out.println("error i gem: " + task.getException() );
            }
        }); // gemmer hele map i aktuelt document
    }
    public void startListener(){ // SnapshotListener lytter hele tiden.
        db.collection(NOTES).addSnapshotListener((values, error) -> {
            noteList.clear();
            for(DocumentSnapshot snap : values.getDocuments()){
                Note note = new Note(snap.get(TITLE).toString(), snap.getId());
                noteList.add(note);
                System.out.print("test 1");
            }
            activity.update(); // kaldes efter vi har hentet data fra Firebase
        });
    }

    public List<Note> notes() {
        return noteList;
    }

    public void delete(Note note) {
        DocumentReference ref = db.collection(NOTES).document(note.getId()); // opret nyt dokument i
        // Firebase, hvor vi selv angiver document id.
        ref.delete();

    }
    // plus de andre CRUD metoder
}
