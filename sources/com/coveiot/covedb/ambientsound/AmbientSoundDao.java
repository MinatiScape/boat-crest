package com.coveiot.covedb.ambientsound;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.coveiot.covedb.ambientsound.model.MonthlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import com.coveiot.covedb.stress.model.MinMaxData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface AmbientSoundDao {
    @Query("SELECT * FROM hourly_ambient_sound_table WHERE serial_no=:serial_no AND date=:date and start_time=:startTime AND end_time=:endTime")
    EntityHourlyAmbientSoundData getAmbientSoundDataWithDateAndTime(String str, String str2, String str3, String str4);

    @Query("SELECT * FROM hourly_ambient_sound_table WHERE serial_no=:serial_no AND date=:date")
    List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundData(String str, String str2);

    @Query("select * from hourly_ambient_sound_table where datetime(date ||' '|| start_time) >= datetime(:time)  AND serial_no=:serial_no  order by datetime(date ||' '|| start_time) ")
    List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundDataFrom(String str, String str2);

    @Query(" SELECT * FROM hourly_ambient_sound_table where min_ambient_sound >0  AND serial_no=:serial_no ORDER BY date DESC , start_time DESC LIMIT 1")
    LiveData<EntityHourlyAmbientSoundData> getLatestHourlyAmbientSoundData(String str);

    @Query("SELECT * FROM hourly_ambient_sound_table where min_ambient_sound>0 AND max_ambient_sound>0 AND serial_no=:serial_no  ORDER BY date DESC,  start_time DESC LIMIT 1")
    EntityHourlyAmbientSoundData getLatestRecordHourly(String str);

    @Query("SELECT * FROM hourly_ambient_sound_table where min_ambient_sound>0 AND max_ambient_sound>0 AND serial_no=:serial_no AND date=:date  ORDER BY   start_time DESC LIMIT 1")
    EntityHourlyAmbientSoundData getLatestRecordHourly(String str, String str2);

    @Query("SELECT * FROM daily_ambient_sound_table WHERE date=:date AND serial_no=:serial_no")
    LiveData<EntityDailyAmbientSoundData> getLiveDailyAmbientSoundData(String str, String str2);

    @Query("SELECT * FROM daily_ambient_sound_table WHERE serial_no=:serial_no")
    LiveData<List<EntityDailyAmbientSoundData>> getLiveDayWiseAmbientSoundDataByMacAddress(String str);

    @Query("SELECT * FROM hourly_ambient_sound_table WHERE date=:date AND serial_no=:serial_no")
    LiveData<List<EntityHourlyAmbientSoundData>> getLiveHourlyAmbientSoundData(String str, String str2);

    @Query("SELECT serial_no as mac_address,MAX(max_ambient_sound) as maxAmbientSound, MIN((case when min_ambient_sound>0 then min_ambient_sound END)) as minAmbientSound, AVG(avg_ambient_sound) as avgAmbientSound, strftime('%m',date) as month,strftime('%Y',date) as year from daily_ambient_sound_table WHERE serial_no=:serial_no  group by month,year")
    LiveData<List<MonthlyAmbientSoundData>> getLiveMonthWiseAmbientSoundData(String str);

    @Query("SELECT serial_no as mac_address,MAX(max_ambient_sound) as maxAmbientSound, MIN((case when min_ambient_sound>0 then min_ambient_sound END)) as minAmbientSound, AVG(avg_ambient_sound) as avgAmbientSound, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_ambient_sound_table  WHERE serial_no=:serial_no  group by week,year")
    LiveData<List<WeeklyAmbientSoundData>> getLiveWeekWiseAmbientSoundData(String str);

    @Query("select max(max_ambient_sound) as maxValue, min(min_ambient_sound)  as minValue from (select min_ambient_sound,max_ambient_sound, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_ambient_sound_table where date>=:startDate and date<=:endDate and min_ambient_sound >0  and max_ambient_sound > 0 and serial_no=:serial_no)    where datestarttime >=:startDateTime and dateendtime <=:endDateTime")
    MinMaxData getMinMaxAmbientSound(String str, String str2, String str3, String str4, String str5);

    @Query("SELECT COUNT(*) FROM daily_ambient_sound_table where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM daily_ambient_sound_table where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * From daily_ambient_sound_table WHERE serial_no=:serial_no AND is_sync_server=0")
    List<EntityDailyAmbientSoundData> getTotalUnSyncedData(String str);

    @Insert(onConflict = 1)
    long insert(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityHourlyAmbientSoundData> list);

    @Insert(onConflict = 1)
    void insertAllDailyAmbientSound(List<EntityDailyAmbientSoundData> list);

    @Insert(onConflict = 1)
    void insertDailyAmbientSound(EntityDailyAmbientSoundData entityDailyAmbientSoundData);

    @Update
    int updateHourlyAmbientSoundData(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData);
}
