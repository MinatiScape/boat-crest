package com.coveiot.android.activitymodes.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.activitymodes.database.convertors.Covertors;
import com.coveiot.android.activitymodes.database.dao.PreparationPlanDao;
import com.coveiot.android.activitymodes.database.dao.SessionDAO;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
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
import com.coveiot.android.activitymodes.preference.PreferenceManager;
@TypeConverters({Covertors.class})
@Database(entities = {EntityWorkoutSession.class, EntityWorkoutSessionSegment.class, WalkSample.class, RunSample.class, EntityPreparationPlan.class, EntityPlanSchedule.class, EntityPreparationWeek.class, EntityPreparationDay.class, BadmintonSample.class, BasketBallSample.class, CyclingSample.class, DanceSample.class, FootballSample.class, HikingSample.class, TennisSample.class, WorkoutSample.class, YogaSample.class, MeditationSample.class, TreadmillSample.class, ClimbingSample.class, SkippingSample.class, FreeExerciseSample.class, EllipticalSample.class, RowingMachineSample.class, PhysicalActivitySample.class, ActivityDataSample.class}, exportSchema = false, version = 15)
/* loaded from: classes2.dex */
public abstract class WorkoutSessionDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f2797a;
    public static final Migration b;
    public static final Migration c;
    public static final String covedatabase = "workout_session_database";
    public static final Migration d;
    public static final Migration e;
    public static final Migration f;
    public static Context g;
    public static final Migration h;
    public static final Migration i;
    public static final Migration j;
    public static final Migration k;
    public static final Migration l;
    public static WorkoutSessionDatabase m;

    /* loaded from: classes2.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'swimmingStyle' TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'totalStrokes' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'poolLength' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'totalLaps' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgSwolf' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgStrokeFreq' INTEGER DEFAULT NULL");
        }
    }

    /* loaded from: classes2.dex */
    public class b extends Migration {
        public b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'isFromHAR' INTEGER DEFAULT NULL");
        }
    }

    /* loaded from: classes2.dex */
    public class c extends Migration {
        public c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_plan_temp` (`planId` TEXT NOT NULL, `userPlanId` TEXT DEFAULT NULL, `shortTitle` TEXT DEFAULT NULL, `fulltitle` TEXT DEFAULT NULL, `subtitle` TEXT DEFAULT NULL, `shortDesc` TEXT DEFAULT NULL, `images` TEXT DEFAULT NULL, `imageUrl` TEXT DEFAULT NULL,  `introText` TEXT DEFAULT NULL, `startDate` TEXT DEFAULT NULL, `category` TEXT DEFAULT NULL, PRIMARY KEY(`planId`))");
            supportSQLiteDatabase.execSQL("INSERT INTO entity_preparation_plan_temp (planId, userPlanId, shortTitle ,fulltitle,subtitle,shortDesc,images,imageUrl,introText,startDate,category) SELECT planId, userPlanId, shortTitle ,fulltitle,subtitle,shortDesc,images,imageUrl,introText,startDate,category FROM entity_preparation_plan");
            supportSQLiteDatabase.execSQL("DROP TABLE entity_preparation_plan");
            supportSQLiteDatabase.execSQL("ALTER TABLE entity_preparation_plan_temp RENAME TO entity_preparation_plan");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_plan_schedule_temp` (`schedule_id` INTEGER NOT NULL, `plan_id` TEXT DEFAULT NULL, `overview` TEXT DEFAULT NULL, PRIMARY KEY(`schedule_id`), FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("INSERT INTO `entity_plan_schedule_temp` (schedule_id, plan_id, overview) SELECT schedule_id, plan_id, overview FROM entity_plan_schedule");
            supportSQLiteDatabase.execSQL("DROP TABLE entity_plan_schedule");
            supportSQLiteDatabase.execSQL("ALTER TABLE entity_plan_schedule_temp RENAME TO entity_plan_schedule");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_plan_schedule_plan_id` ON `entity_plan_schedule` (`plan_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_week_temp` (`week_number` INTEGER NOT NULL, `plan_id` TEXT NOT NULL, `scheduleId` INTEGER NOT NULL,  `name` TEXT DEFAULT NULL, `shortDesc` TEXT DEFAULT NULL, `introTexts` TEXT DEFAULT NULL, `weeklyTarget` INTEGER NOT NULL,  `targetBaseUnit` TEXT NOT NULL, `daysRange` TEXT DEFAULT NULL, PRIMARY KEY(`week_number`,`plan_id`), FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("INSERT INTO `entity_preparation_week_temp` (week_number,plan_id, scheduleId, name, shortDesc, introTexts, weeklyTarget, targetBaseUnit, daysRange) SELECT week_number,plan_id, scheduleId, name, shortDesc, introTexts, weeklyTarget, targetBaseUnit, daysRange FROM entity_preparation_week");
            supportSQLiteDatabase.execSQL("DROP TABLE entity_preparation_week");
            supportSQLiteDatabase.execSQL("ALTER TABLE entity_preparation_week_temp RENAME TO entity_preparation_week");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_preparation_week_plan_id` ON `entity_preparation_week` (`plan_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_preparation_day_temp` ( `week_number` INTEGER NOT NULL, `date` TEXT NOT NULL, `scheduleId` INTEGER NOT NULL, `plan_id` TEXT NOT NULL,  `day_number` INTEGER NOT NULL,  `name` TEXT DEFAULT NULL, `activities` TEXT DEFAULT NULL, PRIMARY KEY(`date`,`plan_id`), FOREIGN KEY(`plan_id`) REFERENCES `entity_preparation_plan`(`planId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("INSERT INTO `entity_preparation_day_temp` (week_number,date,scheduleId,plan_id, day_number, name, activities) SELECT week_number,date,scheduleId,plan_id, day_number, name, activities FROM entity_preparation_day");
            supportSQLiteDatabase.execSQL("DROP TABLE entity_preparation_day");
            supportSQLiteDatabase.execSQL("ALTER TABLE entity_preparation_day_temp RENAME TO entity_preparation_day");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_entity_preparation_day_plan_id` ON `entity_preparation_day` (`plan_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class d extends Migration {
        public d(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'minPace' REAL DEFAULT NULL");
        }
    }

    /* loaded from: classes2.dex */
    public class e extends Migration {
        public e(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN serial_no TEXT");
        }
    }

    /* loaded from: classes2.dex */
    public class f extends Migration {
        public f(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `meditation_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_meditation_sample_sess_id_seg_id` ON `meditation_sample` (`sess_id`, `seg_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class g extends Migration {
        public g(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `treadmill_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_treadmill_sample_sess_id_seg_id` ON `treadmill_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `climbing_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_climbing_sample_sess_id_seg_id` ON `climbing_sample` (`sess_id`, `seg_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class h extends Migration {
        public h(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `skipping_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_skipping_sample_sess_id_seg_id` ON `skipping_sample` (`sess_id`, `seg_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class i extends Migration {
        public i(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `free_exercise_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_free_exercise_sample_sess_id_seg_id` ON `free_exercise_sample` (`sess_id`, `seg_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class j extends Migration {
        public j(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `elliptical_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_elliptical_sample_sess_id_seg_id` ON `elliptical_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rowing_machine_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_rowing_machine_sample_sample_sess_id_seg_id` ON `rowing_machine_sample` (`sess_id`, `seg_id`)");
        }
    }

    /* loaded from: classes2.dex */
    public class k extends Migration {
        public k(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `physical_activity_sample` (`_id` INTEGER NOT NULL, `seg_id` TEXT, `sess_id` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hr_value` INTEGER NOT NULL, `speed_value` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sess_id`, `timestamp`), FOREIGN KEY(`sess_id`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_physical_activity_sample_sess_id_seg_id` ON `physical_activity_sample` (`sess_id`, `seg_id`)");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'workout_session' ADD COLUMN 'categoryId' INTEGER   DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'workout_session' ADD COLUMN 'activityId' INTEGER   DEFAULT NULL");
        }
    }

    /* loaded from: classes2.dex */
    public class l extends Migration {
        public l(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (new PreferenceManager(WorkoutSessionDatabase.g).isSportModeHistorySupported()) {
                supportSQLiteDatabase.execSQL("DELETE FROM workout_session");
            }
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
        }
    }

    /* loaded from: classes2.dex */
    public class m extends Migration {
        public m(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN appConnectivity TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgStepFrequency' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'maxStepFrequency' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgSpeed' REAL DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'maxSpeed' REAL DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgStrideLength' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'maxStrideLength' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'avgPace' REAL DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE `workout_session` ADD COLUMN 'maxPace' REAL DEFAULT NULL");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `activity_data_sample` (`_id` INTEGER NOT NULL, `segmentID` TEXT, `sessionID` TEXT NOT NULL, `activityType` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `step_count` INTEGER NOT NULL, `calories` REAL NOT NULL, `distance` INTEGER NOT NULL, `hrValue` INTEGER NOT NULL, `speedValue` REAL NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`sessionID`, `timestamp`), FOREIGN KEY(`sessionID`) REFERENCES `workout_session`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_activity_data_sample_sessionID_segmentID` ON `activity_data_sample` (`sessionID`, `segmentID`)");
        }
    }

    static {
        new e(1, 2);
        f2797a = new f(4, 5);
        b = new g(5, 6);
        c = new h(6, 7);
        d = new i(7, 8);
        e = new j(8, 9);
        f = new k(9, 10);
        new l(2, 3);
        h = new m(10, 11);
        i = new a(11, 12);
        j = new b(12, 13);
        k = new c(13, 14);
        l = new d(14, 15);
    }

    public static WorkoutSessionDatabase getAppDatabase(Context context) {
        g = context;
        if (m == null) {
            m = (WorkoutSessionDatabase) Room.databaseBuilder(context.getApplicationContext(), WorkoutSessionDatabase.class, covedatabase).addMigrations(f2797a).addMigrations(b).addMigrations(c).addMigrations(d).addMigrations(e).addMigrations(f).addMigrations(h).addMigrations(i).addMigrations(j).addMigrations(k).addMigrations(l).build();
        }
        return m;
    }

    public abstract PreparationPlanDao preparationPlanDao();

    public abstract SessionDAO sessionDAO();
}
