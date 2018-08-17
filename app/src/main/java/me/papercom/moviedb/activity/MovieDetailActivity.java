package me.papercom.moviedb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import me.papercom.moviedb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.img_detail_movie) ImageView imgViewPoster;
    @BindView(R.id.txt_title_detail_movie) TextView txtJudul;
    @BindView(R.id.txt_age_rating_movie) TextView txtDeskripsi;
    @BindView(R.id.txt_date_movie) TextView txtTanggalRilis;
    @BindView(R.id.rating_detail_film) RatingBar ratingBar;
    @BindView(R.id.txt_detail_movie) TextView txtTayangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        String title = getIntent().getStringExtra("EXTRA_TITLE");
        getSupportActionBar().setTitle(title);
        ButterKnife.bind(this);


        String overview = getIntent().getStringExtra("EXTRA_OVERVIEW");
        String releaseDate = getIntent().getStringExtra("EXTRA_RELEASE_DATE");
        String posterUrl =getIntent().getStringExtra("EXTRA_POSTER_URL");
        double vote = getIntent().getDoubleExtra("EXTRA_VOTE",0.0);
        boolean tayangan = getIntent().getBooleanExtra("EXTRA_TAYANGAN",false);
        String tayang;
        if (tayangan==true){
            tayang = getResources().getString(R.string.category_adult);
        }
        else{
            tayang =getResources().getString(R.string.category_parental_guidance);
        }

        String ratingString = String.valueOf(vote);
        Float ratingFloat = Float.parseFloat(ratingString);
        ratingBar.setRating(ratingFloat);
        ratingBar.setStepSize(0.1f);
        txtJudul.setText(title);
        txtDeskripsi.setText(overview);
        txtTanggalRilis.setText(getResources().getString(R.string.release_date)+" "+releaseDate);
        txtTayangan.setText(tayang);
        Glide.with(this).load(posterUrl).into(imgViewPoster);

    }
}
