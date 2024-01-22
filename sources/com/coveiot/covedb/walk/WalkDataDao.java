package com.coveiot.covedb.walk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covedb.walk.model.PersonalBest;
import com.coveiot.covedb.walk.model.StepsDataModelMonthWiseCommon;
import com.coveiot.covedb.walk.model.StepsDataModelWeekWiseCommon;
import java.util.ArrayList;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface WalkDataDao {
    @Query("DELETE from dailywalkdata where mDate=:date AND serial_no=:address")
    int deleteDailyWalkDataFor(String str, String str2);

    @Query("DELETE from hourlywalkdata where date=:date AND serial_no=:address")
    int deleteHourlyWalkDataFor(String str, String str2);

    @Query("select mDate,steps_target,serial_no,active_time,SUM(steps) as steps,SUM(distance) as distance,SUM(calories) as calories,SUM(pace) as pace,is_sync_server from dailywalkdata GROUP BY mDate")
    List<DailyWalkData> getAllDailyWalkData();

    @Query("SELECT * FROM dailywalkdata WHERE serial_no=:macAddress ORDER BY date(mDate) ")
    List<DailyWalkData> getCompleteDailyWalkData(String str);

    @Query("SELECT SUM(steps) as value from dailywalkdata WHERE strftime('%W',mDate)=strftime('%W','now')")
    LiveData<Integer> getCurrentWeekStepCount();

    @Query("SELECT * FROM dailywalkdata WHERE mDate =:date AND serial_no=:macAddress")
    DailyWalkData getDailyWalkData(String str, String str2);

    @Query("SELECT * from dailywalkdata WHERE serial_no=:serial_no AND mDate BETWEEN :startDate AND :endDate")
    List<DailyWalkData> getDailyWalkDataBetweenDates(String str, String str2, String str3);

    @Query("SELECT * FROM dailywalkdata WHERE  mDate=:date AND serial_no=:serail_no")
    DailyWalkData getDailyWalkDataWithDate(String str, String str2);

    @Query("SELECT * FROM HourlyWalkData WHERE date LIKE :datee AND serial_no=:macAddress")
    List<HourlyWalkData> getFiveMinuteData(String str, String str2);

    @Query("SELECT * FROM hourlywalkdata WHERE interval_value>0 AND date=:date AND serial_no=:serail_no ORDER BY start_time DESC LIMIT 1 ")
    HourlyWalkData getHourlyLatestRecordFor(String str, String str2);

    @Query("SELECT mDate FROM dailywalkdata  WHERE serial_no=:macAddress ORDER BY date(mDate)  LIMIT 1")
    String getLastDate(String str);

    @Query("SELECT * FROM dailywalkdata WHERE serial_no=:serial_no ORDER BY date(mDate) DESC  LIMIT 1")
    DailyWalkData getLatestDayData(String str);

    @Query("SELECT * FROM dailywalkdata where steps>0 and serial_no=:serial_no ORDER BY date(mDate) DESC  LIMIT 1")
    DailyWalkData getLatestDayDataWithValue(String str);

    @Query("SELECT * FROM hourlywalkdata where interval_value>0 AND serial_no=:serial_no ORDER BY id DESC LIMIT 1")
    HourlyWalkData getLatestRecordHourly(String str);

    @Query("select mDate,steps_target,serial_no,active_time,SUM(steps) as steps,SUM(distance) as distance,SUM(calories) as calories,SUM(pace) as pace,is_sync_server from dailywalkdata GROUP BY mDate")
    LiveData<List<DailyWalkData>> getLiveAllDailyWalkData();

    @Query("SELECT * FROM dailywalkdata WHERE serial_no=:macAddress ORDER BY date(mDate) ")
    LiveData<List<DailyWalkData>> getLiveCompleteDailyWalkData(String str);

    @Query("SELECT * FROM HourlyWalkData WHERE date LIKE :datee AND serial_no=:macAddress")
    LiveData<List<HourlyWalkData>> getLiveFiveMinuteData(String str, String str2);

    @Query("SELECT date,start_time,end_time,serial_no,id,active_time, SUM(interval_value) as interval_value ,SUM(distance) as distance ,SUM(calories) as calories, codevalue FROM HourlyWalkData WHERE date LIKE :datee GROUP BY start_time")
    LiveData<List<HourlyWalkData>> getLiveTotalFiveMinuteData(String str);

    @Query("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories, strftime('%Y',mDate) as year, substr(mDate, 0,8) as month FROM dailywalkdata GROUP BY month, year")
    LiveData<List<StepsDataModelMonthWiseCommon>> getLiveTotalWalkDataMonthWiseCommon();

    @Query("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories ,strftime('%Y',mDate) as year,strftime('%W',mDate)+1 as week FROM dailywalkdata GROUP BY week,year")
    LiveData<List<StepsDataModelWeekWiseCommon>> getLiveTotalWalkDataWeekWiseCommon();

    @Query("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories, strftime('%Y',mDate) as year, substr(mDate, 0,8) as month FROM dailywalkdata WHERE serial_no=:macAddress GROUP BY month, year")
    LiveData<List<StepsDataModelMonthWiseCommon>> getLiveWalkDataMonthWiseCommon(String str);

    @Query("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories,strftime('%Y',mDate) as year, strftime('%W',mDate)+1 as week FROM dailywalkdata WHERE serial_no=:macAddress GROUP BY week,year")
    LiveData<List<StepsDataModelWeekWiseCommon>> getLiveWalkDataWeekWiseCommon(String str);

    @Query("SELECT mDate as date, MAX(steps) as value from dailywalkdata")
    LiveData<PersonalBest> getMaxValueTillData();

    @Query("SELECT * FROM HourlyWalkData WHERE serial_no=:macAddress AND date LIKE :datee ORDER BY start_time")
    List<HourlyWalkData> getOrderedHourlyWalkDataFor(String str, String str2);

    @Query("SELECT COUNT(*) FROM dailywalkdata where serial_no=:serial_no")
    int getRowCount(String str);

    @Query("SELECT COUNT(*) FROM dailywalkdata where mDate=:date AND serial_no=:serial_no")
    int getRowCount(String str, String str2);

    @Query("SELECT mDate FROM dailywalkdata   ORDER BY date(mDate)  LIMIT 1")
    String getTOTALLastDate();

    @Query("SELECT SUM(calories) from hourlywalkdata where date=:date AND serial_no=:address")
    int getTotalCalories(String str, String str2);

    @Query("SELECT SUM(distance) from hourlywalkdata where date=:date AND serial_no=:address")
    int getTotalDistance(String str, String str2);

    @Query("SELECT date,start_time,end_time,serial_no,id, active_time,SUM(interval_value) as interval_value ,SUM(distance) as distance ,SUM(calories) as calories, codevalue FROM HourlyWalkData WHERE date LIKE :datee GROUP BY start_time")
    List<HourlyWalkData> getTotalFiveMinuteData(String str);

    @Query("SELECT SUM(interval_value) from hourlywalkdata where date=:date AND serial_no=:address")
    int getTotalSteps(String str, String str2);

    @Query("SELECT * FROM dailywalkdata where serial_no=:address AND is_sync_server=0")
    List<DailyWalkData> getTotalUnSyncedWalkData(String str);

    @Query("SELECT mDate,active_time,SUM(steps) as steps,steps_target,SUM(calories) as calories,SUM(distance) as distance,SUM(pace) as pace,serial_no,is_sync_server FROM dailywalkdata WHERE mDate LIKE:datee")
    LiveData<DailyWalkData> getTotalWalkData(String str);

    @Query("SELECT SUM(interval_value) FROM HourlyWalkData WHERE date LIKE :datee AND serial_no=:macAddress")
    int getWalkCountFor(String str, String str2);

    @Query("SELECT * FROM dailywalkdata WHERE mDate LIKE:datee AND serial_no=:macAddress")
    LiveData<DailyWalkData> getWalkData(String str, String str2);

    @Query("SELECT * from hourlywalkdata WHERE serial_no=:serial_no AND date BETWEEN :startDate AND :endDate")
    LiveData<List<HourlyWalkData>> getWalkDataBetweenDates(String str, String str2, String str3);

    @Insert(onConflict = 1)
    void insert(DailyWalkData dailyWalkData);

    @Insert(onConflict = 1)
    void insertAll(List<HourlyWalkData> list);

    @Query("UPDATE dailywalkdata SET calories = :calorievalue WHERE mDate =:date and serial_no =:mac_address")
    void updateDailyCalorieValue(int i, String str, String str2);

    @Query("UPDATE dailywalkdata SET distance = :distanceValue WHERE mDate =:date and serial_no =:mac_address")
    void updateDailyDistanceValue(int i, String str, String str2);

    @Query("UPDATE dailywalkdata SET steps_target = :target WHERE mDate between (:startDate) and (:endDate) and serial_no =:mac_address")
    void updateDailyTargetValue(int i, String str, String str2, String str3);

    @Query("UPDATE hourlywalkdata SET calorie_code_value = :calorieCodeValue, calories = :calorie WHERE date =:date AND start_time=:startTime AND end_time  =:endTime AND serial_no =:mac_address")
    void updateHourlyCalorieValue(ArrayList<Float> arrayList, int i, String str, String str2, String str3, String str4);

    @Query("UPDATE hourlywalkdata SET distance_code_value = :distanceCodeValue, distance = :distance WHERE date =:date and start_time=:startTime and end_time  =:endTime and serial_no =:mac_address")
    void updateHourlyDistanceValue(ArrayList<Integer> arrayList, int i, String str, String str2, String str3, String str4);
}
