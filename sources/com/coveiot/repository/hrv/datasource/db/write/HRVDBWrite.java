package com.coveiot.repository.hrv.datasource.db.write;

import android.content.Context;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.repository.hrv.datasource.db.HRVRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class HRVDBWrite {
    public static HRVDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public HRVRepo f7403a;

    public HRVDBWrite(Context context) {
        this.f7403a = HRVRepo.getInstance(context);
    }

    public static HRVDBWrite getInstance(Context context) {
        if (b == null) {
            b = new HRVDBWrite(context);
        }
        return b;
    }

    public void insert(DailyHRV dailyHRV) {
        this.f7403a.hrvDao.insert(dailyHRV);
    }

    public void insertHourlyList(List<HourlyHRV> list) {
        this.f7403a.hrvDao.insertAll(list);
    }
}
