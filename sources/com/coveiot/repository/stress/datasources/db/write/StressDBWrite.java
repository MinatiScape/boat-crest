package com.coveiot.repository.stress.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.repository.stress.datasources.db.StressRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class StressDBWrite {
    public static StressDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public StressRepo f7446a;

    public StressDBWrite(Context context) {
        this.f7446a = StressRepo.getInstance(context);
    }

    public static StressDBWrite getInstance(Context context) {
        if (b == null) {
            b = new StressDBWrite(context);
        }
        return b;
    }

    public void insertStress(DailyStress dailyStress) {
        this.f7446a.stressDao.insert(dailyStress);
    }

    public void insertStressHourlyList(List<HourlyStress> list) {
        this.f7446a.stressDao.insertAll(list);
    }
}
