package com.coveiot.android.leonardo.dashboard.model;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class LiveUpdateData implements Serializable {
    @Nullable
    private LiveUpdateData INSTANCE;
    private int awake;
    private double bodyTemperature;
    @Nullable
    private String bodyTemperatureRecordedDate;
    @Nullable
    private String bpRecordedDate;
    private int bpSystolic;
    private int byDiastolic;
    private int cal;
    private int deepSleep;
    private int distance;
    @Nullable
    private String endSleepDate;
    private double fatigueLevel;
    private int heartRate;
    @Nullable
    private String heartRateRecordedDate;
    private int hrv;
    @Nullable
    private String hrvRecordedDate;
    private int lightSleep;
    @Nullable
    private String startSleepDate;
    @Nullable
    private String stepsRecordedDate;
    private int stressLevel;
    @Nullable
    private String stressLevelRecordedDate;
    private double surfaceTemperature;
    @Nullable
    private String surfaceTemperatureRecordedDate;
    @Nullable
    private String tagId;
    private int totalSleep;
    private int totalSteps;

    public final int getAwake() {
        return this.awake;
    }

    public final double getBodyTemperature() {
        return this.bodyTemperature;
    }

    @Nullable
    public final String getBodyTemperatureRecordedDate() {
        return this.bodyTemperatureRecordedDate;
    }

    @Nullable
    public final String getBpRecordedDate() {
        return this.bpRecordedDate;
    }

    public final int getBpSystolic() {
        return this.bpSystolic;
    }

    public final int getByDiastolic() {
        return this.byDiastolic;
    }

    public final int getCal() {
        return this.cal;
    }

    public final int getDeepSleep() {
        return this.deepSleep;
    }

    public final int getDistance() {
        return this.distance;
    }

    @Nullable
    public final String getEndSleepDate() {
        return this.endSleepDate;
    }

    public final double getFatigueLevel() {
        return this.fatigueLevel;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    @Nullable
    public final String getHeartRateRecordedDate() {
        return this.heartRateRecordedDate;
    }

    public final int getHrv() {
        return this.hrv;
    }

    @Nullable
    public final String getHrvRecordedDate() {
        return this.hrvRecordedDate;
    }

    @NotNull
    public final LiveUpdateData getInstance() {
        if (this.INSTANCE == null) {
            this.INSTANCE = new LiveUpdateData();
        }
        LiveUpdateData liveUpdateData = this.INSTANCE;
        Intrinsics.checkNotNull(liveUpdateData, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.model.LiveUpdateData");
        return liveUpdateData;
    }

    public final int getLightSleep() {
        return this.lightSleep;
    }

    @Nullable
    public final String getStartSleepDate() {
        return this.startSleepDate;
    }

    @Nullable
    public final String getStepsRecordedDate() {
        return this.stepsRecordedDate;
    }

    public final int getStressLevel() {
        return this.stressLevel;
    }

    @Nullable
    public final String getStressLevelRecordedDate() {
        return this.stressLevelRecordedDate;
    }

    public final double getSurfaceTemperature() {
        return this.surfaceTemperature;
    }

    @Nullable
    public final String getSurfaceTemperatureRecordedDate() {
        return this.surfaceTemperatureRecordedDate;
    }

    @Nullable
    public final String getTagId() {
        return this.tagId;
    }

    public final int getTotalSleep() {
        return this.totalSleep;
    }

    public final int getTotalSteps() {
        return this.totalSteps;
    }

    public final void setAwake(int i) {
        this.awake = i;
    }

    public final void setBodyTemperature(double d) {
        this.bodyTemperature = d;
    }

    public final void setBodyTemperatureRecordedDate(@Nullable String str) {
        this.bodyTemperatureRecordedDate = str;
    }

    public final void setBpRecordedDate(@Nullable String str) {
        this.bpRecordedDate = str;
    }

    public final void setBpSystolic(int i) {
        this.bpSystolic = i;
    }

    public final void setByDiastolic(int i) {
        this.byDiastolic = i;
    }

    public final void setCal(int i) {
        this.cal = i;
    }

    public final void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final void setEndSleepDate(@Nullable String str) {
        this.endSleepDate = str;
    }

    public final void setFatigueLevel(double d) {
        this.fatigueLevel = d;
    }

    public final void setHeartRate(int i) {
        this.heartRate = i;
    }

    public final void setHeartRateRecordedDate(@Nullable String str) {
        this.heartRateRecordedDate = str;
    }

    public final void setHrv(int i) {
        this.hrv = i;
    }

    public final void setHrvRecordedDate(@Nullable String str) {
        this.hrvRecordedDate = str;
    }

    public final void setLightSleep(int i) {
        this.lightSleep = i;
    }

    public final void setStartSleepDate(@Nullable String str) {
        this.startSleepDate = str;
    }

    public final void setStepsRecordedDate(@Nullable String str) {
        this.stepsRecordedDate = str;
    }

    public final void setStressLevel(int i) {
        this.stressLevel = i;
    }

    public final void setStressLevelRecordedDate(@Nullable String str) {
        this.stressLevelRecordedDate = str;
    }

    public final void setSurfaceTemperature(double d) {
        this.surfaceTemperature = d;
    }

    public final void setSurfaceTemperatureRecordedDate(@Nullable String str) {
        this.surfaceTemperatureRecordedDate = str;
    }

    public final void setTagId(@Nullable String str) {
        this.tagId = str;
    }

    public final void setTotalSleep(int i) {
        this.totalSleep = i;
    }

    public final void setTotalSteps(int i) {
        this.totalSteps = i;
    }
}
