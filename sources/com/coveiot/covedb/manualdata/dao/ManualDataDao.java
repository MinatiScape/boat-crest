package com.coveiot.covedb.manualdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface ManualDataDao {
    @Query("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC")
    LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3);

    @Query("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND (strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) BETWEEN :fromDate AND :toDate) AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC")
    LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3, String str4);

    @Query("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND (strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) BETWEEN :fromDate AND :toDate) AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC")
    List<EntityManualData> getBpDataWithoutLiveData(String str, String str2, String str3, String str4);

    @Query("Select *, max(spo2) from manual_data group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))")
    LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay();

    @Query("Select *, max(spo2) from manual_data WHERE serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))")
    LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE hrv>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no)")
    LiveData<List<EntityManualData>> getHrvData(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE hrv>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    LiveData<List<EntityManualData>> getHrvData(String str, String str2, String str3);

    @Query("Select * from manual_data  where systolicBp>0 AND timeStamp in (Select max(timeStamp) from manual_data  WHERE source=COALESCE(:source,source))")
    EntityManualData getLastBpDataFor(String str);

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data  WHERE serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<List<EntityManualData>> getLastBpDataForEachDay(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE diastolicBp>0 AND systolicBp > 0 AND serial_no=COALESCE(:serialNo,serial_no) ORDER BY timeStamp DESC LIMIT 1")
    EntityManualData getLastMeasuredBp(String str);

    @Query("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getLastMeasuredBpHourlyDataForDate(String str);

    @Query("SELECT * FROM manual_data WHERE diastolicBp>0 AND systolicBp > 0 AND serial_no=COALESCE(:serialNo,serial_no) ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredBpLiveData(String str);

    @Query("SELECT * FROM manual_data WHERE hrv>0  AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredHRV(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(:serialNo,serial_no) ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredSpo2(String str);

    @Query("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND isLevelInterpretation=:isLevelInterpretation ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2, int i);

    @Query("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(:serialNo,serial_no) ORDER BY timeStamp DESC LIMIT 1")
    EntityManualData getLastMeasuredSpo2ByMacAddress(String str);

    @Query("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND isLevelInterpretation=:isLevelInterpretation ORDER BY timeStamp DESC")
    LiveData<List<EntityManualData>> getLastMeasuredSpo2Data(String str, String str2, String str3, int i);

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=:date group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str);

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=:date AND serial_no=COALESCE(:serialNo,serial_no) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2);

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=:date AND serial_no=COALESCE(:serialNo,serial_no) AND isLevelInterpretation=:isLevelInterpretation group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2, int i);

    @Query("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str);

    @Query("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3);

    @Query("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND isLevelInterpretation=:islevelInterpretation AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3, int i);

    @Query("SELECT * FROM manual_data WHERE stress>0  AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) ORDER BY timeStamp DESC LIMIT 1")
    LiveData<EntityManualData> getLastMeasuredStress(String str, String str2);

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<List<EntityManualData>> getLastSpo2DataForEachDay();

    @Query("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data  WHERE serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))")
    LiveData<List<EntityManualData>> getLastSpo2DataForEachDay(String str, String str2);

    @Query("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str);

    @Query("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3);

    @Query("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND isLevelInterpretation=:isLevelInterpretation group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3, int i);

    @Query("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str);

    @Query("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3);

    @Query("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND isLevelInterpretation=:isLevelInterpretation group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp")
    LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3, int i);

    @Query("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    LiveData<List<EntityManualData>> getSpo2DataList(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    List<EntityManualData> getSpo2DataList(String str, String str2, String str3);

    @Query("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3);

    @Query("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source) AND isLevelInterpretation=:isLevelInterpretation")
    LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3, int i);

    @Query("SELECT * FROM manual_data WHERE stress>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no)")
    LiveData<List<EntityManualData>> getStressData(String str, String str2);

    @Query("SELECT * FROM manual_data WHERE stress>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    LiveData<List<EntityManualData>> getStressData(String str, String str2, String str3);

    @Query("SELECT * FROM manual_data WHERE temperature>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = :date AND serial_no=COALESCE(:serialNo,serial_no) AND source=COALESCE(:source,source)")
    LiveData<List<EntityManualData>> getTemperatureData(String str, String str2, String str3);

    @Query("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND  isSyncedWithServer=0")
    List<EntityManualData> getUnSyncedBpData();

    @Query("SELECT * FROM manual_data WHERE isSyncedWithServer=0")
    List<EntityManualData> getUnSyncedData();

    @Insert(onConflict = 1)
    void insert(EntityManualData entityManualData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityManualData> list);
}
