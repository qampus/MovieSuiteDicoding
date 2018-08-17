package me.papercom.moviedb.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.papercom.moviedb.adapter.MovieAdapter;
import me.papercom.moviedb.R;
import me.papercom.moviedb.model.MovieModel;
import me.papercom.moviedb.response.MovieResponse;
import me.papercom.moviedb.services.ApiBuilder;
import me.papercom.moviedb.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.papercom.moviedb.BuildConfig.API_KEY;


public class NowPlayingFragment extends Fragment {


    ProgressDialog mProgress;

    public NowPlayingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mProgress = new ProgressDialog(getActivity());
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgress.setMessage(getResources().getString(R.string.waiting));
        loadMovie();
        setRetainInstance(true);
    }
    private void loadMovie() {
        ApiService apiService = ApiBuilder.getClient(getContext()).create(ApiService.class);

        final RecyclerView recyclerView = getActivity().findViewById(R.id.rv_now_playing);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<MovieResponse> call = apiService.getNowPlaying(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<MovieModel> films = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(films, R.layout.item_movie, getContext()));
                mProgress.dismiss();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(getContext(),getResources().getString(R.string.error),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
