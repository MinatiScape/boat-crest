package com.coveiot.repository.heartrate.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.heartrate.model.MonthlyHeartRateData;
import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.repository.heartrate.datasources.db.HeartRateRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class HeartRateDBRead {
    public static HeartRateDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public HeartRateRepo f7396a;

    public HeartRateDBRead(Context context) {
        this.f7396a = HeartRateRepo.getInstance(context);
    }

    public static HeartRateDBRead getInstance(Context context) {
        if (b == null) {
            b = new HeartRateDBRead(context);
        }
        return b;
    }

    public LiveData<List<EntityDailyHeartRateData>> getDailyHearRateDataByMacAddress(String str) {
        return this.f7396a.heartRateDao.getLiveDayWiseHeartRateDataByMacAddress(str);
    }

    public EntityDailyHeartRateData getDailyHeartRateData(String str, String str2) {
        return this.f7396a.heartRateDao.getDailyHeartRateData(str, str2);
    }

    public List<EntityDailyHeartRateData> getDailyHeartRateDataByStartAndEndDate(String str, String str2, String str3) {
        return this.f7396a.heartRateDao.getDailyHeartRateDataByStartAndEndDate(str, str2, str3);
    }

    public LiveData<List<EntityHourlyHeartRateData>> getHearRateData(String str, String str2) {
        return this.f7396a.heartRateDao.getLiveHourlyHeartRateData(str, str2);
    }

    public List<EntityHourlyHeartRateData> getHourlyHeartRateData(String str, String str2) {
        return this.f7396a.heartRateDao.getHourlyHeartRateData(str, str2);
    }

    public List<EntityHourlyHeartRateData> getHourlyHeartRateDataFrom(String str, String str2) {
        return this.f7396a.heartRateDao.getHourlyHeartRateDataFrom(str, str2);
    }

    public EntityHourlyHeartRateData getHourlyHeartRateDate(String str, String str2, String str3, String str4) {
        return this.f7396a.heartRateDao.getHeartRateDataWithDateAndTime(str, str2, str3, str4);
    }

    public MinMaxData getHourlyMinMaxHeartRateDate(String str, String str2, String str3, String str4, String str5) {
        return this.f7396a.heartRateDao.getMinMaxHR(str, str2, str3, str4, str5);
    }

    public LiveData<EntityHourlyHeartRateData> getLastReadHeartRateData(String str) {
        return this.f7396a.heartRateDao.getLatestHourlyHeartRateData(str);
    }

    public EntityHourlyHeartRateData getLatestRecordHourly(String str) {
        return this.f7396a.heartRateDao.getLatestRecordHourly(str);
    }

    public LiveData<EntityDailyHeartRateData> getLiveDailyHeartRateData(String str, String str2) {
        return this.f7396a.heartRateDao.getLiveDailyHeartRateData(str, str2);
    }

    public LiveData<List<MonthlyHeartRateData>> getMonthWiseHeartRateHistory(String str) {
        return this.f7396a.heartRateDao.getLiveMonthWiseHeartRateData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7396a.heartRateDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7396a.heartRateDao.getRowCount(str, str2);
    }

    public List<EntityDailyHeartRateData> getTotalUnsyncedData(String str) {
        return this.f7396a.heartRateDao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklyHeartRateData>> getWeekWiseHeartRateHistory(String str) {
        return this.f7396a.heartRateDao.getLiveWeekWiseHeartRateData(str);
    }

    public EntityHourlyHeartRateData getLatestRecordHourly(String str, String str2) {
        return this.f7396a.heartRateDao.getLatestRecordHourly(str, str2);
    }
}
