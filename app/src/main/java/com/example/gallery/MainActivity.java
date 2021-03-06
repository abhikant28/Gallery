package com.example.gallery;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static Cursor allImageCursor;
    static ArrayList<folderFacer> imageFolders= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            getImagePaths();
            getVideoPaths();
        }
//        else if (shouldShowRequestPermissionRationale()) {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected. In this UI,
//            // include a "cancel" or "no thanks" button that allows the user to
//            // continue using your app without granting the permission.
//            showInContextUI(...);
//        }
        else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        BottomNavigationView bottomNav = findViewById(R.id.MainActivity_BottomNavigationBar);
        bottomNav.setOnItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainActivity_FrameLayout,
                    new Albums_Fragment()).commit();
        }
        UsefulFunctions functions = new UsefulFunctions(getApplicationContext());
        Log.i("IMAGE FUNCTIONS::::::", "");

        imageFolders= functions.getImageFolders();


    }

    private NavigationBarView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_albums:
                            selectedFragment = new Albums_Fragment();
                            break;
                        case R.id.nav_photos:
                            selectedFragment = new AllPhotos_fragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.MainActivity_FrameLayout,
                            selectedFragment).commit();

                    return true;
                }
            };

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    getImagePaths();
                    getVideoPaths();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    public ArrayList<folderFacer> getImagePaths(){
        ArrayList<folderFacer> imageFolders = new ArrayList<>();
        ArrayList<String> imagePaths = new ArrayList<>();
        Uri allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = getContentResolver().query(allImagesuri, projection, null, null, null);

        try {
            cursor.moveToFirst();
            do{

                folderFacer folds = new folderFacer();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                String folderpaths =  datapath.replace(name,"");
                if (!imagePaths.contains(folderpaths)) {
                    imagePaths.add(folderpaths);

                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    imageFolders.add(folds);
                }

            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0;i < imageFolders.size();i++){

            Log.d("Image folders",imageFolders.get(i).getFolderName()+" and path = "+imageFolders.get(i).getPath());

        }

        return imageFolders;
    }

    private ArrayList<folderFacer> getVideoPaths(){
        ArrayList<folderFacer> videoFolders = new ArrayList<>();
        ArrayList<String> videoPaths = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media.BUCKET_ID};
        Cursor cursor = getContentResolver().query(allVideosuri, projection, null, null, null);

        try {
            cursor.moveToFirst();
            do{

                folderFacer folds = new folderFacer();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                String folderpaths =  datapath.replace(name,"");
                if (!videoPaths.contains(folderpaths)) {
                    videoPaths.add(folderpaths);

                    folds.setPath(folderpaths);
                    folds.setFolderName(folder);
                    videoFolders.add(folds);
                }

            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0;i < videoFolders.size();i++){

            Log.d("video folders",videoFolders.get(i).getFolderName()+" and path = "+videoFolders.get(i).getPath());

        }

        return videoFolders;
    }
}