package com.coveiot.khsmadb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.khsmadb.activity.KhActivityDao;
import com.coveiot.khsmadb.activity.KhBleActivity;
import com.coveiot.khsmadb.bp.KhBleBloodPressure;
import com.coveiot.khsmadb.bp.KhBloodPressureDao;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfo;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao;
import com.coveiot.khsmadb.heartrate.KhBleHeartRate;
import com.coveiot.khsmadb.heartrate.KhHeartRateDao;
import com.coveiot.khsmadb.location.KhLocation;
import com.coveiot.khsmadb.location.KhLocationDao;
import com.coveiot.khsmadb.sleep.KhBleSleep;
import com.coveiot.khsmadb.sleep.KhSleepDao;
import com.coveiot.khsmadb.spo2.KhBleSpO2;
import com.coveiot.khsmadb.spo2.KhSpO2Dao;
import com.coveiot.khsmadb.stress.KhBlePressure;
import com.coveiot.khsmadb.stress.KhStressDao;
import com.coveiot.khsmadb.temperature.KhBleTemperature;
import com.coveiot.khsmadb.temperature.KhTemperatureDao;
import com.coveiot.khsmadb.workout.KhBleWorkout;
import com.coveiot.khsmadb.workout.KhBleWorkoutDao;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Database(entities = {KhBleSleep.class, KhBleActivity.class, KhBleHeartRate.class, KhBleTemperature.class, KhBleBloodPressure.class, KhSmaDeviceInfo.class, KhLocation.class, KhBleWorkout.class, KhBleSpO2.class, KhBlePressure.class}, version = 3)
/* loaded from: classes8.dex */
public abstract class KhSmaAppDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f7134a = new Migration() { // from class: com.coveiot.khsmadb.KhSmaAppDatabase$Companion$MIGRATION_1_2$1
        @Override // androidx.room.migration.Migration
        public void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("CREATE TABLE IF NOT EXISTS `KhBlePressure` (`mTime` INTEGER NOT NULL, `mValue` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mAvgBpm' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMaxBpm' INTEGER NOT NULL DEFAULT 0");
        }
    };
    @NotNull
    public static final Migration b = new Migration() { // from class: com.coveiot.khsmadb.KhSmaAppDatabase$Companion$MIGRATION_2_3$1
        @Override // androidx.room.migration.Migration
        public void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMinBpm' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mUndefined' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMaxSpm' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMinSpm' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMaxPace' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'khBleWorkout' ADD COLUMN 'mMinPace' INTEGER NOT NULL DEFAULT 0");
        }
    };

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KhSmaAppDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), KhSmaAppDatabase.class, "khsmaappdb").allowMainThreadQueries().addMigrations(getMIGRATION_1_2()).addMigrations(getMIGRATION_2_3()).build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …\n                .build()");
            return (KhSmaAppDatabase) build;
        }

        @NotNull
        public final Migration getMIGRATION_1_2() {
            return KhSmaAppDatabase.f7134a;
        }

        @NotNull
        public final Migration getMIGRATION_2_3() {
            return KhSmaAppDatabase.b;
        }
    }

    @NotNull
    public abstract KhActivityDao getActivityDao();

    @NotNull
    public abstract KhBloodPressureDao getBloodPressureDao();

    @NotNull
    public abstract KhSmaDeviceInfoDao getDeviceInfoDao();

    @NotNull
    public abstract KhHeartRateDao getHeartRateDao();

    @NotNull
    public abstract KhLocationDao getLocationDao();

    @NotNull
    public abstract KhSleepDao getSleepDao();

    @NotNull
    public abstract KhSpO2Dao getSpO2Dao();

    @NotNull
    public abstract KhStressDao getStressDao();

    @NotNull
    public abstract KhTemperatureDao getTemperatureDao();

    @NotNull
    public abstract KhBleWorkoutDao getWorkoutDao();
}
