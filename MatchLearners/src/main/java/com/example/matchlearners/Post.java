package com.example.matchlearners;

public class Post {
    private int idPost;
    private String title;
    private String description;
    private int idUser;
    private int isLiked;
    private String username;

    public Post(int idPost, String title, String description, int idUser, String username, int isLiked) {
        this.idPost = idPost;
        this.title = title;
        this.description = description;
        this.idUser = idUser;
        this.username = username;
        this.isLiked = isLiked;
    }

    public Post(String title, String description, int idUser, String username, int isLiked) {
        this.title = title;
        this.description = description;
        this.idUser = idUser;
        this.username = username;
        this.isLiked = isLiked;
    }

    public int getIdPost() {
        return this.idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIsLiked() {
        return this.isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
