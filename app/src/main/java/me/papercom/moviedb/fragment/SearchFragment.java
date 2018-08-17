package me.papercom.moviedb.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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


public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    SearchView searchViewFilm;
    ProgressDialog mProgress;
    String title;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchViewFilm = (SearchView) view.findViewById(R.id.searchview_film);
        searchViewFilm.setOnQueryTextListener(this);
        searchViewFilm.requestFocus();
        searchViewFilm.setQueryHint(getResources().getString(R.string.search_hint));
        mProgress = new ProgressDialog(getActivity());

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        title = query;
        loadMovie();
        mProgress.dismiss();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void loadMovie() {
        mProgress.setMessage(getResources().getString(R.string.on_searching) + " " + title);
        String inputFilm = searchViewFilm.getQuery().toString();

        ApiService apiService = ApiBuilder.getClient(getContext()).create(ApiService.class);

        final RecyclerView recyclerView = getActivity().findViewById(R.id.film_search_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<MovieResponse> callInput = apiService.getFilmSearch(inputFilm);
        callInput.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.e("", "done");
                final List<MovieModel> film = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(film, R.layout.item_movie, getContext()));
                mProgress.dismiss();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(getContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
