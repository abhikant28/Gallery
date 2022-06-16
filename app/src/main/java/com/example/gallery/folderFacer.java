package com.example.gallery;

import java.util.ArrayList;

public class folderFacer {

    private String path;
    private String ID;
    private String folderName;
    ArrayList<String> subPaths;

    public folderFacer() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getSubPaths() {
        return subPaths;
    }

    public void setSubPaths(ArrayList<String> subPaths) {
        this.subPaths = subPaths;
    }

    public folderFacer(String path, String folderName) {
        this.path = path;
        this.folderName = folderName;
    }

    public void addSubPath(String subPath) {
        subPaths.add(subPath);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}