package com.coveiot.repository.stress.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.covedb.stress.model.MonthlyStressData;
import com.coveiot.covedb.stress.model.WeeklyStressData;
import com.coveiot.repository.stress.datasources.db.StressRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class StressDBRead {
    public static StressDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public StressRepo f7445a;

    public StressDBRead(Context context) {
        this.f7445a = StressRepo.getInstance(context);
    }

    public static StressDBRead getInstance(Context context) {
        if (b == null) {
            b = new StressDBRead(context);
        }
        return b;
    }

    public DailyStress getDailyStressData(String str, String str2) {
        return this.f7445a.stressDao.getDailyStressData(str, str2);
    }

    public LiveData<List<DailyStress>> getDailyStressDataByMacAddress(String str) {
        return this.f7445a.stressDao.getLiveDayWiseStressDataByMacAddress(str);
    }

    public List<DailyStress> getDailyStressDataByStartAndEndDate(String str, String str2, String str3) {
        return this.f7445a.stressDao.getLiveDayWiseStressDataByStartAndEndDate(str, str2, str3);
    }

    public MinMaxData getHourlyMinMaxStressWithDate(String str, String str2, String str3, String str4, String str5) {
        return this.f7445a.stressDao.getMinMaxStress(str, str2, str3, str4, str5);
    }

    public List<HourlyStress> getHourlyStressData(String str, String str2) {
        return this.f7445a.stressDao.getHourlyStressData(str, str2);
    }

    public List<HourlyStress> getHourlyStressDataFrom(String str, String str2) {
        return this.f7445a.stressDao.getHourlyStressDataFrom(str, str2);
    }

    public LiveData<List<HourlyStress>> getHourlyStressDataLiveData(String str, String str2) {
        return this.f7445a.stressDao.getLiveHourlyStressData(str, str2);
    }

    public HourlyStress getHourlyStressWithDate(String str, String str2, String str3, String str4) {
        return this.f7445a.stressDao.getStressDataWithDateAndTime(str, str2, str3, str4);
    }

    public HourlyStress getLatestHighStressRecordHourly(String str, String str2) {
        return this.f7445a.stressDao.getLatestHighStressRecordHourly(str, str2);
    }

    public HourlyStress getLatestRecordHourly(String str, float f, float f2) {
        return this.f7445a.stressDao.getLatestRecordHourly(str, f, f2);
    }

    public LiveData<HourlyStress> getLatestRecordHourlyLiveData(String str) {
        return this.f7445a.stressDao.getLatestRecordHourlyLiveData(str);
    }

    public HourlyStress getLatestStressRecordHourly(String str, String str2, float f, float f2) {
        return this.f7445a.stressDao.getLatestStressRecordHourly(str, str2, f, f2);
    }

    public LiveData<DailyStress> getLiveDailyStressData(String str, String str2) {
        return this.f7445a.stressDao.getLiveDailyStressData(str, str2);
    }

    public LiveData<List<MonthlyStressData>> getMonthWiseStressHistory(String str) {
        return this.f7445a.stressDao.getLiveMonthWiseStressData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7445a.stressDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7445a.stressDao.getRowCount(str, str2);
    }

    public List<DailyStress> getTotalUnsyncedData(String str) {
        return this.f7445a.stressDao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklyStressData>> getWeekWiseStressHistory(String str) {
        return this.f7445a.stressDao.getLiveWeekWiseStressData(str);
    }

    public HourlyStress getLatestRecordHourly(String str) {
        return this.f7445a.stressDao.getLatestRecordHourly(str);
    }

    public HourlyStress getLatestStressRecordHourly(String str, String str2) {
        return this.f7445a.stressDao.getLatestStressRecordHourly(str, str2);
    }
}
