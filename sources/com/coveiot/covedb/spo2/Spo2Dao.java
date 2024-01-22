package com.coveiot.covedb.spo2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.spo2.model.MonthlySpo2Data;
import com.coveiot.covedb.spo2.model.WeeklySpo2Data;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface Spo2Dao {
    @Query("SELECT * FROM daily_spo2 WHERE date=:date AND serial_no=:serial_no")
    DailyPeriodicSpo2 getDailySpo2Data(String str, String str2);

    @Query("SELECT * FROM hourly_spo2 WHERE serial_no=:serial_no AND date=:date")
    List<EntityHourlySpo2> getHourlySpo2Data(String str, String str2);

    @Query("select * from hourly_spo2 where datetime(date ||' '|| start_time) >= datetime(:time) AND serial_no=:serial_no order by datetime(date ||' '|| start_time) ")
    List<EntityHourlySpo2> getHourlySpo2DataFrom(String str, String str2);

    @Query("select * , MAX(spo2_high) from hourly_spo2 where date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    EntityHourlySpo2 getLatestHighSpo2RecordHourly(String str, String str2);

    @Query("SELECT * FROM hourly_spo2 where spo2_low>0 AND spo2_high>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    EntityHourlySpo2 getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_spo2 where spo2_low>0 AND spo2_high>0 AND spo2_low>=:minFilterValue AND spo2_high<=:maxFilterValue AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    EntityHourlySpo2 getLatestRecordHourly(String str, float f, float f2);

    @Query("select * from hourly_spo2 where spo2_low>0 AND spo2_high>0 AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2);

    @Query("select * from hourly_spo2 where spo2_low>0 AND spo2_high>0 AND spo2_low>=:minFilterValue AND spo2_high<=:maxFilterValue AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2, float f, float f2);

    @Query("SELECT * FROM daily_spo2 WHERE date=:date AND serial_no=:serial_no")
    LiveData<DailyPeriodicSpo2> getLiveDailySpo2Data(String str, String str2);

    @Query("SELECT * FROM daily_spo2 WHERE serial_no=:serial_no")
    LiveData<List<DailyPeriodicSpo2>> getLiveDayWiseSpo2DataByMacAddress(String str);

    @Query("SELECT * FROM hourly_spo2 WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<EntityHourlySpo2>> getLiveHourlySpo2Data(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX(spo2_high) as spo2_high, MIN((case when spo2_low>0 then spo2_low END)) as spo2_low, AVG(spo2_avg) as spo2_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_spo2  WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlySpo2Data>> getLiveMonthWiseSpo2Data(String str);

    @Query("SELECT serial_no as mac_address,MAX(spo2_high) as spo2_high,  MIN((case when spo2_low>0 then spo2_low END)) as spo2_low, AVG(spo2_avg) as spo2_avg, strftime('%W',date)+1 as week, strftime('%Y',date) as year from daily_spo2  WHERE serial_no=:serial_no group by week,year")
    LiveData<List<WeeklySpo2Data>> getLiveWeekWiseSpo2Data(String str);

    @Query("SELECT COUNT(*) FROM daily_spo2 where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_spo2 where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * From daily_spo2 WHERE serial_no=:serial_no AND is_sync_server=0")
    List<DailyPeriodicSpo2> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    void insert(DailyPeriodicSpo2 dailyPeriodicSpo2);

    @Insert(onConflict = 1)
    void insertAll(List<EntityHourlySpo2> list);
}
