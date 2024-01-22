package com.coveiot.khjstyledb.deviceinfo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
/* loaded from: classes8.dex */
public interface KHJstyleDeviceInfoDao {
    @Delete
    void deleteDeviceInfo(KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo);

    @Query("SELECT * FROM device_info WHERE macAddress=:macAddress")
    KHJstyleEntityDeviceInfo getDeviceInfoBy(String str);

    @Insert(onConflict = 1)
    void insert(KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo);
}
