package com.coveiot.android.sportsnotification.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.coveiot.android.sportsnotification.database.cricket.dao.EntityAPIScoreCardDataDao;
import com.coveiot.android.sportsnotification.database.cricket.dao.EntityBleScoreDataDao;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityAPIScoreCardData;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityBleScoreCardData;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Database(entities = {EntityAPIScoreCardData.class, EntityBleScoreCardData.class}, version = 1)
/* loaded from: classes7.dex */
public abstract class SportsDatabase extends RoomDatabase {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SportsDatabase getDatabase(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), SportsDatabase.class, "sports_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …\n                .build()");
            return (SportsDatabase) build;
        }
    }

    @NotNull
    public abstract EntityBleScoreDataDao getBleScoreDataDao();

    @NotNull
    public abstract EntityAPIScoreCardDataDao getScoreCardDao();
}
