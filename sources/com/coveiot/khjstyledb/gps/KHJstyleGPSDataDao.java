package com.coveiot.khjstyledb.gps;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.khjstyledb.gps.model.KHSessionGPSData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface KHJstyleGPSDataDao {
    @Query("SELECT latitude as latitudeValue, longitude as longitudeValue, timeStamp as gpsTimeStamp FROM history_session_gps WHERE serialNo=:serial_no AND timeStamp BETWEEN :startTime AND :endTime")
    List<KHSessionGPSData> getGPSDataListBetweenTime(Long l, Long l2, String str);

    @Insert(onConflict = 5)
    void insert(KHJstyleSessionGPSData kHJstyleSessionGPSData);

    @Insert(onConflict = 5)
    void insertAll(List<KHJstyleSessionGPSData> list);
}
