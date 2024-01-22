package com.coveiot.repository.walk.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covedb.walk.model.PersonalBest;
import com.coveiot.covedb.walk.model.StepsDataModelMonthWiseCommon;
import com.coveiot.covedb.walk.model.StepsDataModelWeekWiseCommon;
import com.coveiot.repository.walk.datasources.db.WalkDBRepo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class WalkDBRead {
    public static WalkDBRead c;

    /* renamed from: a  reason: collision with root package name */
    public LiveData<DailyWalkData> f7462a;
    public WalkDBRepo b;

    public WalkDBRead(Context context) {
        this.b = WalkDBRepo.getInstance(context);
    }

    public static synchronized WalkDBRead getInstance(Context context) {
        WalkDBRead walkDBRead;
        synchronized (WalkDBRead.class) {
            if (c == null) {
                c = new WalkDBRead(context);
            }
            walkDBRead = c;
        }
        return walkDBRead;
    }

    public int deleteDailyWalkDataFor(String str, String str2) {
        return this.b.mWalkDao.deleteDailyWalkDataFor(str, str2);
    }

    public int deleteHourlyWalkDataFor(String str, String str2) {
        return this.b.mWalkDao.deleteHourlyWalkDataFor(str, str2);
    }

    public DailyWalkData getDailyWalkData(String str, String str2) {
        return this.b.mWalkDao.getDailyWalkData(str, str2);
    }

    public List<DailyWalkData> getDailyWalkDataBetweenDates(String str, String str2, String str3) {
        return this.b.mWalkDao.getDailyWalkDataBetweenDates(str, str2, str3);
    }

    public DailyWalkData getDailyWalkDataWithDate(String str, String str2) {
        return this.b.mWalkDao.getDailyWalkDataWithDate(str, str2);
    }

    public HourlyWalkData getHourlyLastRecordFor(String str, String str2) {
        return this.b.mWalkDao.getHourlyLatestRecordFor(str, str2);
    }

    public List<HourlyWalkData> getHourlyWalkData(String str, String str2) {
        if (str2 == null) {
            return this.b.mWalkDao.getTotalFiveMinuteData(str);
        }
        return this.b.mWalkDao.getFiveMinuteData(str, str2);
    }

    public DailyWalkData getLastDayData(@NotNull String str) {
        return this.b.mWalkDao.getLatestDayData(str);
    }

    public DailyWalkData getLastDayDataWithValue(String str) {
        return this.b.mWalkDao.getLatestDayDataWithValue(str);
    }

    public HourlyWalkData getLatestRecordHourly(String str) {
        return this.b.mWalkDao.getLatestRecordHourly(str);
    }

    public LiveData<DailyWalkData> getLiveDailyWalkData(String str, String str2) {
        if (str2 == null) {
            this.f7462a = this.b.mWalkDao.getTotalWalkData(str);
        } else {
            this.f7462a = this.b.mWalkDao.getWalkData(str, str2);
        }
        return this.f7462a;
    }

    public LiveData<List<HourlyWalkData>> getLiveHourlyWalkDataLive(String str, String str2) {
        if (str2 == null) {
            return this.b.mWalkDao.getLiveTotalFiveMinuteData(str);
        }
        return this.b.mWalkDao.getLiveFiveMinuteData(str, str2);
    }

    public LiveData<List<StepsDataModelMonthWiseCommon>> getLiveStepsDataMonthWiseCommon(String str) {
        if (str == null) {
            return this.b.mWalkDao.getLiveTotalWalkDataMonthWiseCommon();
        }
        return this.b.mWalkDao.getLiveWalkDataMonthWiseCommon(str);
    }

    public LiveData<List<StepsDataModelWeekWiseCommon>> getLiveStepsDataWeekWiseCommon(String str) {
        if (str == null) {
            return this.b.mWalkDao.getLiveTotalWalkDataWeekWiseCommon();
        }
        return this.b.mWalkDao.getLiveWalkDataWeekWiseCommon(str);
    }

    public LiveData<List<DailyWalkData>> getLiveTotalDailyWalkDataLive(String str) {
        if (str == null) {
            return this.b.mWalkDao.getLiveAllDailyWalkData();
        }
        return this.b.mWalkDao.getLiveCompleteDailyWalkData(str);
    }

    public LiveData<PersonalBest> getMaxTillDate() {
        return this.b.mWalkDao.getMaxValueTillData();
    }

    public List<HourlyWalkData> getOrderedHourlyWalkDataFor(String str, String str2) {
        return this.b.mWalkDao.getOrderedHourlyWalkDataFor(str, str2);
    }

    public int getRowCount(@NotNull String str) {
        return this.b.mWalkDao.getRowCount(str);
    }

    public int getRowCountForDailyData(@NotNull String str, @NotNull String str2) {
        return this.b.mWalkDao.getRowCount(str, str2);
    }

    public int getTotalCalories(String str, String str2) {
        return this.b.mWalkDao.getTotalCalories(str, str2);
    }

    public List<DailyWalkData> getTotalDailyWalkData(String str) {
        if (str == null) {
            return this.b.mWalkDao.getAllDailyWalkData();
        }
        return this.b.mWalkDao.getCompleteDailyWalkData(str);
    }

    public int getTotalDistance(String str, String str2) {
        return this.b.mWalkDao.getTotalDistance(str, str2);
    }

    public int getTotalSteps(String str, String str2) {
        return this.b.mWalkDao.getTotalSteps(str, str2);
    }

    public List<DailyWalkData> getTotalUnSyncedWalkData(String str) {
        return this.b.mWalkDao.getTotalUnSyncedWalkData(str);
    }

    public LiveData<List<HourlyWalkData>> getWalkDataBetweenDates(String str, String str2, String str3) {
        if (str3 == null) {
            return this.b.mWalkDao.getLiveTotalFiveMinuteData(str);
        }
        return this.b.mWalkDao.getWalkDataBetweenDates(str, str2, str3);
    }
}
