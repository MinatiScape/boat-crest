package com.coveiot.android.leonardo.dashboard.health.model;

import androidx.annotation.Nullable;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes3.dex */
public class SleepDataModelWithDate extends SleepDataModel {
    public List<SleepDataModelForLastNight> k;
    public Calendar l;

    public SleepDataModelWithDate(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super(bArr, i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public boolean equals(@Nullable Object obj) {
        return this.l.equals(((SleepDataModelWithDate) obj).l);
    }

    public Calendar getDate() {
        return this.l;
    }

    public List<SleepDataModelForLastNight> getLastNightSleepData() {
        return this.k;
    }

    public void setDate(Calendar calendar) {
        this.l = calendar;
    }

    public void setLastNightSleepData(List<SleepDataModelForLastNight> list) {
        this.k = list;
    }
}
