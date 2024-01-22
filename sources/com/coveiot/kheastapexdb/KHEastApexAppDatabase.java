package com.coveiot.kheastapexdb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.coveiot.kheastapexdb.activity.EntityEAActivityData;
import com.coveiot.kheastapexdb.activity.KHEAActivityDataDao;
import com.coveiot.kheastapexdb.heartrate.EntityEAHeartRateData;
import com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao;
import com.coveiot.kheastapexdb.sleep.EntityEASleepData;
import com.coveiot.kheastapexdb.sleep.KHEASleepDataDao;
import com.coveiot.kheastapexdb.spo2.EntityEASpO2Data;
import com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao;
import com.coveiot.kheastapexdb.stress.EntityEAStressData;
import com.coveiot.kheastapexdb.stress.KHEAStressDataDao;
import com.coveiot.kheastapexdb.walk.EntityEAStepsData;
import com.coveiot.kheastapexdb.walk.KHEAStepsDataDao;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@TypeConverters({})
@Database(entities = {EntityEAStepsData.class, EntityEASleepData.class, EntityEAHeartRateData.class, EntityEASpO2Data.class, EntityEAStressData.class, EntityEAActivityData.class}, version = 1)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\nH&J\b\u0010\r\u001a\u00020\fH&¨\u0006\u0011"}, d2 = {"Lcom/coveiot/kheastapexdb/KHEastApexAppDatabase;", "Landroidx/room/RoomDatabase;", "Lcom/coveiot/kheastapexdb/walk/KHEAStepsDataDao;", "getEAStepsDao", "Lcom/coveiot/kheastapexdb/sleep/KHEASleepDataDao;", "getEASleepsDao", "Lcom/coveiot/kheastapexdb/heartrate/KHEAHeartRateDataDao;", "getEAHeartRateDao", "Lcom/coveiot/kheastapexdb/spo2/KHEASpO2DataDao;", "getEASpo2Dao", "Lcom/coveiot/kheastapexdb/stress/KHEAStressDataDao;", "getEAStressDao", "Lcom/coveiot/kheastapexdb/activity/KHEAActivityDataDao;", "getEAActivityDataDao", "<init>", "()V", "Companion", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public abstract class KHEastApexAppDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/coveiot/kheastapexdb/KHEastApexAppDatabase$Companion;", "", "Landroid/content/Context;", "context", "Lcom/coveiot/kheastapexdb/KHEastApexAppDatabase;", "getDatabase", "<init>", "()V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KHEastApexAppDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), KHEastApexAppDatabase.class, "kheastapexdb").allowMainThreadQueries().build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n                context.applicationContext,\n                KHEastApexAppDatabase::class.java, \"kheastapexdb\"\n            )\n                .allowMainThreadQueries()\n                //.fallbackToDestructiveMigration()\n                .build()");
            return (KHEastApexAppDatabase) build;
        }
    }

    @NotNull
    public abstract KHEAActivityDataDao getEAActivityDataDao();

    @NotNull
    public abstract KHEAHeartRateDataDao getEAHeartRateDao();

    @NotNull
    public abstract KHEASleepDataDao getEASleepsDao();

    @NotNull
    public abstract KHEASpO2DataDao getEASpo2Dao();

    @NotNull
    public abstract KHEAStepsDataDao getEAStepsDao();

    @NotNull
    public abstract KHEAStressDataDao getEAStressDao();
}
