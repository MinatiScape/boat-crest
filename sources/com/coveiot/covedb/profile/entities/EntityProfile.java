package com.coveiot.covedb.profile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.android.gms.common.Scopes;
import com.mappls.sdk.maps.style.layers.Property;
@Entity(tableName = Scopes.PROFILE)
/* loaded from: classes8.dex */
public class EntityProfile {
    @ColumnInfo(name = "age")
    public int age;
    public int gender;
    @ColumnInfo(name = Property.ICON_TEXT_FIT_HEIGHT)
    public int height;
    @ColumnInfo(name = "physicalActivityScore")
    public double physicalActivityScore;
    @ColumnInfo(name = "restingHr")
    public int restingHr;
    @ColumnInfo(name = "runStrideLength")
    public int runStrideLength;
    @ColumnInfo(name = "sleep_target")
    public int sleepTarget;
    @ColumnInfo(name = "steps_target")
    public int stepsTarget;
    @PrimaryKey
    public long timestamp;
    @ColumnInfo(name = "updatedToBand")
    public boolean updatedToBand;
    @ColumnInfo(name = "walkStrideLength")
    public int walkStrideLength;
    @ColumnInfo(name = "weight")
    public double weight;

    public int getAge() {
        return this.age;
    }

    public int getGender() {
        return this.gender;
    }

    public int getHeight() {
        return this.height;
    }

    public double getPhysicalActivityScore() {
        return this.physicalActivityScore;
    }

    public int getRestingHr() {
        return this.restingHr;
    }

    public int getRunStrideLength() {
        return this.runStrideLength;
    }

    public int getSleepTarget() {
        return this.sleepTarget;
    }

    public int getStepsTarget() {
        return this.stepsTarget;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int getWalkStrideLength() {
        return this.walkStrideLength;
    }

    public double getWeight() {
        return this.weight;
    }

    public boolean isUpdatedToBand() {
        return this.updatedToBand;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setPhysicalActivityScore(double d) {
        this.physicalActivityScore = d;
    }

    public void setRestingHr(int i) {
        this.restingHr = i;
    }

    public void setRunStrideLength(int i) {
        this.runStrideLength = i;
    }

    public void setSleepTarget(int i) {
        this.sleepTarget = i;
    }

    public void setStepsTarget(int i) {
        this.stepsTarget = i;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setUpdatedToBand(boolean z) {
        this.updatedToBand = z;
    }

    public void setWalkStrideLength(int i) {
        this.walkStrideLength = i;
    }

    public void setWeight(double d) {
        this.weight = d;
    }
}
