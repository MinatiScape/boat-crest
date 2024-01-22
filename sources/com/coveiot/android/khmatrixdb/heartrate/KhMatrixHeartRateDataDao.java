package com.coveiot.android.khmatrixdb.heartrate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes3.dex */
public interface KhMatrixHeartRateDataDao {
    @Query("SELECT * from KhMatrixHeartRate WHERE mMacAddress=:macAddress and isProcessed == 0 ORDER BY mTime")
    @Nullable
    List<KhMatrixHeartRate> getAllUnProcessedHRData(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhMatrixHeartRate khMatrixHeartRate);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhMatrixHeartRate> list);

    @Query("UPDATE KhMatrixHeartRate SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateHeartRateDataProcessedBefore(@NotNull String str, long j);
}
