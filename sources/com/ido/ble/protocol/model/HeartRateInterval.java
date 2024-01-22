package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class HeartRateInterval implements Serializable {
    public static final int REMIND_OFF = 0;
    public static final int REMIND_ON = 1;
    private static final long serialVersionUID = 1;
    private int aerobicThreshold;
    private int anaerobicThreshold;
    private int burnFatThreshold;
    private int limintThreshold;
    private int minHr;
    private int range1;
    private int range2;
    private int range3;
    private int range4;
    private int range5;
    private int remindStartHour;
    private int remindStartMinute;
    private int remindStopHour;
    private int remindStopMinute;
    private int userMaxHR;
    private int warmUpThreshold;
    private int maxHrRemind = 0;
    private int minHrRemind = 0;

    public int getAerobicThreshold() {
        return this.aerobicThreshold;
    }

    public int getAnaerobicThreshold() {
        return this.anaerobicThreshold;
    }

    public int getBurnFatThreshold() {
        return this.burnFatThreshold;
    }

    public int getLimintThreshold() {
        return this.limintThreshold;
    }

    public int getMaxHrRemind() {
        return this.maxHrRemind;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public int getMinHrRemind() {
        return this.minHrRemind;
    }

    public int getRemindStartHour() {
        return this.remindStartHour;
    }

    public int getRemindStartMinute() {
        return this.remindStartMinute;
    }

    public int getRemindStopHour() {
        return this.remindStopHour;
    }

    public int getRemindStopMinute() {
        return this.remindStopMinute;
    }

    public int getUserMaxHR() {
        return this.userMaxHR;
    }

    public int getWarmUpThreshold() {
        return this.warmUpThreshold;
    }

    public void setAerobicThreshold(int i) {
        this.aerobicThreshold = i;
        this.range3 = i;
    }

    public void setAnaerobicThreshold(int i) {
        this.anaerobicThreshold = i;
        this.range4 = i;
    }

    public void setBurnFatThreshold(int i) {
        this.burnFatThreshold = i;
        this.range2 = i;
    }

    public void setLimintThreshold(int i) {
        this.limintThreshold = i;
        this.range5 = i;
    }

    public void setMaxHrRemind(int i) {
        this.maxHrRemind = i;
    }

    public void setMinHr(int i) {
        this.minHr = i;
    }

    public void setMinHrRemind(int i) {
        this.minHrRemind = i;
    }

    public void setRemindStartHour(int i) {
        this.remindStartHour = i;
    }

    public void setRemindStartMinute(int i) {
        this.remindStartMinute = i;
    }

    public void setRemindStopHour(int i) {
        this.remindStopHour = i;
    }

    public void setRemindStopMinute(int i) {
        this.remindStopMinute = i;
    }

    public void setUserMaxHR(int i) {
        this.userMaxHR = i;
    }

    public void setWarmUpThreshold(int i) {
        this.warmUpThreshold = i;
        this.range1 = i;
    }

    public String toString() {
        return "HeartRateInterval{burnFatThreshold=" + this.burnFatThreshold + ", aerobicThreshold=" + this.aerobicThreshold + ", limintThreshold=" + this.limintThreshold + ", warmUpThreshold=" + this.warmUpThreshold + ", anaerobicThreshold=" + this.anaerobicThreshold + ", minHr=" + this.minHr + ", maxHrRemind=" + this.maxHrRemind + ", minHrRemind=" + this.minHrRemind + ", remindStartHour=" + this.remindStartHour + ", remindStartMinute=" + this.remindStartMinute + ", remindStopHour=" + this.remindStopHour + ", remindStopMinute=" + this.remindStopMinute + ", userMaxHR=" + this.userMaxHR + ", range1=" + this.range1 + ", range2=" + this.range2 + ", range3=" + this.range3 + ", range4=" + this.range4 + ", range5=" + this.range5 + '}';
    }
}
