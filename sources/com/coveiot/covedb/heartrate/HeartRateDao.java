package com.coveiot.covedb.heartrate;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.coveiot.covedb.heartrate.model.MonthlyHeartRateData;
import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import com.coveiot.covedb.stress.model.MinMaxData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface HeartRateDao {
    @Query("SELECT * FROM daily_heart_rate_table WHERE date=:date AND serial_no=:serial_no")
    EntityDailyHeartRateData getDailyHeartRateData(String str, String str2);

    @Query("SELECT * FROM daily_heart_rate_table WHERE serial_no=:serial_no and date BETWEEN :startDate AND :endDate")
    List<EntityDailyHeartRateData> getDailyHeartRateDataByStartAndEndDate(String str, String str2, String str3);

    @Query("SELECT * FROM hourly_heart_rate_table WHERE serial_no=:serial_no AND date=:date and start_time=:startTime AND end_time=:endTime")
    EntityHourlyHeartRateData getHeartRateDataWithDateAndTime(String str, String str2, String str3, String str4);

    @Query("SELECT * FROM hourly_heart_rate_table WHERE serial_no=:serial_no AND date=:date")
    List<EntityHourlyHeartRateData> getHourlyHeartRateData(String str, String str2);

    @Query("select * from hourly_heart_rate_table where datetime(date ||' '|| start_time) >= datetime(:time)  AND serial_no=:serial_no  order by datetime(date ||' '|| start_time) ")
    List<EntityHourlyHeartRateData> getHourlyHeartRateDataFrom(String str, String str2);

    @Query(" SELECT * FROM hourly_heart_rate_table where min_heart_rate >0  AND serial_no=:serial_no ORDER BY date DESC , start_time DESC LIMIT 1")
    LiveData<EntityHourlyHeartRateData> getLatestHourlyHeartRateData(String str);

    @Query("SELECT * FROM hourly_heart_rate_table where min_heart_rate>0 AND max_heart_rate>0 AND serial_no=:serial_no  ORDER BY date DESC,  start_time DESC LIMIT 1")
    EntityHourlyHeartRateData getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_heart_rate_table where min_heart_rate>0 AND max_heart_rate>0 AND serial_no=:serial_no AND date=:date  ORDER BY   start_time DESC LIMIT 1")
    EntityHourlyHeartRateData getLatestRecordHourly(String str, String str2);

    @Query("SELECT * FROM daily_heart_rate_table WHERE date=:date AND serial_no=:serial_no")
    LiveData<EntityDailyHeartRateData> getLiveDailyHeartRateData(String str, String str2);

    @Query("SELECT * FROM daily_heart_rate_table WHERE serial_no=:serial_no")
    LiveData<List<EntityDailyHeartRateData>> getLiveDayWiseHeartRateDataByMacAddress(String str);

    @Query("SELECT * FROM hourly_heart_rate_table WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<EntityHourlyHeartRateData>> getLiveHourlyHeartRateData(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX(max_heart_rate) as max_heart_rate, MIN((case when min_heart_rate>0 then min_heart_rate END)) as min_heart_rate, AVG(avg_heart_rate) as avg_heart_rate, strftime('%m',date) as month,strftime('%Y',date) as year from daily_heart_rate_table WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlyHeartRateData>> getLiveMonthWiseHeartRateData(String str);

    @Query("SELECT serial_no as mac_address,MAX(max_heart_rate) as maxHeartRate, MIN((case when min_heart_rate>0 then min_heart_rate END)) as minHeartRate, AVG(avg_heart_rate) as avg_heart_rate, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_heart_rate_table  WHERE serial_no=:serial_no  group by week,year")
    LiveData<List<WeeklyHeartRateData>> getLiveWeekWiseHeartRateData(String str);

    @Query("select max(max_heart_rate) as maxValue, min(min_heart_rate )  as minValue from (select min_heart_rate,max_heart_rate, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_heart_rate_table where date>=:startDate and date<=:endDate and min_heart_rate >0  and max_heart_rate > 0 and serial_no=:serial_no)    where datestarttime >=:startDateTime and dateendtime <=:endDateTime")
    MinMaxData getMinMaxHR(String str, String str2, String str3, String str4, String str5);

    @Query("SELECT COUNT(*) FROM daily_heart_rate_table where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_heart_rate_table where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * From daily_heart_rate_table WHERE serial_no=:serial_no AND is_sync_server=0")
    List<EntityDailyHeartRateData> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    long insert(EntityHourlyHeartRateData entityHourlyHeartRateData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityHourlyHeartRateData> list);

    @Insert(onConflict = 1)
    void insertAllDailyHeartRate(List<EntityDailyHeartRateData> list);

    @Insert(onConflict = 1)
    void insertDailyHeartRate(EntityDailyHeartRateData entityDailyHeartRateData);

    @Update
    int upateHourlyHeartRateData(EntityHourlyHeartRateData entityHourlyHeartRateData);
}
