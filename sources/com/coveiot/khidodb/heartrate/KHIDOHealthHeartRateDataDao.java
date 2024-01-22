package com.coveiot.khidodb.heartrate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHIDOHealthHeartRateDataDao {
    @Query("SELECT * from HEALTH_HEARTRATE_V3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthHeartRateSecond> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthHeartRateSecond entityHealthHeartRateSecond);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthHeartRateSecond> list);

    @Query("UPDATE HEALTH_HEARTRATE_V3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
