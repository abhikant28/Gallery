package com.example.gallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Albums_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_albums_fragment, container, false);

        RecyclerView rv_albums= v.findViewById(R.id.AlbumsFragment_RecyclerView);
        Log.i("RECYCLER:::", "VIEW");
        Log.i("RECYCLER LENGTH:::", String.valueOf(MainActivity.imageFolders.size()));
        rv_albums.setLayoutManager(new GridLayoutManager(getContext(),2));
        AlbumsRecyclerViewAdapter adapter = new AlbumsRecyclerViewAdapter(getContext(), MainActivity.imageFolders);
        //adapter.setClickListener(getContext());
        rv_albums.setAdapter(adapter);
        return v;
    }



}