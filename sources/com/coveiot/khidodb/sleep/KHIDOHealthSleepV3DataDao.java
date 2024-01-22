package com.coveiot.khidodb.sleep;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHIDOHealthSleepV3DataDao {
    @Query("SELECT * from HEALTH_SLEEP_V3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthSleepV3> getAllUnProcessedSleepData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthSleepV3 entityHealthSleepV3);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthSleepV3> list);

    @Query("UPDATE HEALTH_SLEEP_V3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateSleepDataProcessedBefore(@NotNull String str, long j);
}
