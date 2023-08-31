package lt.arnoldas.notes;

import static androidx.activity.result.contract.ActivityResultContracts.GetContent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import lt.arnoldas.notes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "my_notes_main_activity";
    private ActivityMainBinding binding;
    private ArrayAdapter<Note> adapter;
    private ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListView();
        setUpListViewItemClick();
        setUpListViewItemLongClick();
        setUpFloatingActionButtonClick();

        //PAVYZDYS
//        binding.myTextView.setTextSize(55);
//        binding.myTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

//        TextView myTextView = findViewById(R.id.myTextView);          // textview geriau nenadoti, geresnis binding, nes su juo aplikacija veikia greiciau ir stabiliau
//        myTextView.setText("Kokia grazi diena ir koks as nuostabus");
//        myTextView.setTextSize(24);
//        myTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void setupListView() {

        notes = new ArrayList<>();
        notes.addAll(UseCaseRepository.generateDummyNotes(25));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        binding.notesListView.setAdapter(adapter);
    }

    private void setUpListViewItemClick() {
        binding.notesListView.setOnItemClickListener(
                (adapterView, view, position, l) -> {

//                    Log.i(TAG, "OnListItemClicked: " + adapterView.getItemAtPosition(position));
//                    Log.i(TAG, "OnListItemClicked: " + position);
                Note note = (Note) adapterView.getItemAtPosition(position);
                    openNoteDetailsActivity(note);
                }
        );
    }

    private void openNoteDetailsActivity(Note note) {
        Intent intent = new Intent(this, NoteDetails.class);
        intent.putExtra("note", note);
        startActivityForReturn.launch(intent);

//        intent.putExtra("id", note.getId());
//        intent.putExtra("Title", note.getTitle());
//        intent.putExtra("Description", note.getDescription());
//        intent.putExtra("Creation Date", note.getCreationDate());
//        intent.putExtra("Update Date", note.getUpdateDate());

// Siuncia zinute per pasisinkta programa
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, note.toString());
//        sendIntent.setType("text/plain");
//
//        Intent shareIntent = Intent.createChooser(sendIntent, null);
//        startActivity(shareIntent);

// Pajungia video youtubei
//        String videoId = "0xB3T4MPEr0";
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
//        intent.putExtra("VIDEO_ID", videoId);
//        startActivity(intent);
    }

    private void setUpListViewItemLongClick() {
        binding.notesListView.setOnItemLongClickListener(
                (adapterView, view, position, l) -> {
                    Note note = (Note) adapterView.getItemAtPosition(position);
                    showItemRemoveAlertDialog(note);
                    return true;
                }
        );
    }

    private void setUpFloatingActionButtonClick() {
        binding.floatingActionButton.setOnClickListener(
                view -> {
                    openNoteDetailsActivity(new Note());
                }
        );
    }

    private void showItemRemoveAlertDialog(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Do you really want to remove this item?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    removeNoteFromList(note);
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showSnackBar(String message) {
        Snackbar
                .make(
                        binding.notesListView,
                        message,
                        Snackbar.LENGTH_LONG
                )
                .show();
    }

    private void removeNoteFromList(Note note) {
        notes.remove(note);
        adapter.notifyDataSetChanged();
        showSnackBar("Note with id(): " + note.getId() + " was removed");
    }

    ActivityResultLauncher<Intent> startActivityForReturn = registerForActivityResult(
           new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Note note = (Note) data.getParcelableExtra("note_object_return");
                        Log.i(TAG, "Returned Note: " + note.toString());
                    }
                }
            }
    );
}