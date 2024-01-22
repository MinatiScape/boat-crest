package com.coveiot.khsmadb.heartrate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhHeartRateDao {
    @Query("SELECT * from KhBleHeartRate where mMacAddress=:macAddress and isProcessed=:isProcessed and mBpm>0 ORDER BY mTime")
    @Nullable
    List<KhBleHeartRate> getAllHeartRateData(@NotNull String str, boolean z);

    @Query("SELECT * from KhBleHeartRate where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBleHeartRate> getHeartRateDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * from KhBleHeartRate where mMacAddress=:macAddress and convertedDate=:date ORDER BY mTime")
    @Nullable
    List<KhBleHeartRate> getHeartRateDataFor(@NotNull String str, @NotNull String str2);

    @Query("SELECT * from KhBleHeartRate where mMacAddress=:macAddress and mTime between :startTime and :endTime")
    @Nullable
    List<KhBleHeartRate> getHeartRateDataListBetweenTime(@NotNull String str, int i, int i2);

    @Query("SELECT DISTINCT convertedDate from KhBleHeartRate where mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<String> getUniqueDatesForHeartRate(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleHeartRate khBleHeartRate);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleHeartRate> list);

    @Query("UPDATE KhBleHeartRate SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateHeartRateDataProcessedBefore(@NotNull String str, long j);

    @Query("UPDATE KhBleHeartRate SET isProcessedInWorkout = 1 WHERE mMacAddress=:macAddress AND mTime<:thresholdTimestamp AND isProcessedInWorkout==0")
    void updateWorkoutHeartRateDataProcessedBefore(@NotNull String str, long j);
}
