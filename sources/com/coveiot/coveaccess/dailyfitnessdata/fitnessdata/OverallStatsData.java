package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class OverallStatsData {
    @SerializedName("totalDuration")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private int f6462a;
    @SerializedName("totalSessions")
    @Expose
    private int b;
    @SerializedName("totalTargetAchieved")
    @Expose
    private int c;
    @SerializedName("totalSwings")
    @Expose
    private int d;
    @SerializedName("totalHits")
    @Expose
    private int e;
    @SerializedName("percentile")
    @Expose
    private int f;
    @SerializedName("avgHandSpeed")
    @Expose
    private double g;
    @SerializedName("maxHandSpeed")
    @Expose
    private double h;
    @SerializedName("totalBallsBowled")
    @Expose
    private int i;
    @SerializedName("handSpeed")
    @Expose
    private List<OverallStatsHandSpeed> j;
    @SerializedName("hitPercentage")
    @Expose
    private List<OverallStatsHitPercentage> k;

    public double getAvgHandSpeed() {
        return this.g;
    }

    public List<OverallStatsHandSpeed> getHandSpeedList() {
        return this.j;
    }

    public List<OverallStatsHitPercentage> getHitPercentageList() {
        return this.k;
    }

    public double getMaxHandSpeed() {
        return this.h;
    }

    public int getPercentile() {
        return this.f;
    }

    public int getTotalBallsBowled() {
        return this.i;
    }

    public int getTotalDuration() {
        return this.f6462a;
    }

    public int getTotalHits() {
        return this.e;
    }

    public int getTotalSessions() {
        return this.b;
    }

    public int getTotalSwings() {
        return this.d;
    }

    public int getTotalTargetAchieved() {
        return this.c;
    }

    public void setAvgHandSpeed(double d) {
        this.g = d;
    }

    public void setHandSpeedList(List<OverallStatsHandSpeed> list) {
        this.j = list;
    }

    public void setHitPercentageList(List<OverallStatsHitPercentage> list) {
        this.k = list;
    }

    public void setMaxHandSpeed(double d) {
        this.h = d;
    }

    public void setPercentile(int i) {
        this.f = i;
    }

    public void setTotalBallsBowled(int i) {
        this.i = i;
    }

    public void setTotalDuration(int i) {
        this.f6462a = i;
    }

    public void setTotalHits(int i) {
        this.e = i;
    }

    public void setTotalSessions(int i) {
        this.b = i;
    }

    public void setTotalSwings(int i) {
        this.d = i;
    }

    public void setTotalTargetAchieved(int i) {
        this.c = i;
    }

    public String toString() {
        return "OverallStatsData{totalDuration=" + this.f6462a + ", totalSessions=" + this.b + ", totalTargetAchieved=" + this.c + ", totalSwings=" + this.d + ", totalHits=" + this.e + ", percentile=" + this.f + ", avgHandSpeed=" + this.g + ", maxHandSpeed=" + this.h + ", handSpeedList=" + this.j + ", hitPercentageList=" + this.k + '}';
    }
}
