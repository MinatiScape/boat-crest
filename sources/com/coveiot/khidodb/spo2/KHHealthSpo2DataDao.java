package com.coveiot.khidodb.spo2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHHealthSpo2DataDao {
    @Query("SELECT * from entity_health_spo2  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthSpo2> getAllUnProcessedSpo2Data(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthSpo2 entityHealthSpo2);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthSpo2> list);

    @Query("UPDATE entity_health_spo2 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateSpo2DataProcessedBefore(@NotNull String str, long j);
}
