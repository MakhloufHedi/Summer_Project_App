package com.example.summer_project_app;

public class CategoryModel {
    private String docID;
    private String name;
    private int noOfTests;


    public CategoryModel(String docID, String name, int noOfTests) {
        this.docID = docID;
        this.name = name;
        this.noOfTests = noOfTests;
    }

    public int getNoOfTests() {
        return noOfTests;
    }

    public void setNoOfTests(int noOfTests) {
        this.noOfTests = noOfTests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }
}
