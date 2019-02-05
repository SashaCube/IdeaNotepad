package com.oleksandr.havryliuk.ideanotepad.UI;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oleksandr.havryliuk.ideanotepad.MainActivity;
import com.oleksandr.havryliuk.ideanotepad.R;
import com.oleksandr.havryliuk.ideanotepad.repository.Idea;
import com.oleksandr.havryliuk.ideanotepad.utils.Utils;

import java.util.List;

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.WordViewHolder> {

    private Activity activity;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView ideaItemView;
        private final TextView category;
        private final TextView date;
        private final LinearLayout layout;

        private WordViewHolder(View itemView) {
            super(itemView);
            ideaItemView = itemView.findViewById(R.id.text);
            category = itemView.findViewById(R.id.category);
            date = itemView.findViewById(R.id.date);
            layout = itemView.findViewById(R.id.main_layout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Idea idea = mIdeas.get(getAdapterPosition());
            ((MainActivity) activity).editIdea(idea);
        }
    }

    private final LayoutInflater mInflater;
    private List<Idea> mIdeas;

    public IdeasAdapter(Activity activity) {
        mInflater = LayoutInflater.from(activity.getApplicationContext());
        this.activity = activity;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_idea, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mIdeas != null) {
            Idea current = mIdeas.get(position);
            holder.ideaItemView.setText(current.getText());
            holder.category.setText(current.getCategory());
            holder.date.setText(Utils.getFormatDate(current.getDate()));
            holder.layout.setBackground(Utils.getColorByRGB(current.getColor()));
        } else {
            holder.ideaItemView.setText("No Ideas");
        }
    }

    public void setIdeas(List<Idea> words) {
        mIdeas = words;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (mIdeas != null)
            return mIdeas.size();
        else return 0;
    }
}