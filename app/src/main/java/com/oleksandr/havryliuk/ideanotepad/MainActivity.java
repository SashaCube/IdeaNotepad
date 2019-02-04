package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IdeaViewModel mIdeaViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIdeaViewModel = ViewModelProviders.of(this).get(IdeaViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final IdeasAdapter adapter = new IdeasAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mIdeaViewModel.getAllIdeas().observe(this, new Observer<List<Idea>>() {
            @Override
            public void onChanged(@Nullable List<Idea> ideas) {
                adapter.setIdeas(ideas);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewIdeaActivity.class);
//                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Idea idea = new Idea(data.getStringExtra(NewIdeaActivity.EXTRA_REPLY));
//            mIdeaViewModel.insert(idea);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
//    }
}
