package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class QRCodeData {
    public int imageId;
    public String imageTag;
    public String imageTitle;

    public QRCodeData(int i, String str, String str2) {
        this.imageId = i;
        this.imageTitle = str;
        this.imageTag = str2;
    }

    public int getImageId() {
        return this.imageId;
    }

    public String getImageTag() {
        return this.imageTag;
    }

    public String getImageTitle() {
        return this.imageTitle;
    }

    public void setImageId(int i) {
        this.imageId = i;
    }

    public void setImageTag(String str) {
        this.imageTag = str;
    }

    public void setImageTitle(String str) {
        this.imageTitle = str;
    }
}
