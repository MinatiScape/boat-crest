package com.coveiot.android.sleepenergyscore.sleepscore.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreFeedbackConverter;
@TypeConverters({SleepScoreFeedbackConverter.class})
@Database(entities = {SleepScoreData.class}, exportSchema = false, version = 3)
/* loaded from: classes6.dex */
public abstract class SleepScoreDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f5740a = new a(1, 2);
    public static final Migration b = new b(2, 3);
    public static SleepScoreDatabase c = null;
    public static final String covedatabase = "sleepscoredatabase";

    /* loaded from: classes6.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sleep_score_table' ADD COLUMN 'lastSyncedDate' INTEGER NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes6.dex */
    public class b extends Migration {
        public b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sleep_score_table' ADD COLUMN 'feedbackList' TEXT DEFAULT NULL ");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sleep_score_table' ADD COLUMN 'questionnaireId' TEXT DEFAULT NULL ");
        }
    }

    public static SleepScoreDatabase getAppDatabase(Context context) {
        if (c == null) {
            c = (SleepScoreDatabase) Room.databaseBuilder(context.getApplicationContext(), SleepScoreDatabase.class, covedatabase).allowMainThreadQueries().addMigrations(f5740a).addMigrations(b).build();
        }
        return c;
    }

    public abstract SleepScoreDao sleepScoreDao();
}
