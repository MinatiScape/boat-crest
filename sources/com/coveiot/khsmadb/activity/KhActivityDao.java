package com.coveiot.khsmadb.activity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhActivityDao {
    @Query("DELETE from KhBleActivity where mMacAddress=:macAddress AND date=:mDate")
    void deleteActivityInfoForDate(@NotNull String str, @NotNull String str2);

    @Query("DELETE from KhBleActivity where mMacAddress=:macAddress")
    void deleteAllActivityInfo(@NotNull String str);

    @Query("DELETE from KhBleActivity where mMacAddress=:macAddress AND mTime<:mTimeStamp")
    void deleteAllActivityInfoFor(@NotNull String str, int i);

    @Query("DELETE from KhBleActivity where mMacAddress=:macAddress AND isProcessed==1 AND mMode==0")
    void deleteAllProcessedActivityInfo(@NotNull String str);

    @Query("DELETE from KhBleActivity where mMacAddress=:macAddress AND isProcessedInWorkout==1 AND mMode!=0")
    void deleteAllProcessedWorkoutActivityInfo(@NotNull String str);

    @Query("SELECT * from KhBleActivity where mMacAddress=:macAddress and mMode==0 and isProcessed=:isProcessed ORDER BY mTime ASC")
    @Nullable
    List<KhBleActivity> getActivityInfo(@NotNull String str, boolean z);

    @Query("SELECT * from khbleactivity where mMacAddress=:macAddress and mTime between :startTime and :endTime")
    @Nullable
    List<KhBleActivity> getActivityListBetweenTime(@NotNull String str, int i, int i2);

    @Query("SELECT MAX(mStep) FROM KhBleActivity WHERE mMacAddress=:macAddress AND date=:mDate")
    @Nullable
    Integer getMaxStepRecordByDate(@NotNull String str, @NotNull String str2);

    @Query("SELECT * from khbleactivity where mMacAddress=:macAddress and mTime between :startTime and :endTime ORDER BY mTime")
    @Nullable
    List<KhBleActivity> getOrderedActivityListBetweenTime(@NotNull String str, int i, int i2);

    @Query("SELECT * FROM KhBleActivity WHERE mMacAddress=:macAddress GROUP BY date ORDER BY mTime DESC")
    @Nullable
    List<KhBleActivity> getUniqueDayInfo(@NotNull String str);

    @Query("SELECT * FROM KhBleActivity WHERE mMacAddress=:macAddress AND mMode!=0 AND isProcessedInWorkout=:isProcessedInWorkout ORDER BY mTime")
    @Nullable
    List<KhBleActivity> getWorkoutActivityInfo(@NotNull String str, boolean z);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleActivity khBleActivity);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleActivity> list);

    @Query("UPDATE KhBleActivity SET isReadyToProcess=1 where mMacAddress=:macAddress and mMode!=0 and mTime between :startTime and :endTime")
    void setWorkoutReadyToProcessBetweenTime(@NotNull String str, int i, int i2);

    @Query("UPDATE KhBleActivity SET isProcessed = 1 WHERE mMacAddress=:macAddress AND mTime<:thresholdTimestamp AND isProcessed==0")
    void updateActivityDataProcessedBefore(@NotNull String str, long j);

    @Query("UPDATE KhBleActivity SET isProcessed = 0 WHERE mMacAddress=:macAddress AND mTime>=:thresholdTimestamp")
    void updateActivityDataUnProcessedAfter(@NotNull String str, int i);

    @Query("UPDATE KhBleActivity SET isProcessedInWorkout = 1 WHERE mMacAddress=:macAddress AND mMode!=0 AND mTime<:thresholdTimestamp AND isProcessedInWorkout==0 AND isReadyToProcess==1")
    void updateWorkoutActivityDataProcessedBefore(@NotNull String str, long j);
}
