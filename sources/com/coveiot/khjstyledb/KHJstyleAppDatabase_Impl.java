package com.coveiot.khjstyledb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao_Impl;
import com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao;
import com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao_Impl;
import com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao;
import com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao_Impl;
import com.coveiot.khjstyledb.walk.KHJstyleWalkDataDao;
import com.coveiot.khjstyledb.walk.KHJstyleWalkDataDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes8.dex */
public final class KHJstyleAppDatabase_Impl extends KHJstyleAppDatabase {
    public volatile KHJstyleHeartRateDao c;
    public volatile KHJstyleWalkDataDao d;
    public volatile KHJstyleDeviceInfoDao e;
    public volatile KHJstyleGPSDataDao f;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `history_session_hr` (`serialNo` TEXT NOT NULL, `timeStamp` INTEGER NOT NULL, `heartRate` INTEGER NOT NULL, PRIMARY KEY(`serialNo`, `timeStamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourlywalkdata` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `interval_value` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, `calories` INTEGER NOT NULL, `distance` INTEGER NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info` (`macAddress` TEXT NOT NULL, `walk_last_sync_timestamp` INTEGER NOT NULL, PRIMARY KEY(`macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `history_session_gps` (`serialNo` TEXT NOT NULL, `timeStamp` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`serialNo`, `timeStamp`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b8ea77dd10141a22e0f5bd28250642c2\")");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `history_session_hr`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourlywalkdata`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `device_info`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `history_session_gps`");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KHJstyleAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHJstyleAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHJstyleAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KHJstyleAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KHJstyleAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KHJstyleAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHJstyleAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHJstyleAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("serialNo", new TableInfo.Column("serialNo", "TEXT", true, 1));
            hashMap.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 2));
            hashMap.put(DeviceKey.HeartRate, new TableInfo.Column(DeviceKey.HeartRate, "INTEGER", true, 0));
            TableInfo tableInfo = new TableInfo("history_session_hr", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "history_session_hr");
            if (tableInfo.equals(read)) {
                HashMap hashMap2 = new HashMap(9);
                hashMap2.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                hashMap2.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                hashMap2.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                hashMap2.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                hashMap2.put("interval_value", new TableInfo.Column("interval_value", "INTEGER", true, 0));
                hashMap2.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                hashMap2.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                hashMap2.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0));
                hashMap2.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0));
                TableInfo tableInfo2 = new TableInfo("hourlywalkdata", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "hourlywalkdata");
                if (tableInfo2.equals(read2)) {
                    HashMap hashMap3 = new HashMap(2);
                    hashMap3.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1));
                    hashMap3.put("walk_last_sync_timestamp", new TableInfo.Column("walk_last_sync_timestamp", "INTEGER", true, 0));
                    TableInfo tableInfo3 = new TableInfo("device_info", hashMap3, new HashSet(0), new HashSet(0));
                    TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "device_info");
                    if (tableInfo3.equals(read3)) {
                        HashMap hashMap4 = new HashMap(4);
                        hashMap4.put("serialNo", new TableInfo.Column("serialNo", "TEXT", true, 1));
                        hashMap4.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 2));
                        hashMap4.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
                        hashMap4.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
                        TableInfo tableInfo4 = new TableInfo("history_session_gps", hashMap4, new HashSet(0), new HashSet(0));
                        TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "history_session_gps");
                        if (tableInfo4.equals(read4)) {
                            return;
                        }
                        throw new IllegalStateException("Migration didn't properly handle history_session_gps(com.coveiot.khjstyledb.gps.KHJstyleSessionGPSData).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                    }
                    throw new IllegalStateException("Migration didn't properly handle device_info(com.coveiot.khjstyledb.deviceinfo.KHJstyleEntityDeviceInfo).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                throw new IllegalStateException("Migration didn't properly handle hourlywalkdata(com.coveiot.khjstyledb.walk.KHJstyleHourlyWalkData).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            throw new IllegalStateException("Migration didn't properly handle history_session_hr(com.coveiot.khjstyledb.heartrate.KHJstyleEntitySessionHeartRateData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `history_session_hr`");
            writableDatabase.execSQL("DELETE FROM `hourlywalkdata`");
            writableDatabase.execSQL("DELETE FROM `device_info`");
            writableDatabase.execSQL("DELETE FROM `history_session_gps`");
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
        return new InvalidationTracker(this, "history_session_hr", "hourlywalkdata", "device_info", "history_session_gps");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "b8ea77dd10141a22e0f5bd28250642c2", "3e8b4951e533595982f00107644296ad")).build());
    }

    @Override // com.coveiot.khjstyledb.KHJstyleAppDatabase
    public KHJstyleDeviceInfoDao deviceInfoDao() {
        KHJstyleDeviceInfoDao kHJstyleDeviceInfoDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KHJstyleDeviceInfoDao_Impl(this);
            }
            kHJstyleDeviceInfoDao = this.e;
        }
        return kHJstyleDeviceInfoDao;
    }

    @Override // com.coveiot.khjstyledb.KHJstyleAppDatabase
    public KHJstyleGPSDataDao gpsDataDao() {
        KHJstyleGPSDataDao kHJstyleGPSDataDao;
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = new KHJstyleGPSDataDao_Impl(this);
            }
            kHJstyleGPSDataDao = this.f;
        }
        return kHJstyleGPSDataDao;
    }

    @Override // com.coveiot.khjstyledb.KHJstyleAppDatabase
    public KHJstyleHeartRateDao heartRateDao() {
        KHJstyleHeartRateDao kHJstyleHeartRateDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KHJstyleHeartRateDao_Impl(this);
            }
            kHJstyleHeartRateDao = this.c;
        }
        return kHJstyleHeartRateDao;
    }

    @Override // com.coveiot.khjstyledb.KHJstyleAppDatabase
    public KHJstyleWalkDataDao walkDataDao() {
        KHJstyleWalkDataDao kHJstyleWalkDataDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KHJstyleWalkDataDao_Impl(this);
            }
            kHJstyleWalkDataDao = this.d;
        }
        return kHJstyleWalkDataDao;
    }
}
