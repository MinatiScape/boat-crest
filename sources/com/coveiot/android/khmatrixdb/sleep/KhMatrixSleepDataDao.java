package com.coveiot.android.khmatrixdb.sleep;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import com.coveiot.android.khmatrixdb.converter.DateConverter;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes3.dex */
public interface KhMatrixSleepDataDao {
    @Query("SELECT * from KhMatrixSleepData WHERE mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<KhMatrixSleepData> getAllSleepData(@NotNull String str);

    @Query("SELECT * from KhMatrixSleepData WHERE mMacAddress=:macAddress and isProcessed == 0")
    @Nullable
    List<KhMatrixSleepData> getAllUnProcessedSleepData(@NotNull String str);

    @Query("SELECT * from KhMatrixSleepDetailData WHERE mMacAddress=:macAddress and isProcessed == 0")
    @Nullable
    List<KhMatrixSleepDetailData> getAllUnProcessedSleepDetailData(@NotNull String str);

    @Query("SELECT * from KhMatrixSleepDetailData where mMacAddress=:macAddress and mStartTime>=:fromTime and mEndTime<=:toTime ORDER BY mStartTime")
    @Nullable
    List<KhMatrixSleepDetailData> getSleepDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * FROM KhMatrixSleepData WHERE mTime =:time and mMacAddress=:macAddress")
    @NotNull
    KhMatrixSleepData getSleepDataByTime(@TypeConverters({DateConverter.class}) long j, @NotNull String str);

    @Query("SELECT DISTINCT convertedDate from KhMatrixSleepDetailData WHERE mMacAddress=:macAddress ORDER BY mStartTime")
    @Nullable
    List<String> getUniqueDatesForSleep(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhMatrixSleepData khMatrixSleepData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhMatrixSleepData> list);

    @Insert(onConflict = 1)
    void insertAllSleepDetailData(@NotNull List<KhMatrixSleepDetailData> list);

    @Query("UPDATE KhMatrixSleepData SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp and isProcessed == 0")
    void updateSleepDataProcessedBefore(@NotNull String str, long j);

    @Query("UPDATE KhMatrixSleepDetailData SET isProcessed = 1 WHERE mMacAddress=:macAddress and mStartTime<:thresholdTimestamp and isProcessed == 0")
    void updateSleepDetailProcessedBefore(@NotNull String str, long j);
}
