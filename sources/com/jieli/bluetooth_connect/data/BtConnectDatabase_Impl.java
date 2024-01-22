package com.jieli.bluetooth_connect.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.jieli.bluetooth_connect.data.dao.HistoryRecordDao;
import com.jieli.bluetooth_connect.data.dao.HistoryRecordDao_Impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class BtConnectDatabase_Impl extends BtConnectDatabase {
    private volatile HistoryRecordDao _historyRecordDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `HistoryRecord`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "HistoryRecord");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(3) { // from class: com.jieli.bluetooth_connect.data.BtConnectDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HistoryRecord` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `address` TEXT NOT NULL, `mapped_address` TEXT, `dev_type` INTEGER NOT NULL, `connect_type` INTEGER NOT NULL, `sdk_flag` INTEGER NOT NULL, `vid` INTEGER NOT NULL, `uid` INTEGER NOT NULL, `pid` INTEGER NOT NULL, `left_dev_lat` REAL NOT NULL, `left_dev_lon` REAL NOT NULL, `left_dev_update_time` INTEGER NOT NULL, `right_dev_lat` REAL NOT NULL, `right_dev_lon` REAL NOT NULL, `right_dev_update_time` INTEGER NOT NULL, `online_time` INTEGER NOT NULL, `update_address` TEXT)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '37c904c212639270e9bf47ab0db1554c')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HistoryRecord`");
                if (BtConnectDatabase_Impl.this.mCallbacks != null) {
                    int size = BtConnectDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BtConnectDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (BtConnectDatabase_Impl.this.mCallbacks != null) {
                    int size = BtConnectDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BtConnectDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                BtConnectDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                BtConnectDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (BtConnectDatabase_Impl.this.mCallbacks != null) {
                    int size = BtConnectDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BtConnectDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(18);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put(AppMeasurementSdk.ConditionalUserProperty.NAME, new TableInfo.Column(AppMeasurementSdk.ConditionalUserProperty.NAME, "TEXT", false, 0, null, 1));
                hashMap.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, 1));
                hashMap.put("mapped_address", new TableInfo.Column("mapped_address", "TEXT", false, 0, null, 1));
                hashMap.put("dev_type", new TableInfo.Column("dev_type", "INTEGER", true, 0, null, 1));
                hashMap.put(com.ido.ble.event.stat.one.d.F, new TableInfo.Column(com.ido.ble.event.stat.one.d.F, "INTEGER", true, 0, null, 1));
                hashMap.put("sdk_flag", new TableInfo.Column("sdk_flag", "INTEGER", true, 0, null, 1));
                hashMap.put("vid", new TableInfo.Column("vid", "INTEGER", true, 0, null, 1));
                hashMap.put("uid", new TableInfo.Column("uid", "INTEGER", true, 0, null, 1));
                hashMap.put("pid", new TableInfo.Column("pid", "INTEGER", true, 0, null, 1));
                hashMap.put("left_dev_lat", new TableInfo.Column("left_dev_lat", "REAL", true, 0, null, 1));
                hashMap.put("left_dev_lon", new TableInfo.Column("left_dev_lon", "REAL", true, 0, null, 1));
                hashMap.put("left_dev_update_time", new TableInfo.Column("left_dev_update_time", "INTEGER", true, 0, null, 1));
                hashMap.put("right_dev_lat", new TableInfo.Column("right_dev_lat", "REAL", true, 0, null, 1));
                hashMap.put("right_dev_lon", new TableInfo.Column("right_dev_lon", "REAL", true, 0, null, 1));
                hashMap.put("right_dev_update_time", new TableInfo.Column("right_dev_update_time", "INTEGER", true, 0, null, 1));
                hashMap.put(com.ido.ble.event.stat.one.d.q, new TableInfo.Column(com.ido.ble.event.stat.one.d.q, "INTEGER", true, 0, null, 1));
                hashMap.put("update_address", new TableInfo.Column("update_address", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("HistoryRecord", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "HistoryRecord");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "HistoryRecord(com.jieli.bluetooth_connect.bean.history.HistoryRecord).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "37c904c212639270e9bf47ab0db1554c", "9eb7cb727e7ddaae57f54c68f427f12d")).build());
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(HistoryRecordDao.class, HistoryRecordDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // com.jieli.bluetooth_connect.data.BtConnectDatabase
    public HistoryRecordDao historyRecordDao() {
        HistoryRecordDao historyRecordDao;
        if (this._historyRecordDao != null) {
            return this._historyRecordDao;
        }
        synchronized (this) {
            if (this._historyRecordDao == null) {
                this._historyRecordDao = new HistoryRecordDao_Impl(this);
            }
            historyRecordDao = this._historyRecordDao;
        }
        return historyRecordDao;
    }
}
