package com.coveiot.android.respiratoryrate.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity;
import com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity;
import java.util.List;
@Dao
/* loaded from: classes6.dex */
public interface RespiratoryRawPPGHistoryDao {
    @Query("DELETE from daily_raw_ppg_history_table where macAddress=:macAddress")
    void deleteAllDailyRawPPGData(String str);

    @Query("DELETE from hourly_raw_ppg_history_table where macAddress=:macAddress")
    void deleteAllHourlyRawPPGData(String str);

    @Query("DELETE from daily_raw_ppg_history_table where macAddress=:macAddress AND date=:date")
    void deleteDailyRawPPGData(String str, String str2);

    @Query("DELETE from hourly_raw_ppg_history_table where macAddress=:macAddress AND date=:date")
    void deleteHourlyRawPPGData(String str, String str2);

    @Query("SELECT *  from daily_raw_ppg_history_table where macAddress=:macAddress")
    List<DailyRespiratoryRawPPGHistoryEntity> getAllDailyRawPPGData(String str);

    @Query("SELECT count(*)  from daily_raw_ppg_history_table where macAddress=:macAddress")
    int getDailyDataCount(String str);

    @Query("SELECT *  from daily_raw_ppg_history_table where macAddress=:macAddress AND date=:date ")
    DailyRespiratoryRawPPGHistoryEntity getDailyRawPPGData(String str, String str2);

    @Query("SELECT *  from hourly_raw_ppg_history_table where macAddress=:macAddress AND date=:date ")
    List<HourlyRespiratoryRawPPGDataEntity> getHourlyRawPPGData(String str, String str2);

    @Query("SELECT * from daily_raw_ppg_history_table where macAddress=:macAddress order by date LIMIT 1")
    DailyRespiratoryRawPPGHistoryEntity getOldestDailyRawPPGData(String str);

    @Insert(onConflict = 1)
    void insert(DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity);

    @Insert(onConflict = 1)
    void insert(HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity);

    @Insert(onConflict = 1)
    void insertAll(List<HourlyRespiratoryRawPPGDataEntity> list);
}
