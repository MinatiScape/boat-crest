package com.coveiot.repository.temperature.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.temperature.model.MonthlyTemperatureData;
import com.coveiot.covedb.temperature.model.WeeklyTemperatureData;
import com.coveiot.repository.temperature.datasources.db.TemperatureRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class TemperatureDBRead {
    public static TemperatureDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public TemperatureRepo f7451a;

    public TemperatureDBRead(Context context) {
        this.f7451a = TemperatureRepo.getInstance(context);
    }

    public static TemperatureDBRead getInstance(Context context) {
        if (b == null) {
            b = new TemperatureDBRead(context);
        }
        return b;
    }

    public LiveData<List<DailyTemperature>> getDailyTemperatureDataByMacAddress(String str) {
        return this.f7451a.temperatureDao.getLiveDayWiseTemperatureDataByMacAddress(str);
    }

    public List<HourlyTemperature> getHourlyTemperatureData(String str, String str2) {
        return this.f7451a.temperatureDao.getHourlyTemperatureData(str, str2);
    }

    public List<HourlyTemperature> getHourlyTemperatureDataFrom(String str, String str2) {
        return this.f7451a.temperatureDao.getHourlyTemperatureDataFrom(str, str2);
    }

    public LiveData<List<HourlyTemperature>> getHourlyTemperatureDataLiveData(String str, String str2) {
        return this.f7451a.temperatureDao.getLiveHourlyTemperatureData(str, str2);
    }

    public HourlyTemperature getLatestHighTemperatureRecordHourly(String str, String str2) {
        return this.f7451a.temperatureDao.getLatestHighTemperatureRecordHourly(str, str2);
    }

    public HourlyTemperature getLatestRecordHourly(String str, float f, float f2) {
        return this.f7451a.temperatureDao.getLatestRecordHourly(str, f, f2);
    }

    public HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2, float f, float f2) {
        return this.f7451a.temperatureDao.getLatestTemperatureRecordHourly(str, str2, f, f2);
    }

    public LiveData<DailyTemperature> getLiveDailyTemperatureData(String str, String str2) {
        return this.f7451a.temperatureDao.getLiveDailyTemperatureData(str, str2);
    }

    public LiveData<List<MonthlyTemperatureData>> getMonthWiseTemperatureHistory(String str) {
        return this.f7451a.temperatureDao.getLiveMonthWiseTemperatureData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7451a.temperatureDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7451a.temperatureDao.getRowCount(str, str2);
    }

    public List<DailyTemperature> getTotalUnsyncedData(String str) {
        return this.f7451a.temperatureDao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklyTemperatureData>> getWeekWiseTemperatureHistory(String str) {
        return this.f7451a.temperatureDao.getLiveWeekWiseTemperatureData(str);
    }

    public HourlyTemperature getLatestRecordHourly(String str) {
        return this.f7451a.temperatureDao.getLatestRecordHourly(str);
    }

    public HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2) {
        return this.f7451a.temperatureDao.getLatestTemperatureRecordHourly(str, str2);
    }
}
