package com.coveiot.khidodb.walk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KHIDOHealthSportV3DataDao {
    @Query("SELECT * from health_sport_v3  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timestamp")
    @Nullable
    List<EntityHealthSportV3> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityHealthSportV3 entityHealthSportV3);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityHealthSportV3> list);

    @Query("UPDATE health_sport_v3 SET isProcessed = 1 WHERE macAddress=:macAddress and timestamp<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
