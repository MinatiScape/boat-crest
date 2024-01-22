package com.coveiot.android.leonardo.dashboard.socialshare.model;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessShareData extends ShareData {
    @Nullable
    private String calories;
    @Nullable
    private String distance;
    @Nullable
    private String distanceUnit;
    @Nullable
    private ArrayList<String> steps;
    @Nullable
    private String target;
    @Nullable
    private String yesterdayTarget;

    @Nullable
    public final String getCalories() {
        return this.calories;
    }

    @Nullable
    public final String getDistance() {
        return this.distance;
    }

    @Nullable
    public final String getDistanceUnit() {
        return this.distanceUnit;
    }

    @Nullable
    public final ArrayList<String> getSteps() {
        return this.steps;
    }

    @Nullable
    public final String getTarget() {
        return this.target;
    }

    @Nullable
    public final String getYesterdayTarget() {
        return this.yesterdayTarget;
    }

    public final void setCalories(@Nullable String str) {
        this.calories = str;
    }

    public final void setDistance(@Nullable String str) {
        this.distance = str;
    }

    public final void setDistanceUnit(@Nullable String str) {
        this.distanceUnit = str;
    }

    public final void setSteps(@Nullable ArrayList<String> arrayList) {
        this.steps = arrayList;
    }

    public final void setTarget(@Nullable String str) {
        this.target = str;
    }

    public final void setYesterdayTarget(@Nullable String str) {
        this.yesterdayTarget = str;
    }
}
