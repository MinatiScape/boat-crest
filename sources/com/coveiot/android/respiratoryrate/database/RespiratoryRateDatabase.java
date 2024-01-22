package com.coveiot.android.respiratoryrate.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity;
import com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter;
@TypeConverters({RespiratoryRateDataConverter.class})
@Database(entities = {DailyRespiratoryRateEntity.class, DailyRespiratoryRawPPGHistoryEntity.class, HourlyRespiratoryRawPPGDataEntity.class}, exportSchema = false, version = 2)
/* loaded from: classes6.dex */
public abstract class RespiratoryRateDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static RespiratoryRateDatabase f5669a = null;
    public static final Migration b = new a(1, 2);
    public static final String covedatabase = "respiratoryratedatabase";

    /* loaded from: classes6.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_raw_ppg_history_table`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_raw_ppg_history_table` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `syncedToServer` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_raw_ppg_history_table`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_raw_ppg_history_table` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL,`timestamp` INTEGER NOT NULL, `recordNumber` INTEGER NOT NULL, `samplingRate` INTEGER NOT NULL,`ppgType` INTEGER NOT NULL,`interval` INTEGER NOT NULL,`duration` INTEGER NOT NULL,`movementLevel` INTEGER NOT NULL,`ppgValues` TEXT, PRIMARY KEY(`timestamp`, `macAddress`),FOREIGN KEY(`date`, `macAddress`) REFERENCES `daily_raw_ppg_history_table`(`date`, `macAddress`))");
        }
    }

    public static RespiratoryRateDatabase getAppDatabase(Context context) {
        if (f5669a == null) {
            f5669a = (RespiratoryRateDatabase) Room.databaseBuilder(context.getApplicationContext(), RespiratoryRateDatabase.class, covedatabase).allowMainThreadQueries().addMigrations(b).build();
        }
        return f5669a;
    }

    public abstract RespiratoryRateDao respiratoryRateDao();

    public abstract RespiratoryRawPPGHistoryDao respiratoryRawPPGHistoryDao();
}
