package com.coveiot.android.khmatrixdb.steps;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes3.dex */
public interface KhMatrixStepsDataDao {
    @Query("SELECT * from KhMatrixStepsData WHERE mMacAddress=:macAddress and isProcessed == 0 ORDER BY mTime")
    @Nullable
    List<KhMatrixStepsData> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhMatrixStepsData khMatrixStepsData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhMatrixStepsData> list);

    @Query("UPDATE KhMatrixStepsData SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
