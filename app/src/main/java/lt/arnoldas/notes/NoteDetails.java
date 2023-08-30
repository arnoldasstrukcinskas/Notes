package lt.arnoldas.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import lt.arnoldas.notes.databinding.ActivityNoteDetailsBinding;

public class NoteDetails extends AppCompatActivity {

    private ActivityNoteDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("Title");
        String description = intent.getStringExtra("Description");
        String creationDate = intent.getStringExtra("Creation Date");

        binding.textView.setText(
                id + "\nTitle: " + title + "\nDescription: " + description

        );
    }
}