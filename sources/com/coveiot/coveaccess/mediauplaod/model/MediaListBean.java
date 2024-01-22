package com.coveiot.coveaccess.mediauplaod.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class MediaListBean {
    private String fileUrl;
    private String mediaClass;
    private String mediaId;
    @SerializedName("priority")
    private double priorityX;

    public String getFileUrl() {
        return this.fileUrl;
    }

    public String getMediaClass() {
        return this.mediaClass;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public double getPriorityX() {
        return this.priorityX;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public void setMediaClass(String str) {
        this.mediaClass = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setPriorityX(double d) {
        this.priorityX = d;
    }

    public String toString() {
        return "MediaListBean{mediaClass='" + this.mediaClass + "', fileUrl='" + this.fileUrl + "'}";
    }
}
