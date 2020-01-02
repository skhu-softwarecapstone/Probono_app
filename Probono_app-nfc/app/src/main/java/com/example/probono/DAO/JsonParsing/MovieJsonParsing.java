package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieJsonParsing {
    private String json;

    public MovieJsonParsing(String json) {this.json = json;}

    public void jsonParsing(ArrayList<Movie> movieList) {
        if(json == null) return;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray movieArray = jsonObject.getJSONArray("movies");
            for(int i=0; i<movieArray.length(); i++) {
                JSONObject movieObject = movieArray.getJSONObject(i);
                Movie movie = new Movie();

                movie.setTitle(movieObject.getString("title"));
                movie.setGrade(movieObject.getString("grade"));
                movie.setCategory(movieObject.getString("category"));

                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
