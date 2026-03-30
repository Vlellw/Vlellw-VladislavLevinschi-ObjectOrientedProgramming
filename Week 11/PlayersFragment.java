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

public class PlayersFragment extends Fragment {

    private Repository<Player> repository;
    private List<Player> currentList;
    private GenericListAdapter<Player> adapter;
    private List<Player> originalList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etSearch = view.findViewById(R.id.et_search);
        Button btnSortName = view.findViewById(R.id.btn_sort_name);
        Button btnSortAge = view.findViewById(R.id.btn_sort_age);
        RecyclerView recycler = view.findViewById(R.id.recycler_view);

        repository = new Repository<>();
        DataProvider dp = new DataProvider();
        for (Player p : dp.getPlayers()) repository.add(p);

        currentList = repository.getAll();
        originalList = new ArrayList<>(currentList);

        adapter = new GenericListAdapter<>(currentList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        // Демонстрация custom iterator
        Iterator<Player> it = repository.getIterator();
        StringBuilder sb = new StringBuilder("Players via iterator:\n");
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
            repository.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
            currentList.clear();
            currentList.addAll(repository.getAll());
            adapter.updateList(currentList);
        });

        btnSortAge.setOnClickListener(v -> {
            repository.sort((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()));
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
            currentList.addAll(repository.filter(p ->
                    p.getName().toLowerCase().contains(query.toLowerCase())));
        }
        adapter.updateList(currentList);
    }
}