package com.coveiot.coveaccess.energyscore.model;
/* loaded from: classes8.dex */
public class SleepSummary {
    public Summary summary;

    /* loaded from: classes8.dex */
    public static class Summary {
        public String sleepEndTime;
        public int sleepScore;
        public String sleepStartTime;

        public String getSleepEndTime() {
            return this.sleepEndTime;
        }

        public int getSleepScore() {
            return this.sleepScore;
        }

        public String getSleepStartTime() {
            return this.sleepStartTime;
        }

        public void setSleepEndTime(String str) {
            this.sleepEndTime = str;
        }

        public void setSleepScore(int i) {
            this.sleepScore = i;
        }

        public void setSleepStartTime(String str) {
            this.sleepStartTime = str;
        }
    }

    public Summary getSummary() {
        return this.summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}
