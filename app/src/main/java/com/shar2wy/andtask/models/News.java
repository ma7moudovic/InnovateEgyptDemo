package com.shar2wy.andtask.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shar2wy on 5/10/17.
 */

public class News implements Serializable{

    @SerializedName("NewsTitle")
    private String newsTitle;

    @SerializedName("Nid")
    private String nid;

    @SerializedName("PostDate")
    private String postDate;

    @SerializedName("ImageUrl")
    private String imageUrl;

    @SerializedName("NewsType")
    private String newsType;

    @SerializedName("NumofViews")
    private String numOfViews;

    @SerializedName("Likes")
    private String likes;

    @SerializedName("ItemDescription")
    private String description;

    @SerializedName("ShareURL")
    private String shareUrl;

    @SerializedName("VideoURL")
    private String videoUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNumOfViews() {
        return numOfViews;
    }

    public void setNumOfViews(String numOfViews) {
        this.numOfViews = numOfViews;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
