package com.coveiot.repository.rr.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.rr.model.MonthlyRrData;
import com.coveiot.covedb.rr.model.WeeklyRrData;
import com.coveiot.repository.rr.datasources.RrRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class RrDbRead {
    public static RrDbRead b;

    /* renamed from: a  reason: collision with root package name */
    public RrRepo f7428a;

    public RrDbRead(Context context) {
        this.f7428a = RrRepo.getInstance(context);
    }

    public static RrDbRead getInstance(Context context) {
        if (b == null) {
            b = new RrDbRead(context);
        }
        return b;
    }

    public LiveData<List<EntityDailyRrData>> getDailyRrByMacAddress(String str) {
        return this.f7428a.rrDataDao.getLiveDayWiseRrDataByMacAddress(str);
    }

    public List<EntityHourlyRrData> getHourlyRrData(String str, String str2) {
        return this.f7428a.rrDataDao.getHourLevelRr(str, str2);
    }

    public LiveData<EntityDailyRrData> getLiveDailyRrData(String str, String str2) {
        return this.f7428a.rrDataDao.getLiveDayLevelRr(str, str2);
    }

    public LiveData<List<MonthlyRrData>> getMonthWiseRrHistory(String str) {
        return this.f7428a.rrDataDao.getLiveMonthWiseRr(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7428a.rrDataDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7428a.rrDataDao.getRowCount(str, str2);
    }

    public LiveData<List<EntityHourlyRrData>> getRrData(String str, String str2) {
        return this.f7428a.rrDataDao.getLiveHourLevelRr(str, str2);
    }

    public List<EntityDailyRrData> getTotalUnsyncedData(String str) {
        return this.f7428a.rrDataDao.getUnSyncedRrData(str);
    }

    public LiveData<List<WeeklyRrData>> getWeekWiseRrHistory(String str) {
        return this.f7428a.rrDataDao.getLiveWeekWiseRr(str);
    }
}
