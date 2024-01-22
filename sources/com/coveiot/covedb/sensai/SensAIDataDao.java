package com.coveiot.covedb.sensai;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface SensAIDataDao {
    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND is_saved_server = 0 AND activity_type = 1")
    List<SensAIActivitySummary> getActivityBattingSummaryList(String str);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND is_saved_server = 0 AND activity_type = 2")
    List<SensAIActivitySummary> getActivityBowlingSummaryList(String str);

    @Query("SELECT * FROM sensai_activity_summary where session_id = :sessionId AND serial_no = :macAddress")
    SensAIActivitySummary getActivitySummary(String str, String str2);

    @Query("SELECT * FROM sensai_activity_summary_details where session_id = :sessionId AND serial_no = :macAddress")
    SensAIActivitySummaryDetails getActivitySummaryDetails(String str, String str2);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress ORDER BY CASE :orderClause WHEN 1 THEN timestamp WHEN 2 THEN duration_sec WHEN 3 THEN goal_completion_pct END DESC ")
    LiveData<List<SensAIActivitySummary>> getActivitySummaryLiveData(String str, int i);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND is_add_to_compare = 1 AND activity_type = 1")
    List<SensAIActivitySummary> getAddToCompareBattingList(String str);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND is_add_to_compare = 1 AND activity_type = 2")
    List<SensAIActivitySummary> getAddToCompareBowlingList(String str);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND is_data_aggregate_saved = 0")
    List<SensAIActivitySummary> getAggregateSummaryList(String str);

    @Query("SELECT * FROM sensai_activity_summary where serial_no = :macAddress AND ((activity_type = :activityType1 AND (hand = :handType1 OR hand =:handType2)) OR (activity_type =:activityType2) AND (hand = :handType1 OR hand =:handType2) AND (bowling_type = :bowlingType1 OR bowling_type = :bowlingType2 OR bowling_type = :bowlingType3)) ORDER BY CASE :orderClause WHEN 1 THEN timestamp WHEN 2 THEN duration_sec WHEN 3 THEN goal_completion_pct END DESC ")
    LiveData<List<SensAIActivitySummary>> getFilteredByActivityAndHandAndBowlingType(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    @Insert(onConflict = 1)
    void insertActivitySummary(List<SensAIActivitySummary> list);

    @Insert(onConflict = 1)
    void insertActivitySummaryDetails(SensAIActivitySummaryDetails sensAIActivitySummaryDetails);

    @Query("SELECT COUNT(*) FROM sensai_activity_summary WHERE session_id=:sessionID AND serial_no = :macAddress")
    int isSessionIdExists(String str, String str2);

    @Update(onConflict = 1)
    void updateActivitySummary(SensAIActivitySummary sensAIActivitySummary);

    @Query("UPDATE sensai_activity_summary SET is_add_to_compare = :isAddToCompare WHERE serial_no=:macAddress AND session_id  =:sessionID")
    void updateAddToCompareItem(boolean z, String str, String str2);

    @Query("UPDATE sensai_activity_summary SET is_data_aggregate_saved = :isSavedToServer WHERE serial_no=:macAddress AND session_id  =:sessionID")
    void updateAggregateItem(boolean z, String str, String str2);

    @Query("UPDATE sensai_activity_summary_details SET is_feedback_saved = :isFeedbackSaved WHERE serial_no=:macAddress AND session_id  =:sessionID")
    void updateFeedbackSavedToServerItem(boolean z, String str, String str2);

    @Query("UPDATE sensai_activity_summary SET is_saved_server = :isSavedToServer , client_ref_id = :clientRefId WHERE serial_no=:macAddress AND session_id  =:sessionID")
    void updateSavedToServerItem(boolean z, String str, String str2, String str3);
}
