package com.coveiot.android.khmatrixdb.workout;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes3.dex */
public interface KhMatrixSportDataDao {
    @Query("SELECT * from KhMatrixSportData WHERE mMacAddress=:macAddress and isProcessed == 0 ORDER BY mTime")
    @Nullable
    List<KhMatrixSportData> getAllUnProcessedSportsData(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhMatrixSportData khMatrixSportData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhMatrixSportData> list);

    @Query("UPDATE KhMatrixSportData SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateSportsDataProcessedBefore(@NotNull String str, long j);
}
