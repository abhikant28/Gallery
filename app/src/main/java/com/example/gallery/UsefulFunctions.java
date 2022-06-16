package com.example.gallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class UsefulFunctions {
    Context context;

    public UsefulFunctions(Context cnxt){
        context=cnxt;
    }

    public ArrayList<folderFacer> getImageFolders(){
        ArrayList<folderFacer> imageFolders = new ArrayList<>();
        ArrayList<String> imagePaths= new ArrayList<>();
        Uri allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Log.i("ALL IMAGE URIs :::", allImagesuri.toString());
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = context.getContentResolver().query(allImagesuri, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{

                folderFacer folds = new folderFacer();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                Log.i("IMAGE NAME:::",name);
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                Log.i("IMAGE FOLDER:::",folder );
                String bID = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID));
                Log.i("IMAGE BUCKET ID:::", bID);
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Log.i("IMAGE DATAPATH:::", datapath);


                String folderPaths =  datapath.replace(name,"");
                Log.i("IMAGE FOLDER PATH:::", folderPaths);


                if (!imagePaths.contains(folderPaths)) {
                    imagePaths.add(folderPaths);

                    folds.setPath(folderPaths);
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

    public ArrayList<folderFacer> getVideoFolders(){
        ArrayList<folderFacer> videoFolders = new ArrayList<>();
        ArrayList<String> videoPaths = new ArrayList<>();
        Uri allVideosUri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media.BUCKET_ID};
        Cursor cursor = context.getContentResolver().query(allVideosUri, projection, null, null, null);

        try {
            cursor.moveToFirst();
            do{

                folderFacer folds = new folderFacer();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                String folderPaths =  datapath.replace(name,"");
                if (!videoPaths.contains(folderPaths)) {
                    videoPaths.add(folderPaths);

                    folds.setPath(folderPaths);
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
