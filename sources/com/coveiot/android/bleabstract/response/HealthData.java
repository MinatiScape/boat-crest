package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.utils.utility.AppUtils;
import java.util.Date;
/* loaded from: classes2.dex */
public class HealthData {

    /* renamed from: a  reason: collision with root package name */
    public HealthVitalsType f3632a;
    public long b;
    public int c;

    public HealthData(HealthVitalsType healthVitalsType, long j, int i) {
        this.f3632a = healthVitalsType;
        this.b = j;
        this.c = i;
    }

    public HealthVitalsType getHealthVitalsType() {
        return this.f3632a;
    }

    public long getTimestamp() {
        return this.b;
    }

    public int getValue() {
        return this.c;
    }

    public String toString() {
        return "HealthData{healthVitalsType=" + this.f3632a + ", timestamp=" + this.b + ", Readable timestamp=" + AppUtils.formatDate(new Date(this.b), "yyyy-MM-dd HH:mm:ss") + ", value=" + this.c + '}';
    }
}
