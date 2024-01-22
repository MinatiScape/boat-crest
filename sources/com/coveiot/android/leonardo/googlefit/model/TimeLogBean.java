package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import java.util.List;
/* loaded from: classes2.dex */
public class TimeLogBean {

    /* renamed from: a  reason: collision with root package name */
    public List<HourlyWalkData> f4847a;
    public List<EntityHourlyHeartRateData> b;

    public List<EntityHourlyHeartRateData> getHeartRateLogs() {
        return this.b;
    }

    public List<HourlyWalkData> getLogBeans() {
        return this.f4847a;
    }

    public void setHeartRateLogs(List<EntityHourlyHeartRateData> list) {
        this.b = list;
    }

    public void setLogBeans(List<HourlyWalkData> list) {
        this.f4847a = list;
    }
}
