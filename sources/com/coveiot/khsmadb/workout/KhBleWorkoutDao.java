package com.coveiot.khsmadb.workout;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Dao
/* loaded from: classes8.dex */
public interface KhBleWorkoutDao {
    @Query("SELECT * FROM khBleWorkout WHERE isProcessed=0 AND macAddress=:macAddress")
    @NotNull
    List<KhBleWorkout> getUnMarkedWorkoutList(@NotNull String str);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<KhBleWorkout> list);
}
