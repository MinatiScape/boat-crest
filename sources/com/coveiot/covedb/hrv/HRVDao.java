package com.coveiot.covedb.hrv;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.hrv.model.MonthlyHRVData;
import com.coveiot.covedb.hrv.model.WeeklyHRVData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface HRVDao {
    @Query("SELECT * FROM daily_hrv WHERE date=:date AND serial_no=:serial_no")
    DailyHRV geDailyHRVData(String str, String str2);

    @Query("SELECT * FROM daily_hrv WHERE serial_no=:serial_no and date BETWEEN :startDate AND :endDate")
    List<DailyHRV> getHRVDataByStartAndEndDate(String str, String str2, String str3);

    @Query("SELECT * FROM hourly_hrv WHERE serial_no=:serial_no AND date=:date")
    List<HourlyHRV> getHourlyHRVData(String str, String str2);

    @Query("select * from hourly_hrv where datetime(date ||' '|| start_time) >= datetime(:time) AND serial_no=:serial_no order by datetime(date ||' '|| start_time) ")
    List<HourlyHRV> getHourlyHRVDataFrom(String str, String str2);

    @Query("select * from hourly_hrv where hrv_low>0 AND hrv_high>0 AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyHRV getLatestHRVRecordHourly(String str, String str2);

    @Query("select * from hourly_hrv where hrv_low>0 AND hrv_high>0 AND hrv_low>=:minFilterValue AND hrv_high<=:maxFilterValue AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyHRV getLatestHRVRecordHourly(String str, String str2, float f, float f2);

    @Query("select * , MAX(hrv_high) from hourly_hrv where date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyHRV getLatestHighHRVRecordHourly(String str, String str2);

    @Query("SELECT * FROM hourly_hrv where hrv_low>0 AND hrv_low>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyHRV getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_hrv where hrv_low>0 AND hrv_low>0 AND hrv_low>=:minFilterValue AND hrv_low<=:maxFilterValue AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyHRV getLatestRecordHourly(String str, float f, float f2);

    @Query("SELECT * FROM daily_hrv WHERE date=:date AND serial_no=:serial_no")
    LiveData<DailyHRV> getLiveDailyHRVData(String str, String str2);

    @Query("SELECT * FROM daily_hrv WHERE serial_no=:serial_no")
    LiveData<List<DailyHRV>> getLiveDayWiseHRVDataByMacAddress(String str);

    @Query("SELECT * FROM hourly_hrv WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<HourlyHRV>> getLiveHourlyHRVData(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX((case when hrv_high>0 then hrv_high END)) as hrv_high, MIN((case when hrv_low>0 then hrv_low END)) as hrv_low, AVG((case when hrv_avg>0 then hrv_avg END)) as hrv_avg, AVG((case when baseline>0 then baseline END)) as baseline, strftime('%m',date) as month,strftime('%Y',date) as year from daily_hrv WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlyHRVData>> getLiveMonthWiseHRVData(String str);

    @Query("SELECT serial_no as mac_address,MAX((case when hrv_high>0 then hrv_high END)) as hrv_high, MIN((case when hrv_low>0 then hrv_low END)) as hrv_low, AVG((case when hrv_avg>0 then hrv_avg END)) as hrv_avg, AVG((case when baseline>0 then baseline END)) as baseline, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_hrv  WHERE serial_no=:serial_no  group by week,year")
    LiveData<List<WeeklyHRVData>> getLiveWeekWiseHRVData(String str);

    @Query("SELECT COUNT(*) FROM daily_hrv where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_hrv where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * From daily_hrv WHERE serial_no=:serial_no AND is_sync_server=0")
    List<DailyHRV> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    void insert(DailyHRV dailyHRV);

    @Insert(onConflict = 1)
    void insertAll(List<HourlyHRV> list);
}
