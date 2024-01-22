package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostRatingReq {
    @SerializedName("comments")
    private String comments;
    @SerializedName("rating")
    private Integer rating;

    public String getComments() {
        return this.comments;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setComments(String str) {
        this.comments = str;
    }

    public void setRating(Integer num) {
        this.rating = num;
    }
}
