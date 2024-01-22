package com.coveiot.repository.bp.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.bp.model.MonthLevelBp;
import com.coveiot.covedb.bp.model.WeekLevelBp;
import com.coveiot.repository.bp.datasources.db.RepoBp;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class BpDBRead {
    public static BpDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public RepoBp f7317a;

    public BpDBRead(Context context) {
        this.f7317a = RepoBp.getInstance(context);
    }

    public static BpDBRead getInstance(Context context) {
        if (b == null) {
            b = new BpDBRead(context);
        }
        return b;
    }

    public List<EntityHourlyBp> getHourLevelBp(String str, String str2) {
        return this.f7317a.getBpDataDao().getHourLevelBp(str, str2);
    }

    public List<EntityHourlyBp> getHourlyBPDataFrom(String str) {
        return this.f7317a.getBpDataDao().getHourlyBPDataFrom(str);
    }

    public EntityHourlyBp getLatestRecordHourly(String str) {
        return this.f7317a.getBpDataDao().getLatestRecordHourly(str);
    }

    public LiveData<EntityDailyBp> getLiveDayLevelBp(String str, String str2) {
        return this.f7317a.getBpDataDao().getLiveDayLevelBp(str, str2);
    }

    public LiveData<List<EntityDailyBp>> getLiveDayWiseBp(String str) {
        return this.f7317a.getBpDataDao().getLiveDayWiseBp(str);
    }

    public LiveData<List<EntityHourlyBp>> getLiveHourLevelBp(String str, String str2) {
        return this.f7317a.getBpDataDao().getLiveHourLevelBp(str, str2);
    }

    public LiveData<List<MonthLevelBp>> getLiveMonthWiseBp(String str) {
        return this.f7317a.getBpDataDao().getLiveMonthWiseBp(str);
    }

    public LiveData<List<WeekLevelBp>> getLiveWeekWiseBp(String str) {
        return this.f7317a.getBpDataDao().getLiveWeekWiseBp(str);
    }

    public int getRowCount(@NotNull String str) {
        return this.f7317a.getBpDataDao().getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.f7317a.getBpDataDao().getRowCount(str, str2);
    }

    public List<EntityDailyBp> getUnSyncedBpData(String str) {
        return this.f7317a.getBpDataDao().getUnSyncedBpData(str);
    }

    public EntityHourlyBp getLatestRecordHourly(String str, String str2) {
        return this.f7317a.getBpDataDao().getLatestRecordHourly(str, str2);
    }
}
