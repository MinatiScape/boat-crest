package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.List;
/* loaded from: classes8.dex */
public class Data {
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("fitnessStats")
    @Expose
    private List<FitnessStat> fitnessStats = null;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose
    private String height;
    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("runningStrideLength")
    @Expose
    private String runningStrideLength;
    @SerializedName("skinType")
    @Expose
    private String skinType;
    @SerializedName("walkingStrideLength")
    @Expose
    private String walkingStrideLength;
    @SerializedName("weight")
    @Expose
    private String weight;

    public String getCreatedDate() {
        return this.createdDate;
    }

    public List<FitnessStat> getFitnessStats() {
        return this.fitnessStats;
    }

    public String getHeight() {
        return this.height;
    }

    public String getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getRunningStrideLength() {
        return this.runningStrideLength;
    }

    public String getSkinType() {
        return this.skinType;
    }

    public String getWalkingStrideLength() {
        return this.walkingStrideLength;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setFitnessStats(List<FitnessStat> list) {
        this.fitnessStats = list;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setLastModifiedDate(String str) {
        this.lastModifiedDate = str;
    }

    public void setRunningStrideLength(String str) {
        this.runningStrideLength = str;
    }

    public void setSkinType(String str) {
        this.skinType = str;
    }

    public void setWalkingStrideLength(String str) {
        this.walkingStrideLength = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }
}
