package com.coveiot.covedb.ecg;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface ECGDao {
    @Query("SELECT * FROM ecg_summary_table WHERE is_sync_server=0")
    List<EntityECGSummaryData> getECGSummaryDataList();

    @Query("SELECT * FROM ecg_summary_table ORDER BY startDateTime DESC LIMIT 1")
    EntityECGSummaryData getLastECGSummaryData();

    @Insert(onConflict = 1)
    void insertECGResultData(EntityECGSummaryData entityECGSummaryData);
}
