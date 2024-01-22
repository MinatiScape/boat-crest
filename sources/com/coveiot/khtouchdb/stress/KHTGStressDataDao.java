package com.coveiot.khtouchdb.stress;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGStressDataDao {
    @Query("SELECT * from ENTITY_TG_STRESS  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityTGStressData> getAllUnProcessedPressureData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGStressData entityTGStressData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGStressData> list);

    @Query("UPDATE ENTITY_TG_STRESS SET isProcessed = 1 WHERE macAddress=:macAddress and timeStamp<:thresholdTimestamp AND isProcessed == 0")
    void updatePressureDataProcessedBefore(@NotNull String str, long j);
}
