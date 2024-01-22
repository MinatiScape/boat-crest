package com.coveiot.android.khmatrixdb;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao;
import com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRateDataDao_Impl;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDataDao_Impl;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2DataDao_Impl;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsDataDao_Impl;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportDataDao_Impl;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public final class KhMatrixAppDatabase_Impl extends KhMatrixAppDatabase {

    /* renamed from: a  reason: collision with root package name */
    public volatile KhMatrixSleepDataDao f4615a;
    public volatile KhMatrixStepsDataDao b;
    public volatile KhMatrixHeartRateDataDao c;
    public volatile KhMatrixSpO2DataDao d;
    public volatile KhMatrixSportDataDao e;

    /* loaded from: classes3.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixStepsData` (`mMacAddress` TEXT NOT NULL, `mTime` INTEGER NOT NULL, `steps` INTEGER NOT NULL, `distance` REAL NOT NULL, `calories` REAL NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixSleepData` (`mMacAddress` TEXT NOT NULL, `mTime` INTEGER NOT NULL, `deepSleep` INTEGER NOT NULL, `lightSleep` INTEGER NOT NULL, `awake` INTEGER NOT NULL, `convertedDate` TEXT, `sleepDetail` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixHeartRate` (`mMacAddress` TEXT NOT NULL, `mTime` INTEGER NOT NULL, `mBpm` INTEGER NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixSpO2` (`mMacAddress` TEXT NOT NULL, `mTime` INTEGER NOT NULL, `mSpO2` INTEGER NOT NULL, `convertedDate` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixSportData` (`sportId` TEXT NOT NULL, `mMacAddress` TEXT NOT NULL, `mTime` INTEGER NOT NULL, `sportType` INTEGER NOT NULL, `step` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `distance` REAL NOT NULL, `calories` REAL NOT NULL, `convertedDate` TEXT, `sportItemDetails` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KhMatrixSleepDetailData` (`mMacAddress` TEXT NOT NULL, `mStartTime` INTEGER NOT NULL, `mEndTime` INTEGER NOT NULL, `status` INTEGER NOT NULL, `convertedDate` TEXT, `convertedStartTime` TEXT, `convertedEndTime` TEXT, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`mStartTime`, `mEndTime`, `mMacAddress`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '193e4898335f3a25bc09e10843af586f')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixStepsData`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixSleepData`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixHeartRate`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixSpO2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixSportData`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KhMatrixSleepDetailData`");
            if (KhMatrixAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhMatrixAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhMatrixAppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KhMatrixAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhMatrixAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhMatrixAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KhMatrixAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KhMatrixAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KhMatrixAppDatabase_Impl.this.mCallbacks != null) {
                int size = KhMatrixAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KhMatrixAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(7);
            hashMap.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, 1));
            hashMap.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, 1));
            hashMap.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("KhMatrixStepsData", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "KhMatrixStepsData");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixStepsData(com.coveiot.android.khmatrixdb.steps.KhMatrixStepsData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(8);
            hashMap2.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap2.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap2.put("deepSleep", new TableInfo.Column("deepSleep", "INTEGER", true, 0, null, 1));
            hashMap2.put("lightSleep", new TableInfo.Column("lightSleep", "INTEGER", true, 0, null, 1));
            hashMap2.put("awake", new TableInfo.Column("awake", "INTEGER", true, 0, null, 1));
            hashMap2.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap2.put("sleepDetail", new TableInfo.Column("sleepDetail", "TEXT", false, 0, null, 1));
            hashMap2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("KhMatrixSleepData", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "KhMatrixSleepData");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixSleepData(com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepData).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(5);
            hashMap3.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap3.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap3.put("mBpm", new TableInfo.Column("mBpm", "INTEGER", true, 0, null, 1));
            hashMap3.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap3.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("KhMatrixHeartRate", hashMap3, new HashSet(0), new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "KhMatrixHeartRate");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixHeartRate(com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRate).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(5);
            hashMap4.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap4.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap4.put("mSpO2", new TableInfo.Column("mSpO2", "INTEGER", true, 0, null, 1));
            hashMap4.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo4 = new TableInfo("KhMatrixSpO2", hashMap4, new HashSet(0), new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "KhMatrixSpO2");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixSpO2(com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(11);
            hashMap5.put("sportId", new TableInfo.Column("sportId", "TEXT", true, 0, null, 1));
            hashMap5.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 2, null, 1));
            hashMap5.put("mTime", new TableInfo.Column("mTime", "INTEGER", true, 1, null, 1));
            hashMap5.put(DeviceKey.sportType, new TableInfo.Column(DeviceKey.sportType, "INTEGER", true, 0, null, 1));
            hashMap5.put(DeviceKey.Step, new TableInfo.Column(DeviceKey.Step, "INTEGER", true, 0, null, 1));
            hashMap5.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
            hashMap5.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, 1));
            hashMap5.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, 1));
            hashMap5.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap5.put("sportItemDetails", new TableInfo.Column("sportItemDetails", "TEXT", false, 0, null, 1));
            hashMap5.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("KhMatrixSportData", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "KhMatrixSportData");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixSportData(com.coveiot.android.khmatrixdb.workout.KhMatrixSportData).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(8);
            hashMap6.put("mMacAddress", new TableInfo.Column("mMacAddress", "TEXT", true, 3, null, 1));
            hashMap6.put("mStartTime", new TableInfo.Column("mStartTime", "INTEGER", true, 1, null, 1));
            hashMap6.put("mEndTime", new TableInfo.Column("mEndTime", "INTEGER", true, 2, null, 1));
            hashMap6.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0, null, 1));
            hashMap6.put("convertedDate", new TableInfo.Column("convertedDate", "TEXT", false, 0, null, 1));
            hashMap6.put("convertedStartTime", new TableInfo.Column("convertedStartTime", "TEXT", false, 0, null, 1));
            hashMap6.put("convertedEndTime", new TableInfo.Column("convertedEndTime", "TEXT", false, 0, null, 1));
            hashMap6.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo6 = new TableInfo("KhMatrixSleepDetailData", hashMap6, new HashSet(0), new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "KhMatrixSleepDetailData");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "KhMatrixSleepDetailData(com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDetailData).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
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
            writableDatabase.execSQL("DELETE FROM `KhMatrixStepsData`");
            writableDatabase.execSQL("DELETE FROM `KhMatrixSleepData`");
            writableDatabase.execSQL("DELETE FROM `KhMatrixHeartRate`");
            writableDatabase.execSQL("DELETE FROM `KhMatrixSpO2`");
            writableDatabase.execSQL("DELETE FROM `KhMatrixSportData`");
            writableDatabase.execSQL("DELETE FROM `KhMatrixSleepDetailData`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "KhMatrixStepsData", "KhMatrixSleepData", "KhMatrixHeartRate", "KhMatrixSpO2", "KhMatrixSportData", "KhMatrixSleepDetailData");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "193e4898335f3a25bc09e10843af586f", "a4d09ad33d9d445503f414e484a9a8bd")).build());
    }

    @Override // com.coveiot.android.khmatrixdb.KhMatrixAppDatabase
    public KhMatrixHeartRateDataDao getHRDao() {
        KhMatrixHeartRateDataDao khMatrixHeartRateDataDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KhMatrixHeartRateDataDao_Impl(this);
            }
            khMatrixHeartRateDataDao = this.c;
        }
        return khMatrixHeartRateDataDao;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(KhMatrixSleepDataDao.class, KhMatrixSleepDataDao_Impl.getRequiredConverters());
        hashMap.put(KhMatrixStepsDataDao.class, KhMatrixStepsDataDao_Impl.getRequiredConverters());
        hashMap.put(KhMatrixHeartRateDataDao.class, KhMatrixHeartRateDataDao_Impl.getRequiredConverters());
        hashMap.put(KhMatrixSpO2DataDao.class, KhMatrixSpO2DataDao_Impl.getRequiredConverters());
        hashMap.put(KhMatrixSportDataDao.class, KhMatrixSportDataDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // com.coveiot.android.khmatrixdb.KhMatrixAppDatabase
    public KhMatrixSleepDataDao getSleepDao() {
        KhMatrixSleepDataDao khMatrixSleepDataDao;
        if (this.f4615a != null) {
            return this.f4615a;
        }
        synchronized (this) {
            if (this.f4615a == null) {
                this.f4615a = new KhMatrixSleepDataDao_Impl(this);
            }
            khMatrixSleepDataDao = this.f4615a;
        }
        return khMatrixSleepDataDao;
    }

    @Override // com.coveiot.android.khmatrixdb.KhMatrixAppDatabase
    public KhMatrixSpO2DataDao getSpO2Dao() {
        KhMatrixSpO2DataDao khMatrixSpO2DataDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KhMatrixSpO2DataDao_Impl(this);
            }
            khMatrixSpO2DataDao = this.d;
        }
        return khMatrixSpO2DataDao;
    }

    @Override // com.coveiot.android.khmatrixdb.KhMatrixAppDatabase
    public KhMatrixSportDataDao getSportsDao() {
        KhMatrixSportDataDao khMatrixSportDataDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KhMatrixSportDataDao_Impl(this);
            }
            khMatrixSportDataDao = this.e;
        }
        return khMatrixSportDataDao;
    }

    @Override // com.coveiot.android.khmatrixdb.KhMatrixAppDatabase
    public KhMatrixStepsDataDao getStepsDao() {
        KhMatrixStepsDataDao khMatrixStepsDataDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new KhMatrixStepsDataDao_Impl(this);
            }
            khMatrixStepsDataDao = this.b;
        }
        return khMatrixStepsDataDao;
    }
}
