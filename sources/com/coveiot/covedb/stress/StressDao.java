package com.coveiot.covedb.stress;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.covedb.stress.model.MonthlyStressData;
import com.coveiot.covedb.stress.model.WeeklyStressData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface StressDao {
    @Query("SELECT * FROM daily_stress WHERE date=:date AND serial_no=:serial_no")
    DailyStress getDailyStressData(String str, String str2);

    @Query("SELECT * FROM hourly_stress WHERE serial_no=:serial_no AND date=:date")
    List<HourlyStress> getHourlyStressData(String str, String str2);

    @Query("select * from hourly_stress where datetime(date ||' '|| start_time) >= datetime(:time) AND serial_no=:serial_no order by datetime(date ||' '|| start_time) ")
    List<HourlyStress> getHourlyStressDataFrom(String str, String str2);

    @Query("select * , MAX(stress_high) from hourly_stress where date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyStress getLatestHighStressRecordHourly(String str, String str2);

    @Query("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyStress getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND stress_low>=:minFilterValue AND stress_high<=:maxFilterValue AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyStress getLatestRecordHourly(String str, float f, float f2);

    @Query("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    LiveData<HourlyStress> getLatestRecordHourlyLiveData(String str);

    @Query("select * from hourly_stress where stress_low>0 AND stress_high>0 AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyStress getLatestStressRecordHourly(String str, String str2);

    @Query("select * from hourly_stress where stress_low>0 AND stress_high>0 AND stress_low>=:minFilterValue AND stress_high<=:maxFilterValue AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyStress getLatestStressRecordHourly(String str, String str2, float f, float f2);

    @Query("SELECT * FROM daily_stress WHERE date=:date AND serial_no=:serial_no")
    LiveData<DailyStress> getLiveDailyStressData(String str, String str2);

    @Query("SELECT * FROM daily_stress WHERE serial_no=:serial_no")
    LiveData<List<DailyStress>> getLiveDayWiseStressDataByMacAddress(String str);

    @Query("SELECT * FROM daily_stress WHERE serial_no=:serial_no and date BETWEEN :startDate AND :endDate")
    List<DailyStress> getLiveDayWiseStressDataByStartAndEndDate(String str, String str2, String str3);

    @Query("SELECT * FROM hourly_stress WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<HourlyStress>> getLiveHourlyStressData(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX((case when stress_high>0 then stress_high END)) as stress_high, MIN((case when stress_low>0 then stress_low END)) as stress_low, AVG((case when stress_avg>0 then stress_avg END)) as stress_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_stress WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlyStressData>> getLiveMonthWiseStressData(String str);

    @Query("SELECT serial_no as mac_address,MAX((case when stress_high>0 then stress_high END)) as stress_high, MIN((case when stress_low>0 then stress_low END)) as stress_low, AVG((case when stress_avg>0 then stress_avg END)) as stress_avg, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_stress  WHERE serial_no=:serial_no  group by week,year")
    LiveData<List<WeeklyStressData>> getLiveWeekWiseStressData(String str);

    @Query("select max(stress_high) as maxValue, min(stress_low)  as minValue from (select stress_low,stress_high, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_stress where date>=:startDate and date<=:endDate and stress_low >0  and stress_high > 0 and serial_no=:serial_no)    where  datestarttime >=:startDateTime and dateendtime <=:endDateTime")
    MinMaxData getMinMaxStress(String str, String str2, String str3, String str4, String str5);

    @Query("SELECT COUNT(*) FROM daily_stress where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_stress where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * FROM hourly_stress WHERE serial_no=:serial_no AND date=:date and start_time=:startTime AND end_time=:endTime")
    HourlyStress getStressDataWithDateAndTime(String str, String str2, String str3, String str4);

    @Query("SELECT * From daily_stress WHERE serial_no=:serial_no AND is_sync_server=0")
    List<DailyStress> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    void insert(DailyStress dailyStress);

    @Insert(onConflict = 1)
    void insertAll(List<HourlyStress> list);
}
