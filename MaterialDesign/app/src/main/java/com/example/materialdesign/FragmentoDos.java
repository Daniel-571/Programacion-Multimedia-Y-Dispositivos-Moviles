package com.example.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FragmentoDos extends Fragment {

    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragmento_dos, container, false);

        recycler = v.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> datos = Arrays.asList(
                "Hola Jose 1",
                "Hola Jose 2",
                "Hola Jose 3",
                "Hola Jose 4",
                "Hola Jose 5"
        );

        recycler.setAdapter(new SimpleAdapter(datos));

        return v;
    }
}
