package com.coveiot.android.leonardo.sensai.model;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SummaryShareData implements Serializable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String OVERALL_STATS = "overall_stats";
    @NotNull
    public static final String SESSION_INSIGHTS = "session_insights";
    @NotNull
    public static final String SHARE_DATA = "share_data";
    @NotNull
    public static final String SHARE_TYPE = "share_type";
    private int activityType;
    private int avgArmSpeed;
    private int avgHandSpeed;
    private int avgHeartRate;
    private int bowlingType;
    private float calories;
    private int duration;
    private int fastestBall;
    private int handType;
    private int hitPercentage;
    private int maxArmSpeed;
    private int maxHandSpeed;
    private int minArmSpeed;
    private int played;
    private int slowestBall;
    private int steps;
    private long timeStamp;
    private int totalBallsBowled;
    private int totalSessions;
    private int totalShots;
    private int totalTargetAchieved;
    @NotNull
    private String shareType = SHARE_TYPE;
    @NotNull
    private String date = "";

    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final int getActivityType() {
        return this.activityType;
    }

    public final int getAvgArmSpeed() {
        return this.avgArmSpeed;
    }

    public final int getAvgHandSpeed() {
        return this.avgHandSpeed;
    }

    public final int getAvgHeartRate() {
        return this.avgHeartRate;
    }

    public final int getBowlingType() {
        return this.bowlingType;
    }

    public final float getCalories() {
        return this.calories;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final int getFastestBall() {
        return this.fastestBall;
    }

    public final int getHandType() {
        return this.handType;
    }

    public final int getHitPercentage() {
        return this.hitPercentage;
    }

    public final int getMaxArmSpeed() {
        return this.maxArmSpeed;
    }

    public final int getMaxHandSpeed() {
        return this.maxHandSpeed;
    }

    public final int getMinArmSpeed() {
        return this.minArmSpeed;
    }

    public final int getPlayed() {
        return this.played;
    }

    @NotNull
    public final String getShareType() {
        return this.shareType;
    }

    public final int getSlowestBall() {
        return this.slowestBall;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final long getTimeStamp() {
        return this.timeStamp;
    }

    public final int getTotalBallsBowled() {
        return this.totalBallsBowled;
    }

    public final int getTotalSessions() {
        return this.totalSessions;
    }

    public final int getTotalShots() {
        return this.totalShots;
    }

    public final int getTotalTargetAchieved() {
        return this.totalTargetAchieved;
    }

    public final void setActivityType(int i) {
        this.activityType = i;
    }

    public final void setAvgArmSpeed(int i) {
        this.avgArmSpeed = i;
    }

    public final void setAvgHandSpeed(int i) {
        this.avgHandSpeed = i;
    }

    public final void setAvgHeartRate(int i) {
        this.avgHeartRate = i;
    }

    public final void setBowlingType(int i) {
        this.bowlingType = i;
    }

    public final void setCalories(float f) {
        this.calories = f;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final void setFastestBall(int i) {
        this.fastestBall = i;
    }

    public final void setHandType(int i) {
        this.handType = i;
    }

    public final void setHitPercentage(int i) {
        this.hitPercentage = i;
    }

    public final void setMaxArmSpeed(int i) {
        this.maxArmSpeed = i;
    }

    public final void setMaxHandSpeed(int i) {
        this.maxHandSpeed = i;
    }

    public final void setMinArmSpeed(int i) {
        this.minArmSpeed = i;
    }

    public final void setPlayed(int i) {
        this.played = i;
    }

    public final void setShareType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shareType = str;
    }

    public final void setSlowestBall(int i) {
        this.slowestBall = i;
    }

    public final void setSteps(int i) {
        this.steps = i;
    }

    public final void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public final void setTotalBallsBowled(int i) {
        this.totalBallsBowled = i;
    }

    public final void setTotalSessions(int i) {
        this.totalSessions = i;
    }

    public final void setTotalShots(int i) {
        this.totalShots = i;
    }

    public final void setTotalTargetAchieved(int i) {
        this.totalTargetAchieved = i;
    }
}
