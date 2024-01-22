package com.coveiot.coveaccess.fitness.config.models.requestmodel;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class FitnessConfigRequest implements Serializable {
    private String height;
    private String runningStrideLength;
    private String walkingStrideLength;
    private String weight;

    public FitnessConfigRequest(String str, String str2, String str3, String str4) {
        this.height = str;
        this.weight = str2;
        this.walkingStrideLength = str3;
        this.runningStrideLength = str4;
    }

    public String getHeight() {
        return this.height;
    }

    public String getRunningStrideLength() {
        return this.runningStrideLength;
    }

    public String getWalkingStrideLength() {
        return this.walkingStrideLength;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setRunningStrideLength(String str) {
        this.runningStrideLength = str;
    }

    public void setWalkingStrideLength(String str) {
        this.walkingStrideLength = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public FitnessConfigRequest(String str, String str2, String str3) {
        this.height = str;
        this.weight = str2;
        this.walkingStrideLength = str3;
    }
}
