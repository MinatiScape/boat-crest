package com.coveiot.covedb.bp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.bp.model.MonthLevelBp;
import com.coveiot.covedb.bp.model.WeekLevelBp;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface BpDataDao {
    @Query("SELECT * FROM hourly_bp WHERE serial_no=:serial_no AND date=:date")
    List<EntityHourlyBp> getHourLevelBp(String str, String str2);

    @Query("select * from hourly_bp where datetime(date ||' '|| start_hour) > datetime(:time) order by datetime(date ||' '|| start_hour) ")
    List<EntityHourlyBp> getHourlyBPDataFrom(String str);

    @Query("SELECT * FROM hourly_bp where diastolic_bp>0 AND systolic_bp>0 AND serial_no=:serial_no  ORDER BY date DESC,  start_hour DESC LIMIT 1")
    EntityHourlyBp getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_bp where diastolic_bp>0 AND systolic_bp>0 AND serial_no=:serial_no  AND date=:data ORDER BY  start_hour DESC LIMIT 1")
    EntityHourlyBp getLatestRecordHourly(String str, String str2);

    @Query("SELECT * FROM daily_bp WHERE serial_no=:serial_no AND date=:date")
    LiveData<EntityDailyBp> getLiveDayLevelBp(String str, String str2);

    @Query("SELECT * FROM daily_bp WHERE serial_no=:serial_no GROUP BY date")
    LiveData<List<EntityDailyBp>> getLiveDayWiseBp(String str);

    @Query("SELECT * FROM hourly_bp WHERE serial_no=:serial_no AND date=:date")
    LiveData<List<EntityHourlyBp>> getLiveHourLevelBp(String str, String str2);

    @Query("SELECT AVG((case when systolic_bp>0 then systolic_bp END)) as systolic_bp, AVG((case when diastolic_bp>0 then diastolic_bp END)) as diastolic_bp,  strftime('%m',date)+1 as month,strftime('%Y',date) as year FROM daily_bp WHERE serial_no=:serial_no GROUP BY month,year")
    LiveData<List<MonthLevelBp>> getLiveMonthWiseBp(String str);

    @Query("SELECT AVG((case when systolic_bp>0 then systolic_bp END)) as systolic_bp, AVG((case when diastolic_bp>0 then diastolic_bp END)) as diastolic_bp,  strftime('%W',date)+1 as week,strftime('%Y',date) as year FROM daily_bp WHERE serial_no=:serial_no GROUP BY week,year")
    LiveData<List<WeekLevelBp>> getLiveWeekWiseBp(String str);

    @Query("SELECT COUNT(*) FROM daily_bp where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_bp where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * FROM daily_bp WHERE serial_no=:serial_no AND is_sync_server=0")
    List<EntityDailyBp> getUnSyncedBpData(String str);

    @Insert(onConflict = 1)
    void insert(EntityDailyBp entityDailyBp);

    @Insert(onConflict = 1)
    void insert(List<EntityHourlyBp> list);
}
