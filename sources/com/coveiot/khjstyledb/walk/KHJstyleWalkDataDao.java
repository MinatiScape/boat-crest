package com.coveiot.khjstyledb.walk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface KHJstyleWalkDataDao {
    @Query("SELECT * FROM hourlywalkdata WHERE serial_no=:serial_no AND id BETWEEN :startTime AND :endTime")
    List<KHJstyleHourlyWalkData> getHourlyStepsValueBetween(String str, String str2, String str3);

    @Insert(onConflict = 1)
    void insertAll(List<KHJstyleHourlyWalkData> list);
}
