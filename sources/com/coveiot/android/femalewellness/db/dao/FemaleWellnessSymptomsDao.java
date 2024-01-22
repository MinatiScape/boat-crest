package com.coveiot.android.femalewellness.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.wellnesscalendar.model.PhaseAndDateModel;
import java.util.List;
@Dao
/* loaded from: classes4.dex */
public interface FemaleWellnessSymptomsDao {
    @Query("DELETE FROM female_wellness_record_symptoms WHERE DATE(date)>=DATE(:dateStr)")
    void deleteFutureRecordsIfAny(String str);

    @Query("SELECT date, phase FROM female_wellness_record_symptoms WHERE phase IS NOT NULL   and DATE(date)>DATE(:selectedDate) order by DATE(date) ASC limit 1")
    PhaseAndDateModel fetchNextPeriodOvulationDate(String str);

    @Query("SELECT date,phase FROM female_wellness_record_symptoms WHERE phase IS NOT NULL   and DATE(date)<DATE(:selectedDate) order by DATE(date) DESC limit 1")
    PhaseAndDateModel fetchPreviousPeriodOvulationDate(String str);

    @Query("SELECT date FROM female_wellness_record_symptoms WHERE date =:dateStr and  phase='OVULATION'  and DATE(date)<DATE(:startDate)")
    List<String> fetchRecordedOvulationDates(String str, String str2);

    @Query("SELECT date FROM female_wellness_record_symptoms WHERE date =:dateStr and  phase='PERIOD'   and DATE(date)<DATE(:startDate)")
    List<String> fetchRecordedPeriodDates(String str, String str2);

    @Query("SELECT cycleStartDate from female_wellness_record_symptoms order by cycleStartDate asc limit 1")
    String getOldestLMD();

    @Query("Select * from female_wellness_record_symptoms WHERE date = :date")
    LiveData<List<EntityFemaleWellnessSymptoms>> getTheRecordedSymptomsByDate(String str);

    @Insert(onConflict = 1)
    void insert(EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms);

    @Query("INSERT OR REPLACE  INTO female_wellness_record_symptoms (date ,cycleStartDate,cycleLength,periodLength,phase, isActive)  VALUES (:date,:cycleStartDate,:cycleLength,:periodLength,:phase,:isActive)")
    void insertOrUpdateWellnessData(String str, String str2, int i, int i2, String str3, Boolean bool);

    @Query("UPDATE female_wellness_record_symptoms SET flow = :flow, pain =:pain, mood =:mood, symptoms =:symptomsList WHERE date  = :date")
    void updateFemaleWellnessSymptoms(String str, String str2, String str3, String str4, List<String> list);
}
