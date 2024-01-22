package com.coveiot.repository.heartrate.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.repository.heartrate.datasources.db.HeartRateRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class HeartRateDBWrite {
    public static HeartRateDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public HeartRateRepo f7397a;

    public HeartRateDBWrite(Context context) {
        this.f7397a = HeartRateRepo.getInstance(context);
    }

    public static HeartRateDBWrite getInstance(Context context) {
        if (b == null) {
            b = new HeartRateDBWrite(context);
        }
        return b;
    }

    public void inserDailyHeartRateDataList(List<EntityDailyHeartRateData> list) {
        this.f7397a.heartRateDao.insertAllDailyHeartRate(list);
    }

    public void inserHeartRateDataList(List<EntityHourlyHeartRateData> list) {
        this.f7397a.heartRateDao.insertAll(list);
    }

    public void insertDailyHeartRate(EntityDailyHeartRateData entityDailyHeartRateData) {
        this.f7397a.heartRateDao.insertDailyHeartRate(entityDailyHeartRateData);
    }

    public long insertHeartRate(EntityHourlyHeartRateData entityHourlyHeartRateData) {
        return this.f7397a.heartRateDao.insert(entityHourlyHeartRateData);
    }

    public int updateHrData(EntityHourlyHeartRateData entityHourlyHeartRateData) {
        return this.f7397a.heartRateDao.upateHourlyHeartRateData(entityHourlyHeartRateData);
    }
}
