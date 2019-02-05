package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oleksandr.havryliuk.ideanotepad.UI.IdeaViewModel;
import com.oleksandr.havryliuk.ideanotepad.UI.IdeasAdapter;
import com.oleksandr.havryliuk.ideanotepad.repository.Idea;
import com.oleksandr.havryliuk.ideanotepad.utils.PreferenceManager;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IdeaViewModel mIdeaViewModel;
    public static final int NEW_IDEA_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_IDEA_ACTIVITY_REQUEST_CODE = 2;

    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String CATEGORY = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.init(this);

        mIdeaViewModel = ViewModelProviders.of(this).get(IdeaViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final IdeasAdapter adapter = new IdeasAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mIdeaViewModel.getAllIdeas().observe(this, ideas -> adapter.setIdeas(ideas));
        mIdeaViewModel.setOrder(IdeaViewModel.TIME);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ImageView idea = findViewById(R.id.idea_sort);
        idea.setOnClickListener(this);
        ImageView category = findViewById(R.id.category_sort);
        category.setOnClickListener(this);
        ImageView time = findViewById(R.id.time_sort);
        time.setOnClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_IDEA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Idea idea = new Idea(data.getStringExtra(NewIdeaActivity.EXTRA_IDEA),
                    data.getStringExtra(NewIdeaActivity.EXTRA_CATEGORY),
                    (new Date()).getTime());
            mIdeaViewModel.insert(idea);
        } else {
            if (requestCode == EDIT_IDEA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

                String action = data.getStringExtra(EditIdeaActivity.EXTRA_ACTION);
                if (action.equals(EditIdeaActivity.EDIT)) {
                    Idea idea = new Idea(data.getStringExtra(EditIdeaActivity.EXTRA_NEW_TEXT),
                            data.getStringExtra(EditIdeaActivity.EXTRA_NEW_CATEGORY),
                            data.getLongExtra(EditIdeaActivity.EXTRA_TIME, 0));
                    idea.setId(data.getIntExtra(EditIdeaActivity.EXTRA_ID, 0));
                    mIdeaViewModel.update(idea);
                }
                if (action.equals(EditIdeaActivity.DELETE)) {
                    Idea idea = new Idea("", "", 0);
                    idea.setId(data.getIntExtra(EditIdeaActivity.EXTRA_ID, 0));
                    mIdeaViewModel.delete(idea);
                }
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idea_sort:
                mIdeaViewModel.setOrder(IdeaViewModel.IDEA);
                break;
            case R.id.category_sort:
                mIdeaViewModel.setOrder(IdeaViewModel.CATEGORY);
                break;
            case R.id.time_sort:
                mIdeaViewModel.setOrder(IdeaViewModel.TIME);
                break;
            case R.id.fab:
                Intent intent = new Intent(MainActivity.this, NewIdeaActivity.class);
                startActivityForResult(intent, NEW_IDEA_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    public void editIdea(Idea idea) {
        Intent intent = new Intent(MainActivity.this, EditIdeaActivity.class);
        intent.putExtra(TEXT, idea.getText());
        intent.putExtra(TIME, idea.getDate());
        intent.putExtra(CATEGORY, idea.getCategory());
        intent.putExtra(ID, idea.getId());
        startActivityForResult(intent, EDIT_IDEA_ACTIVITY_REQUEST_CODE);
    }
}
