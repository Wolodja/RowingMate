package com.example.vvaskovy.rowingmate;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by VVASKOVY on 13.12.2017.
 */

public class Advice extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
