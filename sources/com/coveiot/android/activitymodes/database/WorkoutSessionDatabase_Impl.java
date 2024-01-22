package com.coveiot.android.activitymodes.database;

import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.activitymodes.database.dao.PreparationPlanDao;
import com.coveiot.android.activitymodes.database.dao.PreparationPlanDao_Impl;
import com.coveiot.android.activitymodes.database.dao.SessionDAO;
import com.coveiot.android.activitymodes.database.dao.SessionDAO_Impl;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.event.stat.one.d;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes2.dex */
public final class WorkoutSessionDatabase_Impl extends WorkoutSessionDatabase {
    public volatile SessionDAO n;
    public volatile PreparationPlanDao o;

    /* loaded from: classes2.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `workout_session` (`session_id` TEXT NOT NULL, `categoryId` INTEGER, `activityId` INTEGER, `serial_no` TEXT, `client_ref_id` TEXT, `date` TEXT, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `activity_type` TEXT, `target` TEXT, `target_baseunit` TEXT, `indoor_outdoor` TEXT, `session_duration` INTEGER NOT NULL, `steps_sampling_rate` INTEGER NOT NULL, `hr_sampling_rate` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `total_distance` INTEGER NOT NULL, `max_hr` INTEGER NOT NULL, `min_hr` INTEGER NOT NULL, `avg_hr` INTEGER NOT NULL, `pace` REAL NOT NULL, `fatigue_level` INTEGER NOT NULL, `sent_to_server` INTEGER NOT NULL, `session_place` TEXT, `mood_after_session` TEXT, `feedback` TEXT, `hrZoneRanges` TEXT, `timespent_per_heartratezone` TEXT, `appConnectivity` TEXT, `avgStepFrequency` INTEGER, `maxStepFrequency` INTEGER, `avgSpeed` REAL, `maxSpeed` REAL, `avgStrideLength` INTEGER, `maxStrideLength` INTEGER, `avgPace` REAL, `maxPace` REAL, `totalStrokes` INTEGER, `swimmingStyle` TEXT, `poolLength` INTEGER, `totalLaps` INTEGER, `avgSwolf` INTEGER, `avgStrokeFreq` INTEGER, `isFromHAR` INTEGER, `minPace` REAL, PRIMARY KEY(`session_id`))");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_session_client_ref_id` ON `workout_session` (`client_ref_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `workout_session_segment` (`segment_id` TEXT NOT NULL, `sess_id` TEXT NOT NULL, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `segment_duration` INTEGER NOT NULL, PRIMARY KEY(`segment_id`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_session_segment_sess_id` ON `workout_session_segment` (`sess_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `walk_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_walk_sample_sess_id_seg_id` ON `walk_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `run_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_run_sample_sess_id_seg_id` ON `run_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_plan` (`planId` TEXT NOT NULL, `userPlanId` TEXT, `shortTitle` TEXT, `fulltitle` TEXT, `subtitle` TEXT, `shortDesc` TEXT, `images` TEXT, `imageUrl` TEXT, `introText` TEXT, `startDate` TEXT, `category` TEXT, PRIMARY KEY(`planId`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_plan_schedule` (`schedule_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plan_id` TEXT, `overview` TEXT, FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_plan_schedule_plan_id` ON `entity_plan_schedule` (`plan_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_week` (`week_number` INTEGER NOT NULL, `plan_id` TEXT NOT NULL, `scheduleId` INTEGER NOT NULL, `name` TEXT, `shortDesc` TEXT, `introTexts` TEXT, `weeklyTarget` INTEGER NOT NULL, `targetBaseUnit` TEXT NOT NULL, `daysRange` TEXT, PRIMARY KEY(`week_number`, `plan_id`), FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_preparation_week_plan_id` ON `entity_preparation_week` (`plan_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_day` (`week_number` INTEGER NOT NULL, `date` TEXT NOT NULL, `scheduleId` INTEGER NOT NULL, `plan_id` TEXT NOT NULL, `day_number` INTEGER NOT NULL, `name` TEXT, `activities` TEXT, PRIMARY KEY(`date`, `plan_id`), FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_preparation_day_plan_id` ON `entity_preparation_day` (`plan_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `badminton_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_badminton_sample_sess_id_seg_id` ON `badminton_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `basketball_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_basketball_sample_sess_id_seg_id` ON `basketball_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `cycling_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_cycling_sample_sess_id_seg_id` ON `cycling_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `dance_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_dance_sample_sess_id_seg_id` ON `dance_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `football_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_football_sample_sess_id_seg_id` ON `football_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hiking_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_hiking_sample_sess_id_seg_id` ON `hiking_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `tennis_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_tennis_sample_sess_id_seg_id` ON `tennis_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `workout_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_sample_sess_id_seg_id` ON `workout_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `yoga_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_yoga_sample_sess_id_seg_id` ON `yoga_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `meditation_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_meditation_sample_sess_id_seg_id` ON `meditation_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `treadmill_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_treadmill_sample_sess_id_seg_id` ON `treadmill_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `climbing_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_climbing_sample_sess_id_seg_id` ON `climbing_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `skipping_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_skipping_sample_sess_id_seg_id` ON `skipping_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `free_exercise_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_free_exercise_sample_sess_id_seg_id` ON `free_exercise_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `elliptical_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_elliptical_sample_sess_id_seg_id` ON `elliptical_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rowing_machine_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_rowing_machine_sample_sess_id_seg_id` ON `rowing_machine_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `physical_activity_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_physical_activity_sample_sess_id_seg_id` ON `physical_activity_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `activity_data_sample` (`_id` INTEGER NOT NULL, `segmentID` TEXT, `sessionID` TEXT NOT NULL, `activityType` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `step_count` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hrValue` INTEGER NOT NULL, `speedValue` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sessionID`, `timestamp`), FOREIGN KEY(`sessionID`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_activity_data_sample_sessionID_segmentID` ON `activity_data_sample` (`sessionID`, `segmentID`)");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f0b7091eda3e8468c0dd00434a08141c')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `workout_session`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `workout_session_segment`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `walk_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `run_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_preparation_plan`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_plan_schedule`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_preparation_week`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_preparation_day`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `badminton_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `basketball_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `cycling_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `dance_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `football_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hiking_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `tennis_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `workout_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `yoga_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `meditation_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `treadmill_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `climbing_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `skipping_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `free_exercise_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `elliptical_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `rowing_machine_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `physical_activity_sample`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `activity_data_sample`");
            if (WorkoutSessionDatabase_Impl.this.mCallbacks != null) {
                int size = WorkoutSessionDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) WorkoutSessionDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (WorkoutSessionDatabase_Impl.this.mCallbacks != null) {
                int size = WorkoutSessionDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) WorkoutSessionDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            WorkoutSessionDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            WorkoutSessionDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (WorkoutSessionDatabase_Impl.this.mCallbacks != null) {
                int size = WorkoutSessionDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) WorkoutSessionDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(46);
            hashMap.put(WorkoutConstants.SESSION_ID, new TableInfo.Column(WorkoutConstants.SESSION_ID, "TEXT", true, 1, null, 1));
            hashMap.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", false, 0, null, 1));
            hashMap.put("activityId", new TableInfo.Column("activityId", "INTEGER", false, 0, null, 1));
            hashMap.put("serial_no", new TableInfo.Column("serial_no", "TEXT", false, 0, null, 1));
            hashMap.put("client_ref_id", new TableInfo.Column("client_ref_id", "TEXT", false, 0, null, 1));
            hashMap.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0, null, 1));
            hashMap.put("end_time", new TableInfo.Column("end_time", "INTEGER", true, 0, null, 1));
            hashMap.put("activity_type", new TableInfo.Column("activity_type", "TEXT", false, 0, null, 1));
            hashMap.put(TypedValues.AttributesType.S_TARGET, new TableInfo.Column(TypedValues.AttributesType.S_TARGET, "TEXT", false, 0, null, 1));
            hashMap.put("target_baseunit", new TableInfo.Column("target_baseunit", "TEXT", false, 0, null, 1));
            hashMap.put(WorkoutConstants.INDOOR_OUTDOOR, new TableInfo.Column(WorkoutConstants.INDOOR_OUTDOOR, "TEXT", false, 0, null, 1));
            hashMap.put("session_duration", new TableInfo.Column("session_duration", "INTEGER", true, 0, null, 1));
            hashMap.put("steps_sampling_rate", new TableInfo.Column("steps_sampling_rate", "INTEGER", true, 0, null, 1));
            hashMap.put("hr_sampling_rate", new TableInfo.Column("hr_sampling_rate", "INTEGER", true, 0, null, 1));
            hashMap.put("total_steps", new TableInfo.Column("total_steps", "INTEGER", true, 0, null, 1));
            hashMap.put("total_calories", new TableInfo.Column("total_calories", "REAL", true, 0, null, 1));
            hashMap.put("total_distance", new TableInfo.Column("total_distance", "INTEGER", true, 0, null, 1));
            hashMap.put("max_hr", new TableInfo.Column("max_hr", "INTEGER", true, 0, null, 1));
            hashMap.put("min_hr", new TableInfo.Column("min_hr", "INTEGER", true, 0, null, 1));
            hashMap.put("avg_hr", new TableInfo.Column("avg_hr", "INTEGER", true, 0, null, 1));
            hashMap.put("pace", new TableInfo.Column("pace", "REAL", true, 0, null, 1));
            hashMap.put("fatigue_level", new TableInfo.Column("fatigue_level", "INTEGER", true, 0, null, 1));
            hashMap.put("sent_to_server", new TableInfo.Column("sent_to_server", "INTEGER", true, 0, null, 1));
            hashMap.put("session_place", new TableInfo.Column("session_place", "TEXT", false, 0, null, 1));
            hashMap.put("mood_after_session", new TableInfo.Column("mood_after_session", "TEXT", false, 0, null, 1));
            hashMap.put(d.O, new TableInfo.Column(d.O, "TEXT", false, 0, null, 1));
            hashMap.put("hrZoneRanges", new TableInfo.Column("hrZoneRanges", "TEXT", false, 0, null, 1));
            hashMap.put("timespent_per_heartratezone", new TableInfo.Column("timespent_per_heartratezone", "TEXT", false, 0, null, 1));
            hashMap.put("appConnectivity", new TableInfo.Column("appConnectivity", "TEXT", false, 0, null, 1));
            hashMap.put("avgStepFrequency", new TableInfo.Column("avgStepFrequency", "INTEGER", false, 0, null, 1));
            hashMap.put("maxStepFrequency", new TableInfo.Column("maxStepFrequency", "INTEGER", false, 0, null, 1));
            hashMap.put("avgSpeed", new TableInfo.Column("avgSpeed", "REAL", false, 0, null, 1));
            hashMap.put("maxSpeed", new TableInfo.Column("maxSpeed", "REAL", false, 0, null, 1));
            hashMap.put("avgStrideLength", new TableInfo.Column("avgStrideLength", "INTEGER", false, 0, null, 1));
            hashMap.put("maxStrideLength", new TableInfo.Column("maxStrideLength", "INTEGER", false, 0, null, 1));
            hashMap.put("avgPace", new TableInfo.Column("avgPace", "REAL", false, 0, null, 1));
            hashMap.put("maxPace", new TableInfo.Column("maxPace", "REAL", false, 0, null, 1));
            hashMap.put("totalStrokes", new TableInfo.Column("totalStrokes", "INTEGER", false, 0, null, 1));
            hashMap.put("swimmingStyle", new TableInfo.Column("swimmingStyle", "TEXT", false, 0, null, 1));
            hashMap.put("poolLength", new TableInfo.Column("poolLength", "INTEGER", false, 0, null, 1));
            hashMap.put("totalLaps", new TableInfo.Column("totalLaps", "INTEGER", false, 0, null, 1));
            hashMap.put("avgSwolf", new TableInfo.Column("avgSwolf", "INTEGER", false, 0, null, 1));
            hashMap.put("avgStrokeFreq", new TableInfo.Column("avgStrokeFreq", "INTEGER", false, 0, null, 1));
            hashMap.put("isFromHAR", new TableInfo.Column("isFromHAR", "INTEGER", false, 0, null, 1));
            hashMap.put("minPace", new TableInfo.Column("minPace", "REAL", false, 0, null, 1));
            HashSet hashSet = new HashSet(0);
            HashSet hashSet2 = new HashSet(1);
            hashSet2.add(new TableInfo.Index("index_workout_session_client_ref_id", false, Arrays.asList("client_ref_id")));
            TableInfo tableInfo = new TableInfo("workout_session", hashMap, hashSet, hashSet2);
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "workout_session");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "workout_session(com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(5);
            hashMap2.put("segment_id", new TableInfo.Column("segment_id", "TEXT", true, 1, null, 1));
            hashMap2.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 0, null, 1));
            hashMap2.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0, null, 1));
            hashMap2.put("end_time", new TableInfo.Column("end_time", "INTEGER", true, 0, null, 1));
            hashMap2.put("segment_duration", new TableInfo.Column("segment_duration", "INTEGER", true, 0, null, 1));
            HashSet hashSet3 = new HashSet(1);
            hashSet3.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet4 = new HashSet(1);
            hashSet4.add(new TableInfo.Index("index_workout_session_segment_sess_id", false, Arrays.asList("sess_id")));
            TableInfo tableInfo2 = new TableInfo("workout_session_segment", hashMap2, hashSet3, hashSet4);
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "workout_session_segment");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "workout_session_segment(com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(11);
            hashMap3.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap3.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap3.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap3.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap3.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap3.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap3.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap3.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap3.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap3.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap3.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet5 = new HashSet(1);
            hashSet5.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet6 = new HashSet(1);
            hashSet6.add(new TableInfo.Index("index_walk_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo3 = new TableInfo("walk_sample", hashMap3, hashSet5, hashSet6);
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "walk_sample");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "walk_sample(com.coveiot.android.activitymodes.database.entities.WalkSample).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(11);
            hashMap4.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap4.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap4.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap4.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap4.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap4.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap4.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap4.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap4.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap4.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap4.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet7 = new HashSet(1);
            hashSet7.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet8 = new HashSet(1);
            hashSet8.add(new TableInfo.Index("index_run_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo4 = new TableInfo("run_sample", hashMap4, hashSet7, hashSet8);
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "run_sample");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "run_sample(com.coveiot.android.activitymodes.database.entities.RunSample).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(11);
            hashMap5.put("planId", new TableInfo.Column("planId", "TEXT", true, 1, null, 1));
            hashMap5.put("userPlanId", new TableInfo.Column("userPlanId", "TEXT", false, 0, null, 1));
            hashMap5.put("shortTitle", new TableInfo.Column("shortTitle", "TEXT", false, 0, null, 1));
            hashMap5.put("fulltitle", new TableInfo.Column("fulltitle", "TEXT", false, 0, null, 1));
            hashMap5.put("subtitle", new TableInfo.Column("subtitle", "TEXT", false, 0, null, 1));
            hashMap5.put("shortDesc", new TableInfo.Column("shortDesc", "TEXT", false, 0, null, 1));
            hashMap5.put("images", new TableInfo.Column("images", "TEXT", false, 0, null, 1));
            hashMap5.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, 1));
            hashMap5.put("introText", new TableInfo.Column("introText", "TEXT", false, 0, null, 1));
            hashMap5.put("startDate", new TableInfo.Column("startDate", "TEXT", false, 0, null, 1));
            hashMap5.put(SavingTrackHelper.POINT_COL_CATEGORY, new TableInfo.Column(SavingTrackHelper.POINT_COL_CATEGORY, "TEXT", false, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("entity_preparation_plan", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "entity_preparation_plan");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_preparation_plan(com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(3);
            hashMap6.put("schedule_id", new TableInfo.Column("schedule_id", "INTEGER", true, 1, null, 1));
            hashMap6.put("plan_id", new TableInfo.Column("plan_id", "TEXT", false, 0, null, 1));
            hashMap6.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, null, 1));
            HashSet hashSet9 = new HashSet(1);
            hashSet9.add(new TableInfo.ForeignKey("entity_preparation_plan", "CASCADE", "NO ACTION", Arrays.asList("plan_id"), Arrays.asList("planId")));
            HashSet hashSet10 = new HashSet(1);
            hashSet10.add(new TableInfo.Index("index_entity_plan_schedule_plan_id", false, Arrays.asList("plan_id")));
            TableInfo tableInfo6 = new TableInfo("entity_plan_schedule", hashMap6, hashSet9, hashSet10);
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "entity_plan_schedule");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_plan_schedule(com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
            }
            HashMap hashMap7 = new HashMap(9);
            hashMap7.put("week_number", new TableInfo.Column("week_number", "INTEGER", true, 1, null, 1));
            hashMap7.put("plan_id", new TableInfo.Column("plan_id", "TEXT", true, 2, null, 1));
            hashMap7.put("scheduleId", new TableInfo.Column("scheduleId", "INTEGER", true, 0, null, 1));
            hashMap7.put(AppMeasurementSdk.ConditionalUserProperty.NAME, new TableInfo.Column(AppMeasurementSdk.ConditionalUserProperty.NAME, "TEXT", false, 0, null, 1));
            hashMap7.put("shortDesc", new TableInfo.Column("shortDesc", "TEXT", false, 0, null, 1));
            hashMap7.put("introTexts", new TableInfo.Column("introTexts", "TEXT", false, 0, null, 1));
            hashMap7.put("weeklyTarget", new TableInfo.Column("weeklyTarget", "INTEGER", true, 0, null, 1));
            hashMap7.put("targetBaseUnit", new TableInfo.Column("targetBaseUnit", "TEXT", true, 0, null, 1));
            hashMap7.put("daysRange", new TableInfo.Column("daysRange", "TEXT", false, 0, null, 1));
            HashSet hashSet11 = new HashSet(1);
            hashSet11.add(new TableInfo.ForeignKey("entity_preparation_plan", "CASCADE", "NO ACTION", Arrays.asList("plan_id"), Arrays.asList("planId")));
            HashSet hashSet12 = new HashSet(1);
            hashSet12.add(new TableInfo.Index("index_entity_preparation_week_plan_id", false, Arrays.asList("plan_id")));
            TableInfo tableInfo7 = new TableInfo("entity_preparation_week", hashMap7, hashSet11, hashSet12);
            TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "entity_preparation_week");
            if (!tableInfo7.equals(read7)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_preparation_week(com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
            }
            HashMap hashMap8 = new HashMap(7);
            hashMap8.put("week_number", new TableInfo.Column("week_number", "INTEGER", true, 0, null, 1));
            hashMap8.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap8.put("scheduleId", new TableInfo.Column("scheduleId", "INTEGER", true, 0, null, 1));
            hashMap8.put("plan_id", new TableInfo.Column("plan_id", "TEXT", true, 2, null, 1));
            hashMap8.put("day_number", new TableInfo.Column("day_number", "INTEGER", true, 0, null, 1));
            hashMap8.put(AppMeasurementSdk.ConditionalUserProperty.NAME, new TableInfo.Column(AppMeasurementSdk.ConditionalUserProperty.NAME, "TEXT", false, 0, null, 1));
            hashMap8.put("activities", new TableInfo.Column("activities", "TEXT", false, 0, null, 1));
            HashSet hashSet13 = new HashSet(1);
            hashSet13.add(new TableInfo.ForeignKey("entity_preparation_plan", "CASCADE", "NO ACTION", Arrays.asList("plan_id"), Arrays.asList("planId")));
            HashSet hashSet14 = new HashSet(1);
            hashSet14.add(new TableInfo.Index("index_entity_preparation_day_plan_id", false, Arrays.asList("plan_id")));
            TableInfo tableInfo8 = new TableInfo("entity_preparation_day", hashMap8, hashSet13, hashSet14);
            TableInfo read8 = TableInfo.read(supportSQLiteDatabase, "entity_preparation_day");
            if (!tableInfo8.equals(read8)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_preparation_day(com.coveiot.android.activitymodes.database.entities.EntityPreparationDay).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
            }
            HashMap hashMap9 = new HashMap(11);
            hashMap9.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap9.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap9.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap9.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap9.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap9.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap9.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap9.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap9.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap9.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap9.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet15 = new HashSet(1);
            hashSet15.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet16 = new HashSet(1);
            hashSet16.add(new TableInfo.Index("index_badminton_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo9 = new TableInfo("badminton_sample", hashMap9, hashSet15, hashSet16);
            TableInfo read9 = TableInfo.read(supportSQLiteDatabase, "badminton_sample");
            if (!tableInfo9.equals(read9)) {
                return new RoomOpenHelper.ValidationResult(false, "badminton_sample(com.coveiot.android.activitymodes.database.entities.BadmintonSample).\n Expected:\n" + tableInfo9 + "\n Found:\n" + read9);
            }
            HashMap hashMap10 = new HashMap(11);
            hashMap10.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap10.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap10.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap10.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap10.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap10.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap10.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap10.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap10.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap10.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap10.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet17 = new HashSet(1);
            hashSet17.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet18 = new HashSet(1);
            hashSet18.add(new TableInfo.Index("index_basketball_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo10 = new TableInfo("basketball_sample", hashMap10, hashSet17, hashSet18);
            TableInfo read10 = TableInfo.read(supportSQLiteDatabase, "basketball_sample");
            if (!tableInfo10.equals(read10)) {
                return new RoomOpenHelper.ValidationResult(false, "basketball_sample(com.coveiot.android.activitymodes.database.entities.BasketBallSample).\n Expected:\n" + tableInfo10 + "\n Found:\n" + read10);
            }
            HashMap hashMap11 = new HashMap(11);
            hashMap11.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap11.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap11.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap11.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap11.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap11.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap11.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap11.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap11.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap11.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap11.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet19 = new HashSet(1);
            hashSet19.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet20 = new HashSet(1);
            hashSet20.add(new TableInfo.Index("index_cycling_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo11 = new TableInfo("cycling_sample", hashMap11, hashSet19, hashSet20);
            TableInfo read11 = TableInfo.read(supportSQLiteDatabase, "cycling_sample");
            if (!tableInfo11.equals(read11)) {
                return new RoomOpenHelper.ValidationResult(false, "cycling_sample(com.coveiot.android.activitymodes.database.entities.CyclingSample).\n Expected:\n" + tableInfo11 + "\n Found:\n" + read11);
            }
            HashMap hashMap12 = new HashMap(11);
            hashMap12.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap12.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap12.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap12.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap12.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap12.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap12.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap12.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap12.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap12.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap12.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet21 = new HashSet(1);
            hashSet21.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet22 = new HashSet(1);
            hashSet22.add(new TableInfo.Index("index_dance_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo12 = new TableInfo("dance_sample", hashMap12, hashSet21, hashSet22);
            TableInfo read12 = TableInfo.read(supportSQLiteDatabase, "dance_sample");
            if (!tableInfo12.equals(read12)) {
                return new RoomOpenHelper.ValidationResult(false, "dance_sample(com.coveiot.android.activitymodes.database.entities.DanceSample).\n Expected:\n" + tableInfo12 + "\n Found:\n" + read12);
            }
            HashMap hashMap13 = new HashMap(11);
            hashMap13.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap13.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap13.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap13.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap13.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap13.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap13.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap13.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap13.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap13.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap13.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet23 = new HashSet(1);
            hashSet23.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet24 = new HashSet(1);
            hashSet24.add(new TableInfo.Index("index_football_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo13 = new TableInfo("football_sample", hashMap13, hashSet23, hashSet24);
            TableInfo read13 = TableInfo.read(supportSQLiteDatabase, "football_sample");
            if (!tableInfo13.equals(read13)) {
                return new RoomOpenHelper.ValidationResult(false, "football_sample(com.coveiot.android.activitymodes.database.entities.FootballSample).\n Expected:\n" + tableInfo13 + "\n Found:\n" + read13);
            }
            HashMap hashMap14 = new HashMap(11);
            hashMap14.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap14.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap14.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap14.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap14.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap14.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap14.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap14.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap14.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap14.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap14.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet25 = new HashSet(1);
            hashSet25.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet26 = new HashSet(1);
            hashSet26.add(new TableInfo.Index("index_hiking_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo14 = new TableInfo("hiking_sample", hashMap14, hashSet25, hashSet26);
            TableInfo read14 = TableInfo.read(supportSQLiteDatabase, "hiking_sample");
            if (!tableInfo14.equals(read14)) {
                return new RoomOpenHelper.ValidationResult(false, "hiking_sample(com.coveiot.android.activitymodes.database.entities.HikingSample).\n Expected:\n" + tableInfo14 + "\n Found:\n" + read14);
            }
            HashMap hashMap15 = new HashMap(11);
            hashMap15.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap15.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap15.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap15.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap15.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap15.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap15.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap15.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap15.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap15.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap15.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet27 = new HashSet(1);
            hashSet27.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet28 = new HashSet(1);
            hashSet28.add(new TableInfo.Index("index_tennis_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo15 = new TableInfo("tennis_sample", hashMap15, hashSet27, hashSet28);
            TableInfo read15 = TableInfo.read(supportSQLiteDatabase, "tennis_sample");
            if (!tableInfo15.equals(read15)) {
                return new RoomOpenHelper.ValidationResult(false, "tennis_sample(com.coveiot.android.activitymodes.database.entities.TennisSample).\n Expected:\n" + tableInfo15 + "\n Found:\n" + read15);
            }
            HashMap hashMap16 = new HashMap(11);
            hashMap16.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap16.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap16.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap16.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap16.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap16.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap16.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap16.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap16.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap16.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap16.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet29 = new HashSet(1);
            hashSet29.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet30 = new HashSet(1);
            hashSet30.add(new TableInfo.Index("index_workout_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo16 = new TableInfo("workout_sample", hashMap16, hashSet29, hashSet30);
            TableInfo read16 = TableInfo.read(supportSQLiteDatabase, "workout_sample");
            if (!tableInfo16.equals(read16)) {
                return new RoomOpenHelper.ValidationResult(false, "workout_sample(com.coveiot.android.activitymodes.database.entities.WorkoutSample).\n Expected:\n" + tableInfo16 + "\n Found:\n" + read16);
            }
            HashMap hashMap17 = new HashMap(11);
            hashMap17.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap17.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap17.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap17.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap17.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap17.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap17.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap17.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap17.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap17.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap17.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet31 = new HashSet(1);
            hashSet31.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet32 = new HashSet(1);
            hashSet32.add(new TableInfo.Index("index_yoga_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo17 = new TableInfo("yoga_sample", hashMap17, hashSet31, hashSet32);
            TableInfo read17 = TableInfo.read(supportSQLiteDatabase, "yoga_sample");
            if (!tableInfo17.equals(read17)) {
                return new RoomOpenHelper.ValidationResult(false, "yoga_sample(com.coveiot.android.activitymodes.database.entities.YogaSample).\n Expected:\n" + tableInfo17 + "\n Found:\n" + read17);
            }
            HashMap hashMap18 = new HashMap(11);
            hashMap18.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap18.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap18.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap18.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap18.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap18.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap18.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap18.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap18.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap18.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap18.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet33 = new HashSet(1);
            hashSet33.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet34 = new HashSet(1);
            hashSet34.add(new TableInfo.Index("index_meditation_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo18 = new TableInfo("meditation_sample", hashMap18, hashSet33, hashSet34);
            TableInfo read18 = TableInfo.read(supportSQLiteDatabase, "meditation_sample");
            if (!tableInfo18.equals(read18)) {
                return new RoomOpenHelper.ValidationResult(false, "meditation_sample(com.coveiot.android.activitymodes.database.entities.MeditationSample).\n Expected:\n" + tableInfo18 + "\n Found:\n" + read18);
            }
            HashMap hashMap19 = new HashMap(11);
            hashMap19.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap19.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap19.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap19.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap19.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap19.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap19.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap19.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap19.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap19.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap19.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet35 = new HashSet(1);
            hashSet35.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet36 = new HashSet(1);
            hashSet36.add(new TableInfo.Index("index_treadmill_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo19 = new TableInfo("treadmill_sample", hashMap19, hashSet35, hashSet36);
            TableInfo read19 = TableInfo.read(supportSQLiteDatabase, "treadmill_sample");
            if (!tableInfo19.equals(read19)) {
                return new RoomOpenHelper.ValidationResult(false, "treadmill_sample(com.coveiot.android.activitymodes.database.entities.TreadmillSample).\n Expected:\n" + tableInfo19 + "\n Found:\n" + read19);
            }
            HashMap hashMap20 = new HashMap(11);
            hashMap20.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap20.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap20.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap20.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap20.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap20.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap20.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap20.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap20.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap20.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap20.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet37 = new HashSet(1);
            hashSet37.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet38 = new HashSet(1);
            hashSet38.add(new TableInfo.Index("index_climbing_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo20 = new TableInfo("climbing_sample", hashMap20, hashSet37, hashSet38);
            TableInfo read20 = TableInfo.read(supportSQLiteDatabase, "climbing_sample");
            if (!tableInfo20.equals(read20)) {
                return new RoomOpenHelper.ValidationResult(false, "climbing_sample(com.coveiot.android.activitymodes.database.entities.ClimbingSample).\n Expected:\n" + tableInfo20 + "\n Found:\n" + read20);
            }
            HashMap hashMap21 = new HashMap(11);
            hashMap21.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap21.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap21.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap21.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap21.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap21.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap21.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap21.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap21.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap21.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap21.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet39 = new HashSet(1);
            hashSet39.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet40 = new HashSet(1);
            hashSet40.add(new TableInfo.Index("index_skipping_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo21 = new TableInfo("skipping_sample", hashMap21, hashSet39, hashSet40);
            TableInfo read21 = TableInfo.read(supportSQLiteDatabase, "skipping_sample");
            if (!tableInfo21.equals(read21)) {
                return new RoomOpenHelper.ValidationResult(false, "skipping_sample(com.coveiot.android.activitymodes.database.entities.SkippingSample).\n Expected:\n" + tableInfo21 + "\n Found:\n" + read21);
            }
            HashMap hashMap22 = new HashMap(11);
            hashMap22.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap22.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap22.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap22.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap22.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap22.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap22.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap22.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap22.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap22.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap22.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet41 = new HashSet(1);
            hashSet41.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet42 = new HashSet(1);
            hashSet42.add(new TableInfo.Index("index_free_exercise_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo22 = new TableInfo("free_exercise_sample", hashMap22, hashSet41, hashSet42);
            TableInfo read22 = TableInfo.read(supportSQLiteDatabase, "free_exercise_sample");
            if (!tableInfo22.equals(read22)) {
                return new RoomOpenHelper.ValidationResult(false, "free_exercise_sample(com.coveiot.android.activitymodes.database.entities.FreeExerciseSample).\n Expected:\n" + tableInfo22 + "\n Found:\n" + read22);
            }
            HashMap hashMap23 = new HashMap(11);
            hashMap23.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap23.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap23.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap23.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap23.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap23.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap23.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap23.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap23.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap23.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap23.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet43 = new HashSet(1);
            hashSet43.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet44 = new HashSet(1);
            hashSet44.add(new TableInfo.Index("index_elliptical_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo23 = new TableInfo("elliptical_sample", hashMap23, hashSet43, hashSet44);
            TableInfo read23 = TableInfo.read(supportSQLiteDatabase, "elliptical_sample");
            if (!tableInfo23.equals(read23)) {
                return new RoomOpenHelper.ValidationResult(false, "elliptical_sample(com.coveiot.android.activitymodes.database.entities.EllipticalSample).\n Expected:\n" + tableInfo23 + "\n Found:\n" + read23);
            }
            HashMap hashMap24 = new HashMap(11);
            hashMap24.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap24.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap24.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap24.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap24.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap24.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap24.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap24.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap24.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap24.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap24.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet45 = new HashSet(1);
            hashSet45.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet46 = new HashSet(1);
            hashSet46.add(new TableInfo.Index("index_rowing_machine_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo24 = new TableInfo("rowing_machine_sample", hashMap24, hashSet45, hashSet46);
            TableInfo read24 = TableInfo.read(supportSQLiteDatabase, "rowing_machine_sample");
            if (!tableInfo24.equals(read24)) {
                return new RoomOpenHelper.ValidationResult(false, "rowing_machine_sample(com.coveiot.android.activitymodes.database.entities.RowingMachineSample).\n Expected:\n" + tableInfo24 + "\n Found:\n" + read24);
            }
            HashMap hashMap25 = new HashMap(11);
            hashMap25.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap25.put("seg_id", new TableInfo.Column("seg_id", "TEXT", false, 0, null, 1));
            hashMap25.put("sess_id", new TableInfo.Column("sess_id", "TEXT", true, 1, null, 1));
            hashMap25.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap25.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap25.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap25.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap25.put("hr_value", new TableInfo.Column("hr_value", "INTEGER", true, 0, null, 1));
            hashMap25.put("speed_value", new TableInfo.Column("speed_value", "REAL", true, 0, null, 1));
            hashMap25.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap25.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet47 = new HashSet(1);
            hashSet47.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sess_id"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet48 = new HashSet(1);
            hashSet48.add(new TableInfo.Index("index_physical_activity_sample_sess_id_seg_id", false, Arrays.asList("sess_id", "seg_id")));
            TableInfo tableInfo25 = new TableInfo("physical_activity_sample", hashMap25, hashSet47, hashSet48);
            TableInfo read25 = TableInfo.read(supportSQLiteDatabase, "physical_activity_sample");
            if (!tableInfo25.equals(read25)) {
                return new RoomOpenHelper.ValidationResult(false, "physical_activity_sample(com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample).\n Expected:\n" + tableInfo25 + "\n Found:\n" + read25);
            }
            HashMap hashMap26 = new HashMap(12);
            hashMap26.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, 1));
            hashMap26.put("segmentID", new TableInfo.Column("segmentID", "TEXT", false, 0, null, 1));
            hashMap26.put("sessionID", new TableInfo.Column("sessionID", "TEXT", true, 1, null, 1));
            hashMap26.put("activityType", new TableInfo.Column("activityType", "TEXT", true, 0, null, 1));
            hashMap26.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2, null, 1));
            hashMap26.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
            hashMap26.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap26.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap26.put("hrValue", new TableInfo.Column("hrValue", "INTEGER", true, 0, null, 1));
            hashMap26.put("speedValue", new TableInfo.Column("speedValue", "REAL", true, 0, null, 1));
            hashMap26.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
            hashMap26.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
            HashSet hashSet49 = new HashSet(1);
            hashSet49.add(new TableInfo.ForeignKey("workout_session", "CASCADE", "NO ACTION", Arrays.asList("sessionID"), Arrays.asList(WorkoutConstants.SESSION_ID)));
            HashSet hashSet50 = new HashSet(1);
            hashSet50.add(new TableInfo.Index("index_activity_data_sample_sessionID_segmentID", false, Arrays.asList("sessionID", "segmentID")));
            TableInfo tableInfo26 = new TableInfo("activity_data_sample", hashMap26, hashSet49, hashSet50);
            TableInfo read26 = TableInfo.read(supportSQLiteDatabase, "activity_data_sample");
            if (!tableInfo26.equals(read26)) {
                return new RoomOpenHelper.ValidationResult(false, "activity_data_sample(com.coveiot.android.activitymodes.database.entities.ActivityDataSample).\n Expected:\n" + tableInfo26 + "\n Found:\n" + read26);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } finally {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `workout_session`");
        writableDatabase.execSQL("DELETE FROM `workout_session_segment`");
        writableDatabase.execSQL("DELETE FROM `walk_sample`");
        writableDatabase.execSQL("DELETE FROM `run_sample`");
        writableDatabase.execSQL("DELETE FROM `entity_preparation_plan`");
        writableDatabase.execSQL("DELETE FROM `entity_plan_schedule`");
        writableDatabase.execSQL("DELETE FROM `entity_preparation_week`");
        writableDatabase.execSQL("DELETE FROM `entity_preparation_day`");
        writableDatabase.execSQL("DELETE FROM `badminton_sample`");
        writableDatabase.execSQL("DELETE FROM `basketball_sample`");
        writableDatabase.execSQL("DELETE FROM `cycling_sample`");
        writableDatabase.execSQL("DELETE FROM `dance_sample`");
        writableDatabase.execSQL("DELETE FROM `football_sample`");
        writableDatabase.execSQL("DELETE FROM `hiking_sample`");
        writableDatabase.execSQL("DELETE FROM `tennis_sample`");
        writableDatabase.execSQL("DELETE FROM `workout_sample`");
        writableDatabase.execSQL("DELETE FROM `yoga_sample`");
        writableDatabase.execSQL("DELETE FROM `meditation_sample`");
        writableDatabase.execSQL("DELETE FROM `treadmill_sample`");
        writableDatabase.execSQL("DELETE FROM `climbing_sample`");
        writableDatabase.execSQL("DELETE FROM `skipping_sample`");
        writableDatabase.execSQL("DELETE FROM `free_exercise_sample`");
        writableDatabase.execSQL("DELETE FROM `elliptical_sample`");
        writableDatabase.execSQL("DELETE FROM `rowing_machine_sample`");
        writableDatabase.execSQL("DELETE FROM `physical_activity_sample`");
        writableDatabase.execSQL("DELETE FROM `activity_data_sample`");
        super.setTransactionSuccessful();
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "workout_session", "workout_session_segment", "walk_sample", "run_sample", "entity_preparation_plan", "entity_plan_schedule", "entity_preparation_week", "entity_preparation_day", "badminton_sample", "basketball_sample", "cycling_sample", "dance_sample", "football_sample", "hiking_sample", "tennis_sample", "workout_sample", "yoga_sample", "meditation_sample", "treadmill_sample", "climbing_sample", "skipping_sample", "free_exercise_sample", "elliptical_sample", "rowing_machine_sample", "physical_activity_sample", "activity_data_sample");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(15), "f0b7091eda3e8468c0dd00434a08141c", "1539dc3a5a419b92872496fcf487dc34")).build());
    }

    @Override // com.coveiot.android.activitymodes.database.WorkoutSessionDatabase
    public PreparationPlanDao preparationPlanDao() {
        PreparationPlanDao preparationPlanDao;
        if (this.o != null) {
            return this.o;
        }
        synchronized (this) {
            if (this.o == null) {
                this.o = new PreparationPlanDao_Impl(this);
            }
            preparationPlanDao = this.o;
        }
        return preparationPlanDao;
    }

    @Override // com.coveiot.android.activitymodes.database.WorkoutSessionDatabase
    public SessionDAO sessionDAO() {
        SessionDAO sessionDAO;
        if (this.n != null) {
            return this.n;
        }
        synchronized (this) {
            if (this.n == null) {
                this.n = new SessionDAO_Impl(this);
            }
            sessionDAO = this.n;
        }
        return sessionDAO;
    }
}
