package com.coveiot.khsmadb.bp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhBloodPressureDao {
    @Query("SELECT * from KhBleBloodPressure where mMacAddress=:macAddress and isProcessed=:isProcessed ORDER BY mTime")
    @Nullable
    List<KhBleBloodPressure> getAllBloodPressureData(@NotNull String str, boolean z);

    @Query("SELECT * from KhBleBloodPressure where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBleBloodPressure> getBloodPressureDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * from KhBleBloodPressure where mMacAddress=:macAddress and convertedDate=:date ORDER BY mTime")
    @Nullable
    List<KhBleBloodPressure> getBloodPressureDataFor(@NotNull String str, @NotNull String str2);

    @Query("SELECT DISTINCT convertedDate from KhBleBloodPressure where mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<String> getUniqueDatesForBloodPressure(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBleBloodPressure khBleBloodPressure);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleBloodPressure> list);

    @Query("UPDATE KhBleBloodPressure SET isProcessed = 1 WHERE mMacAddress=:macAddress AND mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateBloodPressureDataProcessedBefore(@NotNull String str, long j);
}
