package com.coveiot.khsmadb.location;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes8.dex */
public interface KhLocationDao {
    @Query("SELECT * from KhLocation where macAddress=:macAddress and mTime between :startTime and :endTime")
    @Nullable
    List<KhLocation> getLocationDataListBetweenTime(@NotNull String str, int i, int i2);

    @Insert(onConflict = 1)
    void insert(@NotNull KhLocation khLocation);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhLocation> list);
}
