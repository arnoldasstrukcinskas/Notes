package lt.arnoldas.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import lt.arnoldas.notes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Note> list = new ArrayList<>();

        generateDummyNotes(25);

        list.addAll(generateDummyNotes(25));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        binding.notesListView.setAdapter(adapter);

        //PAVYZDYS
//        binding.myTextView.setTextSize(55);
//        binding.myTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

//        TextView myTextView = findViewById(R.id.myTextView);          // textview geriau nenadoti, geresnis binding, nes su juo aplikacija veikia greiciau ir stabiliau
//        myTextView.setText("Kokia grazi diena ir koks as nuostabus");
//        myTextView.setTextSize(24);
//        myTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private List<Note> generateDummyNotes(int notesNumber) {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 1; i <= notesNumber; i++) {
            notes.add(new Note(
                            i,
                            UUID.randomUUID().toString().substring(0, 10),
                            UUID.randomUUID().toString()
                    )
            );
        }
        return notes;
    }
}