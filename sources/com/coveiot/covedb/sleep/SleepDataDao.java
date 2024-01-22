package com.coveiot.covedb.sleep;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.sleep.model.SleepDataModelMonthWiseCommon;
import com.coveiot.covedb.sleep.model.SleepDataModelWeekWiseCommon;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface SleepDataDao {
    @Query("SELECT SUM(awake) FROM HourlySleepData WHERE date=:datee")
    int getAwakeInterval(String str);

    @Query("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=:macAddress) a group by date) b, dailysleepdata c where b.date = c.date AND c.serial_no=:macAddress")
    List<DailySleepDataAlias> getDailyLastNightSleepData(String str);

    @Query("SELECT * from dailysleepdata WHERE serial_no=:serial_no AND date BETWEEN :startDate AND :endDate")
    List<DailySleepData> getDailySleepDataBetweenDates(String str, String str2, String str3);

    @Query("SELECT * FROM dailysleepdata WHERE  date=:date AND serial_no=:serail_no")
    DailySleepData getDailySleepDataWithDate(String str, String str2);

    @Query("SELECT SUM(deep_sleep) FROM HourlySleepData WHERE date=:datee")
    int getDeepSleepInterval(String str);

    @Query("SELECT date FROM dailysleepdata WHERE serial_no=:macAddress   ORDER BY date(date)  LIMIT 1")
    String getLastDate(String str);

    @Query("select id,date,start_time,end_time,codevalue,ligth_sleep,deep_sleep,awake,intervalValue,(:todayDateTime) as 'today_date' from HourlySleepData where serial_no=:macAddress AND id between (:previousDayDateTime) and (:todayDateTime)")
    List<SleepDataModelForLastNight> getLastNightSleepDataHourly(String str, String str2, String str3);

    @Query("SELECT * FROM HourlySleepData where ligth_sleep>0 OR deep_sleep>0 AND serial_no=:macAddress  ORDER BY id DESC LIMIT 1")
    HourlySleepData getLatestRecordHourly(String str);

    @Query("SELECT SUM(ligth_sleep) FROM HourlySleepData WHERE date=:datee")
    int getLightSleepInterval(String str);

    @Query("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=:macAddress) a group by date) b, dailysleepdata c where b.date = c.date AND c.serial_no=:macAddress")
    LiveData<List<DailySleepDataAlias>> getLiveDailyLastNightSleepData(String str);

    @Query("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=:macAddress) a group by date) b, dailysleepdata c where b.date =:date AND c.serial_no=:macAddress")
    LiveData<DailySleepDataAlias> getLiveLastNightSleepData(String str, String str2);

    @Query("select id,date,start_time,end_time,codevalue,ligth_sleep,deep_sleep,awake,intervalValue,(:todayDateTime) as 'today_date' from HourlySleepData where serial_no=:macAddress AND id between (:previousDayDateTime) and (:todayDateTime)")
    LiveData<List<SleepDataModelForLastNight>> getLiveLastNightSleepDataHourly(String str, String str2, String str3);

    @Query("SELECT substr(date, 0,8) as month,sum(deep_sleep) as totalDeepSleep, sum(light_sleep) as totalLightSleep,sum(awake) as awake, count(*) as rowCount from dailysleepdata WHERE  serial_no=:macAddress GROUP BY month")
    LiveData<List<SleepDataModelMonthWiseCommon>> getLiveMonthWiseData(String str);

    @Query("SELECT * FROM dailysleepdata WHERE date LIKE:date")
    LiveData<DailySleepData> getLiveSleepData(String str);

    @Query("SELECT * FROM HourlySleepData where date LIKE:date AND serial_no=:macAddress")
    LiveData<List<HourlySleepData>> getLiveTotalMinuteData(String str, String str2);

    @Query("SELECT * FROM dailysleepdata WHERE  serial_no=:macAddress")
    LiveData<List<DailySleepData>> getLiveTotalSleepData(String str);

    @Query("SELECT * FROM dailysleepdata WHERE  serial_no=:macAddress and is_sync_server=0")
    LiveData<List<DailySleepData>> getLiveTotalUnSyncedSleepData(String str);

    @Query("SELECT strftime('%W',date)+1 as week,sum(deep_sleep) as totalDeepSleep, sum(light_sleep) as totalLightSleep,sum(awake) as awake, count(*) as rowCount from dailysleepdata WHERE  serial_no=:macAddress GROUP BY week")
    LiveData<List<SleepDataModelWeekWiseCommon>> getLiveWeekWiseData(String str);

    @Query("SELECT COUNT(*) FROM dailysleepdata where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM hourlysleepdata where date=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT * from hourlysleepdata WHERE serial_no=:serial_no AND date BETWEEN :startDate AND :endDate")
    List<HourlySleepData> getSleepDataBetweenDates(String str, String str2, String str3);

    @Query("SELECT SUM(intervalValue) FROM HourlySleepData WHERE date=:datee")
    int getSleepInterval(String str);

    @Query("SELECT * FROM HourlySleepData where date LIKE:date AND serial_no=:macAddress")
    List<HourlySleepData> getTotalMinuteData(String str, String str2);

    @Query("SELECT * FROM dailysleepdata WHERE  serial_no=:macAddress")
    List<DailySleepData> getTotalSleepData(String str);

    @Query("SELECT * FROM dailysleepdata WHERE value>0 AND serial_no=:macAddress ORDER BY _id DESC LIMIT:value")
    List<DailySleepData> getTotalSleepData(String str, Integer num);

    @Query("SELECT * FROM dailysleepdata WHERE  serial_no=:macAddress and is_sync_server=0")
    List<DailySleepData> getTotalUnSyncedSleepData(String str);

    @Query("SELECT * FROM dailysleepdata WHERE  serial_no=:macAddress and isMinMaxUpdated=0")
    List<DailySleepData> getTotalUnsyncedMinMaxHRStressSleepData(String str);

    @Insert(onConflict = 1)
    void insert(DailySleepData dailySleepData);

    @Insert(onConflict = 1)
    void insertAllSleepData(List<HourlySleepData> list);

    @Query("UPDATE dailysleepdata SET   minHr=:minHr , maxHr=:maxHr , minStress=:minStress , maxStress=:maxStress, minAmbientSound=:minAmbientSound , maxAmbientSound=:maxAmbientSound,isMinMaxUpdated=:isMinMaxUpdated WHERE serial_no=:macAddress and date=:date")
    void updateMinMaxAmbientSoundInDailySleepData(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, String str2, Integer num7);

    @Query("UPDATE dailysleepdata SET  minHr=:minHr , maxHr=:maxHr , minStress=:minStress , maxStress=:maxStress,isMinMaxUpdated=:isMinMaxUpdated WHERE serial_no=:macAddress and date=:date")
    void updateMinMaxHrStressInDailySleepData(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5);
}
