package me.papercom.moviedb.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.papercom.moviedb.model.MovieModel;

/**
 * Created by Hasan on 02/08/2018.
 */

public class MovieResponse {
    @SerializedName("results")
    private List<MovieModel> results;
    @SerializedName("page")
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
