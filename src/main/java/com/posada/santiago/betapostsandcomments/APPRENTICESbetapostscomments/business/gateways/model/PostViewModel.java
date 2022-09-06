package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel {

    private String id;
    private String postId;
    private String author;
    private String title;
    private String favorite;
    private List <CommentViewModel> comments;

    public PostViewModel() {
    }

    public PostViewModel(String postId, String author, String title, String favorite, List<CommentViewModel> comments) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.favorite = favorite;
        this.comments = comments;
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

    public String getTitle() {
        return title;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }
}
