package com.coveiot.khidodb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.khidodb.activities.EntityHealthActivityV3;
import com.coveiot.khidodb.activities.EntityHealthSwimV3;
import com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa;
import com.coveiot.khidodb.converts.ActivityDataConvertor;
import com.coveiot.khidodb.converts.HealthHeartRateItemConverter;
import com.coveiot.khidodb.converts.HealthNoiseItemConverter;
import com.coveiot.khidodb.converts.HealthPressureItemConverter;
import com.coveiot.khidodb.converts.HealthSleepItemConverter;
import com.coveiot.khidodb.converts.HealthSpo2ItemConverter;
import com.coveiot.khidodb.converts.HealthSportItemConverter;
import com.coveiot.khidodb.heartrate.EntityHealthHeartRateSecond;
import com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao;
import com.coveiot.khidodb.noise.EntityHealthNoise;
import com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao;
import com.coveiot.khidodb.sleep.EntityHealthSleepV3;
import com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao;
import com.coveiot.khidodb.spo2.EntityHealthSpo2;
import com.coveiot.khidodb.spo2.KHHealthSpo2DataDao;
import com.coveiot.khidodb.stress.EntityHealthPressure;
import com.coveiot.khidodb.stress.KHHealthPressureDataDao;
import com.coveiot.khidodb.walk.EntityHealthSportV3;
import com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@TypeConverters({HealthSportItemConverter.class, HealthSleepItemConverter.class, HealthHeartRateItemConverter.class, ActivityDataConvertor.class, HealthPressureItemConverter.class, HealthSpo2ItemConverter.class, HealthNoiseItemConverter.class})
@Database(entities = {EntityHealthSportV3.class, EntityHealthSleepV3.class, EntityHealthHeartRateSecond.class, EntityHealthActivityV3.class, EntityHealthPressure.class, EntityHealthSpo2.class, EntityHealthSwimV3.class, EntityHealthNoise.class}, version = 4)
/* loaded from: classes8.dex */
public abstract class KHIDOAppDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f7080a = new Migration() { // from class: com.coveiot.khidodb.KHIDOAppDatabase$Companion$MIGRATION_3_4$1
        @Override // androidx.room.migration.Migration
        public void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("CREATE TABLE IF NOT EXISTS `health_noise_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hour` INTEGER NOT NULL, `minute` INTEGER NOT NULL, `second` INTEGER NOT NULL, `avgNoise` INTEGER NOT NULL, `maxNoise` INTEGER NOT NULL, `minNoise` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `items` TEXT, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `startTime`, `macAddress`))");
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
        public final KHIDOAppDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), KHIDOAppDatabase.class, "khidodb").allowMainThreadQueries().addMigrations(KHIDOAppDatabase.f7080a).build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …\n                .build()");
            return (KHIDOAppDatabase) build;
        }
    }

    @NotNull
    public abstract KHIDOHealthActivityV3Doa getActivityV3Data();

    @NotNull
    public abstract KHIDOHealthHeartRateDataDao getHealthHeartRateDao();

    @NotNull
    public abstract KHIDOHealthNoiseDataDao getHealthNoiseData();

    @NotNull
    public abstract KHHealthPressureDataDao getHealthPressureData();

    @NotNull
    public abstract KHIDOHealthSleepV3DataDao getHealthSleepV3Dao();

    @NotNull
    public abstract KHHealthSpo2DataDao getHealthSpo2Data();

    @NotNull
    public abstract KHIDOHealthSportV3DataDao getHealthSportV3Dao();
}
