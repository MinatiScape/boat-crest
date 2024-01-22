package com.coveiot.android.leonardo.dashboard.health.model;

import com.coveiot.android.leonardo.more.models.GoalsModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ShareData implements Serializable {
    @Nullable
    private String avgData;
    @Nullable
    private String avg_ambient_sound;
    @Nullable
    private String awake;
    @Nullable
    private String cal;
    @Nullable
    private String cal_unit;
    @Nullable
    private String data;
    @Nullable
    private String dataType;
    @Nullable
    private String deepSleep;
    @Nullable
    private String distance;
    @Nullable
    private String distance_unit;
    @Nullable
    private String dwmValue;
    @Nullable
    private String ecgURL;
    private int endEnergyScore;
    @Nullable
    private final String goalAchieved;
    @Nullable
    private String graphType;
    @Nullable
    private String heartRate;
    @Nullable
    private String high_temp;
    @Nullable
    private String lightSleep;
    @Nullable
    private String low_temp;
    @Nullable
    private String maxDate;
    private int maxSleepScore;
    @Nullable
    private String max_bpm;
    @Nullable
    private String minDate;
    private int minSleepScore;
    @Nullable
    private String min_bpm;
    @Nullable
    private String remSleep;
    private int sleepScore;
    @Nullable
    private String spo2_avg;
    @Nullable
    private String spo2_max;
    @Nullable
    private String spo2_min;
    private int startEnergyScore;
    @Nullable
    private String stress_max;
    @Nullable
    private String stress_min;
    private int target;
    @Nullable
    private String totalSleep;
    @Nullable
    private String totalSleepFormatted;
    @Nullable
    private String totalTime;
    @Nullable
    private String totalValueTitle;
    @Nullable
    private String weekMonthTotalSleep;
    @NotNull
    private StressType stressType = StressType.MANUAL;
    @NotNull
    private List<GoalsModel> goalsModel = new ArrayList();

    /* loaded from: classes3.dex */
    public enum StressType {
        PERIODIC,
        MANUAL
    }

    @Nullable
    public final String getAvgData() {
        return this.avgData;
    }

    @Nullable
    public final String getAvg_ambient_sound() {
        return this.avg_ambient_sound;
    }

    @Nullable
    public final String getAwake() {
        return this.awake;
    }

    @Nullable
    public final String getCal() {
        return this.cal;
    }

    @Nullable
    public final String getCal_unit() {
        return this.cal_unit;
    }

    @Nullable
    public final String getData() {
        return this.data;
    }

    @Nullable
    public final String getDataType() {
        return this.dataType;
    }

    @Nullable
    public final String getDeepSleep() {
        return this.deepSleep;
    }

    @Nullable
    public final String getDistance() {
        return this.distance;
    }

    @Nullable
    public final String getDistance_unit() {
        return this.distance_unit;
    }

    @Nullable
    public final String getDwmValue() {
        return this.dwmValue;
    }

    @Nullable
    public final String getEcgURL() {
        return this.ecgURL;
    }

    public final int getEndEnergyScore() {
        return this.endEnergyScore;
    }

    @Nullable
    public final String getGoalAchieved() {
        return this.goalAchieved;
    }

    @NotNull
    public final List<GoalsModel> getGoalsModel() {
        return this.goalsModel;
    }

    @Nullable
    public final String getGraphType() {
        return this.graphType;
    }

    @Nullable
    public final String getHeartRate() {
        return this.heartRate;
    }

    @Nullable
    public final String getHigh_temp() {
        return this.high_temp;
    }

    @Nullable
    public final String getLightSleep() {
        return this.lightSleep;
    }

    @Nullable
    public final String getLow_temp() {
        return this.low_temp;
    }

    @Nullable
    public final String getMaxDate() {
        return this.maxDate;
    }

    public final int getMaxSleepScore() {
        return this.maxSleepScore;
    }

    @Nullable
    public final String getMax_bpm() {
        return this.max_bpm;
    }

    @Nullable
    public final String getMinDate() {
        return this.minDate;
    }

    public final int getMinSleepScore() {
        return this.minSleepScore;
    }

    @Nullable
    public final String getMin_bpm() {
        return this.min_bpm;
    }

    @Nullable
    public final String getRemSleep() {
        return this.remSleep;
    }

    public final int getSleepScore() {
        return this.sleepScore;
    }

    @Nullable
    public final String getSpo2_avg() {
        return this.spo2_avg;
    }

    @Nullable
    public final String getSpo2_max() {
        return this.spo2_max;
    }

    @Nullable
    public final String getSpo2_min() {
        return this.spo2_min;
    }

    public final int getStartEnergyScore() {
        return this.startEnergyScore;
    }

    @NotNull
    public final StressType getStressType() {
        return this.stressType;
    }

    @Nullable
    public final String getStress_max() {
        return this.stress_max;
    }

    @Nullable
    public final String getStress_min() {
        return this.stress_min;
    }

    public final int getTarget() {
        return this.target;
    }

    @Nullable
    public final String getTotalSleep() {
        return this.totalSleep;
    }

    @Nullable
    public final String getTotalSleepFormatted() {
        return this.totalSleepFormatted;
    }

    @Nullable
    public final String getTotalTime() {
        return this.totalTime;
    }

    @Nullable
    public final String getTotalValueTitle() {
        return this.totalValueTitle;
    }

    @Nullable
    public final String getWeekMonthTotalSleep() {
        return this.weekMonthTotalSleep;
    }

    public final void setAvgData(@Nullable String str) {
        this.avgData = str;
    }

    public final void setAvg_ambient_sound(@Nullable String str) {
        this.avg_ambient_sound = str;
    }

    public final void setAwake(@Nullable String str) {
        this.awake = str;
    }

    public final void setCal(@Nullable String str) {
        this.cal = str;
    }

    public final void setCal_unit(@Nullable String str) {
        this.cal_unit = str;
    }

    public final void setData(@Nullable String str) {
        this.data = str;
    }

    public final void setDataType(@Nullable String str) {
        this.dataType = str;
    }

    public final void setDeepSleep(@Nullable String str) {
        this.deepSleep = str;
    }

    public final void setDistance(@Nullable String str) {
        this.distance = str;
    }

    public final void setDistance_unit(@Nullable String str) {
        this.distance_unit = str;
    }

    public final void setDwmValue(@Nullable String str) {
        this.dwmValue = str;
    }

    public final void setEcgURL(@Nullable String str) {
        this.ecgURL = str;
    }

    public final void setEndEnergyScore(int i) {
        this.endEnergyScore = i;
    }

    public final void setGoalsModel(@NotNull List<GoalsModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.goalsModel = list;
    }

    public final void setGraphType(@Nullable String str) {
        this.graphType = str;
    }

    public final void setHeartRate(@Nullable String str) {
        this.heartRate = str;
    }

    public final void setHigh_temp(@Nullable String str) {
        this.high_temp = str;
    }

    public final void setLightSleep(@Nullable String str) {
        this.lightSleep = str;
    }

    public final void setLow_temp(@Nullable String str) {
        this.low_temp = str;
    }

    public final void setMaxDate(@Nullable String str) {
        this.maxDate = str;
    }

    public final void setMaxSleepScore(int i) {
        this.maxSleepScore = i;
    }

    public final void setMax_bpm(@Nullable String str) {
        this.max_bpm = str;
    }

    public final void setMinDate(@Nullable String str) {
        this.minDate = str;
    }

    public final void setMinSleepScore(int i) {
        this.minSleepScore = i;
    }

    public final void setMin_bpm(@Nullable String str) {
        this.min_bpm = str;
    }

    public final void setRemSleep(@Nullable String str) {
        this.remSleep = str;
    }

    public final void setSleepScore(int i) {
        this.sleepScore = i;
    }

    public final void setSpo2_avg(@Nullable String str) {
        this.spo2_avg = str;
    }

    public final void setSpo2_max(@Nullable String str) {
        this.spo2_max = str;
    }

    public final void setSpo2_min(@Nullable String str) {
        this.spo2_min = str;
    }

    public final void setStartEnergyScore(int i) {
        this.startEnergyScore = i;
    }

    public final void setStressType(@NotNull StressType stressType) {
        Intrinsics.checkNotNullParameter(stressType, "<set-?>");
        this.stressType = stressType;
    }

    public final void setStress_max(@Nullable String str) {
        this.stress_max = str;
    }

    public final void setStress_min(@Nullable String str) {
        this.stress_min = str;
    }

    public final void setTarget(int i) {
        this.target = i;
    }

    public final void setTotalSleep(@Nullable String str) {
        this.totalSleep = str;
    }

    public final void setTotalSleepFormatted(@Nullable String str) {
        this.totalSleepFormatted = str;
    }

    public final void setTotalTime(@Nullable String str) {
        this.totalTime = str;
    }

    public final void setTotalValueTitle(@Nullable String str) {
        this.totalValueTitle = str;
    }

    public final void setWeekMonthTotalSleep(@Nullable String str) {
        this.weekMonthTotalSleep = str;
    }
}
