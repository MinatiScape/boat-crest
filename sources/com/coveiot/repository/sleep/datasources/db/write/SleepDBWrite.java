package com.coveiot.repository.sleep.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.repository.sleep.datasources.db.SleepRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class SleepDBWrite {
    public static SleepDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public SleepRepo f7440a;

    public SleepDBWrite(Context context) {
        this.f7440a = SleepRepo.getInstance(context);
    }

    public static SleepDBWrite getInstance(Context context) {
        if (b == null) {
            b = new SleepDBWrite(context);
        }
        return b;
    }

    public void insertDailySleepData(DailySleepData dailySleepData) {
        new InsertSleepDataAsyncTask(this.f7440a.mSleepDataDao).execute(dailySleepData);
    }

    public void insertHourlySleepData(List<HourlySleepData> list) {
        new InsertSleepDataAsyncTask(this.f7440a.mSleepDataDao).execute(list);
    }
}
