package com.coveiot.android.activitymodes.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.models.FitnessChallengeStatsData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes2.dex */
public interface SessionDAO {
    @Delete
    void deleteSession(@NotNull EntityWorkoutSession entityWorkoutSession);

    @Query("DELETE FROM workout_session")
    void deleteWorkOutSession();

    @Query("SELECT * FROM skipping_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<SkippingSample>> geSkippingSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session GROUP BY categoryId, activityId")
    @NotNull
    List<EntityWorkoutSession> getActivitiesByActivityAndCategoryID();

    @Query("SELECT * FROM workout_session GROUP BY categoryId")
    @NotNull
    List<EntityWorkoutSession> getActivitiesByCategoryID();

    @Query("SELECT * FROM activity_data_sample WHERE sessionID=:session_id")
    @NotNull
    List<ActivityDataSample> getActivityDataSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM activity_data_sample WHERE segmentID=:segment_id AND sessionID=:session_id")
    @NotNull
    List<ActivityDataSample> getActivityDataSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM activity_data_sample WHERE sessionID=:session_id")
    @NotNull
    LiveData<List<ActivityDataSample>> getActivityDataSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT SUM(total_steps) as totalSteps, SUM(total_calories) as totalCalories, SUM(total_distance) as totalDistance FROM workout_session where serial_no=:macAddress AND date=:date")
    @NotNull
    FitnessChallengeStatsData getAllSessionDataByDate(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM badminton_sample WHERE sess_id=:session_id")
    @NotNull
    List<BadmintonSample> getBadmintonSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM badminton_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<BadmintonSample> getBadmintonSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM badminton_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<BadmintonSample>> getBadmintonSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM basketball_sample WHERE sess_id=:session_id")
    @NotNull
    List<BasketBallSample> getBasketBallSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM basketball_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<BasketBallSample> getBasketBallSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM basketball_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<BasketBallSample>> getBasketBallSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE categoryId=:categoryId AND  activityId=:activityId ORDER BY total_calories DESC LIMIT 1")
    @NotNull
    EntityWorkoutSession getBestSessionByActivityAndCategoryID(int i, int i2);

    @Query("SELECT * FROM workout_session WHERE activity_type=:activityType ORDER BY total_calories DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getBestSessionByActivityType(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE activity_type=:activityType AND  indoor_outdoor=:indoorOutdoorType ORDER BY total_calories DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getBestSessionByActivityType(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM workout_session WHERE categoryId=:categoryId ORDER BY total_calories DESC LIMIT 1")
    @NotNull
    EntityWorkoutSession getBestSessionByCategoryID(int i);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) = :date AND categoryId =:categoryId AND activityId =:activityId")
    int getCaloriesByActivityNCategoryIDs(@NotNull String str, int i, int i2);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE activity_type=:type AND strftime('%Y-%m-%d',date)=:date")
    int getCaloriesByActivityType(@NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE categoryId =:categoryId AND activityId =:activityId AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getCaloriesForWeekByActivityNCategoryIDs(int i, int i2, @NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE activity_type=:type AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getCaloriesForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getCaloriesWithoutActivityMapping(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM climbing_sample WHERE sess_id=:session_id")
    @NotNull
    List<ClimbingSample> getClimbingSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM climbing_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<ClimbingSample> getClimbingSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM climbing_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<ClimbingSample>> getClimbingSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM cycling_sample WHERE sess_id=:session_id")
    @NotNull
    List<CyclingSample> getCyclingSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM cycling_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<CyclingSample> getCyclingSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM cycling_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<CyclingSample>> getCyclingSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM dance_sample WHERE sess_id=:session_id")
    @NotNull
    List<DanceSample> getDanceSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM dance_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<DanceSample> getDanceSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM dance_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<DanceSample>> getDanceSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) = :date AND categoryId =:categoryId AND activityId =:activityId")
    int getDistanceByActivityNCategoryIDs(@NotNull String str, int i, int i2);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) = :date AND activity_type =:type")
    int getDistanceByType(@NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE categoryId =:categoryId AND activityId =:activityId AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getDistanceForWeekByActivityNCategoryIDs(int i, int i2, @NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE activity_type=:type AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getDistanceForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getDistanceWithoutActivityMapping(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM elliptical_sample WHERE sess_id=:session_id")
    @NotNull
    List<EllipticalSample> getEllipticalSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM elliptical_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<EllipticalSample> getEllipticalSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM elliptical_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<EllipticalSample>> getEllipticalSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM football_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<FootballSample> getFootBallSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM football_sample WHERE sess_id=:session_id")
    @NotNull
    List<FootballSample> getFootballSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM football_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<FootballSample>> getFootballSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM free_exercise_sample WHERE sess_id=:session_id")
    @NotNull
    List<FreeExerciseSample> getFreeExerciseSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM free_exercise_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<FreeExerciseSample> getFreeExerciseSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM free_exercise_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<FreeExerciseSample>> getFreeExerciseSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM hiking_sample WHERE sess_id=:session_id")
    @NotNull
    List<HikingSample> getHikingSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM hiking_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<HikingSample> getHikingSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM hiking_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<HikingSample>> getHikingSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session where serial_no=:macAddress ORDER BY start_time DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getLastSessionData(@NotNull String str);

    @Query("SELECT * FROM workout_session where serial_no=:macAddress AND date=:date ORDER BY start_time DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getLastSessionDataByDate(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM workout_session ORDER BY start_time DESC LIMIT 1")
    @NotNull
    LiveData<EntityWorkoutSession> getLastSessionLiveData();

    @Query("SELECT * FROM workout_session WHERE serial_no =:serial_no ORDER BY start_time DESC LIMIT 1")
    @NotNull
    LiveData<EntityWorkoutSession> getLastSessionLiveDataForConnectedDevice(@NotNull String str);

    @Query("SELECT * FROM workout_session ORDER BY end_time DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getLatestSession();

    @Query("SELECT * FROM workout_session WHERE activity_type=:activityType ORDER BY end_time DESC LIMIT 1")
    @Nullable
    EntityWorkoutSession getLatestSessionByActivityType(@NotNull String str);

    @Query("SELECT * FROM meditation_sample WHERE sess_id=:session_id")
    @NotNull
    List<MeditationSample> getMeditationSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM meditation_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<MeditationSample> getMeditationSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM meditation_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<MeditationSample>> getMeditationSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM physical_activity_sample WHERE sess_id=:session_id")
    @NotNull
    List<PhysicalActivitySample> getPhysicalActivitySamplesBy(@NotNull String str);

    @Query("SELECT * FROM physical_activity_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<PhysicalActivitySample> getPhysicalActivitySamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM physical_activity_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<PhysicalActivitySample>> getPhysicalActivitySamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM rowing_machine_sample WHERE sess_id=:session_id")
    @NotNull
    List<RowingMachineSample> getRowingMachineSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM rowing_machine_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<RowingMachineSample> getRowingMachineSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM rowing_machine_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<RowingMachineSample>> getRowingMachineSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM run_sample WHERE sess_id=:session_id")
    @NotNull
    List<RunSample> getRunSamplesBy(@NotNull String str);

    @Query("SELECT * FROM run_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<RunSample> getRunSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM run_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<RunSample>> getRunSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session_segment WHERE  sess_id=:session_id")
    @NotNull
    List<EntityWorkoutSessionSegment> getSegmentsListBy(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE  session_id=:session_id")
    @NotNull
    EntityWorkoutSession getSessionBy(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE  session_id=:session_id")
    @NotNull
    LiveData<EntityWorkoutSession> getSessionLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session_segment WHERE  sess_id=:session_id")
    @NotNull
    List<EntityWorkoutSessionSegment> getSessionSegmentListBy(@NotNull String str);

    @Query("SELECT * FROM workout_session_segment WHERE  sess_id=:session_id")
    @NotNull
    LiveData<List<EntityWorkoutSessionSegment>> getSessionSegmentListLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session")
    @NotNull
    List<EntityWorkoutSession> getSessionsList();

    @Query("SELECT * FROM workout_session ORDER BY start_time DESC")
    @NotNull
    LiveData<List<EntityWorkoutSession>> getSessionsListLiveData();

    @Query("SELECT * FROM workout_session ORDER BY start_time DESC")
    @NotNull
    LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataByDescStartTime();

    @Query("SELECT * FROM workout_session WHERE activity_type=:activityType ORDER BY start_time DESC")
    @NotNull
    LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE activity_type=:activityType AND serial_no=COALESCE(:serial_no,serial_no) ORDER BY start_time DESC")
    @NotNull
    LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM workout_session WHERE serial_no =:serial_no ORDER BY start_time DESC ")
    @NotNull
    LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataForConnectedDevice(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE strftime('%Y-%m-%d',date) = :date")
    @NotNull
    List<EntityWorkoutSession> getSessionsOfParticularDay(@NotNull String str);

    @Query("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) =:date")
    int getSingleDayCalorieWithoutActivityMapping(@NotNull String str);

    @Query("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) =:date")
    int getSingleDayDistanceWithoutActivityMapping(@NotNull String str);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) =:date")
    int getSingleDayStepWithoutActivityMapping(@NotNull String str);

    @Query("SELECT * FROM skipping_sample WHERE sess_id=:session_id")
    @NotNull
    List<SkippingSample> getSkippingSamplesBy(@NotNull String str);

    @Query("SELECT * FROM skipping_sample WHERE sess_id=:session_id")
    @NotNull
    List<SkippingSample> getSkippingSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM skipping_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<SkippingSample> getSkippingSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM skipping_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<SkippingSample>> getSkippingSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) = :date AND categoryId =:categoryId AND activityId =:activityId")
    int getStepsByActivityNCategoryIDs(@NotNull String str, int i, int i2);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE activity_type=:type AND strftime('%Y-%m-%d',date)=:date")
    int getStepsByActivityType(@NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE categoryId =:categoryId AND activityId =:activityId AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getStepsForWeekByActivityNCategoryIDs(int i, int i2, @NotNull String str, @NotNull String str2);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE activity_type=:type AND strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getStepsForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3);

    @Query("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN :startDate AND :endDate")
    int getStepsWithoutActivityMapping(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM tennis_sample WHERE sess_id=:session_id")
    @NotNull
    List<TennisSample> getTennisSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM tennis_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<TennisSample> getTennisSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM tennis_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<TennisSample>> getTennisSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM treadmill_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<TreadmillSample> getTreadMillSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM treadmill_sample WHERE sess_id=:session_id")
    @NotNull
    List<TreadmillSample> getTreadmillSamplesBy(@NotNull String str);

    @Query("SELECT * FROM treadmill_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<TreadmillSample>> getTreadmillSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_session WHERE sent_to_server=0")
    @NotNull
    List<EntityWorkoutSession> getUnSyncedSessions();

    @Query("SELECT * FROM walk_sample WHERE sess_id=:session_id")
    @NotNull
    List<WalkSample> getWalkSamplesBy(@NotNull String str);

    @Query("SELECT * FROM walk_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<WalkSample> getWalkSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM walk_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<WalkSample>> getWalkSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM workout_sample WHERE sess_id=:session_id")
    @NotNull
    List<WorkoutSample> getWorkoutSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM workout_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<WorkoutSample> getWorkoutSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM workout_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<WorkoutSample>> getWorkoutSamplesLiveDataBy(@NotNull String str);

    @Query("SELECT * FROM yoga_sample WHERE sess_id=:session_id")
    @NotNull
    List<YogaSample> getYogaSamplesBySessionId(@NotNull String str);

    @Query("SELECT * FROM yoga_sample WHERE seg_id=:segment_id AND sess_id=:session_id")
    @NotNull
    List<YogaSample> getYogaSamplesBySessionIdAndSegmentId(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM yoga_sample WHERE sess_id=:session_id")
    @NotNull
    LiveData<List<YogaSample>> getYogaSamplesLiveDataBy(@NotNull String str);

    @Insert(onConflict = 1)
    void insertActivityDataSamples(@NotNull List<ActivityDataSample> list);

    @Insert(onConflict = 1)
    void insertBadmintonSamples(@NotNull List<BadmintonSample> list);

    @Insert(onConflict = 1)
    void insertBasketBallSamples(@NotNull List<BasketBallSample> list);

    @Insert(onConflict = 1)
    void insertClimbingSamples(@NotNull List<ClimbingSample> list);

    @Insert(onConflict = 1)
    void insertCyclingSamples(@NotNull List<CyclingSample> list);

    @Insert(onConflict = 1)
    void insertDanceSamples(@NotNull List<DanceSample> list);

    @Insert(onConflict = 1)
    void insertEllipticalSamples(@NotNull List<EllipticalSample> list);

    @Insert(onConflict = 1)
    void insertFootballSamples(@NotNull List<FootballSample> list);

    @Insert(onConflict = 1)
    void insertFreeExerciseSamples(@NotNull List<FreeExerciseSample> list);

    @Insert(onConflict = 1)
    void insertHikingSamples(@NotNull List<HikingSample> list);

    @Insert(onConflict = 1)
    void insertMeditationSamples(@NotNull List<MeditationSample> list);

    @Insert(onConflict = 1)
    void insertPhysicalActivitySamples(@NotNull List<PhysicalActivitySample> list);

    @Insert(onConflict = 1)
    void insertRowingMachineSamples(@NotNull List<RowingMachineSample> list);

    @Insert(onConflict = 1)
    void insertRunSamples(@NotNull List<RunSample> list);

    @Insert(onConflict = 5)
    void insertSession(@NotNull EntityWorkoutSession entityWorkoutSession);

    @Insert(onConflict = 1)
    void insertSessionSegment(@NotNull EntityWorkoutSessionSegment entityWorkoutSessionSegment);

    @Insert(onConflict = 1)
    void insertSessionSegments(@NotNull List<EntityWorkoutSessionSegment> list);

    @Insert(onConflict = 1)
    void insertSkippingSamples(@NotNull List<SkippingSample> list);

    @Insert(onConflict = 1)
    void insertTennisSamples(@NotNull List<TennisSample> list);

    @Insert(onConflict = 1)
    void insertTreadmillSamples(@NotNull List<TreadmillSample> list);

    @Insert(onConflict = 1)
    void insertWalkSamples(@NotNull List<WalkSample> list);

    @Insert(onConflict = 1)
    void insertWorkoutSamples(@NotNull List<WorkoutSample> list);

    @Insert(onConflict = 1)
    void insertYogaSamples(@NotNull List<YogaSample> list);

    @Query("SELECT COUNT(*) FROM workout_session WHERE client_ref_id=:clientRefId")
    int isClientRefIdExists(@NotNull String str);

    @Query("SELECT COUNT(*) FROM workout_session WHERE  serial_no=:serial_no AND activity_type=:activityType AND start_time=:start_time")
    int isDataPresentInDb(@NotNull String str, @NotNull String str2, long j);

    @Query("SELECT COUNT(*) FROM workout_session_segment WHERE sess_id=:session_id")
    int isSampleDataExists(@NotNull String str);

    @Query("SELECT COUNT(*) FROM workout_session WHERE session_id=:session_id")
    int isSessionIdExists(@NotNull String str);

    @Update(onConflict = 1)
    void updateSession(@NotNull EntityWorkoutSession entityWorkoutSession);
}
