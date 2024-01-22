package com.coveiot.khtouchdb.heartrate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGHeartRateDataDao {
    @Query("SELECT * from ENTITY_TG_HEART_RATE  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityTGHeartRateData> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGHeartRateData entityTGHeartRateData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGHeartRateData> list);

    @Query("UPDATE ENTITY_TG_HEART_RATE SET isProcessed = 1 WHERE macAddress=:macAddress AND isProcessed == 0 AND timeStamp<:thresholdTimestamp")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
