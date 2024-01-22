package com.coveiot.android.respiratoryrate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.model.MonthlyRespiratoryRateData;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.WeeklyRespiratoryRateData;
import java.util.List;
@Dao
/* loaded from: classes6.dex */
public interface RespiratoryRateDao {
    @Query("SELECT date as date, min as min, max as max  from daily_respiratory_rate where macAddress=:macAddress AND date between (:startDate) and (:endDate) AND min is not null AND min > 0 AND max is not null AND max > 0 ORDER BY date DESC")
    List<RespiratoryRateListBean> getDailyRespiratoryRateDateBetweenDates(String str, String str2, String str3);

    @Query("SELECT date as date, min as min, max as max  from daily_respiratory_rate where macAddress=:macAddress AND date between (:startDate) and (:endDate) AND min is not null AND min > 0 AND max is not null AND max > 0 ORDER BY date ASC")
    List<RespiratoryRateListBean> getDailyRespiratoryRateRangeBetweenDates(String str, String str2, String str3);

    @Query("SELECT mDate from daily_respiratory_rate WHERE macAddress=:macAddress ORDER BY mDate DESC limit 1")
    String getLastSyncedDate(String str);

    @Query("SELECT * from daily_respiratory_rate where macAddress=:macAddress ORDER BY mDate DESC LIMIT 1")
    LiveData<DailyRespiratoryRateEntity> getLatestRespiratoryRateData(String str);

    @Query("SELECT *  from daily_respiratory_rate where macAddress=:macAddress AND isSyncedWithServer=0 ")
    List<DailyRespiratoryRateEntity> getListOfUnSyncedRespiratoryRate(String str);

    @Query("SELECT * from daily_respiratory_rate where macAddress=:macAddress AND min is not null AND min > 0 AND max is not null AND max > 0")
    LiveData<List<DailyRespiratoryRateEntity>> getLiveDayWiseRespiratoryRateData(String str);

    @Query("SELECT macAddress as macAddress,MAX((case when max>0 then max END)) as max, MIN((case when min>0 then min END)) as min, AVG((case when avg>0 then avg END)) as avg, MAX(mDate) as endDate, MIN(mDate) as startDate, strftime('%m',date) as month,strftime('%Y',date) as year from daily_respiratory_rate WHERE macAddress=:macAddress  group by month,year")
    LiveData<List<MonthlyRespiratoryRateData>> getLiveMonthWiseRespiratoryRateData(String str);

    @Query("SELECT macAddress as macAddress,MAX((case when max>0 then max END)) as max, MIN((case when min>0 then min END)) as min, AVG((case when avg>0 then avg END)) as avg, MAX(mDate) as endDate, MIN(mDate) as startDate,strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_respiratory_rate  WHERE macAddress=:macAddress  group by week,year")
    LiveData<List<WeeklyRespiratoryRateData>> getLiveWeekWiseRespiratoryRateData(String str);

    @Query("SELECT *  from daily_respiratory_rate where macAddress=:macAddress AND mDate=:date ")
    DailyRespiratoryRateEntity getRespiratoryRate(String str, String str2);

    @Query("SELECT *  from daily_respiratory_rate where macAddress=:macAddress AND mDate=:date ")
    LiveData<DailyRespiratoryRateEntity> getRespiratoryRateLiveData(String str, String str2);

    @Query("SELECT COUNT(*) from daily_respiratory_rate where macAddress=:macAddress")
    int getRowCount(String str);

    @Insert(onConflict = 1)
    long insert(DailyRespiratoryRateEntity dailyRespiratoryRateEntity);

    @Query("UPDATE daily_respiratory_rate SET isSyncedWithServer = 1 WHERE macAddress=:macAddress AND mDate=:mDate")
    void updateRespiratoryRateToServer(String str, String str2);
}
