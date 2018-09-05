package com.example.developer.popmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.developer.popmovies.Helpers.Consts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {
    private final List<MoviePoster> moviePosters;
    private final ItemClickListener listener;

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    public MoviePosterAdapter(List<MoviePoster> moviePosters, ItemClickListener listener) {
        this.moviePosters = moviePosters;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.movie_poster_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoviePoster poster = moviePosters.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(Consts.moviePosterUrl + poster.getPosterPath())
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return moviePosters.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView moviePoster;

        private ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            moviePoster = itemView.findViewById(R.id.iv_poster);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}
