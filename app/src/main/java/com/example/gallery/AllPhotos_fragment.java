package com.example.gallery;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AllPhotos_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_all_photos_fragment, container, false);

        RecyclerView rv_allPhotos=v.findViewById(R.id.AllPhotosFragment_RecyclerView);


        RecyclerView rv_albums= v.findViewById(R.id.AlbumsFragment_RecyclerView);
        rv_albums.setLayoutManager(new GridLayoutManager(getContext(),2));
        AlbumsRecyclerViewAdapter adapter = new AlbumsRecyclerViewAdapter(getContext(), MainActivity.allImageCursor);
        //adapter.setClickListener(getContext());
        rv_albums.setAdapter(adapter);

        return v;
    }
}