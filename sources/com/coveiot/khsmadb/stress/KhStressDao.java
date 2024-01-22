package com.coveiot.khsmadb.stress;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhStressDao {
    @Query("SELECT * from khblepressure where mMacAddress=:macAddress and isProcessed=:isProcessed and mValue>0 ORDER BY mTime")
    @Nullable
    List<KhBlePressure> getAllStressData(@NotNull String str, boolean z);

    @Query("SELECT * from khblepressure where mMacAddress=:macAddress and mTime>=:fromTime and mTime<=:toTime ORDER BY mTime")
    @Nullable
    List<KhBlePressure> getStressDataBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * from khblepressure where mMacAddress=:macAddress and convertedDate=:date ORDER BY mTime")
    @Nullable
    List<KhBlePressure> getStressDataFor(@NotNull String str, @NotNull String str2);

    @Query("SELECT * from khblepressure where mMacAddress=:macAddress and mTime between :startTime and :endTime")
    @Nullable
    List<KhBlePressure> getStressDataListBetweenTime(@NotNull String str, int i, int i2);

    @Query("SELECT DISTINCT convertedDate from khblepressure where mMacAddress=:macAddress ORDER BY mTime")
    @Nullable
    List<String> getUniqueDatesForStress(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhBlePressure khBlePressure);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBlePressure> list);

    @Query("UPDATE khblepressure SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateStressDataProcessedBefore(@NotNull String str, long j);
}
