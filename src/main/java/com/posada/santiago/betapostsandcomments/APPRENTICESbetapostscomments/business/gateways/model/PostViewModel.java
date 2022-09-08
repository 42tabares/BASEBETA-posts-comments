package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model;

import java.util.List;

public class PostViewModel {

    private String aggregateId;
    private String author;
    private String title;
    private String favorite;
    private List <CommentViewModel> comments;

    public PostViewModel() {
    }

    public PostViewModel(String AggregateId, String author, String title, String favorite, List<CommentViewModel> comments) {
        this.aggregateId = AggregateId;
        this.author = author;
        this.title = title;
        this.favorite = favorite;
        this.comments = comments;
    }

    public String getAggregateId() {
        return aggregateId;
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

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }
}
