package com.oleksandr.havryliuk.ideanotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.oleksandr.havryliuk.ideanotepad.utils.PreferenceManager;

import java.util.Set;

import static com.oleksandr.havryliuk.ideanotepad.MainActivity.CATEGORY;
import static com.oleksandr.havryliuk.ideanotepad.MainActivity.ID;
import static com.oleksandr.havryliuk.ideanotepad.MainActivity.TEXT;
import static com.oleksandr.havryliuk.ideanotepad.MainActivity.TIME;

public class EditIdeaActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_TEXT = "newText";
    public static final String EXTRA_NEW_CATEGORY = "newCategory";
    public static final String EXTRA_TIME = "time";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_ACTION = "action";

    public static final String EDIT = "edit";
    public static final String DELETE = "delete";


    private EditText mEditIdeaView;
    private AutoCompleteTextView mEditCategoryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idea);
        mEditIdeaView = findViewById(R.id.edit_new_idea);
        mEditCategoryView = findViewById(R.id.edit_new_category);
        mEditIdeaView.setText(getIntent().getStringExtra(TEXT));
        mEditCategoryView.setText(getIntent().getStringExtra(CATEGORY));


        // autocomplete
        Set<String> keys = PreferenceManager.getAllKeys();
        String[] categories = keys.toArray(new String[keys.size()]);
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        mEditCategoryView.setAdapter(stringArrayAdapter);

        final Button editButton = findViewById(R.id.button_edit);
        editButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditIdeaView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String newIdea = mEditIdeaView.getText().toString();
                String newCategory = mEditCategoryView.getText().toString();
                replyIntent.putExtra(EXTRA_NEW_TEXT, newIdea);
                replyIntent.putExtra(EXTRA_TIME, getIntent().getLongExtra(TIME, 0));
                replyIntent.putExtra(EXTRA_NEW_CATEGORY, newCategory);
                replyIntent.putExtra(EXTRA_ID, getIntent().getIntExtra(ID, 0));
                replyIntent.putExtra(EXTRA_ACTION, EDIT);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        final Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditIdeaView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_ID, getIntent().getIntExtra(ID, 0));
                replyIntent.putExtra(EXTRA_ACTION, DELETE);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}

