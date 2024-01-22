package com.coveiot.android.sleepenergyscore.sleepscore.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import java.util.ArrayList;
import java.util.List;
@Dao
/* loaded from: classes6.dex */
public interface SleepScoreDao {
    @Query("SELECT lastSyncedDate from sleep_score_table WHERE macAddress=:macAddress ORDER BY lastSyncedDate DESC limit 1")
    long getLastSyncedDate(String str);

    @Query("SELECT sleepScore  from sleep_score_table where macAddress=:macAddress AND date=:date ")
    int getSleepScore(String str, String str2);

    @Query("SELECT *  from sleep_score_table where macAddress=:macAddress AND date=:date ")
    SleepScoreData getSleepScoreData(String str, String str2);

    @Query("SELECT *  from sleep_score_table where macAddress=:macAddress AND  date BETWEEN :startDate AND :endDate ")
    List<SleepScoreData> getSleepScoreDataListBetweenDates(String str, String str2, String str3);

    @Insert(onConflict = 1)
    long insert(SleepScoreData sleepScoreData);

    @Query("UPDATE sleep_score_table SET feedbackList =:feedbackLists , questionnaireId=:questionnaireid WHERE macAddress=:macAddress AND date=:mDate")
    void updateFeedbackList(ArrayList<QuestionAnswerSleepData> arrayList, String str, String str2, String str3);
}
