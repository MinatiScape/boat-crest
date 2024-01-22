package com.coveiot.android.sleepenergyscore.energymeter.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyDataConverter;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
@TypeConverters({EnergyDataConverter.class})
@Database(entities = {EnergyScoreDbData.class}, exportSchema = false, version = 4)
/* loaded from: classes6.dex */
public abstract class EnergyScoreDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f5702a = new a(1, 2);
    public static final Migration b = new b(2, 3);
    public static final Migration c = new c(3, 4);
    public static final String covedatabase = "energyscoredatabase";
    public static EnergyScoreDatabase d;

    /* loaded from: classes6.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'energy_score_table' ADD COLUMN 'isSyncedWithServer' INTEGER NOT NUll  DEFAULT 0");
        }
    }

    /* loaded from: classes6.dex */
    public class b extends Migration {
        public b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'energy_score_table' ADD COLUMN 'lastSyncedDate' INTEGER NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes6.dex */
    public class c extends Migration {
        public c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'energy_score_table' ADD COLUMN 'feedbackList' TEXT DEFAULT NULL ");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'energy_score_table' ADD COLUMN 'questionnaireId' TEXT DEFAULT NULL ");
        }
    }

    public static EnergyScoreDatabase getAppDatabase(Context context) {
        if (d == null) {
            d = (EnergyScoreDatabase) Room.databaseBuilder(context.getApplicationContext(), EnergyScoreDatabase.class, covedatabase).allowMainThreadQueries().addMigrations(f5702a).addMigrations(b).addMigrations(c).build();
        }
        return d;
    }

    public abstract EnergyScoreDao energyScoreDao();
}
