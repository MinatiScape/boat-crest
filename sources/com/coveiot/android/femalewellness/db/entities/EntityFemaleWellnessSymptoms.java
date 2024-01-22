package com.coveiot.android.femalewellness.db.entities;

import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.List;
@Entity(tableName = "female_wellness_record_symptoms")
/* loaded from: classes4.dex */
public class EntityFemaleWellnessSymptoms {
    @ColumnInfo(name = "cycleLength")
    public Integer cycleLength;
    @ColumnInfo(name = "cycleStartDate")
    public String cycleStartDate;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "flow")
    public String flow;
    @ColumnInfo(name = "isActive")
    public boolean isActive;
    @ColumnInfo(name = "mood")
    public String mood;
    @ColumnInfo(name = "pain")
    public String pain;
    @ColumnInfo(name = "periodLength")
    public Integer periodLength;
    @ColumnInfo(name = TypedValues.CycleType.S_WAVE_PHASE)
    public String phase;
    @ColumnInfo(name = "pmsLength")
    public Integer pmsLength;
    @ColumnInfo(name = "symptoms")
    public List<String> symptoms = null;

    public Integer getCycleLength() {
        return this.cycleLength;
    }

    public String getCycleStartDate() {
        return this.cycleStartDate;
    }

    public String getDate() {
        return this.date;
    }

    public String getFlow() {
        return this.flow;
    }

    public String getMood() {
        return this.mood;
    }

    public String getPain() {
        return this.pain;
    }

    public Integer getPeriodLength() {
        return this.periodLength;
    }

    public String getPhase() {
        return this.phase;
    }

    public Integer getPmsLength() {
        return this.pmsLength;
    }

    public List<String> getSymptoms() {
        return this.symptoms;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }

    public void setCycleLength(Integer num) {
        this.cycleLength = num;
    }

    public void setCycleStartDate(String str) {
        this.cycleStartDate = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFlow(String str) {
        this.flow = str;
    }

    public void setMood(String str) {
        this.mood = str;
    }

    public void setPain(String str) {
        this.pain = str;
    }

    public void setPeriodLength(Integer num) {
        this.periodLength = num;
    }

    public void setPhase(String str) {
        this.phase = str;
    }

    public void setPmsLength(Integer num) {
        this.pmsLength = num;
    }

    public void setSymptoms(List<String> list) {
        this.symptoms = list;
    }
}
