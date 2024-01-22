package com.coveiot.repository.sleep.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.sleep.model.SleepDataModelMonthWiseCommon;
import com.coveiot.covedb.sleep.model.SleepDataModelWeekWiseCommon;
import com.coveiot.repository.sleep.datasources.db.SleepRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class SleepDBRead {
    public static SleepDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public final SleepRepo f7438a;

    public SleepDBRead(Context context) {
        this.f7438a = SleepRepo.getInstance(context);
    }

    public static SleepDBRead getInstance(Context context) {
        if (b == null) {
            b = new SleepDBRead(context);
        }
        return b;
    }

    public int getAwakeFor(String str) {
        return this.f7438a.mSleepDataDao.getAwakeInterval(str);
    }

    public List<DailySleepData> getDailySleepDataBetweenDates(String str, String str2, String str3) {
        return this.f7438a.mSleepDataDao.getDailySleepDataBetweenDates(str, str2, str3);
    }

    public DailySleepData getDailySleepDatafortheDate(String str, String str2) {
        return this.f7438a.mSleepDataDao.getDailySleepDataWithDate(str, str2);
    }

    public int getDeepSleep(String str) {
        return this.f7438a.mSleepDataDao.getDeepSleepInterval(str);
    }

    public List<SleepDataModelForLastNight> getLastNightDataHourly(String str, String str2, String str3) {
        return this.f7438a.mSleepDataDao.getLastNightSleepDataHourly(str, str2, str3);
    }

    public List<DailySleepData> getLatestFourSetOfSleepData(String str, Integer num) {
        return this.f7438a.mSleepDataDao.getTotalSleepData(str, num);
    }

    public HourlySleepData getLatestRecordHourly(String str) {
        return this.f7438a.mSleepDataDao.getLatestRecordHourly(str);
    }

    public int getLightSleep(String str) {
        return this.f7438a.mSleepDataDao.getLightSleepInterval(str);
    }

    public LiveData<List<DailySleepDataAlias>> getLiveDailyLastNightData(String str) {
        return this.f7438a.mSleepDataDao.getLiveDailyLastNightSleepData(str);
    }

    public LiveData<List<SleepDataModelForLastNight>> getLiveLastNightDataHourly(String str, String str2, String str3) {
        return this.f7438a.mSleepDataDao.getLiveLastNightSleepDataHourly(str, str2, str3);
    }

    public LiveData<DailySleepDataAlias> getLiveLastNightSleepData(String str, String str2) {
        return this.f7438a.mSleepDataDao.getLiveLastNightSleepData(str, str2);
    }

    public LiveData<List<SleepDataModelMonthWiseCommon>> getLiveMonthWiseData(String str) {
        return this.f7438a.mSleepDataDao.getLiveMonthWiseData(str);
    }

    public LiveData<List<DailySleepData>> getLiveTotalData(String str) {
        return this.f7438a.mSleepDataDao.getLiveTotalSleepData(str);
    }

    public LiveData<List<HourlySleepData>> getLiveTotalMinuteData(String str, String str2) {
        return this.f7438a.mSleepDataDao.getLiveTotalMinuteData(str, str2);
    }

    public LiveData<List<DailySleepData>> getLiveTotalUnSyncedData(String str) {
        return this.f7438a.mSleepDataDao.getLiveTotalUnSyncedSleepData(str);
    }

    public LiveData<List<SleepDataModelWeekWiseCommon>> getLiveWeekWiseData(String str) {
        return this.f7438a.mSleepDataDao.getLiveWeekWiseData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7438a.mSleepDataDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7438a.mSleepDataDao.getRowCount(str, str2);
    }

    public int getSleep(String str) {
        return this.f7438a.mSleepDataDao.getSleepInterval(str);
    }

    public List<HourlySleepData> getSleepDataBetweenDates(String str, String str2, String str3) {
        return this.f7438a.mSleepDataDao.getSleepDataBetweenDates(str, str2, str3);
    }

    public List<DailySleepData> getTotalData(String str) {
        return this.f7438a.mSleepDataDao.getTotalSleepData(str);
    }

    public List<DailySleepData> getTotalUnSyncedData(String str) {
        return this.f7438a.mSleepDataDao.getTotalUnSyncedSleepData(str);
    }

    public List<DailySleepData> getTotalUnSyncedSleepDatFomHrStress(String str) {
        return this.f7438a.mSleepDataDao.getTotalUnsyncedMinMaxHRStressSleepData(str);
    }

    public List<HourlySleepData> getTotaleHourlyData(String str, String str2) {
        return this.f7438a.mSleepDataDao.getTotalMinuteData(str, str2);
    }

    public void updateMinMaxHrStressAmbientSoundInDailySleepData(DailySleepData dailySleepData) {
        this.f7438a.mSleepDataDao.updateMinMaxAmbientSoundInDailySleepData(Integer.valueOf(dailySleepData.getMinHr()), Integer.valueOf(dailySleepData.getMaxHr()), Integer.valueOf(dailySleepData.getMaxStress()), Integer.valueOf(dailySleepData.getMinStress()), Integer.valueOf(dailySleepData.getMinAmbientSound()), Integer.valueOf(dailySleepData.getMaxAmbientSound()), dailySleepData.getMacAddress(), dailySleepData.getDate(), 1);
    }

    public void updateMinMaxHrStressData(DailySleepData dailySleepData) {
        this.f7438a.mSleepDataDao.updateMinMaxHrStressInDailySleepData(Integer.valueOf(dailySleepData.getMinHr()), Integer.valueOf(dailySleepData.getMaxHr()), Integer.valueOf(dailySleepData.getMaxStress()), Integer.valueOf(dailySleepData.getMinStress()), dailySleepData.getMacAddress(), dailySleepData.getDate(), 1);
    }
}
