package com.coveiot.covedb.deviceinfo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
/* loaded from: classes8.dex */
public interface DeviceInfoDao {
    @Delete
    void deleteDeviceInfo(EntityDeviceInfo entityDeviceInfo);

    @Query("SELECT * FROM device_info WHERE macAddress=:macAddress")
    EntityDeviceInfo getDeviceInfoBy(String str);

    @Insert(onConflict = 1)
    void insert(EntityDeviceInfo entityDeviceInfo);
}
