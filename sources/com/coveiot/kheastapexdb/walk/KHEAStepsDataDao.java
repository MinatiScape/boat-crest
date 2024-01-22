package com.coveiot.kheastapexdb.walk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'J\u0016\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H'J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\nH'J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H'¨\u0006\u000f"}, d2 = {"Lcom/coveiot/kheastapexdb/walk/KHEAStepsDataDao;", "", "Lcom/coveiot/kheastapexdb/walk/EntityEAStepsData;", "walk", "", "insert", "", "walkList", "", "insertAll", "", DeviceKey.MacAddress, "getAllUnProcessedStepsData", "thresholdTimestamp", "updateStepsDataProcessedBefore", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public interface KHEAStepsDataDao {
    @Query("SELECT * from entity_ea_steps  WHERE macAddress=:macAddress and isProcessed == 0 ORDER BY timeStamp")
    @Nullable
    List<EntityEAStepsData> getAllUnProcessedStepsData(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull EntityEAStepsData entityEAStepsData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityEAStepsData> list);

    @Query("UPDATE entity_ea_steps SET isProcessed = 1 WHERE macAddress=:macAddress and timeStamp<:thresholdTimestamp AND isProcessed == 0")
    void updateStepsDataProcessedBefore(@NotNull String str, long j);
}
