package com.coveiot.android.activitymodes.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Dao
/* loaded from: classes2.dex */
public interface PreparationPlanDao {
    @Query("DELETE FROM entity_preparation_plan")
    void deletePlan();

    @Query("SELECT * FROM entity_preparation_plan ORDER BY startDate DESC LIMIT 1")
    @NotNull
    EntityPreparationPlan getCurrentPlanInfo();

    @Query("SELECT * FROM entity_preparation_day WHERE plan_id=:planId AND week_number=:week_number")
    @NotNull
    LiveData<List<EntityPreparationDay>> getDayLevelInfo(@NotNull String str, int i);

    @Query("SELECT * FROM entity_preparation_day WHERE plan_id=:planId AND date=:date")
    @NotNull
    EntityPreparationDay getDayLevelInfoBasedOnDate(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM entity_preparation_day WHERE plan_id=:planId AND week_number=:week_number")
    @NotNull
    List<EntityPreparationDay> getDayLevelInfoWithOutLiveData(@NotNull String str, int i);

    @Query("SELECT * FROM entity_preparation_day WHERE plan_id=:planId AND strftime('%Y-%m-%d',date) BETWEEN :fromDate AND :untilDate")
    @NotNull
    List<EntityPreparationDay> getDayLevelInfoWithinDateRange(@NotNull String str, @NotNull String str2, @NotNull String str3);

    @Query("SELECT date FROM  entity_preparation_day WHERE plan_id=:planId ORDER BY date DESC LIMIT 1")
    @NotNull
    String getPlanEndDate(@NotNull String str);

    @Query("SELECT * FROM entity_preparation_week WHERE plan_id=:planId")
    @NotNull
    LiveData<List<EntityPreparationWeek>> getWeekLevelInfo(@NotNull String str);

    @Query("SELECT * FROM entity_preparation_week WHERE plan_id=:planId AND week_number=:weekNumber")
    @NotNull
    EntityPreparationWeek getWeekLevelInfo(@NotNull String str, int i);

    @Query("SELECT * FROM entity_preparation_week WHERE plan_id=:planId")
    @NotNull
    List<EntityPreparationWeek> getWeekLevelInfoWithoutLiveData(@NotNull String str);

    @Insert(onConflict = 1)
    void insertDailyPlan(@NotNull List<EntityPreparationDay> list);

    @Insert(onConflict = 1)
    void insertPlan(@NotNull EntityPreparationPlan entityPreparationPlan);

    @Insert
    void insertPlanSchedule(@NotNull EntityPlanSchedule entityPlanSchedule);

    @Insert
    void insertWeeklyPlan(@NotNull List<EntityPreparationWeek> list);

    @Query("SELECT COUNT(*) FROM entity_preparation_plan")
    int rowCountPlan();
}
