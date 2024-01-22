package com.coveiot.kheastapexdb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.kheastapexdb.activity.KHEAActivityDataDao;
import com.coveiot.kheastapexdb.activity.KHEAActivityDataDao_Impl;
import com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao;
import com.coveiot.kheastapexdb.heartrate.KHEAHeartRateDataDao_Impl;
import com.coveiot.kheastapexdb.sleep.KHEASleepDataDao;
import com.coveiot.kheastapexdb.sleep.KHEASleepDataDao_Impl;
import com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao;
import com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao_Impl;
import com.coveiot.kheastapexdb.stress.KHEAStressDataDao;
import com.coveiot.kheastapexdb.stress.KHEAStressDataDao_Impl;
import com.coveiot.kheastapexdb.walk.KHEAStepsDataDao;
import com.coveiot.kheastapexdb.walk.KHEAStepsDataDao_Impl;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class KHEastApexAppDatabase_Impl extends KHEastApexAppDatabase {

    /* renamed from: a  reason: collision with root package name */
    public volatile KHEAStepsDataDao f7053a;
    public volatile KHEASleepDataDao b;
    public volatile KHEAHeartRateDataDao c;
    public volatile KHEASpO2DataDao d;
    public volatile KHEAStressDataDao e;
    public volatile KHEAActivityDataDao f;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_steps` (`epochTimeStamp` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `steps` INTEGER NOT NULL, `calorie` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `avgHeartRate` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`epochTimeStamp`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_sleep` (`epochTimeStamp` INTEGER NOT NULL, `sleepMode` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`epochTimeStamp`, `sleepMode`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_heart_rate` (`epochTimeStamp` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hrValue` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`epochTimeStamp`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_spo2` (`epochTimeStamp` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `spo2Value` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`epochTimeStamp`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_stress` (`epochTimeStamp` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `stressValue` INTEGER NOT NULL, `level` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`epochTimeStamp`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_ea_activity` (`beginTimestamp` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `activityType` INTEGER NOT NULL, `endTimestamp` INTEGER NOT NULL, `steps` INTEGER NOT NULL, `calorie` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `trainingEffectNormal` INTEGER NOT NULL, `trainingEffectWarmUp` INTEGER NOT NULL, `trainingEffectFatConsumption` INTEGER NOT NULL, `trainingEffectAerobic` INTEGER NOT NULL, `trainingEffectAnaerobic` INTEGER NOT NULL, `trainingEffectLimit` INTEGER NOT NULL, `avgHeartRate` INTEGER NOT NULL, `avgTemperature` REAL NOT NULL, `avgSpeed` INTEGER NOT NULL, `avgPace` INTEGER NOT NULL, `avgStepFreq` INTEGER NOT NULL, `avgStride` INTEGER NOT NULL, `avgAltitude` INTEGER NOT NULL, `maxHeartRate` INTEGER NOT NULL, `minHeartRate` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`beginTimestamp`, `macAddress`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '89e24c63cd0fd705d052c95e4c83bdbc')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_steps`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_sleep`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_heart_rate`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_spo2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_stress`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_ea_activity`");
            if (KHEastApexAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHEastApexAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHEastApexAppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KHEastApexAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHEastApexAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHEastApexAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KHEastApexAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KHEastApexAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KHEastApexAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHEastApexAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHEastApexAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(9);
            hashMap.put("epochTimeStamp", new TableInfo.Column("epochTimeStamp", "INTEGER", true, 1, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, 1));
            hashMap.put("calorie", new TableInfo.Column("calorie", "INTEGER", true, 0, null, 1));
            hashMap.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
            hashMap.put("avgHeartRate", new TableInfo.Column("avgHeartRate", "INTEGER", true, 0, null, 1));
            hashMap.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("entity_ea_steps", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "entity_ea_steps");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_steps(com.coveiot.kheastapexdb.walk.EntityEAStepsData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(5);
            hashMap2.put("epochTimeStamp", new TableInfo.Column("epochTimeStamp", "INTEGER", true, 1, null, 1));
            hashMap2.put("sleepMode", new TableInfo.Column("sleepMode", "INTEGER", true, 2, null, 1));
            hashMap2.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 3, null, 1));
            hashMap2.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("entity_ea_sleep", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "entity_ea_sleep");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_sleep(com.coveiot.kheastapexdb.sleep.EntityEASleepData).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(5);
            hashMap3.put("epochTimeStamp", new TableInfo.Column("epochTimeStamp", "INTEGER", true, 1, null, 1));
            hashMap3.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap3.put("hrValue", new TableInfo.Column("hrValue", "INTEGER", true, 0, null, 1));
            hashMap3.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap3.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("entity_ea_heart_rate", hashMap3, new HashSet(0), new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "entity_ea_heart_rate");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_heart_rate(com.coveiot.kheastapexdb.heartrate.EntityEAHeartRateData).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(5);
            hashMap4.put("epochTimeStamp", new TableInfo.Column("epochTimeStamp", "INTEGER", true, 1, null, 1));
            hashMap4.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap4.put("spo2Value", new TableInfo.Column("spo2Value", "INTEGER", true, 0, null, 1));
            hashMap4.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo4 = new TableInfo("entity_ea_spo2", hashMap4, new HashSet(0), new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "entity_ea_spo2");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_spo2(com.coveiot.kheastapexdb.spo2.EntityEASpO2Data).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(6);
            hashMap5.put("epochTimeStamp", new TableInfo.Column("epochTimeStamp", "INTEGER", true, 1, null, 1));
            hashMap5.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap5.put("stressValue", new TableInfo.Column("stressValue", "INTEGER", true, 0, null, 1));
            hashMap5.put(FirebaseAnalytics.Param.LEVEL, new TableInfo.Column(FirebaseAnalytics.Param.LEVEL, "INTEGER", true, 0, null, 1));
            hashMap5.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap5.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("entity_ea_stress", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "entity_ea_stress");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_stress(com.coveiot.kheastapexdb.stress.EntityEAStressData).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(25);
            hashMap6.put("beginTimestamp", new TableInfo.Column("beginTimestamp", "INTEGER", true, 1, null, 1));
            hashMap6.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap6.put("activityType", new TableInfo.Column("activityType", "INTEGER", true, 0, null, 1));
            hashMap6.put("endTimestamp", new TableInfo.Column("endTimestamp", "INTEGER", true, 0, null, 1));
            hashMap6.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, 1));
            hashMap6.put("calorie", new TableInfo.Column("calorie", "INTEGER", true, 0, null, 1));
            hashMap6.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap6.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectNormal", new TableInfo.Column("trainingEffectNormal", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectWarmUp", new TableInfo.Column("trainingEffectWarmUp", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectFatConsumption", new TableInfo.Column("trainingEffectFatConsumption", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectAerobic", new TableInfo.Column("trainingEffectAerobic", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectAnaerobic", new TableInfo.Column("trainingEffectAnaerobic", "INTEGER", true, 0, null, 1));
            hashMap6.put("trainingEffectLimit", new TableInfo.Column("trainingEffectLimit", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgHeartRate", new TableInfo.Column("avgHeartRate", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgTemperature", new TableInfo.Column("avgTemperature", "REAL", true, 0, null, 1));
            hashMap6.put("avgSpeed", new TableInfo.Column("avgSpeed", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgPace", new TableInfo.Column("avgPace", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgStepFreq", new TableInfo.Column("avgStepFreq", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgStride", new TableInfo.Column("avgStride", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgAltitude", new TableInfo.Column("avgAltitude", "INTEGER", true, 0, null, 1));
            hashMap6.put("maxHeartRate", new TableInfo.Column("maxHeartRate", "INTEGER", true, 0, null, 1));
            hashMap6.put("minHeartRate", new TableInfo.Column("minHeartRate", "INTEGER", true, 0, null, 1));
            hashMap6.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap6.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo6 = new TableInfo("entity_ea_activity", hashMap6, new HashSet(0), new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "entity_ea_activity");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_ea_activity(com.coveiot.kheastapexdb.activity.EntityEAActivityData).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
            }
            return new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        try {
            beginTransaction();
            writableDatabase.execSQL("DELETE FROM `entity_ea_steps`");
            writableDatabase.execSQL("DELETE FROM `entity_ea_sleep`");
            writableDatabase.execSQL("DELETE FROM `entity_ea_heart_rate`");
            writableDatabase.execSQL("DELETE FROM `entity_ea_spo2`");
            writableDatabase.execSQL("DELETE FROM `entity_ea_stress`");
            writableDatabase.execSQL("DELETE FROM `entity_ea_activity`");
            setTransactionSuccessful();
        } finally {
            endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "entity_ea_steps", "entity_ea_sleep", "entity_ea_heart_rate", "entity_ea_spo2", "entity_ea_stress", "entity_ea_activity");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(1), "89e24c63cd0fd705d052c95e4c83bdbc", "ce1d1e4da59f67674e23512dabe3497a")).build());
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEAActivityDataDao getEAActivityDataDao() {
        KHEAActivityDataDao kHEAActivityDataDao;
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = new KHEAActivityDataDao_Impl(this);
            }
            kHEAActivityDataDao = this.f;
        }
        return kHEAActivityDataDao;
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEAHeartRateDataDao getEAHeartRateDao() {
        KHEAHeartRateDataDao kHEAHeartRateDataDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KHEAHeartRateDataDao_Impl(this);
            }
            kHEAHeartRateDataDao = this.c;
        }
        return kHEAHeartRateDataDao;
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEASleepDataDao getEASleepsDao() {
        KHEASleepDataDao kHEASleepDataDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new KHEASleepDataDao_Impl(this);
            }
            kHEASleepDataDao = this.b;
        }
        return kHEASleepDataDao;
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEASpO2DataDao getEASpo2Dao() {
        KHEASpO2DataDao kHEASpO2DataDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KHEASpO2DataDao_Impl(this);
            }
            kHEASpO2DataDao = this.d;
        }
        return kHEASpO2DataDao;
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEAStepsDataDao getEAStepsDao() {
        KHEAStepsDataDao kHEAStepsDataDao;
        if (this.f7053a != null) {
            return this.f7053a;
        }
        synchronized (this) {
            if (this.f7053a == null) {
                this.f7053a = new KHEAStepsDataDao_Impl(this);
            }
            kHEAStepsDataDao = this.f7053a;
        }
        return kHEAStepsDataDao;
    }

    @Override // com.coveiot.kheastapexdb.KHEastApexAppDatabase
    public KHEAStressDataDao getEAStressDao() {
        KHEAStressDataDao kHEAStressDataDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KHEAStressDataDao_Impl(this);
            }
            kHEAStressDataDao = this.e;
        }
        return kHEAStressDataDao;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(KHEAStepsDataDao.class, KHEAStepsDataDao_Impl.getRequiredConverters());
        hashMap.put(KHEASleepDataDao.class, KHEASleepDataDao_Impl.getRequiredConverters());
        hashMap.put(KHEAHeartRateDataDao.class, KHEAHeartRateDataDao_Impl.getRequiredConverters());
        hashMap.put(KHEASpO2DataDao.class, KHEASpO2DataDao_Impl.getRequiredConverters());
        hashMap.put(KHEAStressDataDao.class, KHEAStressDataDao_Impl.getRequiredConverters());
        hashMap.put(KHEAActivityDataDao.class, KHEAActivityDataDao_Impl.getRequiredConverters());
        return hashMap;
    }
}
