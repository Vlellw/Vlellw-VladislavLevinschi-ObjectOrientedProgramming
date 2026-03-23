package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import org.json.JSONException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        errorTextView = findViewById(R.id.errorTextView);

        setupRecyclerView();
        loadMovieData();
    }

    private void setupRecyclerView() {
        adapter = new MovieAdapter(null);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setAdapter(adapter);
    }

    private void loadMovieData() {
        try {
            List<Movie> movies = JsonUtils.loadMoviesFromJson(this);
            adapter.updateMovies(movies);
            errorTextView.setVisibility(View.GONE);
        } catch (IOException e) {
            showError("Missing JSON file");
            JsonUtils.handleJsonException(e);
        } catch (JSONException e) {
            showError("Invalid JSON format");
            JsonUtils.handleJsonException(e);
        } catch (Exception e) {
            showError("Error loading movies");
            JsonUtils.handleJsonException(e);
        }
    }

    private void showError(String message) {
        errorTextView.setText(message);
        errorTextView.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
