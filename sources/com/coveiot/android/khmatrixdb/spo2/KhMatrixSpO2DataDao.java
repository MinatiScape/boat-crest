package com.coveiot.android.khmatrixdb.spo2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes3.dex */
public interface KhMatrixSpO2DataDao {
    @Query("SELECT * from KhMatrixSpO2 WHERE mMacAddress=:macAddress and isProcessed == 0 ORDER BY mTime")
    @Nullable
    List<KhMatrixSpO2> getAllUnProcessedSp02Data(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull KhMatrixSpO2 khMatrixSpO2);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhMatrixSpO2> list);

    @Query("UPDATE KhMatrixSpO2 SET isProcessed = 1 WHERE mMacAddress=:macAddress and mTime<:thresholdTimestamp AND isProcessed == 0")
    void updateSpO2DataProcessedBefore(@NotNull String str, long j);
}
