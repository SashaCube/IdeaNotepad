package com.oleksandr.havryliuk.ideanotepad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

    private final LayoutInflater mInflater;
    private List<Idea> mIdeas;

    IdeasAdapter(Context context) { mInflater = LayoutInflater.from(context); }

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
            // Covers the case of data not being ready yet.
            holder.ideaItemView.setText("No Ideas");
        }
    }

    void setIdeas(List<Idea> words){
        mIdeas = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mIdeas != null)
            return mIdeas.size();
        else return 0;
    }
}
