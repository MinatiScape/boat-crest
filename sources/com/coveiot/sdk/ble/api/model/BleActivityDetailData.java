package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class BleActivityDetailData {
    private int activityId;
    private List<CalorieSample> calorieSampleList;
    private int categoryId;
    private long dataType;
    private int duration;
    private List<GpsSample> gpsSamplesList;
    private List<HeartSample> heartSampleList;
    private int leastSampleFrequency;
    private int min_data_num;
    private long sessionId;
    private List<StepSample> stepSampleList;
    private long timeStamp;
    private List<SpeedSample> speedSampleList = null;
    private List<PaceSample> paceSampleList = null;

    public BleActivityDetailData(long j, int i, int i2, long j2, int i3, long j3, List<CalorieSample> list, List<HeartSample> list2) {
        this.calorieSampleList = null;
        this.sessionId = j;
        this.categoryId = i;
        this.activityId = i2;
        this.dataType = j2;
        this.min_data_num = i3;
        this.timeStamp = j3;
        this.calorieSampleList = list;
        this.heartSampleList = list2;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public List<CalorieSample> getCalorieSampleList() {
        return this.calorieSampleList;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public long getDataType() {
        return this.dataType;
    }

    public int getDuration() {
        return this.duration;
    }

    public List<GpsSample> getGpsSamplesList() {
        return this.gpsSamplesList;
    }

    public List<HeartSample> getHeartSampleList() {
        return this.heartSampleList;
    }

    public int getLeastSampleFrequency() {
        return this.leastSampleFrequency;
    }

    public int getMin_data_num() {
        return this.min_data_num;
    }

    public List<PaceSample> getPaceSampleList() {
        return this.paceSampleList;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public List<SpeedSample> getSpeedSampleList() {
        return this.speedSampleList;
    }

    public List<StepSample> getStepSampleList() {
        return this.stepSampleList;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setGpsSamplesList(List<GpsSample> list) {
        this.gpsSamplesList = list;
    }

    public void setLeastSampleFrequency(int i) {
        this.leastSampleFrequency = i;
    }

    public void setPaceSampleList(List<PaceSample> list) {
        this.paceSampleList = list;
    }

    public void setSpeedSampleList(List<SpeedSample> list) {
        this.speedSampleList = list;
    }

    public void setStepSampleList(List<StepSample> list) {
        this.stepSampleList = list;
    }
}
