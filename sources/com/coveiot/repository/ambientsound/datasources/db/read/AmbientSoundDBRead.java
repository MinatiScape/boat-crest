package com.coveiot.repository.ambientsound.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.MonthlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.repository.ambientsound.datasources.db.AmbientSoundRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class AmbientSoundDBRead {
    public static AmbientSoundDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public AmbientSoundRepo f7311a;

    public AmbientSoundDBRead(Context context) {
        this.f7311a = AmbientSoundRepo.getInstance(context);
    }

    public static AmbientSoundDBRead getInstance(Context context) {
        if (b == null) {
            b = new AmbientSoundDBRead(context);
        }
        return b;
    }

    public LiveData<List<EntityHourlyAmbientSoundData>> getAmbientSoundData(String str, String str2) {
        return this.f7311a.ambientSoundDao.getLiveHourlyAmbientSoundData(str, str2);
    }

    public LiveData<List<EntityDailyAmbientSoundData>> getDailyAmbientSoundDataByMacAddress(String str) {
        return this.f7311a.ambientSoundDao.getLiveDayWiseAmbientSoundDataByMacAddress(str);
    }

    public List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundData(String str, String str2) {
        return this.f7311a.ambientSoundDao.getHourlyAmbientSoundData(str, str2);
    }

    public List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundDataFrom(String str, String str2) {
        return this.f7311a.ambientSoundDao.getHourlyAmbientSoundDataFrom(str, str2);
    }

    public EntityHourlyAmbientSoundData getHourlyAmbientSoundDate(String str, String str2, String str3, String str4) {
        return this.f7311a.ambientSoundDao.getAmbientSoundDataWithDateAndTime(str, str2, str3, str4);
    }

    public MinMaxData getHourlyMinMaxAmbientSoundDate(String str, String str2, String str3, String str4, String str5) {
        return this.f7311a.ambientSoundDao.getMinMaxAmbientSound(str, str2, str3, str4, str5);
    }

    public LiveData<EntityHourlyAmbientSoundData> getLastReadAmbientSoundData(String str) {
        return this.f7311a.ambientSoundDao.getLatestHourlyAmbientSoundData(str);
    }

    public EntityHourlyAmbientSoundData getLatestRecordHourly(String str) {
        return this.f7311a.ambientSoundDao.getLatestRecordHourly(str);
    }

    public LiveData<EntityDailyAmbientSoundData> getLiveDailyAmbientSoundData(String str, String str2) {
        return this.f7311a.ambientSoundDao.getLiveDailyAmbientSoundData(str, str2);
    }

    public LiveData<List<MonthlyAmbientSoundData>> getMonthWiseAmbientSoundHistory(String str) {
        return this.f7311a.ambientSoundDao.getLiveMonthWiseAmbientSoundData(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7311a.ambientSoundDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7311a.ambientSoundDao.getRowCount(str, str2);
    }

    public List<EntityDailyAmbientSoundData> getTotalUnsyncedData(String str) {
        return this.f7311a.ambientSoundDao.getTotalUnSyncedData(str);
    }

    public LiveData<List<WeeklyAmbientSoundData>> getWeekWiseAmbientSoundHistory(String str) {
        return this.f7311a.ambientSoundDao.getLiveWeekWiseAmbientSoundData(str);
    }

    public EntityHourlyAmbientSoundData getLatestRecordHourly(String str, String str2) {
        return this.f7311a.ambientSoundDao.getLatestRecordHourly(str, str2);
    }
}
