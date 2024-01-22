package com.coveiot.android.leonardo.sp02.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao;
import com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao_Impl;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes5.dex */
public final class SPO2Database_Impl extends SPO2Database {
    public volatile SPO2Dao c;

    /* loaded from: classes5.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `spo2_records` (`timeStamp` INTEGER NOT NULL, `value` REAL NOT NULL, `date` TEXT, `timeZoneOffSet` TEXT, `isLevelIntepreTation` INTEGER NOT NULL, `isSyncedToServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4e1be8d410bf79343abbd19049f59b3c')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `spo2_records`");
            if (SPO2Database_Impl.this.mCallbacks != null) {
                int size = SPO2Database_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SPO2Database_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (SPO2Database_Impl.this.mCallbacks != null) {
                int size = SPO2Database_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SPO2Database_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            SPO2Database_Impl.this.mDatabase = supportSQLiteDatabase;
            SPO2Database_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (SPO2Database_Impl.this.mCallbacks != null) {
                int size = SPO2Database_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) SPO2Database_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(6);
            hashMap.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 1, null, 1));
            hashMap.put("value", new TableInfo.Column("value", "REAL", true, 0, null, 1));
            hashMap.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap.put("timeZoneOffSet", new TableInfo.Column("timeZoneOffSet", "TEXT", false, 0, null, 1));
            hashMap.put("isLevelIntepreTation", new TableInfo.Column("isLevelIntepreTation", "INTEGER", true, 0, null, 1));
            hashMap.put("isSyncedToServer", new TableInfo.Column("isSyncedToServer", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("spo2_records", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "spo2_records");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "spo2_records(com.coveiot.android.leonardo.sp02.database.entities.SPO2Record).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
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
            writableDatabase.execSQL("DELETE FROM `spo2_records`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "spo2_records");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(7), "4e1be8d410bf79343abbd19049f59b3c", "0cfd41c35bdf904b68ea813b38570e50")).build());
    }

    @Override // com.coveiot.android.leonardo.sp02.database.SPO2Database
    public SPO2Dao spo2Dao() {
        SPO2Dao sPO2Dao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new SPO2Dao_Impl(this);
            }
            sPO2Dao = this.c;
        }
        return sPO2Dao;
    }
}
