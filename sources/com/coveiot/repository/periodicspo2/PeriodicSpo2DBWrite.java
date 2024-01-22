package com.coveiot.repository.periodicspo2;

import android.content.Context;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import java.util.List;
/* loaded from: classes9.dex */
public class PeriodicSpo2DBWrite {
    public static PeriodicSpo2DBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public PeriodicSpo2Repo f7416a;

    public PeriodicSpo2DBWrite(Context context) {
        this.f7416a = PeriodicSpo2Repo.getInstance(context);
    }

    public static PeriodicSpo2DBWrite getInstance(Context context) {
        if (b == null) {
            b = new PeriodicSpo2DBWrite(context);
        }
        return b;
    }

    public void insertPeriodicSpo2(DailyPeriodicSpo2 dailyPeriodicSpo2) {
        this.f7416a.spo2Dao.insert(dailyPeriodicSpo2);
    }

    public void insertSpo2HourlyList(List<EntityHourlySpo2> list) {
        this.f7416a.spo2Dao.insertAll(list);
    }
}
