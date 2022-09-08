package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model;

public class CommentViewModel {
    private String id;
    private String postId;
    private String author;
    private String content;
    private String favorite;

    public CommentViewModel() {
    }

    public CommentViewModel(String id,  String postId, String author, String content, String favorite) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.favorite = favorite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getFavorite() {
        return favorite;
    }
}
