package com.coveiot.khtouchdb.activities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGWorkoutRecordsDao {
    @Query("SELECT * from entity_tg_workout_record  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityTGWorkoutRecord> getAllUnProcessedActivityData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGWorkoutRecord entityTGWorkoutRecord);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGWorkoutRecord> list);

    @Query("UPDATE entity_tg_workout_record SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateActivityDataProcessedBefore(@NotNull String str, long j);
}
