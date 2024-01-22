package com.coveiot.khtouchdb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.coveiot.khtouchdb.activities.EntityTGWorkoutRecord;
import com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao;
import com.coveiot.khtouchdb.converter.DateConverter;
import com.coveiot.khtouchdb.converter.TGActivityConverter;
import com.coveiot.khtouchdb.converter.TGHeartRateItemConverter;
import com.coveiot.khtouchdb.converter.TGSPO2ItemConverter;
import com.coveiot.khtouchdb.converter.TGSleepItemConverter;
import com.coveiot.khtouchdb.converter.TGStepsItemConverter;
import com.coveiot.khtouchdb.converter.TGStressItemConverter;
import com.coveiot.khtouchdb.heartrate.EntityTGHeartRateData;
import com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao;
import com.coveiot.khtouchdb.sleep.EntityTGSleepData;
import com.coveiot.khtouchdb.sleep.KHTGSleepDataDao;
import com.coveiot.khtouchdb.spo2.EntityTGSpO2Data;
import com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao;
import com.coveiot.khtouchdb.stress.EntityTGStressData;
import com.coveiot.khtouchdb.stress.KHTGStressDataDao;
import com.coveiot.khtouchdb.walk.EntityTGStepData;
import com.coveiot.khtouchdb.walk.KHTGStepsDataDao;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@TypeConverters({TGStepsItemConverter.class, TGSleepItemConverter.class, TGHeartRateItemConverter.class, TGSPO2ItemConverter.class, TGStressItemConverter.class, DateConverter.class, TGActivityConverter.class})
@Database(entities = {EntityTGStepData.class, EntityTGSleepData.class, EntityTGHeartRateData.class, EntityTGSpO2Data.class, EntityTGStressData.class, EntityTGWorkoutRecord.class}, version = 1)
/* loaded from: classes8.dex */
public abstract class KHTouchAppDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KHTouchAppDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), KHTouchAppDatabase.class, "khtouchdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …\n                .build()");
            return (KHTouchAppDatabase) build;
        }
    }

    @NotNull
    public abstract KHTGHeartRateDataDao getTGHeartRateDao();

    @NotNull
    public abstract KHTGSleepDataDao getTGSleepDao();

    @NotNull
    public abstract KHTGSpO2DataDao getTGSpo2Dao();

    @NotNull
    public abstract KHTGStepsDataDao getTGStepsDao();

    @NotNull
    public abstract KHTGStressDataDao getTGStressDao();

    @NotNull
    public abstract KHTGWorkoutRecordsDao getTGWorkoutDao();
}
