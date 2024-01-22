package com.coveiot.covedb.sedentary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface SedentaryDataDao {
    @Query("SELECT COUNT(*) FROM sedentary_data where date=:date AND serial_number=:address")
    int countOfSedentaryAlertsForDate(String str, String str2);

    @Query("SELECT COUNT(*) FROM sedentary_data where timestamp>=:time AND serial_number=:address")
    int countOfSedentaryAlertsFrom(long j, String str);

    @Query("SELECT timestamp FROM sedentary_data where timestamp>=:time AND serial_number=:address ORDER BY timestamp DESC LIMIT 1")
    long getLastSedentaryAlertTimeStampFor(long j, String str);

    @Query("SELECT * FROM sedentary_data where is_saved_server = 0 AND serial_number=:address")
    List<EntitySedentary> getUnSyncedSedentaryAlertsList(String str);

    @Insert(onConflict = 1)
    void insert(EntitySedentary entitySedentary);

    @Insert(onConflict = 1)
    void insertAll(List<EntitySedentary> list);

    @Query("UPDATE sedentary_data SET is_saved_server= 1 WHERE date = :date")
    void updateSyncState(String str);
}
