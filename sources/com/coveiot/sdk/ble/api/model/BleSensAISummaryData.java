package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleSensAISummaryData {
    private int activityNum;
    private int activityType;
    private int avgArmSpeed;
    private int avgHandSpeed;
    private int avgHr;
    private int bowlingType;
    private long duration;
    private int goalCompletionPct;
    private int goalType;
    private int hand;
    private int hitPct;
    private int maxArmSpeed;
    private int maxHandSpeed;
    private int maxHr;
    private int minArmSpeed;
    private int played;
    private long sessionId;
    private int targetGoalValue;
    private long timeStamp;
    private int totalBallsBowled;
    private double totalCalories;
    private int totalSteps;
    private int totalSwings;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private int activityNum;
        private int activityType;
        private int avgArmSpeed;
        private int avgHandSpeed;
        private int avgHr;
        private int bowlingType;
        private long duration;
        private int goalCompletionPct;
        private int goalType;
        private int hand;
        private int hitPct;
        private int maxArmSpeed;
        private int maxHandSpeed;
        private int maxHr;
        private int minArmSpeed;
        private int played;
        private long sessionId;
        private int targetGoalValue;
        private long timeStamp;
        private int totalBallsBowled;
        private double totalCalories;
        private int totalSteps;
        private int totalSwings;

        public Builder() {
        }

        public BleSensAISummaryData build() {
            BleSensAISummaryData bleSensAISummaryData = new BleSensAISummaryData();
            bleSensAISummaryData.timeStamp = this.timeStamp;
            bleSensAISummaryData.activityNum = this.activityNum;
            bleSensAISummaryData.sessionId = this.sessionId;
            bleSensAISummaryData.activityType = this.activityType;
            bleSensAISummaryData.duration = this.duration;
            bleSensAISummaryData.totalSteps = this.totalSteps;
            bleSensAISummaryData.totalCalories = this.totalCalories;
            bleSensAISummaryData.hand = this.hand;
            bleSensAISummaryData.goalType = this.goalType;
            bleSensAISummaryData.targetGoalValue = this.targetGoalValue;
            bleSensAISummaryData.goalCompletionPct = this.goalCompletionPct;
            bleSensAISummaryData.totalSwings = this.totalSwings;
            bleSensAISummaryData.played = this.played;
            bleSensAISummaryData.hitPct = this.hitPct;
            bleSensAISummaryData.maxHandSpeed = this.maxHandSpeed;
            bleSensAISummaryData.avgHandSpeed = this.avgHandSpeed;
            bleSensAISummaryData.avgHr = this.avgHr;
            bleSensAISummaryData.maxHr = this.maxHr;
            bleSensAISummaryData.bowlingType = this.bowlingType;
            bleSensAISummaryData.totalBallsBowled = this.totalBallsBowled;
            bleSensAISummaryData.maxArmSpeed = this.maxArmSpeed;
            bleSensAISummaryData.minArmSpeed = this.minArmSpeed;
            bleSensAISummaryData.avgArmSpeed = this.avgArmSpeed;
            return bleSensAISummaryData;
        }

        public Builder(long j, int i, long j2, int i2, long j3, int i3, double d, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19) {
            this.timeStamp = j;
            this.activityNum = i;
            this.sessionId = j2;
            this.activityType = i2;
            this.duration = j3;
            this.totalSteps = i3;
            this.totalCalories = d;
            this.hand = i4;
            this.goalType = i5;
            this.targetGoalValue = i6;
            this.goalCompletionPct = i7;
            this.totalSwings = i8;
            this.played = i9;
            this.hitPct = i10;
            this.maxHandSpeed = i11;
            this.avgHandSpeed = i12;
            this.avgHr = i13;
            this.maxHr = i14;
            this.bowlingType = i15;
            this.totalBallsBowled = i16;
            this.maxArmSpeed = i17;
            this.minArmSpeed = i18;
            this.avgArmSpeed = i19;
        }
    }

    public int getActivityNum() {
        return this.activityNum;
    }

    public int getActivityType() {
        return this.activityType;
    }

    public int getAvgArmSpeed() {
        return this.avgArmSpeed;
    }

    public int getAvgHandSpeed() {
        return this.avgHandSpeed;
    }

    public int getAvgHr() {
        return this.avgHr;
    }

    public int getBowlingType() {
        return this.bowlingType;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getGoalCompletionPct() {
        return this.goalCompletionPct;
    }

    public int getGoalType() {
        return this.goalType;
    }

    public int getHand() {
        return this.hand;
    }

    public int getHitPct() {
        return this.hitPct;
    }

    public int getMaxArmSpeed() {
        return this.maxArmSpeed;
    }

    public int getMaxHandSpeed() {
        return this.maxHandSpeed;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMinArmSpeed() {
        return this.minArmSpeed;
    }

    public int getPlayed() {
        return this.played;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public int getTargetGoalValue() {
        return this.targetGoalValue;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public int getTotalBallsBowled() {
        return this.totalBallsBowled;
    }

    public double getTotalCalories() {
        return this.totalCalories;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public int getTotalSwings() {
        return this.totalSwings;
    }

    public String toString() {
        return "BleSensAISummaryData{timeStamp=" + this.timeStamp + ", activityNum=" + this.activityNum + ", sessionId=" + this.sessionId + ", activityType=" + this.activityType + ", duration=" + this.duration + ", totalSteps=" + this.totalSteps + ", totalCalories=" + this.totalCalories + ", hand=" + this.hand + ", goalType=" + this.goalType + ", targetGoalValue=" + this.targetGoalValue + ", goalCompletionPct=" + this.goalCompletionPct + ", totalSwings=" + this.totalSwings + ", played=" + this.played + ", hitPct=" + this.hitPct + ", maxHandSpeed=" + this.maxHandSpeed + ", avgHandSpeed=" + this.avgHandSpeed + ", avgHr=" + this.avgHr + ", maxHr=" + this.maxHr + ", bowlingType=" + this.bowlingType + ", totalBallsBowled=" + this.totalBallsBowled + ", maxArmSpeed=" + this.maxArmSpeed + ", minArmSpeed=" + this.minArmSpeed + ", avgArmSpeed=" + this.avgArmSpeed + '}';
    }
}
