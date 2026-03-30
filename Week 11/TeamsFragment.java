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

public class TeamsFragment extends Fragment {

    private Repository<Team> repository;
    private List<Team> currentList;
    private GenericListAdapter<Team> adapter;
    private List<Team> originalList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etSearch = view.findViewById(R.id.et_search);
        Button btnSortName = view.findViewById(R.id.btn_sort_name);
        Button btnSortYear = view.findViewById(R.id.btn_sort_year);
        RecyclerView recycler = view.findViewById(R.id.recycler_view);

        repository = new Repository<>();
        DataProvider dp = new DataProvider();
        for (Team t : dp.getTeams()) repository.add(t);

        currentList = repository.getAll();
        originalList = new ArrayList<>(currentList);

        adapter = new GenericListAdapter<>(currentList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        Iterator<Team> it = repository.getIterator();
        StringBuilder sb = new StringBuilder("Teams via iterator:\n");
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
            repository.sort((t1, t2) -> t1.getName().compareTo(t2.getName()));
            currentList.clear();
            currentList.addAll(repository.getAll());
            adapter.updateList(currentList);
        });

        btnSortYear.setOnClickListener(v -> {
            repository.sort((t1, t2) -> Integer.compare(t2.getFoundedYear(), t1.getFoundedYear()));
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
            currentList.addAll(repository.filter(t ->
                    t.getName().toLowerCase().contains(query.toLowerCase())));
        }
        adapter.updateList(currentList);
    }
}