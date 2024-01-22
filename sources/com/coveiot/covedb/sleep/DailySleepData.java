package com.coveiot.covedb.sleep;

import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(tableName = "dailysleepdata")
/* loaded from: classes8.dex */
public class DailySleepData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public String f6971a;
    @ColumnInfo(name = "deep_sleep")
    public int b;
    @ColumnInfo(name = "light_sleep")
    public int c;
    @ColumnInfo(name = "awake")
    public int d;
    @ColumnInfo(name = TypedValues.AttributesType.S_TARGET)
    public int e;
    @ColumnInfo(name = "sleepScore")
    public int f;
    @ColumnInfo(name = "maxHr")
    public int g;
    @ColumnInfo(name = "minHr")
    public int h;
    @ColumnInfo(name = "maxStress")
    public int i;
    @ColumnInfo(name = "minStress")
    public int j;
    @ColumnInfo(name = "minAmbientSound")
    public int k;
    @ColumnInfo(name = "maxAmbientSound")
    public int l;
    @ColumnInfo(name = "breathQuality")
    public int m;
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "isMinMaxUpdated")
    public int n;
    public int o;
    public boolean p;

    public int getAwakeTime() {
        return this.d;
    }

    public int getBreathQuality() {
        return this.m;
    }

    public String getDate() {
        return this.f6971a;
    }

    public int getDeepSleep() {
        return this.b;
    }

    public int getIsMinMaxUpdated() {
        return this.n;
    }

    public int getLightSleep() {
        return this.c;
    }

    @NonNull
    public String getMacAddress() {
        return this.mac_address;
    }

    public int getMaxAmbientSound() {
        return this.l;
    }

    public int getMaxHr() {
        return this.g;
    }

    public int getMaxStress() {
        return this.i;
    }

    public int getMinAmbientSound() {
        return this.k;
    }

    public int getMinHr() {
        return this.h;
    }

    public int getMinStress() {
        return this.j;
    }

    public int getSleepScore() {
        return this.f;
    }

    public int getSleepTarget() {
        return this.e;
    }

    public int getValue() {
        return this.o;
    }

    public String get_id() {
        return this.mac_address + this.f6971a;
    }

    public boolean isSyncedToSevrer() {
        return this.p;
    }

    public void setAwakeTime(int i) {
        this.d = i;
    }

    public void setBreathQuality(int i) {
        this.m = i;
    }

    public void setDate(String str) {
        this.f6971a = str;
    }

    public void setDeepSleep(int i) {
        this.b = i;
    }

    public void setIsMinMaxUpdated(int i) {
        this.n = i;
    }

    public void setLightSleep(int i) {
        this.c = i;
    }

    public void setMacAddress(@NonNull String str) {
        this.mac_address = str;
    }

    public void setMaxAmbientSound(int i) {
        this.l = i;
    }

    public void setMaxHr(int i) {
        this.g = i;
    }

    public void setMaxStress(int i) {
        this.i = i;
    }

    public void setMinAmbientSound(int i) {
        this.k = i;
    }

    public void setMinHr(int i) {
        this.h = i;
    }

    public void setMinStress(int i) {
        this.j = i;
    }

    public void setSleepScore(int i) {
        this.f = i;
    }

    public void setSleepTarget(int i) {
        this.e = i;
    }

    public void setSyncedToSevrer(boolean z) {
        this.p = z;
    }

    public void setValue(int i) {
        this.o = i;
    }

    public void set_id(String str) {
    }
}
