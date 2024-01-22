package com.coveiot.khsmadb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.khsmadb.activity.KhActivityDao;
import com.coveiot.khsmadb.activity.KhActivityDao_Impl;
import com.coveiot.khsmadb.bp.KhBloodPressureDao;
import com.coveiot.khsmadb.bp.KhBloodPressureDao_Impl;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao_Impl;
import com.coveiot.khsmadb.heartrate.KhHeartRateDao;
import com.coveiot.khsmadb.heartrate.KhHeartRateDao_Impl;
import com.coveiot.khsmadb.location.KhLocationDao;
import com.coveiot.khsmadb.location.KhLocationDao_Impl;
import com.coveiot.khsmadb.sleep.KhSleepDao;
import com.coveiot.khsmadb.sleep.KhSleepDao_Impl;
import com.coveiot.khsmadb.spo2.KhSpO2Dao;
import com.coveiot.khsmadb.spo2.KhSpO2Dao_Impl;
import com.coveiot.khsmadb.stress.KhStressDao;
import com.coveiot.khsmadb.stress.KhStressDao_Impl;
import com.coveiot.khsmadb.temperature.KhTemperatureDao;
import com.coveiot.khsmadb.temperature.KhTemperatureDao_Impl;
import com.coveiot.khsmadb.workout.KhBleWorkoutDao;
import com.coveiot.khsmadb.workout.KhBleWorkoutDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes8.dex */
public final class KhSmaAppDatabase_Impl extends KhSmaAppDatabase {
    public volatile KhSleepDao c;
    public volatile KhActivityDao d;
    public volatile KhHeartRateDao e;
    public volatile KhLocationDao f;
    public volatile KhBleWorkoutDao g;
    public volatile KhTemperatureDao h;
    public volatile KhBloodPressureDao i;
    public volatile KhSmaDeviceInfoDao j;
    public volatile KhSpO2Dao k;
    public volatile KhStressDao l;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleSleep` (`mTime` INTEGER NOT NULL, `mMode` INTEGER NOT NULL, `mSoft` INTEGER NOT NULL, `mStrong` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleActivity` (`mTime` INTEGER NOT NULL, `mMode` INTEGER NOT NULL, `mState` INTEGER NOT NULL, `mStep` INTEGER NOT NULL, `mCalorie` INTEGER NOT NULL, `mDistance` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `isReadyToProcess` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, `isProcessedInWorkout` INTEGER NOT NULL, `Timestamp` TEXT NOT NULL, `date` TEXT NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleHeartRate` (`mTime` INTEGER NOT NULL, `mBpm` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `isProcessedInWorkout` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleTemperature` (`mTime` INTEGER NOT NULL, `mTemperature` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleBloodPressure` (`mTime` INTEGER NOT NULL, `mSystolic` INTEGER NOT NULL, `mDiastolic` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhSmaDeviceInfo` (`macAddress` TEXT NOT NULL, `stepDataLastSyncTime` INTEGER, `hrDataLastSyncTime` INTEGER, `sleepDataLastSyncTime` INTEGER, `temperatureDataLastSyncTime` INTEGER, `bpDataLastSyncTime` INTEGER, `spO2DataLastSyncTime` INTEGER, PRIMARY KEY(`macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhLocation` (`mTime` INTEGER NOT NULL, `mActivityMode` INTEGER NOT NULL, `mAltitude` INTEGER NOT NULL, `mLongitude` REAL NOT NULL, `mLatitude` REAL NOT NULL, `macAddress` TEXT NOT NULL, `date` TEXT NOT NULL, `datetime` TEXT NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `khBleWorkout` (`mStart` INTEGER NOT NULL, `mEnd` INTEGER NOT NULL, `mDuration` INTEGER NOT NULL, `mAltitude` INTEGER NOT NULL, `mAirPressure` INTEGER NOT NULL, `mSpm` INTEGER NOT NULL, `mMode` INTEGER NOT NULL, `mStep` INTEGER NOT NULL, `mDistance` INTEGER NOT NULL, `mCalorie` INTEGER NOT NULL, `mSpeed` INTEGER NOT NULL, `mPace` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `mAvgBpm` INTEGER NOT NULL, `mMaxBpm` INTEGER NOT NULL, `mMinBpm` INTEGER NOT NULL, `mUndefined` INTEGER NOT NULL, `mMaxSpm` INTEGER NOT NULL, `mMinSpm` INTEGER NOT NULL, `mMaxPace` INTEGER NOT NULL, `mMinPace` INTEGER NOT NULL, `startDate` TEXT NOT NULL, `endDate` TEXT NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mStart`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBleSpO2` (`mTime` INTEGER NOT NULL, `mValue` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhBlePressure` (`mTime` INTEGER NOT NULL, `mValue` INTEGER NOT NULL, `mMacAddress` TEXT NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, `Timestamp` TEXT, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '24376565c170625b41b631c6095d88da')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleSleep`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleActivity`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleHeartRate`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleTemperature`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleBloodPressure`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhSmaDeviceInfo`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhLocation`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `khBleWorkout`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBleSpO2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhBlePressure`");
            if (KhSmaAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhSmaAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhSmaAppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KhSmaAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhSmaAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhSmaAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KhSmaAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KhSmaAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KhSmaAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhSmaAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhSmaAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(8);
            hashMap.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap.put("mMode", new TableInfo.Column("mMode", "INTEGER", true, 0, null, 1));
            hashMap.put("mSoft", new TableInfo.Column("mSoft", "INTEGER", true, 0, null, 1));
            hashMap.put("mStrong", new TableInfo.Column("mStrong", "INTEGER", true, 0, null, 1));
            hashMap.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo = new TableInfo("KhBleSleep", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "KhBleSleep");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleSleep(com.coveiot.khsmadb.sleep.KhBleSleep).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(12);
            hashMap2.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap2.put("mMode", new TableInfo.Column("mMode", "INTEGER", true, 0, null, 1));
            hashMap2.put("mState", new TableInfo.Column("mState", "INTEGER", true, 0, null, 1));
            hashMap2.put("mStep", new TableInfo.Column("mStep", "INTEGER", true, 0, null, 1));
            hashMap2.put("mCalorie", new TableInfo.Column("mCalorie", "INTEGER", true, 0, null, 1));
            hashMap2.put("mDistance", new TableInfo.Column("mDistance", "INTEGER", true, 0, null, 1));
            hashMap2.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap2.put("isReadyToProcess", new TableInfo.Column("isReadyToProcess", "INTEGER", true, 0, null, 1));
            hashMap2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap2.put("isProcessedInWorkout", new TableInfo.Column("isProcessedInWorkout", "INTEGER", true, 0, null, 1));
            hashMap2.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", true, 0, null, 1));
            hashMap2.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("KhBleActivity", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "KhBleActivity");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleActivity(com.coveiot.khsmadb.activity.KhBleActivity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(7);
            hashMap3.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap3.put("mBpm", new TableInfo.Column("mBpm", "INTEGER", true, 0, null, 1));
            hashMap3.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap3.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap3.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap3.put("isProcessedInWorkout", new TableInfo.Column("isProcessedInWorkout", "INTEGER", true, 0, null, 1));
            hashMap3.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("KhBleHeartRate", hashMap3, new HashSet(0), new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "KhBleHeartRate");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleHeartRate(com.coveiot.khsmadb.heartrate.KhBleHeartRate).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(6);
            hashMap4.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap4.put("mTemperature", new TableInfo.Column("mTemperature", "INTEGER", true, 0, null, 1));
            hashMap4.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap4.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap4.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo4 = new TableInfo("KhBleTemperature", hashMap4, new HashSet(0), new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "KhBleTemperature");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleTemperature(com.coveiot.khsmadb.temperature.KhBleTemperature).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(7);
            hashMap5.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap5.put("mSystolic", new TableInfo.Column("mSystolic", "INTEGER", true, 0, null, 1));
            hashMap5.put("mDiastolic", new TableInfo.Column("mDiastolic", "INTEGER", true, 0, null, 1));
            hashMap5.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap5.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap5.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap5.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("KhBleBloodPressure", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "KhBleBloodPressure");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleBloodPressure(com.coveiot.khsmadb.bp.KhBleBloodPressure).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(7);
            hashMap6.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1, null, 1));
            hashMap6.put("stepDataLastSyncTime", new TableInfo.Column("stepDataLastSyncTime", "INTEGER", false, 0, null, 1));
            hashMap6.put("hrDataLastSyncTime", new TableInfo.Column("hrDataLastSyncTime", "INTEGER", false, 0, null, 1));
            hashMap6.put("sleepDataLastSyncTime", new TableInfo.Column("sleepDataLastSyncTime", "INTEGER", false, 0, null, 1));
            hashMap6.put("temperatureDataLastSyncTime", new TableInfo.Column("temperatureDataLastSyncTime", "INTEGER", false, 0, null, 1));
            hashMap6.put("bpDataLastSyncTime", new TableInfo.Column("bpDataLastSyncTime", "INTEGER", false, 0, null, 1));
            hashMap6.put("spO2DataLastSyncTime", new TableInfo.Column("spO2DataLastSyncTime", "INTEGER", false, 0, null, 1));
            TableInfo tableInfo6 = new TableInfo("KhSmaDeviceInfo", hashMap6, new HashSet(0), new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "KhSmaDeviceInfo");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "KhSmaDeviceInfo(com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfo).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
            }
            HashMap hashMap7 = new HashMap(9);
            hashMap7.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap7.put("mActivityMode", new TableInfo.Column("mActivityMode", "INTEGER", true, 0, null, 1));
            hashMap7.put("mAltitude", new TableInfo.Column("mAltitude", "INTEGER", true, 0, null, 1));
            hashMap7.put("mLongitude", new TableInfo.Column("mLongitude", "REAL", true, 0, null, 1));
            hashMap7.put("mLatitude", new TableInfo.Column("mLatitude", "REAL", true, 0, null, 1));
            hashMap7.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap7.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, 1));
            hashMap7.put("datetime", new TableInfo.Column("datetime", "TEXT", true, 0, null, 1));
            hashMap7.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo7 = new TableInfo("KhLocation", hashMap7, new HashSet(0), new HashSet(0));
            TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "KhLocation");
            if (!tableInfo7.equals(read7)) {
                return new RoomOpenHelper.ValidationResult(false, "KhLocation(com.coveiot.khsmadb.location.KhLocation).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
            }
            HashMap hashMap8 = new HashMap(24);
            hashMap8.put("mStart", new TableInfo.Column("mStart", "INTEGER", true, 1, null, 1));
            hashMap8.put("mEnd", new TableInfo.Column("mEnd", "INTEGER", true, 0, null, 1));
            hashMap8.put("mDuration", new TableInfo.Column("mDuration", "INTEGER", true, 0, null, 1));
            hashMap8.put("mAltitude", new TableInfo.Column("mAltitude", "INTEGER", true, 0, null, 1));
            hashMap8.put("mAirPressure", new TableInfo.Column("mAirPressure", "INTEGER", true, 0, null, 1));
            hashMap8.put("mSpm", new TableInfo.Column("mSpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMode", new TableInfo.Column("mMode", "INTEGER", true, 0, null, 1));
            hashMap8.put("mStep", new TableInfo.Column("mStep", "INTEGER", true, 0, null, 1));
            hashMap8.put("mDistance", new TableInfo.Column("mDistance", "INTEGER", true, 0, null, 1));
            hashMap8.put("mCalorie", new TableInfo.Column("mCalorie", "INTEGER", true, 0, null, 1));
            hashMap8.put("mSpeed", new TableInfo.Column("mSpeed", "INTEGER", true, 0, null, 1));
            hashMap8.put("mPace", new TableInfo.Column("mPace", "INTEGER", true, 0, null, 1));
            hashMap8.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap8.put("mAvgBpm", new TableInfo.Column("mAvgBpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMaxBpm", new TableInfo.Column("mMaxBpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMinBpm", new TableInfo.Column("mMinBpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mUndefined", new TableInfo.Column("mUndefined", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMaxSpm", new TableInfo.Column("mMaxSpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMinSpm", new TableInfo.Column("mMinSpm", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMaxPace", new TableInfo.Column("mMaxPace", "INTEGER", true, 0, null, 1));
            hashMap8.put("mMinPace", new TableInfo.Column("mMinPace", "INTEGER", true, 0, null, 1));
            hashMap8.put("startDate", new TableInfo.Column("startDate", "TEXT", true, 0, null, 1));
            hashMap8.put("endDate", new TableInfo.Column("endDate", "TEXT", true, 0, null, 1));
            hashMap8.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo8 = new TableInfo("khBleWorkout", hashMap8, new HashSet(0), new HashSet(0));
            TableInfo read8 = TableInfo.read(supportSQLiteDatabase, "khBleWorkout");
            if (!tableInfo8.equals(read8)) {
                return new RoomOpenHelper.ValidationResult(false, "khBleWorkout(com.coveiot.khsmadb.workout.KhBleWorkout).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
            }
            HashMap hashMap9 = new HashMap(6);
            hashMap9.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap9.put("mValue", new TableInfo.Column("mValue", "INTEGER", true, 0, null, 1));
            hashMap9.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap9.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap9.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap9.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo9 = new TableInfo("KhBleSpO2", hashMap9, new HashSet(0), new HashSet(0));
            TableInfo read9 = TableInfo.read(supportSQLiteDatabase, "KhBleSpO2");
            if (!tableInfo9.equals(read9)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBleSpO2(com.coveiot.khsmadb.spo2.KhBleSpO2).\n Expected:\n" + tableInfo9 + "\n Found:\n" + read9);
            }
            HashMap hashMap10 = new HashMap(6);
            hashMap10.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap10.put("mValue", new TableInfo.Column("mValue", "INTEGER", true, 0, null, 1));
            hashMap10.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap10.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap10.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap10.put("Timestamp", new TableInfo.Column("Timestamp", "TEXT", false, 0, null, 1));
            TableInfo tableInfo10 = new TableInfo("KhBlePressure", hashMap10, new HashSet(0), new HashSet(0));
            TableInfo read10 = TableInfo.read(supportSQLiteDatabase, "KhBlePressure");
            if (!tableInfo10.equals(read10)) {
                return new RoomOpenHelper.ValidationResult(false, "KhBlePressure(com.coveiot.khsmadb.stress.KhBlePressure).\n Expected:\n" + tableInfo10 + "\n Found:\n" + read10);
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
            writableDatabase.execSQL("DELETE FROM `KhBleSleep`");
            writableDatabase.execSQL("DELETE FROM `KhBleActivity`");
            writableDatabase.execSQL("DELETE FROM `KhBleHeartRate`");
            writableDatabase.execSQL("DELETE FROM `KhBleTemperature`");
            writableDatabase.execSQL("DELETE FROM `KhBleBloodPressure`");
            writableDatabase.execSQL("DELETE FROM `KhSmaDeviceInfo`");
            writableDatabase.execSQL("DELETE FROM `KhLocation`");
            writableDatabase.execSQL("DELETE FROM `khBleWorkout`");
            writableDatabase.execSQL("DELETE FROM `KhBleSpO2`");
            writableDatabase.execSQL("DELETE FROM `KhBlePressure`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "KhBleSleep", "KhBleActivity", "KhBleHeartRate", "KhBleTemperature", "KhBleBloodPressure", "KhSmaDeviceInfo", "KhLocation", "khBleWorkout", "KhBleSpO2", "KhBlePressure");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(3), "24376565c170625b41b631c6095d88da", "b6db8bbfb94c059829dda045480a94a3")).build());
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhActivityDao getActivityDao() {
        KhActivityDao khActivityDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KhActivityDao_Impl(this);
            }
            khActivityDao = this.d;
        }
        return khActivityDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhBloodPressureDao getBloodPressureDao() {
        KhBloodPressureDao khBloodPressureDao;
        if (this.i != null) {
            return this.i;
        }
        synchronized (this) {
            if (this.i == null) {
                this.i = new KhBloodPressureDao_Impl(this);
            }
            khBloodPressureDao = this.i;
        }
        return khBloodPressureDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhSmaDeviceInfoDao getDeviceInfoDao() {
        KhSmaDeviceInfoDao khSmaDeviceInfoDao;
        if (this.j != null) {
            return this.j;
        }
        synchronized (this) {
            if (this.j == null) {
                this.j = new KhSmaDeviceInfoDao_Impl(this);
            }
            khSmaDeviceInfoDao = this.j;
        }
        return khSmaDeviceInfoDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhHeartRateDao getHeartRateDao() {
        KhHeartRateDao khHeartRateDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KhHeartRateDao_Impl(this);
            }
            khHeartRateDao = this.e;
        }
        return khHeartRateDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhLocationDao getLocationDao() {
        KhLocationDao khLocationDao;
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = new KhLocationDao_Impl(this);
            }
            khLocationDao = this.f;
        }
        return khLocationDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhSleepDao getSleepDao() {
        KhSleepDao khSleepDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KhSleepDao_Impl(this);
            }
            khSleepDao = this.c;
        }
        return khSleepDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhSpO2Dao getSpO2Dao() {
        KhSpO2Dao khSpO2Dao;
        if (this.k != null) {
            return this.k;
        }
        synchronized (this) {
            if (this.k == null) {
                this.k = new KhSpO2Dao_Impl(this);
            }
            khSpO2Dao = this.k;
        }
        return khSpO2Dao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhStressDao getStressDao() {
        KhStressDao khStressDao;
        if (this.l != null) {
            return this.l;
        }
        synchronized (this) {
            if (this.l == null) {
                this.l = new KhStressDao_Impl(this);
            }
            khStressDao = this.l;
        }
        return khStressDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhTemperatureDao getTemperatureDao() {
        KhTemperatureDao khTemperatureDao;
        if (this.h != null) {
            return this.h;
        }
        synchronized (this) {
            if (this.h == null) {
                this.h = new KhTemperatureDao_Impl(this);
            }
            khTemperatureDao = this.h;
        }
        return khTemperatureDao;
    }

    @Override // com.coveiot.khsmadb.KhSmaAppDatabase
    public KhBleWorkoutDao getWorkoutDao() {
        KhBleWorkoutDao khBleWorkoutDao;
        if (this.g != null) {
            return this.g;
        }
        synchronized (this) {
            if (this.g == null) {
                this.g = new KhBleWorkoutDao_Impl(this);
            }
            khBleWorkoutDao = this.g;
        }
        return khBleWorkoutDao;
    }
}
