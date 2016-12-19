package com.example.mani_4647.jsonarraytolist;

/**
 * Created by mani-4647 on 19/12/16.
 */

public class ListModel {

    String userId;
    String id;
    String title;
    String body;

    public ListModel(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
