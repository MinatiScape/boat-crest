package com.coveiot.android.leonardo.sp02.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import java.util.List;
@Dao
/* loaded from: classes5.dex */
public interface SPO2Dao {
    @Query("SELECT *  from spo2_records where date=:date ORDER BY timeStamp DESC LIMIT 1")
    SPO2Record getLatestSpo2Record(String str);

    @Query("SELECT * from spo2_records where isSyncedToServer=0")
    List<SPO2Record> getUnSyncedSPO2Recods();

    @Insert(onConflict = 1)
    void insert(SPO2Record sPO2Record);
}
