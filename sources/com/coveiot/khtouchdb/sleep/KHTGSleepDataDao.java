package com.coveiot.khtouchdb.sleep;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGSleepDataDao {
    @Query("SELECT * from ENTITY_TG_SLEEP  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityTGSleepData> getAllUnProcessedSleepData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGSleepData entityTGSleepData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGSleepData> list);

    @Query("UPDATE ENTITY_TG_SLEEP SET isProcessed = 1 WHERE macAddress=:macAddress and timeStamp<:thresholdTimestamp AND isProcessed == 0")
    void updateSleepDataProcessedBefore(@NotNull String str, long j);
}
