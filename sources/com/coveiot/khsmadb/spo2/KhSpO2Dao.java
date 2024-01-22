package com.coveiot.khsmadb.spo2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhSpO2Dao {
    @Query("SELECT * from KhBleSpO2 where mMacAddress=:macAddress and isProcessed=:isProcessed and mValue>0 ORDER BY mTime")
    @Nullable
    List<KhBleSpO2> getAllSpO2Data(@NotNull String str, boolean z);

    @Query("SELECT * from KhBleSpO2 where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBleSpO2> getSpO2DataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * from KhBleSpO2 where mMacAddress=:macAddress and convertedDate=:date ORDER BY mTime")
    @Nullable
    List<KhBleSpO2> getSpO2DataFor(@NotNull String str, @NotNull String str2);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleSpO2 khBleSpO2);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleSpO2> list);

    @Query("UPDATE KhBleSpO2 SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateSpO2DataProcessedBefore(@NotNull String str, long j);
}
