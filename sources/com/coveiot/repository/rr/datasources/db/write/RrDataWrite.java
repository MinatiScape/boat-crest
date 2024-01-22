package com.coveiot.repository.rr.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.repository.rr.datasources.RrRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class RrDataWrite {
    public static RrDataWrite b;

    /* renamed from: a  reason: collision with root package name */
    public RrRepo f7429a;

    public RrDataWrite(Context context) {
        this.f7429a = RrRepo.getInstance(context);
    }

    public static RrDataWrite getInstance(Context context) {
        if (b == null) {
            b = new RrDataWrite(context);
        }
        return b;
    }

    public void inserDailyRrDataList(List<EntityDailyRrData> list) {
        this.f7429a.rrDataDao.insertAllDailyRr(list);
    }

    public void insertDailyRr(EntityDailyRrData entityDailyRrData) {
        this.f7429a.rrDataDao.insertDailyRr(entityDailyRrData);
    }

    public void insertHourRr(EntityHourlyRrData entityHourlyRrData) {
        this.f7429a.rrDataDao.insert(entityHourlyRrData);
    }

    public void insertHourlyRrDataList(List<EntityHourlyRrData> list) {
        this.f7429a.rrDataDao.insertAll(list);
    }
}
