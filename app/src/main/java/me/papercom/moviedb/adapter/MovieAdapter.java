package me.papercom.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.papercom.moviedb.BuildConfig;
import me.papercom.moviedb.R;

import me.papercom.moviedb.activity.MovieDetailActivity;
import me.papercom.moviedb.model.MovieModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hasan on 02/08/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.FilmsViewHolder> {

    private List<MovieModel> films;
    private int rowLayout;
    private Context context;

    public MovieAdapter(List<MovieModel> films, int rowLayout, Context context) {
        this.films = films;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieAdapter.FilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FilmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.FilmsViewHolder holder, final int position) {

        String posterUrl = "http://image.tmdb.org/t/p/w185";
        Glide.with(context).load(posterUrl + films.get(position).getPosterPath()).into(holder.imgPoster);

        holder.tvTitle.setText(films.get(position).getTitle());
        holder.tvReleaseDate.setText(films.get(position).getReleaseDate());
        holder.tvDesc.setText(films.get(position).getOverview());


        holder.cvFilmItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = films.get(position).getTitle().toString();
                Double voteAverage = films.get(position).getVoteAverage().doubleValue();
                String posterPath = BuildConfig.IMG_URL + films.get(position).getPosterPath().toString();
                String releaseDate = null;
                if (films.get(position).getReleaseDate().toString().equals("")) {
                    releaseDate = "-";
                } else {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
                    try {
                        date = dateFormat.parse(films.get(position).getReleaseDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
                    releaseDate = formatter.format(date);
                }
                boolean adult = films.get(position).getAdult();
                String overview = films.get(position).getOverview().toString();

                Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_TITLE", title);
                bundle.putString("EXTRA_OVERVIEW", overview);
                bundle.putString("EXTRA_RELEASE_DATE", releaseDate);
                bundle.putString("EXTRA_POSTER_URL", posterPath);
                bundle.putDouble("EXTRA_VOTE", voteAverage);
                bundle.putBoolean("EXTRA_TAYANGAN", adult);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class FilmsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_film_item)
        CardView cvFilmItem;
        @BindView(R.id.img_item_poster)
        ImageView imgPoster;
        @BindView(R.id.tv_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.tv_item_desc)
        TextView tvDesc;


        public FilmsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
