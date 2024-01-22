package com.coveiot.android.activitymodes.models;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityShareData implements Serializable {
    @NotNull
    public static final String BEST_ACTIVITIES_ID = "best_activities_id";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SHARE_DATA = "share_data";
    @Nullable
    private Integer activityId;
    @Nullable
    private String activityTitleName;
    @Nullable
    private String activityType;
    private int avgPace;
    private float avgSpeed;
    private int avgStepFreq;
    private int avgStrideLength;
    private int avgStrokeFreq;
    private int avgSwolf;
    private float cal;
    @Nullable
    private Integer categoryId;
    private int distance;
    private int duration;
    @Nullable
    private String dwmValue;
    private int heartRate;
    private boolean isIndoor;
    private int maxHeartRate;
    private float maxPace;
    private int maxSPM;
    private int minHeartRate;
    private float minPace;
    private float pace;
    private int steps;
    @Nullable
    private String swimStroke;
    private int target;
    private int totalLaps;
    private int totalStrokes;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    public final Integer getActivityId() {
        return this.activityId;
    }

    @Nullable
    public final String getActivityTitleName() {
        return this.activityTitleName;
    }

    @Nullable
    public final String getActivityType() {
        return this.activityType;
    }

    public final int getAvgPace() {
        return this.avgPace;
    }

    public final float getAvgSpeed() {
        return this.avgSpeed;
    }

    public final int getAvgStepFreq() {
        return this.avgStepFreq;
    }

    public final int getAvgStrideLength() {
        return this.avgStrideLength;
    }

    public final int getAvgStrokeFreq() {
        return this.avgStrokeFreq;
    }

    public final int getAvgSwolf() {
        return this.avgSwolf;
    }

    public final float getCal() {
        return this.cal;
    }

    @Nullable
    public final Integer getCategoryId() {
        return this.categoryId;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final int getDuration() {
        return this.duration;
    }

    @Nullable
    public final String getDwmValue() {
        return this.dwmValue;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public final float getMaxPace() {
        return this.maxPace;
    }

    public final int getMaxSPM() {
        return this.maxSPM;
    }

    public final int getMinHeartRate() {
        return this.minHeartRate;
    }

    public final float getMinPace() {
        return this.minPace;
    }

    public final float getPace() {
        return this.pace;
    }

    public final int getSteps() {
        return this.steps;
    }

    @Nullable
    public final String getSwimStroke() {
        return this.swimStroke;
    }

    public final int getTarget() {
        return this.target;
    }

    public final int getTotalLaps() {
        return this.totalLaps;
    }

    public final int getTotalStrokes() {
        return this.totalStrokes;
    }

    public final boolean isIndoor() {
        return this.isIndoor;
    }

    public final void setActivityId(@Nullable Integer num) {
        this.activityId = num;
    }

    public final void setActivityTitleName(@Nullable String str) {
        this.activityTitleName = str;
    }

    public final void setActivityType(@Nullable String str) {
        this.activityType = str;
    }

    public final void setAvgPace(int i) {
        this.avgPace = i;
    }

    public final void setAvgSpeed(float f) {
        this.avgSpeed = f;
    }

    public final void setAvgStepFreq(int i) {
        this.avgStepFreq = i;
    }

    public final void setAvgStrideLength(int i) {
        this.avgStrideLength = i;
    }

    public final void setAvgStrokeFreq(int i) {
        this.avgStrokeFreq = i;
    }

    public final void setAvgSwolf(int i) {
        this.avgSwolf = i;
    }

    public final void setCal(float f) {
        this.cal = f;
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.categoryId = num;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final void setDwmValue(@Nullable String str) {
        this.dwmValue = str;
    }

    public final void setHeartRate(int i) {
        this.heartRate = i;
    }

    public final void setIndoor(boolean z) {
        this.isIndoor = z;
    }

    public final void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public final void setMaxPace(float f) {
        this.maxPace = f;
    }

    public final void setMaxSPM(int i) {
        this.maxSPM = i;
    }

    public final void setMinHeartRate(int i) {
        this.minHeartRate = i;
    }

    public final void setMinPace(float f) {
        this.minPace = f;
    }

    public final void setPace(float f) {
        this.pace = f;
    }

    public final void setSteps(int i) {
        this.steps = i;
    }

    public final void setSwimStroke(@Nullable String str) {
        this.swimStroke = str;
    }

    public final void setTarget(int i) {
        this.target = i;
    }

    public final void setTotalLaps(int i) {
        this.totalLaps = i;
    }

    public final void setTotalStrokes(int i) {
        this.totalStrokes = i;
    }
}
