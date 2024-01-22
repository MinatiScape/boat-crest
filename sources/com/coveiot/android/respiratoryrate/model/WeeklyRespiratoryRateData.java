package com.coveiot.android.respiratoryrate.model;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class WeeklyRespiratoryRateData {
    @Nullable
    private Integer avg;
    @Nullable
    private String endDate;
    @Nullable
    private String macAddress;
    @Nullable
    private Integer max;
    @Nullable
    private Integer min;
    @Nullable
    private String startDate;
    @Nullable
    private String week;
    @Nullable
    private String year;

    @Nullable
    public final Integer getAvg() {
        return this.avg;
    }

    @Nullable
    public final String getEndDate() {
        return this.endDate;
    }

    @Nullable
    public final String getMacAddress() {
        return this.macAddress;
    }

    @Nullable
    public final Integer getMax() {
        return this.max;
    }

    @Nullable
    public final Integer getMin() {
        return this.min;
    }

    @Nullable
    public final String getStartDate() {
        return this.startDate;
    }

    @Nullable
    public final String getWeek() {
        return this.week;
    }

    @Nullable
    public final String getYear() {
        return this.year;
    }

    public final void setAvg(@Nullable Integer num) {
        this.avg = num;
    }

    public final void setEndDate(@Nullable String str) {
        this.endDate = str;
    }

    public final void setMacAddress(@Nullable String str) {
        this.macAddress = str;
    }

    public final void setMax(@Nullable Integer num) {
        this.max = num;
    }

    public final void setMin(@Nullable Integer num) {
        this.min = num;
    }

    public final void setStartDate(@Nullable String str) {
        this.startDate = str;
    }

    public final void setWeek(@Nullable String str) {
        this.week = str;
    }

    public final void setYear(@Nullable String str) {
        this.year = str;
    }
}
