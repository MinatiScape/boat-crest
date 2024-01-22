package com.coveiot.khtouchdb.walk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGStepsDataDao {
    @Query("SELECT * from ENTITY_TG_STEPS  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityTGStepData> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGStepData entityTGStepData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGStepData> list);

    @Query("UPDATE ENTITY_TG_STEPS SET isProcessed = 1 WHERE macAddress=:macAddress and timeStamp<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
