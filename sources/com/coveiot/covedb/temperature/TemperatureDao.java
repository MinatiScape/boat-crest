package com.coveiot.covedb.temperature;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.temperature.model.MonthlyTemperatureData;
import com.coveiot.covedb.temperature.model.WeeklyTemperatureData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface TemperatureDao {
    @Query("SELECT * FROM hourly_temperature WHERE serial_no=:serial_no AND date=:date")
    List<HourlyTemperature> getHourlyTemperatureData(String str, String str2);

    @Query("select * from hourly_temperature where datetime(date ||' '|| start_time) >= datetime(:time) AND serial_no=:serial_no order by datetime(date ||' '|| start_time) ")
    List<HourlyTemperature> getHourlyTemperatureDataFrom(String str, String str2);

    @Query("select * , MAX(temperature_high) from hourly_temperature where date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyTemperature getLatestHighTemperatureRecordHourly(String str, String str2);

    @Query("SELECT * FROM hourly_temperature where temperature_low>0 AND temperature_high>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyTemperature getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_temperature where temperature_low>0 AND temperature_high>0 AND temperature_low>=:minFilterValue AND temperature_high<=:maxFilterValue AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyTemperature getLatestRecordHourly(String str, float f, float f2);

    @Query("select * from hourly_temperature where temperature_low>0 AND temperature_high>0 AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2);

    @Query("select * from hourly_temperature where temperature_low>0 AND temperature_high>0 AND temperature_low>=:minFilterValue AND temperature_high<=:maxFilterValue AND date=:date AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2, float f, float f2);

    @Query("SELECT * FROM daily_temperature WHERE date=:date AND serial_no=:serial_no")
    LiveData<DailyTemperature> getLiveDailyTemperatureData(String str, String str2);

    @Query("SELECT * FROM daily_temperature WHERE serial_no=:serial_no")
    LiveData<List<DailyTemperature>> getLiveDayWiseTemperatureDataByMacAddress(String str);

    @Query("SELECT * FROM hourly_temperature WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<HourlyTemperature>> getLiveHourlyTemperatureData(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX(temperature_high) as temperature_high, MIN((case when temperature_low>0 then temperature_low END)) as temperature_low, AVG(temperature_avg) as temperature_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_temperature WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlyTemperatureData>> getLiveMonthWiseTemperatureData(String str);

    @Query("SELECT serial_no as mac_address,MAX(temperature_high) as temperature_high, MIN((case when temperature_low>0 then temperature_low END)) as temperature_low, AVG(temperature_avg) as temperature_avg, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_temperature  WHERE serial_no=:serial_no  group by week,year")
    LiveData<List<WeeklyTemperatureData>> getLiveWeekWiseTemperatureData(String str);

    @Query("SELECT COUNT(*) FROM daily_temperature where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_temperature where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * From daily_temperature WHERE serial_no=:serial_no AND is_sync_server=0")
    List<DailyTemperature> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    void insert(DailyTemperature dailyTemperature);

    @Insert(onConflict = 1)
    void insertAll(List<HourlyTemperature> list);
}
