package com.coveiot.khtouchdb;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao;
import com.coveiot.khtouchdb.activities.KHTGWorkoutRecordsDao_Impl;
import com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao;
import com.coveiot.khtouchdb.heartrate.KHTGHeartRateDataDao_Impl;
import com.coveiot.khtouchdb.sleep.KHTGSleepDataDao;
import com.coveiot.khtouchdb.sleep.KHTGSleepDataDao_Impl;
import com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao;
import com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao_Impl;
import com.coveiot.khtouchdb.stress.KHTGStressDataDao;
import com.coveiot.khtouchdb.stress.KHTGStressDataDao_Impl;
import com.coveiot.khtouchdb.walk.KHTGStepsDataDao;
import com.coveiot.khtouchdb.walk.KHTGStepsDataDao_Impl;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class KHTouchAppDatabase_Impl extends KHTouchAppDatabase {

    /* renamed from: a  reason: collision with root package name */
    public volatile KHTGStepsDataDao f7167a;
    public volatile KHTGSleepDataDao b;
    public volatile KHTGHeartRateDataDao c;
    public volatile KHTGSpO2DataDao d;
    public volatile KHTGStressDataDao e;
    public volatile KHTGWorkoutRecordsDao f;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_steps` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `minuteOffset` INTEGER NOT NULL, `perMinute` INTEGER NOT NULL, `itemCount` INTEGER NOT NULL, `packetCount` INTEGER NOT NULL, `totalSteps` INTEGER NOT NULL, `totalCal` INTEGER NOT NULL, `totalDistance` INTEGER NOT NULL, `totalActiveTime` INTEGER NOT NULL, `standCount` INTEGER NOT NULL, `items` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_sleep` (`date` TEXT NOT NULL, `endHour` INTEGER NOT NULL, `endMinute` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `totalMinute` INTEGER NOT NULL, `sleepMinute` INTEGER NOT NULL, `itemCount` INTEGER NOT NULL, `packetCount` INTEGER NOT NULL, `lightCount` INTEGER NOT NULL, `deepCount` INTEGER NOT NULL, `wakeCount` INTEGER NOT NULL, `eyeMoveCount` INTEGER NOT NULL, `lightMinute` INTEGER NOT NULL, `deepMinute` INTEGER NOT NULL, `eyeMoveMinute` INTEGER NOT NULL, `sleepScore` INTEGER NOT NULL, `items` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`, `endHour`, `endMinute`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_heart_rate` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `minuteOffset` INTEGER NOT NULL, `silentHr` INTEGER NOT NULL, `itemCount` INTEGER NOT NULL, `packetCount` INTEGER NOT NULL, `burnFatThreshold` INTEGER NOT NULL, `burnFatMinutes` INTEGER NOT NULL, `aerobicThreshold` INTEGER NOT NULL, `aerobicMinutes` INTEGER NOT NULL, `limitThreshold` INTEGER NOT NULL, `limitMinutes` INTEGER NOT NULL, `warmUpThreshold` INTEGER NOT NULL, `warmUpMinutes` INTEGER NOT NULL, `anaerobicThreshold` INTEGER NOT NULL, `anaerobicMinutes` INTEGER NOT NULL, `items` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_spo2` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `haveMoreData` INTEGER NOT NULL, `items` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_stress` (`date` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `haveMoreData` INTEGER NOT NULL, `offset` INTEGER NOT NULL, `items` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `startTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_tg_workout_record` (`date` TEXT NOT NULL, `macAddress` TEXT NOT NULL, `duration` INTEGER NOT NULL, `type` INTEGER NOT NULL, `step` INTEGER NOT NULL, `calories` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `avgHr` INTEGER NOT NULL, `maxHr` INTEGER NOT NULL, `minHr` INTEGER NOT NULL, `relax` INTEGER NOT NULL, `warmUp` INTEGER NOT NULL, `fatBurning` INTEGER NOT NULL, `aerobicExercise` INTEGER NOT NULL, `anaerobicExercise` INTEGER NOT NULL, `extremeExercise` INTEGER NOT NULL, `avgStrideFrequency` INTEGER NOT NULL, `avgStrideLength` INTEGER NOT NULL, `avgSpeed` INTEGER NOT NULL, `maxSpeed` INTEGER NOT NULL, `minSpeed` INTEGER NOT NULL, `avgPaceSecs` INTEGER NOT NULL, `maxPace` INTEGER NOT NULL, `minPace` INTEGER NOT NULL, `paddleNum` INTEGER NOT NULL, `paddleFrq` INTEGER NOT NULL, `boxingNum` INTEGER NOT NULL, `avgSkipFrq` INTEGER NOT NULL, `skipNum` INTEGER NOT NULL, `dumbbellNum` INTEGER NOT NULL, `eventItems` TEXT, `heartRateItems` TEXT, `paceItems` TEXT, `rowingItems` TEXT, `gpsData` TEXT, `swimData` TEXT, `footballFieldGpsData` TEXT, `footballAvgPace` TEXT, `realTimeData` TEXT, `keepTrack` TEXT, `timeStamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`date`, `macAddress`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ec546424a85d4fe361d99f8aea6b6eff')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_steps`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_sleep`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_heart_rate`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_spo2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_stress`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_tg_workout_record`");
            if (KHTouchAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHTouchAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHTouchAppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KHTouchAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHTouchAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHTouchAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KHTouchAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KHTouchAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KHTouchAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHTouchAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHTouchAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            hashMap.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap.put("minuteOffset", new TableInfo.Column("minuteOffset", "INTEGER", true, 0, null, 1));
            hashMap.put("perMinute", new TableInfo.Column("perMinute", "INTEGER", true, 0, null, 1));
            hashMap.put("itemCount", new TableInfo.Column("itemCount", "INTEGER", true, 0, null, 1));
            hashMap.put("packetCount", new TableInfo.Column("packetCount", "INTEGER", true, 0, null, 1));
            hashMap.put("totalSteps", new TableInfo.Column("totalSteps", "INTEGER", true, 0, null, 1));
            hashMap.put("totalCal", new TableInfo.Column("totalCal", "INTEGER", true, 0, null, 1));
            hashMap.put("totalDistance", new TableInfo.Column("totalDistance", "INTEGER", true, 0, null, 1));
            hashMap.put("totalActiveTime", new TableInfo.Column("totalActiveTime", "INTEGER", true, 0, null, 1));
            hashMap.put("standCount", new TableInfo.Column("standCount", "INTEGER", true, 0, null, 1));
            hashMap.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("entity_tg_steps", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "entity_tg_steps");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_steps(com.coveiot.khtouchdb.walk.EntityTGStepData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(19);
            hashMap2.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap2.put("endHour", new TableInfo.Column("endHour", "INTEGER", true, 3, null, 1));
            hashMap2.put("endMinute", new TableInfo.Column("endMinute", "INTEGER", true, 4, null, 1));
            hashMap2.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap2.put("totalMinute", new TableInfo.Column("totalMinute", "INTEGER", true, 0, null, 1));
            hashMap2.put("sleepMinute", new TableInfo.Column("sleepMinute", "INTEGER", true, 0, null, 1));
            hashMap2.put("itemCount", new TableInfo.Column("itemCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("packetCount", new TableInfo.Column("packetCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("lightCount", new TableInfo.Column("lightCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("deepCount", new TableInfo.Column("deepCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("wakeCount", new TableInfo.Column("wakeCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("eyeMoveCount", new TableInfo.Column("eyeMoveCount", "INTEGER", true, 0, null, 1));
            hashMap2.put("lightMinute", new TableInfo.Column("lightMinute", "INTEGER", true, 0, null, 1));
            hashMap2.put("deepMinute", new TableInfo.Column("deepMinute", "INTEGER", true, 0, null, 1));
            hashMap2.put("eyeMoveMinute", new TableInfo.Column("eyeMoveMinute", "INTEGER", true, 0, null, 1));
            hashMap2.put("sleepScore", new TableInfo.Column("sleepScore", "INTEGER", true, 0, null, 1));
            hashMap2.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap2.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("entity_tg_sleep", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "entity_tg_sleep");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_sleep(com.coveiot.khtouchdb.sleep.EntityTGSleepData).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(19);
            hashMap3.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap3.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap3.put("minuteOffset", new TableInfo.Column("minuteOffset", "INTEGER", true, 0, null, 1));
            hashMap3.put("silentHr", new TableInfo.Column("silentHr", "INTEGER", true, 0, null, 1));
            hashMap3.put("itemCount", new TableInfo.Column("itemCount", "INTEGER", true, 0, null, 1));
            hashMap3.put("packetCount", new TableInfo.Column("packetCount", "INTEGER", true, 0, null, 1));
            hashMap3.put("burnFatThreshold", new TableInfo.Column("burnFatThreshold", "INTEGER", true, 0, null, 1));
            hashMap3.put("burnFatMinutes", new TableInfo.Column("burnFatMinutes", "INTEGER", true, 0, null, 1));
            hashMap3.put("aerobicThreshold", new TableInfo.Column("aerobicThreshold", "INTEGER", true, 0, null, 1));
            hashMap3.put("aerobicMinutes", new TableInfo.Column("aerobicMinutes", "INTEGER", true, 0, null, 1));
            hashMap3.put("limitThreshold", new TableInfo.Column("limitThreshold", "INTEGER", true, 0, null, 1));
            hashMap3.put("limitMinutes", new TableInfo.Column("limitMinutes", "INTEGER", true, 0, null, 1));
            hashMap3.put("warmUpThreshold", new TableInfo.Column("warmUpThreshold", "INTEGER", true, 0, null, 1));
            hashMap3.put("warmUpMinutes", new TableInfo.Column("warmUpMinutes", "INTEGER", true, 0, null, 1));
            hashMap3.put("anaerobicThreshold", new TableInfo.Column("anaerobicThreshold", "INTEGER", true, 0, null, 1));
            hashMap3.put("anaerobicMinutes", new TableInfo.Column("anaerobicMinutes", "INTEGER", true, 0, null, 1));
            hashMap3.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap3.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap3.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("entity_tg_heart_rate", hashMap3, new HashSet(0), new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "entity_tg_heart_rate");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_heart_rate(com.coveiot.khtouchdb.heartrate.EntityTGHeartRateData).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(6);
            hashMap4.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap4.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap4.put("haveMoreData", new TableInfo.Column("haveMoreData", "INTEGER", true, 0, null, 1));
            hashMap4.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap4.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo4 = new TableInfo("entity_tg_spo2", hashMap4, new HashSet(0), new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "entity_tg_spo2");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_spo2(com.coveiot.khtouchdb.spo2.EntityTGSpO2Data).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(8);
            hashMap5.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap5.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 2, null, 1));
            hashMap5.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 3, null, 1));
            hashMap5.put("haveMoreData", new TableInfo.Column("haveMoreData", "INTEGER", true, 0, null, 1));
            hashMap5.put(TypedValues.CycleType.S_WAVE_OFFSET, new TableInfo.Column(TypedValues.CycleType.S_WAVE_OFFSET, "INTEGER", true, 0, null, 1));
            hashMap5.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap5.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap5.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("entity_tg_stress", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "entity_tg_stress");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_stress(com.coveiot.khtouchdb.stress.EntityTGStressData).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(42);
            hashMap6.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, 1));
            hashMap6.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 2, null, 1));
            hashMap6.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
            hashMap6.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
            hashMap6.put(DeviceKey.Step, new TableInfo.Column(DeviceKey.Step, "INTEGER", true, 0, null, 1));
            hashMap6.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, 1));
            hashMap6.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgHr", new TableInfo.Column("avgHr", "INTEGER", true, 0, null, 1));
            hashMap6.put("maxHr", new TableInfo.Column("maxHr", "INTEGER", true, 0, null, 1));
            hashMap6.put("minHr", new TableInfo.Column("minHr", "INTEGER", true, 0, null, 1));
            hashMap6.put("relax", new TableInfo.Column("relax", "INTEGER", true, 0, null, 1));
            hashMap6.put("warmUp", new TableInfo.Column("warmUp", "INTEGER", true, 0, null, 1));
            hashMap6.put("fatBurning", new TableInfo.Column("fatBurning", "INTEGER", true, 0, null, 1));
            hashMap6.put("aerobicExercise", new TableInfo.Column("aerobicExercise", "INTEGER", true, 0, null, 1));
            hashMap6.put("anaerobicExercise", new TableInfo.Column("anaerobicExercise", "INTEGER", true, 0, null, 1));
            hashMap6.put("extremeExercise", new TableInfo.Column("extremeExercise", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgStrideFrequency", new TableInfo.Column("avgStrideFrequency", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgStrideLength", new TableInfo.Column("avgStrideLength", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgSpeed", new TableInfo.Column("avgSpeed", "INTEGER", true, 0, null, 1));
            hashMap6.put("maxSpeed", new TableInfo.Column("maxSpeed", "INTEGER", true, 0, null, 1));
            hashMap6.put("minSpeed", new TableInfo.Column("minSpeed", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgPaceSecs", new TableInfo.Column("avgPaceSecs", "INTEGER", true, 0, null, 1));
            hashMap6.put("maxPace", new TableInfo.Column("maxPace", "INTEGER", true, 0, null, 1));
            hashMap6.put("minPace", new TableInfo.Column("minPace", "INTEGER", true, 0, null, 1));
            hashMap6.put("paddleNum", new TableInfo.Column("paddleNum", "INTEGER", true, 0, null, 1));
            hashMap6.put("paddleFrq", new TableInfo.Column("paddleFrq", "INTEGER", true, 0, null, 1));
            hashMap6.put("boxingNum", new TableInfo.Column("boxingNum", "INTEGER", true, 0, null, 1));
            hashMap6.put("avgSkipFrq", new TableInfo.Column("avgSkipFrq", "INTEGER", true, 0, null, 1));
            hashMap6.put("skipNum", new TableInfo.Column("skipNum", "INTEGER", true, 0, null, 1));
            hashMap6.put("dumbbellNum", new TableInfo.Column("dumbbellNum", "INTEGER", true, 0, null, 1));
            hashMap6.put("eventItems", new TableInfo.Column("eventItems", "TEXT", false, 0, null, 1));
            hashMap6.put("heartRateItems", new TableInfo.Column("heartRateItems", "TEXT", false, 0, null, 1));
            hashMap6.put("paceItems", new TableInfo.Column("paceItems", "TEXT", false, 0, null, 1));
            hashMap6.put("rowingItems", new TableInfo.Column("rowingItems", "TEXT", false, 0, null, 1));
            hashMap6.put("gpsData", new TableInfo.Column("gpsData", "TEXT", false, 0, null, 1));
            hashMap6.put("swimData", new TableInfo.Column("swimData", "TEXT", false, 0, null, 1));
            hashMap6.put("footballFieldGpsData", new TableInfo.Column("footballFieldGpsData", "TEXT", false, 0, null, 1));
            hashMap6.put("footballAvgPace", new TableInfo.Column("footballAvgPace", "TEXT", false, 0, null, 1));
            hashMap6.put("realTimeData", new TableInfo.Column("realTimeData", "TEXT", false, 0, null, 1));
            hashMap6.put("keepTrack", new TableInfo.Column("keepTrack", "TEXT", false, 0, null, 1));
            hashMap6.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 0, null, 1));
            hashMap6.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo6 = new TableInfo("entity_tg_workout_record", hashMap6, new HashSet(0), new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "entity_tg_workout_record");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_tg_workout_record(com.coveiot.khtouchdb.activities.EntityTGWorkoutRecord).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
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
            writableDatabase.execSQL("DELETE FROM `entity_tg_steps`");
            writableDatabase.execSQL("DELETE FROM `entity_tg_sleep`");
            writableDatabase.execSQL("DELETE FROM `entity_tg_heart_rate`");
            writableDatabase.execSQL("DELETE FROM `entity_tg_spo2`");
            writableDatabase.execSQL("DELETE FROM `entity_tg_stress`");
            writableDatabase.execSQL("DELETE FROM `entity_tg_workout_record`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "entity_tg_steps", "entity_tg_sleep", "entity_tg_heart_rate", "entity_tg_spo2", "entity_tg_stress", "entity_tg_workout_record");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(1), "ec546424a85d4fe361d99f8aea6b6eff", "4d02429b5f83624edcb8d51708015145")).build());
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(KHTGStepsDataDao.class, KHTGStepsDataDao_Impl.getRequiredConverters());
        hashMap.put(KHTGSleepDataDao.class, KHTGSleepDataDao_Impl.getRequiredConverters());
        hashMap.put(KHTGHeartRateDataDao.class, KHTGHeartRateDataDao_Impl.getRequiredConverters());
        hashMap.put(KHTGSpO2DataDao.class, KHTGSpO2DataDao_Impl.getRequiredConverters());
        hashMap.put(KHTGStressDataDao.class, KHTGStressDataDao_Impl.getRequiredConverters());
        hashMap.put(KHTGWorkoutRecordsDao.class, KHTGWorkoutRecordsDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGHeartRateDataDao getTGHeartRateDao() {
        KHTGHeartRateDataDao kHTGHeartRateDataDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KHTGHeartRateDataDao_Impl(this);
            }
            kHTGHeartRateDataDao = this.c;
        }
        return kHTGHeartRateDataDao;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGSleepDataDao getTGSleepDao() {
        KHTGSleepDataDao kHTGSleepDataDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new KHTGSleepDataDao_Impl(this);
            }
            kHTGSleepDataDao = this.b;
        }
        return kHTGSleepDataDao;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGSpO2DataDao getTGSpo2Dao() {
        KHTGSpO2DataDao kHTGSpO2DataDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KHTGSpO2DataDao_Impl(this);
            }
            kHTGSpO2DataDao = this.d;
        }
        return kHTGSpO2DataDao;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGStepsDataDao getTGStepsDao() {
        KHTGStepsDataDao kHTGStepsDataDao;
        if (this.f7167a != null) {
            return this.f7167a;
        }
        synchronized (this) {
            if (this.f7167a == null) {
                this.f7167a = new KHTGStepsDataDao_Impl(this);
            }
            kHTGStepsDataDao = this.f7167a;
        }
        return kHTGStepsDataDao;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGStressDataDao getTGStressDao() {
        KHTGStressDataDao kHTGStressDataDao;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KHTGStressDataDao_Impl(this);
            }
            kHTGStressDataDao = this.e;
        }
        return kHTGStressDataDao;
    }

    @Override // com.coveiot.khtouchdb.KHTouchAppDatabase
    public KHTGWorkoutRecordsDao getTGWorkoutDao() {
        KHTGWorkoutRecordsDao kHTGWorkoutRecordsDao;
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = new KHTGWorkoutRecordsDao_Impl(this);
            }
            kHTGWorkoutRecordsDao = this.f;
        }
        return kHTGWorkoutRecordsDao;
    }
}
