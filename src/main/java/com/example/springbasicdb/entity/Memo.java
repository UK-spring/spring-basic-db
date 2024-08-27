package com.example.springbasicdb.entity;

import lombok.Getter;

@Getter
public class Memo {

    private Long id;
    private String title;
    private String contents;

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(String title) {
        this.title = title;
    }

}
