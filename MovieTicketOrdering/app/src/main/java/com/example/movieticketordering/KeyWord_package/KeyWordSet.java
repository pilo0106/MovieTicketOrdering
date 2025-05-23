package com.example.movieticketordering.KeyWord_package;

public class KeyWordSet {
    private String id;
    private String name;
    private String keyword;

    public KeyWordSet(String id, String name, String keyword){
        this.id = id;
        this.name = name;
        this.keyword = keyword;
    }
    public KeyWordSet(String name){
        this(name, ""); // 在此使用另一個構造函數，並將 keyword 設置為空字符串
    }

    public KeyWordSet(String name, String keyword){
        this.name = name;
        this.keyword = keyword;
    }
    public KeyWordSet(){

    }

    @Override
    public String toString() {
        return "KeyWordSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
    /*@Override
    public String toString() {
        return "Name: " + name + ", Keyword: " + keyword;
    }*/
    public String getId() {
        return id;
    }
    public String getKeyword() {
        return keyword;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
