package com.coveiot.repository.periodicspo2;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.spo2.model.MonthlySpo2Data;
import com.coveiot.covedb.spo2.model.WeeklySpo2Data;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class PeriodicSpo2DBRead {
    public static PeriodicSpo2DBRead b;

    /* renamed from: a  reason: collision with root package name */
    public PeriodicSpo2Repo f7415a;

    public PeriodicSpo2DBRead(Context context) {
        this.f7415a = PeriodicSpo2Repo.getInstance(context);
    }

    public static PeriodicSpo2DBRead getInstance(Context context) {
        if (b == null) {
            b = new PeriodicSpo2DBRead(context);
        }
        return b;
    }

    public DailyPeriodicSpo2 getDailyPeriodicSpo2Data(String str, String str2) {
        return this.f7415a.spo2Dao.getDailySpo2Data(str, str2);
    }

    public LiveData<List<DailyPeriodicSpo2>> getDailySpo2DataByMacAddress(String str) {
        return this.f7415a.spo2Dao.getLiveDayWiseSpo2DataByMacAddress(str);
    }

    public List<EntityHourlySpo2> getHourlySpo2Data(String str, String str2) {
        return this.f7415a.spo2Dao.getHourlySpo2Data(str, str2);
    }

    public List<EntityHourlySpo2> getHourlySpo2DataFrom(String str, String str2) {
        return this.f7415a.spo2Dao.getHourlySpo2DataFrom(str, str2);
    }

    public LiveData<List<EntityHourlySpo2>> getHourlySpo2DataLiveData(String str, String str2) {
        return this.f7415a.spo2Dao.getLiveHourlySpo2Data(str, str2);
    }

    public EntityHourlySpo2 getLatestHighSpo2RecordHourly(String str, String str2) {
        return this.f7415a.spo2Dao.getLatestHighSpo2RecordHourly(str, str2);
    }

    public EntityHourlySpo2 getLatestRecordHourly(String str, float f, float f2) {
        return this.f7415a.spo2Dao.getLatestRecordHourly(str, f, f2);
    }

    public EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2, float f, float f2) {
        return this.f7415a.spo2Dao.getLatestSpo2RecordHourly(str, str2, f, f2);
    }

    public LiveData<DailyPeriodicSpo2> getLiveDailyPeriodicSpo2Data(String str, String str2) {
        return this.f7415a.spo2Dao.getLiveDailySpo2Data(str, str2);
    }

    public LiveData<List<MonthlySpo2Data>> getMonthWiseSpo2History(String str) {
        return this.f7415a.spo2Dao.getLiveMonthWiseSpo2Data(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7415a.spo2Dao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7415a.spo2Dao.getRowCount(str, str2);
    }

    public List<DailyPeriodicSpo2> getTotalUnsyncedData(String str) {
        return this.f7415a.spo2Dao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklySpo2Data>> getWeekWiseSpo2History(String str) {
        return this.f7415a.spo2Dao.getLiveWeekWiseSpo2Data(str);
    }

    public EntityHourlySpo2 getLatestRecordHourly(String str) {
        return this.f7415a.spo2Dao.getLatestRecordHourly(str);
    }

    public EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2) {
        return this.f7415a.spo2Dao.getLatestSpo2RecordHourly(str, str2);
    }
}
