package com.coveiot.android.leonardo.sp02.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
@Database(entities = {SPO2Record.class}, exportSchema = false, version = 7)
/* loaded from: classes5.dex */
public abstract class SPO2Database extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f5407a = new a(6, 7);
    public static SPO2Database b = null;
    public static final String covedatabase = "spo2db";

    /* loaded from: classes5.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `spo2_records`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `spo2_records` (`timeStamp` INTEGER NOT NULL, `value` REAL NOT NULL, `date` TEXT, `timeZoneOffSet` TEXT, `isLevelIntepreTation` INTEGER NOT NULL, `isSyncedToServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`))");
        }
    }

    public static SPO2Database getAppDatabase(Context context) {
        if (b == null) {
            b = (SPO2Database) Room.databaseBuilder(context.getApplicationContext(), SPO2Database.class, covedatabase).allowMainThreadQueries().fallbackToDestructiveMigration().addMigrations(f5407a).build();
        }
        return b;
    }

    public abstract SPO2Dao spo2Dao();
}
