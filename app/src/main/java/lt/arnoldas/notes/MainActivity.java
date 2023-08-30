package lt.arnoldas.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
        
        setUpListViewClickListener();

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

    private void setUpListViewClickListener() {
        binding.notesListView.setOnItemClickListener(
                (adapterView, view, position, l) -> {

                    Log.i(TAG, "OnListItemClicked: " + adapterView.getItemAtPosition(position));
                    Log.i(TAG, "OnListItemClicked: " + position);
                }
        );
    }

}