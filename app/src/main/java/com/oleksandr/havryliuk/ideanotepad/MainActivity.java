package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IdeaViewModel mIdeaViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.init(this);

        // autocomplete
        AutoCompleteTextView ideaAutoComplete = findViewById(R.id.search_view);
        String[] cities = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        ideaAutoComplete.setAdapter(stringArrayAdapter);


        mIdeaViewModel = ViewModelProviders.of(this).get(IdeaViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final IdeasAdapter adapter = new IdeasAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mIdeaViewModel.getAllIdeas().observe(this, ideas -> adapter.setIdeas(ideas));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewIdeaActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Idea idea = new Idea(data.getStringExtra(NewIdeaActivity.EXTRA_IDEA),
                                 data.getStringExtra(NewIdeaActivity.EXTRA_CATEGORY));
            mIdeaViewModel.insert(idea);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
