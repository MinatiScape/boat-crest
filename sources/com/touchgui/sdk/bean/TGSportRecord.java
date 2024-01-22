package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSportRecord {
    @Deprecated
    public static final int BASKETBALL = 21;
    @Deprecated
    public static final int CRICKET = 75;
    @Deprecated
    public static final int ELLIPTICAL = 56;
    @Deprecated
    public static final int FOOTBALL = 76;
    @Deprecated
    public static final int FREE_WORKOUT = 58;
    @Deprecated
    public static final int HIKING = 4;
    @Deprecated
    public static final int HIKING_NO_GPS = 5;
    @Deprecated
    public static final int INDOOR_RUN = 49;
    @Deprecated
    public static final int INDOOR_WALK = 53;
    @Deprecated
    public static final int OUTDOOR_CYCLING = 50;
    @Deprecated
    public static final int OUTDOOR_CYCLING_NO_GPS = 3;
    @Deprecated
    public static final int OUTDOOR_RUN = 48;
    @Deprecated
    public static final int OUTDOOR_RUN_NO_GPS = 1;
    @Deprecated
    public static final int OUTDOOR_WALK = 52;
    @Deprecated
    public static final int OUTDOOR_WALK_NO_GPS = 2;
    @Deprecated
    public static final int ROWING = 57;
    @Deprecated
    public static final int SPINNING = 10;
    @Deprecated
    public static final int STRENGTH_TRAINING = 8;
    @Deprecated
    public static final int SWIMMING = 54;
    @Deprecated
    public static final int YOGA = 18;
    private int aerobicExercise;
    private int anaerobicExercise;
    private int avgHr;
    private int avgPaceSecs;
    private int avgSkipFrq;
    private int avgSpeed;
    private int avgStrideFrequency;
    private int avgStrideLength;
    private int boxingNum;
    private int calories;
    private Date date;
    private int distance;
    private int dumbbellNum;
    private int duration;
    private int extremeExercise;
    private int fatBurning;
    @Nullable
    private List<HeartRateItem> hearts;
    private int maxHr;
    private int maxPace;
    private int maxSpeed;
    private int minHr;
    private int minPace;
    private int minSpeed;
    private int paddleFrq;
    private int paddleNum;
    private int relax;
    private int skipNum;
    private int step;
    private int type;
    private int warmUp;

    /* loaded from: classes12.dex */
    public static class HeartRateItem {
        private int heartrate;

        public int getHeartrate() {
            return this.heartrate;
        }

        public void setHeartrate(int i) {
            this.heartrate = i;
        }
    }

    public int getAerobicExercise() {
        return this.aerobicExercise;
    }

    public int getAnaerobicExercise() {
        return this.anaerobicExercise;
    }

    public int getAvgHr() {
        return this.avgHr;
    }

    public int getAvgPaceSecs() {
        return this.avgPaceSecs;
    }

    public int getAvgSkipFrq() {
        return this.avgSkipFrq;
    }

    public int getAvgSpeed() {
        return this.avgSpeed;
    }

    public int getAvgStrideFrequency() {
        return this.avgStrideFrequency;
    }

    public int getAvgStrideLength() {
        return this.avgStrideLength;
    }

    public int getBoxingNum() {
        return this.boxingNum;
    }

    public int getCalories() {
        return this.calories;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDumbbellNum() {
        return this.dumbbellNum;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getExtremeExercise() {
        return this.extremeExercise;
    }

    public int getFatBurning() {
        return this.fatBurning;
    }

    @Nullable
    public List<HeartRateItem> getHearts() {
        return this.hearts;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMaxPace() {
        return this.maxPace;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public int getMinPace() {
        return this.minPace;
    }

    public int getMinSpeed() {
        return this.minSpeed;
    }

    public int getPaddleFrq() {
        return this.paddleFrq;
    }

    public int getPaddleNum() {
        return this.paddleNum;
    }

    public int getRelax() {
        return this.relax;
    }

    public int getSkipNum() {
        return this.skipNum;
    }

    public int getStep() {
        return this.step;
    }

    public int getType() {
        return this.type;
    }

    public int getWarmUp() {
        return this.warmUp;
    }

    public void setAerobicExercise(int i) {
        this.aerobicExercise = i;
    }

    public void setAnaerobicExercise(int i) {
        this.anaerobicExercise = i;
    }

    public void setAvgHr(int i) {
        this.avgHr = i;
    }

    public void setAvgPaceSecs(int i) {
        this.avgPaceSecs = i;
    }

    public void setAvgSkipFrq(int i) {
        this.avgSkipFrq = i;
    }

    public void setAvgSpeed(int i) {
        this.avgSpeed = i;
    }

    public void setAvgStrideFrequency(int i) {
        this.avgStrideFrequency = i;
    }

    public void setAvgStrideLength(int i) {
        this.avgStrideLength = i;
    }

    public void setBoxingNum(int i) {
        this.boxingNum = i;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDumbbellNum(int i) {
        this.dumbbellNum = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setExtremeExercise(int i) {
        this.extremeExercise = i;
    }

    public void setFatBurning(int i) {
        this.fatBurning = i;
    }

    public void setHearts(@Nullable List<HeartRateItem> list) {
        this.hearts = list;
    }

    public void setMaxHr(int i) {
        this.maxHr = i;
    }

    public void setMaxPace(int i) {
        this.maxPace = i;
    }

    public void setMaxSpeed(int i) {
        this.maxSpeed = i;
    }

    public void setMinHr(int i) {
        this.minHr = i;
    }

    public void setMinPace(int i) {
        this.minPace = i;
    }

    public void setMinSpeed(int i) {
        this.minSpeed = i;
    }

    public void setPaddleFrq(int i) {
        this.paddleFrq = i;
    }

    public void setPaddleNum(int i) {
        this.paddleNum = i;
    }

    public void setRelax(int i) {
        this.relax = i;
    }

    public void setSkipNum(int i) {
        this.skipNum = i;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setWarmUp(int i) {
        this.warmUp = i;
    }

    public String toString() {
        return "TGSportRecord{date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(this.date) + ", duration=" + this.duration + ", type=" + this.type + ", step=" + this.step + ", calories=" + this.calories + ", distance=" + this.distance + ", avgHr=" + this.avgHr + ", maxHr=" + this.maxHr + ", minHr=" + this.minHr + ", avgSpeed=" + this.avgSpeed + ", maxSpeed=" + this.maxSpeed + ", minSpeed=" + this.minSpeed + ", avgPaceSecs=" + this.avgPaceSecs + ", maxPace=" + this.maxPace + ", minPace=" + this.minPace + '}';
    }
}
