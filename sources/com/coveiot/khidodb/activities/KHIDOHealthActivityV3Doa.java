package com.coveiot.khidodb.activities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHIDOHealthActivityV3Doa {
    @Query("SELECT * from entity_activity_v3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthActivityV3> getAllUnProcessedActivityData(@NotNull String str);

    @Query("SELECT * from entity_swim_v3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthSwimV3> getAllUnProcessedSwimActivityData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthActivityV3 entityHealthActivityV3);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthActivityV3> list);

    @Insert(onConflict = 1)
    void insertAllSwimData(@NotNull List<EntityHealthSwimV3> list);

    @Insert(onConflict = 1)
    long insertSwimData(@NotNull EntityHealthSwimV3 entityHealthSwimV3);

    @Query("UPDATE entity_swim_v3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateASwimActivityDataProcessedBefore(@NotNull String str, long j);

    @Query("UPDATE entity_activity_v3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateActivityDataProcessedBefore(@NotNull String str, long j);
}
