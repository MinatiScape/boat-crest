package com.coveiot.khsmadb.deviceInfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhSmaDeviceInfoDao {
    @Query("SELECT * from KhSmaDeviceInfo where macAddress=:macAddress")
    @Nullable
    KhSmaDeviceInfo getDeviceInfo(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhSmaDeviceInfo khSmaDeviceInfo);

    @Query("UPDATE KhSmaDeviceInfo SET bpDataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateBloodPressureLastSyncTime(@NotNull String str, long j);

    @Query("UPDATE KhSmaDeviceInfo SET hrDataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateHeartRateLastSyncTime(@NotNull String str, long j);

    @Query("UPDATE KhSmaDeviceInfo SET sleepDataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateSleepLastSyncTime(@NotNull String str, long j);

    @Query("UPDATE KhSmaDeviceInfo SET spO2DataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateSpO2LastSyncTime(@NotNull String str, long j);

    @Query("UPDATE KhSmaDeviceInfo SET stepDataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateStepLastSyncTime(@NotNull String str, long j);

    @Query("UPDATE KhSmaDeviceInfo SET temperatureDataLastSyncTime=:lastSyncTimestamp WHERE macAddress=:macAddress")
    void updateTemperatureLastSyncTime(@NotNull String str, long j);
}
