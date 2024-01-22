package com.coveiot.khtouchdb.spo2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHTGSpO2DataDao {
    @Query("SELECT * from ENTITY_TG_SPO2  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityTGSpO2Data> getAllUnProcessedSpO2Data(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityTGSpO2Data entityTGSpO2Data);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityTGSpO2Data> list);

    @Query("UPDATE ENTITY_TG_SPO2 SET isProcessed = 1 WHERE macAddress=:macAddress AND isProcessed == 0 AND timeStamp<:thresholdTimestamp")
    void updateSpO2DataProcessedBefore(@NotNull String str, long j);
}
