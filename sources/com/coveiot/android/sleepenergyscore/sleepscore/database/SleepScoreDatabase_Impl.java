package com.coveiot.android.sleepenergyscore.sleepscore.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao;
import com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes6.dex */
public final class SleepScoreDatabase_Impl extends SleepScoreDatabase {
    public volatile SleepScoreDao d;

    /* loaded from: classes6.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sleep_score_table` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `sleepScore` INTEGER, `totalSleep` TEXT, `algoIdentifier` TEXT, `computedDate` TEXT, `timeToDeepSleep` INTEGER, `wascoCount` INTEGER, `sleepConsistency` TEXT, `lastSyncedDate` INTEGER NOT NULL, `questionnaireId` TEXT, `feedbackList` TEXT, PRIMARY KEY(`macAddress`, `date`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '431294e85d5eda3cb106dc73a9756604')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sleep_score_table`");
            if (SleepScoreDatabase_Impl.this.mCallbacks != null) {
                int size = SleepScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SleepScoreDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (SleepScoreDatabase_Impl.this.mCallbacks != null) {
                int size = SleepScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SleepScoreDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            SleepScoreDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            SleepScoreDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (SleepScoreDatabase_Impl.this.mCallbacks != null) {
                int size = SleepScoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SleepScoreDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(12);
            hashMap.put("date", new TableInfo.Column("date", "TEXT", true, 2, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1, null, 1));
            hashMap.put("sleepScore", new TableInfo.Column("sleepScore", "INTEGER", false, 0, null, 1));
            hashMap.put("totalSleep", new TableInfo.Column("totalSleep", "TEXT", false, 0, null, 1));
            hashMap.put("algoIdentifier", new TableInfo.Column("algoIdentifier", "TEXT", false, 0, null, 1));
            hashMap.put("computedDate", new TableInfo.Column("computedDate", "TEXT", false, 0, null, 1));
            hashMap.put("timeToDeepSleep", new TableInfo.Column("timeToDeepSleep", "INTEGER", false, 0, null, 1));
            hashMap.put("wascoCount", new TableInfo.Column("wascoCount", "INTEGER", false, 0, null, 1));
            hashMap.put("sleepConsistency", new TableInfo.Column("sleepConsistency", "TEXT", false, 0, null, 1));
            hashMap.put("lastSyncedDate", new TableInfo.Column("lastSyncedDate", "INTEGER", true, 0, null, 1));
            hashMap.put("questionnaireId", new TableInfo.Column("questionnaireId", "TEXT", false, 0, null, 1));
            hashMap.put("feedbackList", new TableInfo.Column("feedbackList", "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("sleep_score_table", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "sleep_score_table");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "sleep_score_table(com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `sleep_score_table`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "sleep_score_table");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(3), "431294e85d5eda3cb106dc73a9756604", "7aba152dfd721e020cb864b0f277cf9c")).build());
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreDatabase
    public SleepScoreDao sleepScoreDao() {
        SleepScoreDao sleepScoreDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new SleepScoreDao_Impl(this);
            }
            sleepScoreDao = this.d;
        }
        return sleepScoreDao;
    }
}
