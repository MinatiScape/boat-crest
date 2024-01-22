package com.coveiot.android.khmatrixdb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRate;
import com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepData;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDetailData;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsData;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportData;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Database(entities = {KhMatrixStepsData.class, KhMatrixSleepData.class, KhMatrixHeartRate.class, KhMatrixSpO2.class, KhMatrixSportData.class, KhMatrixSleepDetailData.class}, version = 2)
/* loaded from: classes3.dex */
public abstract class KhMatrixAppDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KhMatrixAppDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), KhMatrixAppDatabase.class, "khmatrixdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …\n                .build()");
            return (KhMatrixAppDatabase) build;
        }
    }

    @NotNull
    public abstract KhMatrixHeartRateDataDao getHRDao();

    @NotNull
    public abstract KhMatrixSleepDataDao getSleepDao();

    @NotNull
    public abstract KhMatrixSpO2DataDao getSpO2Dao();

    @NotNull
    public abstract KhMatrixSportDataDao getSportsDao();

    @NotNull
    public abstract KhMatrixStepsDataDao getStepsDao();
}
