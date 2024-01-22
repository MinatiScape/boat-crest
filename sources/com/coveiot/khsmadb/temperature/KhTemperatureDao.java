package com.coveiot.khsmadb.temperature;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhTemperatureDao {
    @Query("SELECT * from KhBleTemperature where mMacAddress=:macAddress and isProcessed=:isProcessed and mTemperature>0 ORDER BY mTime")
    @Nullable
    List<KhBleTemperature> getAllTemperatureData(@NotNull String str, boolean z);

    @Query("SELECT * from KhBleTemperature where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBleTemperature> getTemperatureDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * from KhBleTemperature where mMacAddress=:macAddress and convertedDate=:date ORDER BY mTime")
    @Nullable
    List<KhBleTemperature> getTemperatureDataFor(@NotNull String str, @NotNull String str2);

    @Query("SELECT * from KhBleTemperature where mMacAddress=:macAddress and mTime between :startTime and :endTime")
    @Nullable
    List<KhBleTemperature> getTemperatureDataListBetweenTime(@NotNull String str, int i, int i2);

    @Query("SELECT DISTINCT convertedDate from KhBleTemperature where mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<String> getUniqueDatesForTemperature(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleTemperature khBleTemperature);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleTemperature> list);

    @Query("UPDATE KhBleTemperature SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateTemperatureDataProcessedBefore(@NotNull String str, long j);
}
