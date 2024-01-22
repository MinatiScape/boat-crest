package com.coveiot.khsmadb.sleep;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhSleepDao {
    @Query("SELECT * from KhBleSleep WHERE mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<KhBleSleep> getAllSleepData(@NotNull String str);

    @Query("SELECT * from KhBleSleep where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBleSleep> getSleepDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT DISTINCT convertedDate from KhBleSleep WHERE mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<String> getUniqueDatesForSleep(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleSleep khBleSleep);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleSleep> list);

    @Query("UPDATE KhBleSleep SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp and isProcessed == 0")
    void updateSleepDataProcessedBefore(@NotNull String str, long j);
}
