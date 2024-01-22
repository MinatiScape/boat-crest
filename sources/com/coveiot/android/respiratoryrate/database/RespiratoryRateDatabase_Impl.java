package com.coveiot.android.respiratoryrate.database;

import android.os.Build;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes6.dex */
public final class RespiratoryRateDatabase_Impl extends RespiratoryRateDatabase {
    public volatile RespiratoryRateDao c;
    public volatile RespiratoryRawPPGHistoryDao d;

    /* loaded from: classes6.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_respiratory_rate` (`mDate` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `isSyncedWithServer` INTEGER NOT NULL, `date` TEXT, `tzOffset` TEXT, `computedDate` TEXT, `min` INTEGER, `max` INTEGER, `avg` INTEGER, `values` TEXT, `baseUnit` TEXT, `accuracies` TEXT, `source` TEXT, `baseUnits` TEXT, PRIMARY KEY(`macAddress`, `mDate`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_raw_ppg_history_table` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `syncedToServer` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_raw_ppg_history_table` (`timestamp` INTEGER NOT NULL, `date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `ppgValues` TEXT, PRIMARY KEY(`timestamp`, `macAddress`), FOREIGN KEY(`date`, `macAddress`) REFERENCES `daily_raw_ppg_history_table`(`date`, `macAddress`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f2c08af12367e5eef24c2f379cb49d05')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_respiratory_rate`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_raw_ppg_history_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_raw_ppg_history_table`");
            if (RespiratoryRateDatabase_Impl.this.mCallbacks != null) {
                int size = RespiratoryRateDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RespiratoryRateDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (RespiratoryRateDatabase_Impl.this.mCallbacks != null) {
                int size = RespiratoryRateDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RespiratoryRateDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            RespiratoryRateDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            RespiratoryRateDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (RespiratoryRateDatabase_Impl.this.mCallbacks != null) {
                int size = RespiratoryRateDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) RespiratoryRateDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(14);
            hashMap.put("mDate", new TableInfo.Column("mDate", "TEXT", true, 2, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1, null, 1));
            hashMap.put("isSyncedWithServer", new TableInfo.Column("isSyncedWithServer", "INTEGER", true, 0, null, 1));
            hashMap.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap.put("tzOffset", new TableInfo.Column("tzOffset", "TEXT", false, 0, null, 1));
            hashMap.put("computedDate", new TableInfo.Column("computedDate", "TEXT", false, 0, null, 1));
            hashMap.put("min", new TableInfo.Column("min", "INTEGER", false, 0, null, 1));
            hashMap.put(Constants.PRIORITY_MAX, new TableInfo.Column(Constants.PRIORITY_MAX, "INTEGER", false, 0, null, 1));
            hashMap.put("avg", new TableInfo.Column("avg", "INTEGER", false, 0, null, 1));
            hashMap.put("values", new TableInfo.Column("values", "TEXT", false, 0, null, 1));
            hashMap.put("baseUnit", new TableInfo.Column("baseUnit", "TEXT", false, 0, null, 1));
            hashMap.put("accuracies", new TableInfo.Column("accuracies", "TEXT", false, 0, null, 1));
            hashMap.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, 1));
            hashMap.put("baseUnits", new TableInfo.Column("baseUnits", "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("daily_respiratory_rate", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "daily_respiratory_rate");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "daily_respiratory_rate(com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(3);
            hashMap2.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap2.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap2.put("syncedToServer", new TableInfo.Column("syncedToServer", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("daily_raw_ppg_history_table", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "daily_raw_ppg_history_table");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "daily_raw_ppg_history_table(com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(10);
            hashMap3.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 1, null, 1));
            hashMap3.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, 1));
            hashMap3.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap3.put("recordNumber", new TableInfo.Column("recordNumber", "INTEGER", true, 0, null, 1));
            hashMap3.put("samplingRate", new TableInfo.Column("samplingRate", "INTEGER", true, 0, null, 1));
            hashMap3.put("ppgType", new TableInfo.Column("ppgType", "INTEGER", true, 0, null, 1));
            hashMap3.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
            hashMap3.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
            hashMap3.put("movementLevel", new TableInfo.Column("movementLevel", "INTEGER", true, 0, null, 1));
            hashMap3.put("ppgValues", new TableInfo.Column("ppgValues", "TEXT", false, 0, null, 1));
            HashSet hashSet = new HashSet(1);
            hashSet.add(new TableInfo.ForeignKey("daily_raw_ppg_history_table", "NO ACTION", "NO ACTION", Arrays.asList("date", DeviceKey.MacAddress), Arrays.asList("date", DeviceKey.MacAddress)));
            TableInfo tableInfo3 = new TableInfo("hourly_raw_ppg_history_table", hashMap3, hashSet, new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "hourly_raw_ppg_history_table");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "hourly_raw_ppg_history_table(com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } finally {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `daily_respiratory_rate`");
        writableDatabase.execSQL("DELETE FROM `hourly_raw_ppg_history_table`");
        writableDatabase.execSQL("DELETE FROM `daily_raw_ppg_history_table`");
        super.setTransactionSuccessful();
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "daily_respiratory_rate", "daily_raw_ppg_history_table", "hourly_raw_ppg_history_table");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "f2c08af12367e5eef24c2f379cb49d05", "45539f99ce70a068f710952dcd2895db")).build());
    }

    @Override // com.coveiot.android.respiratoryrate.database.RespiratoryRateDatabase
    public RespiratoryRateDao respiratoryRateDao() {
        RespiratoryRateDao respiratoryRateDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new RespiratoryRateDao_Impl(this);
            }
            respiratoryRateDao = this.c;
        }
        return respiratoryRateDao;
    }

    @Override // com.coveiot.android.respiratoryrate.database.RespiratoryRateDatabase
    public RespiratoryRawPPGHistoryDao respiratoryRawPPGHistoryDao() {
        RespiratoryRawPPGHistoryDao respiratoryRawPPGHistoryDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new RespiratoryRawPPGHistoryDao_Impl(this);
            }
            respiratoryRawPPGHistoryDao = this.d;
        }
        return respiratoryRawPPGHistoryDao;
    }
}
