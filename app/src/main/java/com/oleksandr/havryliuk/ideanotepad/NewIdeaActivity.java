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

public class NewIdeaActivity extends AppCompatActivity {

    public static final String EXTRA_IDEA = "idea";
    public static final String EXTRA_CATEGORY = "category";

    private EditText mEditIdeaView;
    private AutoCompleteTextView mEditCategoryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea);
        mEditIdeaView = findViewById(R.id.edit_idea);
        mEditCategoryView = findViewById(R.id.edit_category);

        // autocomplete
        Set<String> keys = PreferenceManager.getAllKeys();
        String[] categories = keys.toArray(new String[keys.size()]);
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        mEditCategoryView.setAdapter(stringArrayAdapter);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditIdeaView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String idea = mEditIdeaView.getText().toString();
                String category = this.mEditCategoryView.getText().toString();
                replyIntent.putExtra(EXTRA_IDEA, idea);
                replyIntent.putExtra(EXTRA_CATEGORY, category);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}