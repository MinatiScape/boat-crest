package com.coveiot.repository.hrv.datasource.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.hrv.model.MonthlyHRVData;
import com.coveiot.covedb.hrv.model.WeeklyHRVData;
import com.coveiot.repository.hrv.datasource.db.HRVRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class HRVDBRead {
    public static HRVDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public HRVRepo f7402a;

    public HRVDBRead(Context context) {
        this.f7402a = HRVRepo.getInstance(context);
    }

    public static HRVDBRead getInstance(Context context) {
        if (b == null) {
            b = new HRVDBRead(context);
        }
        return b;
    }

    public DailyHRV getDailyHRVData(String str, String str2) {
        return this.f7402a.hrvDao.geDailyHRVData(str, str2);
    }

    public LiveData<List<DailyHRV>> getDailyHRVDataByMacAddress(String str) {
        return this.f7402a.hrvDao.getLiveDayWiseHRVDataByMacAddress(str);
    }

    public List<DailyHRV> getHRVDataByStartAndEndDate(String str, String str2, String str3) {
        return this.f7402a.hrvDao.getHRVDataByStartAndEndDate(str, str2, str3);
    }

    public List<HourlyHRV> getHourlyHRVData(String str, String str2) {
        return this.f7402a.hrvDao.getHourlyHRVData(str, str2);
    }

    public List<HourlyHRV> getHourlyHRVDataFrom(String str, String str2) {
        return this.f7402a.hrvDao.getHourlyHRVDataFrom(str, str2);
    }

    public LiveData<List<HourlyHRV>> getHourlyHRVDataLiveData(String str, String str2) {
        return this.f7402a.hrvDao.getLiveHourlyHRVData(str, str2);
    }

    public HourlyHRV getLatestHRVRecordHourly(String str, String str2, float f, float f2) {
        return this.f7402a.hrvDao.getLatestHRVRecordHourly(str, str2, f, f2);
    }

    public HourlyHRV getLatestHighHRVRecordHourly(String str, String str2) {
        return this.f7402a.hrvDao.getLatestHighHRVRecordHourly(str, str2);
    }

    public HourlyHRV getLatestRecordHourly(String str, float f, float f2) {
        return this.f7402a.hrvDao.getLatestRecordHourly(str, f, f2);
    }

    public LiveData<DailyHRV> getLiveDailyHRVData(String str, String str2) {
        return this.f7402a.hrvDao.getLiveDailyHRVData(str, str2);
    }

    public LiveData<List<MonthlyHRVData>> getMonthWiseHRVHistory(String str) {
        return this.f7402a.hrvDao.getLiveMonthWiseHRVData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7402a.hrvDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7402a.hrvDao.getRowCount(str, str2);
    }

    public List<DailyHRV> getTotalUnsyncedData(String str) {
        return this.f7402a.hrvDao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklyHRVData>> getWeekWiseHRVHistory(String str) {
        return this.f7402a.hrvDao.getLiveWeekWiseHRVData(str);
    }

    public HourlyHRV getLatestHRVRecordHourly(String str, String str2) {
        return this.f7402a.hrvDao.getLatestHRVRecordHourly(str, str2);
    }

    public HourlyHRV getLatestRecordHourly(String str) {
        return this.f7402a.hrvDao.getLatestRecordHourly(str);
    }
}
