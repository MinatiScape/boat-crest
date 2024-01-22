package com.coveiot.coveaccess.workoutvideos.model;
/* loaded from: classes8.dex */
public enum VideoType {
    CULT_FIT("CULT_FIT"),
    SENSE_AI_COACH("SENSE_AI_COACH");
    
    private String videoType;

    VideoType(String str) {
        this.videoType = str;
    }

    public String getVideoType() {
        return this.videoType;
    }
}
