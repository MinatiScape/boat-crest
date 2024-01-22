package com.coveiot.khidodb.stress;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHHealthPressureDataDao {
    @Query("SELECT * from entity_health_pressure  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthPressure> getAllUnProcessedPressureData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthPressure entityHealthPressure);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthPressure> list);

    @Query("UPDATE entity_health_pressure SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updatePressureDataProcessedBefore(@NotNull String str, long j);
}
