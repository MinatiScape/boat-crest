package com.mappls.sdk.navigation.model;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.navigation.data.a;
/* loaded from: classes11.dex */
public class Junction implements LocationPoint {

    /* renamed from: a  reason: collision with root package name */
    public double f12919a;
    public Bitmap bitmap;
    @SerializedName("encodeImage")
    @Expose
    public String encodeImage;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("latitude")
    @Expose
    public double latitude;
    @SerializedName("longitude")
    @Expose
    public double longitude;
    @SerializedName("nodeId")
    @Expose
    public int nodeId;
    @SerializedName("nodeIdx")
    @Expose
    public int nodeIdx;

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getEncodeImage() {
        return this.encodeImage;
    }

    public String getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public double getLatitude() {
        return this.latitude;
    }

    public double getLeftDistance() {
        return this.f12919a;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public double getLongitude() {
        return this.longitude;
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public int getNodeIdx() {
        return this.nodeIdx;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public a getPointDescription(Context context) {
        return new a(this.latitude, this.longitude);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setEncodeImage(String str) {
        this.encodeImage = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLeftDistance(double d) {
        this.f12919a = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setNodeId(int i) {
        this.nodeId = i;
    }

    public void setNodeIdx(int i) {
        this.nodeIdx = i;
    }
}
