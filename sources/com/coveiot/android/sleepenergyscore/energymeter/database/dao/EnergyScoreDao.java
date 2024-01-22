package com.coveiot.android.sleepenergyscore.energymeter.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import java.util.ArrayList;
import java.util.List;
@Dao
/* loaded from: classes6.dex */
public interface EnergyScoreDao {
    @Query("SELECT *  from energy_score_table where macAddress=:macAddress AND mDate=:date ")
    EnergyScoreDbData getEnergyScoreData(String str, String str2);

    @Query("SELECT lastSyncedDate from energy_score_table WHERE macAddress=:macAddress ORDER BY lastSyncedDate DESC limit 1")
    long getLastSyncedDate(String str);

    @Query("SELECT * from energy_score_table WHERE macAddress=:macAddress ORDER BY mDate DESC limit 1")
    LiveData<EnergyScoreDbData> getLastSyncedScoreLiveData(String str);

    @Query("SELECT * from energy_score_table where macAddress=:macAddress")
    List<EnergyScoreDbData> getListOfEnergyScoreData(String str);

    @Query("SELECT *  from energy_score_table where macAddress=:macAddress AND isSyncedWithServer=0 ")
    List<EnergyScoreDbData> getListOfUnSyncedEnergyScoreData(String str);

    @Insert(onConflict = 1)
    long insert(EnergyScoreDbData energyScoreDbData);

    @Query("UPDATE energy_score_table SET feedbackList =:feedbackLists , questionnaireId=:questionnarieid WHERE macAddress=:macAddress AND mDate=:mDate")
    void updateFeedbackList(ArrayList<QuestionAnswerData> arrayList, String str, String str2, String str3);

    @Query("UPDATE energy_score_table SET isSyncedWithServer = 1 WHERE macAddress=:macAddress AND mDate=:mDate")
    void updateSyncedDataToServer(String str, String str2);
}
