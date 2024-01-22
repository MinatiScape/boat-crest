package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.activitysession.ActivitiesSampleDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GenericActivitySessionData extends ActivitiesSampleDetails {
    @SerializedName("avgHandSpeed")
    @Expose
    private Double avgHandSpeed;
    @SerializedName("avgJumpFreq")
    @Expose
    private Integer avgJumpFreq;
    @SerializedName("avgStepFreq")
    @Expose
    private Double avgStepFreq;
    @SerializedName("avgStrideLength")
    @Expose
    private Double avgStrideLength;
    @SerializedName("avgStrokeFreq")
    @Expose
    private Integer avgStrokeFreq;
    @SerializedName("avgSwolf")
    @Expose
    private Integer avgSwolf;
    @SerializedName("bowlingType")
    @Expose
    private String bowlingType;
    @SerializedName("hitPercentage")
    @Expose
    private Integer hitPercentage;
    @SerializedName("maxHandSpeed")
    @Expose
    private Double maxHandSpeed;
    @SerializedName("maxPace")
    @Expose
    private Integer maxPace;
    @SerializedName("maxStepFreq")
    @Expose
    private Integer maxStepFreq;
    @SerializedName("maxStrideLength")
    @Expose
    private Integer maxStrideLength;
    @SerializedName("minHandSpeed")
    @Expose
    private Double minHandSpeed;
    @SerializedName("minPace")
    @Expose
    private Integer minPace;
    @SerializedName("playingHand")
    @Expose
    private String playingHand;
    @SerializedName("poolLength")
    @Expose
    private Integer poolLength;
    @SerializedName("recoveryDate")
    @Expose
    private String recoveryDate;
    @SerializedName("swimmingStyle")
    @Expose
    private String swimmingStyle;
    @SerializedName("targetAchievedPct")
    @Expose
    private Integer targetAchievedPct;
    @SerializedName("totalBallsBowled")
    @Expose
    private Integer totalBallsBowled;
    @SerializedName("totalHits")
    @Expose
    private Integer totalHits;
    @SerializedName("totalJumps")
    @Expose
    private Integer totalJumps;
    @SerializedName("totalLaps")
    @Expose
    private Integer totalLaps;
    @SerializedName("totalStrokes")
    @Expose
    private int totalStrokes;
    @SerializedName("totalSwings")
    @Expose
    private Integer totalSwings;
    @SerializedName("trainingEffect")
    @Expose
    private Double trainingEffect;
    @SerializedName("vo2Max")
    @Expose
    private Integer vo2Max;

    public Double getAvgHandSpeed() {
        return this.avgHandSpeed;
    }

    public Integer getAvgJumpFreq() {
        return this.avgJumpFreq;
    }

    public Double getAvgStepFreq() {
        return this.avgStepFreq;
    }

    public Double getAvgStrideLength() {
        return this.avgStrideLength;
    }

    public Integer getAvgStrokeFreq() {
        return this.avgStrokeFreq;
    }

    public Integer getAvgSwolf() {
        return this.avgSwolf;
    }

    public String getBowlingType() {
        return this.bowlingType;
    }

    public Integer getHitPercentage() {
        return this.hitPercentage;
    }

    public Double getMaxHandSpeed() {
        return this.maxHandSpeed;
    }

    public Integer getMaxPace() {
        return this.maxPace;
    }

    public Integer getMaxStepFreq() {
        return this.maxStepFreq;
    }

    public Integer getMaxStrideLength() {
        return this.maxStrideLength;
    }

    public Double getMinHandSpeed() {
        return this.minHandSpeed;
    }

    public Integer getMinPace() {
        return this.minPace;
    }

    public String getPlayingHand() {
        return this.playingHand;
    }

    public Integer getPoolLength() {
        return this.poolLength;
    }

    public String getRecoveryDate() {
        return this.recoveryDate;
    }

    public String getSwimmingStyle() {
        return this.swimmingStyle;
    }

    public Integer getTargetAchievedPct() {
        return this.targetAchievedPct;
    }

    public Integer getTotalBallsBowled() {
        return this.totalBallsBowled;
    }

    public Integer getTotalHits() {
        return this.totalHits;
    }

    public Integer getTotalJumps() {
        return this.totalJumps;
    }

    public Integer getTotalLaps() {
        return this.totalLaps;
    }

    public int getTotalStrokes() {
        return this.totalStrokes;
    }

    public Integer getTotalSwings() {
        return this.totalSwings;
    }

    public Double getTrainingEffect() {
        return this.trainingEffect;
    }

    public Integer getVo2Max() {
        return this.vo2Max;
    }

    public void setAvgHandSpeed(Double d) {
        this.avgHandSpeed = d;
    }

    public void setAvgJumpFreq(Integer num) {
        this.avgJumpFreq = num;
    }

    public void setAvgStepFreq(Double d) {
        this.avgStepFreq = d;
    }

    public void setAvgStrideLength(Double d) {
        this.avgStrideLength = d;
    }

    public void setAvgStrokeFreq(Integer num) {
        this.avgStrokeFreq = num;
    }

    public void setAvgSwolf(Integer num) {
        this.avgSwolf = num;
    }

    public void setBowlingType(String str) {
        this.bowlingType = str;
    }

    public void setHitPercentage(Integer num) {
        this.hitPercentage = num;
    }

    public void setMaxHandSpeed(Double d) {
        this.maxHandSpeed = d;
    }

    public void setMaxPace(Integer num) {
        this.maxPace = num;
    }

    public void setMaxStepFreq(Integer num) {
        this.maxStepFreq = num;
    }

    public void setMaxStrideLength(Integer num) {
        this.maxStrideLength = num;
    }

    public void setMinHandSpeed(Double d) {
        this.minHandSpeed = d;
    }

    public void setMinPace(Integer num) {
        this.minPace = num;
    }

    public void setPlayingHand(String str) {
        this.playingHand = str;
    }

    public void setPoolLength(Integer num) {
        this.poolLength = num;
    }

    public void setRecoveryDate(String str) {
        this.recoveryDate = str;
    }

    public void setSwimmingStyle(String str) {
        this.swimmingStyle = str;
    }

    public void setTargetAchievedPct(Integer num) {
        this.targetAchievedPct = num;
    }

    public void setTotalBallsBowled(Integer num) {
        this.totalBallsBowled = num;
    }

    public void setTotalHits(Integer num) {
        this.totalHits = num;
    }

    public void setTotalJumps(Integer num) {
        this.totalJumps = num;
    }

    public void setTotalLaps(Integer num) {
        this.totalLaps = num;
    }

    public void setTotalStrokes(int i) {
        this.totalStrokes = i;
    }

    public void setTotalSwings(Integer num) {
        this.totalSwings = num;
    }

    public void setTrainingEffect(Double d) {
        this.trainingEffect = d;
    }

    public void setVo2Max(Integer num) {
        this.vo2Max = num;
    }
}
