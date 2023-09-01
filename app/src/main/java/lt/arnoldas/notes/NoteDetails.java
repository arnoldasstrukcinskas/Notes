package lt.arnoldas.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.time.format.DateTimeFormatter;

import lt.arnoldas.notes.databinding.ActivityNoteDetailsBinding;
import lt.arnoldas.notes.repository.MainDatabase;
import lt.arnoldas.notes.repository.NoteDao;

public class NoteDetails extends BaseActivity {

    private ActivityNoteDetailsBinding binding;
    private Note note;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String demoResult;
    private final String SAVE_INSTANCE_KEY = "note_details_save_instance_key";
    private NoteDao noteDao;

    public NoteDetails() {
        super("NoteDetails", "tst_lfc_note_details_activity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteDao = MainDatabase.getInstance(getApplicationContext()).noteDao();

        Intent intent = getIntent();
        int noteId = 0;

        if (intent.getExtras() != null) {
            noteId = intent.getIntExtra("noteId", 0);
            note = noteDao.getById(noteId);
            if(note == null) note = new Note();
        } else {
            note = new Note();
        }

        displayNoteDetails();
        setUpSaveButton();

//        if(savedInstanceState != null){
//            demoResult = binding.noteNameEditText.getText().toString();
//        }

        print("onCreate demoResult: " + demoResult);

        binding.noteNameEditText.setOnFocusChangeListener(
                (view, b) -> {
                    demoResult = binding.noteNameEditText.getText().toString();
                    print("onFocus demoResult: " + demoResult);
                }
        );
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

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        demoResult = savedInstanceState.getString(SAVE_INSTANCE_KEY);
        print("onRestore demoResult " + demoResult);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SAVE_INSTANCE_KEY, demoResult);
    }

    private void displayNoteDetails() {
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

    private void getNoteFromRepository(int noteId) {
//        note = UseCaseRepository.notes.stream()
//                .filter(note -> note.getId() == noteId)
//                .findFirst()
//                .get();
    }

    private void setUpSaveButton() {
        binding.saveButton.setOnClickListener(
                v -> {
                    saveNote();
                    finish();
                }
        );
    }

    private void saveNote() {
        note.setTitle(
                binding.noteNameEditText.getText().toString()
        );
        note.setDescription(
                binding.noteContentEditText.getText().toString()
        );
        noteDao.insertNote(note);
//        int maxId = UseCaseRepository.notes.stream()
//                .max(Comparator.comparing(Note::getId))
//                .get()
//                .getId();
//
//        UseCaseRepository.notes.add(
//                new Note(
//                        maxId + 1,
//                        note.getTitle(),
//                        note.getDescription()
//                )
//        );
    }
}