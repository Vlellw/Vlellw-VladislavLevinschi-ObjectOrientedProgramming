package com.example.soccerteammanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatchesFragment extends Fragment {

    private Repository<Match> repository;
    private List<Match> currentList;
    private GenericListAdapter<Match> adapter;
    private List<Match> originalList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etSearch = view.findViewById(R.id.et_search);
        Button btnSortName = view.findViewById(R.id.btn_sort_name);
        Button btnSortDate = view.findViewById(R.id.btn_sort_date);
        RecyclerView recycler = view.findViewById(R.id.recycler_view);

        repository = new Repository<>();
        DataProvider dp = new DataProvider();
        for (Match m : dp.getMatches()) repository.add(m);

        currentList = repository.getAll();
        originalList = new ArrayList<>(currentList);

        adapter = new GenericListAdapter<>(currentList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        // Демонстрация custom iterator
        Iterator<Match> it = repository.getIterator();
        StringBuilder sb = new StringBuilder("Matches via iterator:\n");
        while (it.hasNext()) {
            sb.append(it.next().getName()).append("\n");
        }
        Log.d("IteratorDemo", sb.toString());

        etSearch.addTextChangedListener(new android.text.TextWatcher() {
            public void afterTextChanged(android.text.Editable s) { filter(s.toString()); }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        btnSortName.setOnClickListener(v -> {
            repository.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
            currentList.clear();
            currentList.addAll(repository.getAll());
            adapter.updateList(currentList);
        });

        btnSortDate.setOnClickListener(v -> {
            repository.sort((m1, m2) -> m2.toString().compareTo(m1.toString())); // простая сортировка по строке с датой
            currentList.clear();
            currentList.addAll(repository.getAll());
            adapter.updateList(currentList);
        });
    }

    private void filter(String query) {
        if (query.trim().isEmpty()) {
            currentList.clear();
            currentList.addAll(originalList);
        } else {
            currentList.clear();
            currentList.addAll(repository.filter(m ->
                    m.getName().toLowerCase().contains(query.toLowerCase())));
        }
        adapter.updateList(currentList);
    }
}