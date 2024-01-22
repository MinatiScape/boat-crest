package com.coveiot.khidodb.noise;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHIDOHealthNoiseDataDao {
    @Query("SELECT * from health_noise_v3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthNoise> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthNoise entityHealthNoise);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthNoise> list);

    @Query("UPDATE health_noise_v3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
