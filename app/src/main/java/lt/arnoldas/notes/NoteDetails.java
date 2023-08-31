package lt.arnoldas.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.time.format.DateTimeFormatter;

import lt.arnoldas.notes.databinding.ActivityNoteDetailsBinding;

public class NoteDetails extends AppCompatActivity {

    private ActivityNoteDetailsBinding binding;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            note = intent.getParcelableExtra("note");
            displayNoteDetails(note);
        }
        setUpSaveButtonClick();
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
    private void setUpSaveButtonClick() {
        binding.saveButton.setOnClickListener(
                v ->{
                    Intent finishIntent = new Intent();
                    finishIntent.putExtra("note_object_return", note);
                    setResult(RESULT_OK, finishIntent);
                    finish(); // uzdaro sekanti langa ir atidato pries tai buvusi
                }
        );
    }
}