package com.coveiot.khjstyledb.heartrate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.khjstyledb.heartrate.model.SessionHR;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface KHJstyleHeartRateDao {
    @Query("SELECT heartRate as hrValue, timeStamp as hrTimeStamp FROM history_session_hr WHERE serialNo=:serial_no AND timeStamp BETWEEN :startTime AND :endTime")
    List<SessionHR> getHeartRateDataListBetweenTime(Long l, Long l2, String str);

    @Query("SELECT heartRate as hrValue, timeStamp as hrTimeStamp FROM history_session_hr WHERE serialNo=:serial_no")
    List<SessionHR> getSessionHeartRateDataList(String str);

    @Insert(onConflict = 1)
    void insert(KHJstyleEntitySessionHeartRateData kHJstyleEntitySessionHeartRateData);

    @Insert(onConflict = 1)
    void insertAll(List<KHJstyleEntitySessionHeartRateData> list);
}
