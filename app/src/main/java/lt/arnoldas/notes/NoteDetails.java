package lt.arnoldas.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.time.format.DateTimeFormatter;

import lt.arnoldas.notes.databinding.ActivityNoteDetailsBinding;

public class NoteDetails extends AppCompatActivity {

    private ActivityNoteDetailsBinding binding;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Note note = intent.getParcelableExtra("note");
            displayNoteDetails(note);
        }
//        int id = intent.getIntExtra("id", 0);
//        String title = intent.getStringExtra("Title");
//        String description = intent.getStringExtra("Description");
//        String creationDate = intent.getStringExtra("Creation Date");
//
//        binding.textView.setText(
//                id + "\nTitle: " + title + "\nDescription: " + description
//
//        );
    }

    private void displayNoteDetails(Note note) {
        binding.noteIdTextView.setText(String.valueOf(note.getId()));
        binding.noteNameEditText.setText(note.getTitle());
        binding.noteContentEditText.setText(note.getDescription());
        binding.noteCreationDateTextView.setText(
                note.getCreationDate() != null ? note.getCreationDate().format(formatter) : "no data"
                );
        binding.noteUpdateDateTextView.setText(
                note.getUpdateDate() != null ? note.getUpdateDate().format(formatter) : "no data"
        );
    }
}