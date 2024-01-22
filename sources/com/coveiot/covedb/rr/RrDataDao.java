package com.coveiot.covedb.rr;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.rr.model.MonthlyRrData;
import com.coveiot.covedb.rr.model.WeeklyRrData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface RrDataDao {
    @Query("SELECT * FROM hourly_rr_table WHERE serial_no=:serial_no AND date=:date")
    List<EntityHourlyRrData> getHourLevelRr(String str, String str2);

    @Query("SELECT * FROM daily_rr_table WHERE serial_no=:serial_no AND date=:date")
    LiveData<EntityDailyRrData> getLiveDayLevelRr(String str, String str2);

    @Query("SELECT * FROM daily_rr_table WHERE serial_no=:serial_no GROUP BY date")
    LiveData<List<EntityDailyRrData>> getLiveDayWiseRr(String str);

    @Query("SELECT * FROM daily_rr_table WHERE serial_no=:serial_no")
    LiveData<List<EntityDailyRrData>> getLiveDayWiseRrDataByMacAddress(String str);

    @Query("SELECT * FROM hourly_rr_table WHERE serial_no=:serial_no AND date=:date")
    LiveData<List<EntityHourlyRrData>> getLiveHourLevelRr(String str, String str2);

    @Query("SELECT AVG((case when avgRrValue>0 then avgRrValue END)) as avgRrValue, strftime('%m',date)+1 as month,strftime('%Y',date) as year FROM daily_rr_table WHERE serial_no=:serial_no GROUP BY month,year")
    LiveData<List<MonthlyRrData>> getLiveMonthWiseRr(String str);

    @Query("SELECT AVG((case when avgRrValue>0 then avgRrValue END)) as avgRrValue,strftime('%W',date)+1 as week,strftime('%Y',date) as year FROM daily_rr_table WHERE serial_no=:serial_no GROUP BY week,year")
    LiveData<List<WeeklyRrData>> getLiveWeekWiseRr(String str);

    @Query("SELECT COUNT(*) FROM daily_rr_table where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_rr_table where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * FROM daily_rr_table WHERE serial_no=:serial_no AND is_sync_server=0")
    List<EntityDailyRrData> getUnSyncedRrData(String str);

    @Insert(onConflict = 1)
    void insert(EntityHourlyRrData entityHourlyRrData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityHourlyRrData> list);

    @Insert(onConflict = 1)
    void insertAllDailyRr(List<EntityDailyRrData> list);

    @Insert(onConflict = 1)
    void insertDailyRr(EntityDailyRrData entityDailyRrData);
}
