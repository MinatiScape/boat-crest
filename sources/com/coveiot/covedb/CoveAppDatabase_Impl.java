package com.coveiot.covedb;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.covedb.accelerometer.RawAccelerometerDao;
import com.coveiot.covedb.accelerometer.RawAccelerometerDao_Impl;
import com.coveiot.covedb.ambientsound.AmbientSoundDao;
import com.coveiot.covedb.ambientsound.AmbientSoundDao_Impl;
import com.coveiot.covedb.bp.BpDataDao;
import com.coveiot.covedb.bp.BpDataDao_Impl;
import com.coveiot.covedb.deviceinfo.DeviceInfoDao;
import com.coveiot.covedb.deviceinfo.DeviceInfoDao_Impl;
import com.coveiot.covedb.ecg.ECGDao;
import com.coveiot.covedb.ecg.ECGDao_Impl;
import com.coveiot.covedb.heartrate.HeartRateDao;
import com.coveiot.covedb.heartrate.HeartRateDao_Impl;
import com.coveiot.covedb.hrv.HRVDao;
import com.coveiot.covedb.hrv.HRVDao_Impl;
import com.coveiot.covedb.manualdata.dao.ManualDataDao;
import com.coveiot.covedb.manualdata.dao.ManualDataDao_Impl;
import com.coveiot.covedb.ppg.RawPPGDao;
import com.coveiot.covedb.ppg.RawPPGDao_Impl;
import com.coveiot.covedb.profile.dao.DaoProfile;
import com.coveiot.covedb.profile.dao.DaoProfile_Impl;
import com.coveiot.covedb.rr.RrDataDao;
import com.coveiot.covedb.rr.RrDataDao_Impl;
import com.coveiot.covedb.sedentary.SedentaryDataDao;
import com.coveiot.covedb.sedentary.SedentaryDataDao_Impl;
import com.coveiot.covedb.sensai.SensAIDataDao;
import com.coveiot.covedb.sensai.SensAIDataDao_Impl;
import com.coveiot.covedb.sleep.SleepDataDao;
import com.coveiot.covedb.sleep.SleepDataDao_Impl;
import com.coveiot.covedb.spo2.Spo2Dao;
import com.coveiot.covedb.spo2.Spo2Dao_Impl;
import com.coveiot.covedb.stress.StressDao;
import com.coveiot.covedb.stress.StressDao_Impl;
import com.coveiot.covedb.temperature.TemperatureDao;
import com.coveiot.covedb.temperature.TemperatureDao_Impl;
import com.coveiot.covedb.walk.WalkDataDao;
import com.coveiot.covedb.walk.WalkDataDao_Impl;
import com.google.android.gms.common.Scopes;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes8.dex */
public final class CoveAppDatabase_Impl extends CoveAppDatabase {
    public volatile SleepDataDao C;
    public volatile WalkDataDao D;
    public volatile DeviceInfoDao E;
    public volatile HeartRateDao F;
    public volatile BpDataDao G;
    public volatile DaoProfile H;
    public volatile RrDataDao I;
    public volatile ECGDao J;
    public volatile TemperatureDao K;
    public volatile ManualDataDao L;
    public volatile RawPPGDao M;
    public volatile RawAccelerometerDao N;
    public volatile StressDao O;
    public volatile SedentaryDataDao P;
    public volatile Spo2Dao Q;
    public volatile HRVDao R;
    public volatile AmbientSoundDao S;
    public volatile SensAIDataDao T;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourlysleepdata` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `codevalue` TEXT, `serial_no` TEXT NOT NULL, `ligth_sleep` INTEGER NOT NULL, `deep_sleep` INTEGER NOT NULL, `awake` INTEGER NOT NULL, `intervalValue` INTEGER NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourlywalkdata` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `interval_value` INTEGER NOT NULL, `codevalue` TEXT, `distance_code_value` TEXT, `calorie_code_value` TEXT, `serial_no` TEXT NOT NULL, `calories` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `active_time` INTEGER, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `dailywalkdata` (`mDate` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `steps` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `calories` REAL NOT NULL, `pace` REAL NOT NULL, `steps_target` INTEGER NOT NULL, `active_time` INTEGER, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `mDate`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `dailysleepdata` (`date` TEXT NOT NULL, `serial_no` TEXT, `_id` TEXT NOT NULL, `deep_sleep` INTEGER NOT NULL, `light_sleep` INTEGER NOT NULL, `awake` INTEGER NOT NULL, `target` INTEGER NOT NULL, `sleepScore` INTEGER NOT NULL, `maxHr` INTEGER NOT NULL, `minHr` INTEGER NOT NULL, `maxStress` INTEGER NOT NULL, `minStress` INTEGER NOT NULL, `minAmbientSound` INTEGER NOT NULL, `maxAmbientSound` INTEGER NOT NULL, `breathQuality` INTEGER NOT NULL, `isMinMaxUpdated` INTEGER NOT NULL, `value` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`_id`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_sens_ai_summary` TEXT, `last_sync_date_distance_data` TEXT, `last_sync_date_calorie_data` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, `last_sync_date_periodic_spo2` TEXT, `last_server_sync_date_periodic_spo2` TEXT, `last_sync_date_ambient_sound` TEXT, PRIMARY KEY(`macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_heart_rate_table` (`serial_no` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT, `date` TEXT NOT NULL, `min_heart_rate` INTEGER NOT NULL, `max_heart_rate` INTEGER NOT NULL, `avg_heart_rate` REAL NOT NULL, `rest_heart_rate` INTEGER NOT NULL, `time_stamp` INTEGER NOT NULL, `coded_values` TEXT, PRIMARY KEY(`serial_no`, `start_time`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_bp` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `systolic_bp` INTEGER NOT NULL, `diastolic_bp` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`date`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_bp` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `start_hour` TEXT NOT NULL, `end_hour` TEXT NOT NULL, `systolic_bp` INTEGER NOT NULL, `diastolic_bp` INTEGER NOT NULL, `codevalues` TEXT, PRIMARY KEY(`date`, `serial_no`, `start_hour`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_heart_rate_table` (`serial_no` TEXT NOT NULL, `date` TEXT NOT NULL, `min_heart_rate` INTEGER NOT NULL, `max_heart_rate` INTEGER NOT NULL, `avg_heart_rate` INTEGER NOT NULL, `rest_heart_rate` INTEGER NOT NULL, `column_1` REAL NOT NULL, `column_2` REAL NOT NULL, `column_3` TEXT, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `profile` (`timestamp` INTEGER NOT NULL, `height` INTEGER NOT NULL, `weight` REAL NOT NULL, `steps_target` INTEGER NOT NULL, `sleep_target` INTEGER NOT NULL, `age` INTEGER NOT NULL, `walkStrideLength` INTEGER NOT NULL, `runStrideLength` INTEGER NOT NULL, `restingHr` INTEGER NOT NULL, `physicalActivityScore` REAL NOT NULL, `updatedToBand` INTEGER NOT NULL, `gender` INTEGER NOT NULL, PRIMARY KEY(`timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_rr_table` (`serial_no` TEXT NOT NULL, `date` TEXT NOT NULL, `avgRrvalue` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_rr_table` (`serial_no` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT, `date` TEXT NOT NULL, `avgRrValue` INTEGER NOT NULL, `coded_values` TEXT, PRIMARY KEY(`serial_no`, `start_time`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `ecg_summary_table` (`session_id` TEXT NOT NULL, `serial_no` TEXT, `startDateTime` TEXT, `endDateTime` TEXT, `heart_rate` INTEGER NOT NULL, `hrv_value` INTEGER NOT NULL, `stress` INTEGER NOT NULL, `high_bp` INTEGER NOT NULL, `low_bp` INTEGER NOT NULL, `rr_interval` INTEGER NOT NULL, `json_data_URL` TEXT, `mediaID` TEXT, `mediaURL` TEXT, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`session_id`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` REAL NOT NULL, `temperature` REAL NOT NULL, `isSyncedWithServer` INTEGER NOT NULL, `isLevelInterpretation` INTEGER NOT NULL, `spo2Level` TEXT, `hrv` INTEGER NOT NULL, `stress` INTEGER NOT NULL, `vascularAging` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `isLevelInterpretation`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_temperature` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `temperature_avg` REAL NOT NULL, `temperature_high` REAL NOT NULL, `temperature_low` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_temperature` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `temperature_avg` REAL NOT NULL, `temperature_high` REAL NOT NULL, `temperature_low` REAL NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `date` TEXT, `is_saved_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `baseline` REAL NOT NULL, `readiness` REAL NOT NULL, `isfeedbackgiven` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, `answered_questions` TEXT, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT, `serial_no` TEXT NOT NULL, `start_time` TEXT, `end_time` TEXT, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_hrv` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `baseline` REAL NOT NULL, `readiness` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_hrv` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_ambient_sound_table` (`serial_no` TEXT NOT NULL, `is_sync_server` INTEGER NOT NULL, `date` TEXT NOT NULL, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `total_time` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_ambient_sound_table` (`serial_no` TEXT NOT NULL, `date` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `coded_values` TEXT, PRIMARY KEY(`serial_no`, `start_time`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary` (`session_id` TEXT NOT NULL, `client_ref_id` TEXT, `serial_no` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `activity_type` INTEGER NOT NULL, `duration_sec` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `hand` INTEGER NOT NULL, `goal_type` INTEGER NOT NULL, `target_goal_value` INTEGER NOT NULL, `goal_completion_pct` INTEGER NOT NULL, `max_hr` INTEGER NOT NULL, `avg_hr` INTEGER NOT NULL, `total_swings` INTEGER NOT NULL, `played` INTEGER NOT NULL, `hit_pct` INTEGER NOT NULL, `max_hand_speed` INTEGER NOT NULL, `avg_hand_speed` INTEGER NOT NULL, `bowling_type` INTEGER NOT NULL, `total_balls_bowled` INTEGER NOT NULL, `max_arm_speed` INTEGER NOT NULL, `min_arm_speed` INTEGER NOT NULL, `avg_arm_speed` INTEGER NOT NULL, `is_saved_server` INTEGER NOT NULL, `is_add_to_compare` INTEGER NOT NULL, `is_data_aggregate_saved` INTEGER NOT NULL, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary_details` (`session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `details_data_num` INTEGER NOT NULL, `unix_time_stamp` INTEGER, `hand_speed` TEXT, `hr` TEXT, `steps` TEXT, `distance` TEXT, `calories` TEXT, `hit_miss` TEXT, `is_feedback_saved` INTEGER NOT NULL, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"51f62816e58ed08a404eb944af274113\")");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourlysleepdata`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourlywalkdata`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `dailywalkdata`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `dailysleepdata`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `device_info`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_heart_rate_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_bp`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_bp`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_heart_rate_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `profile`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_rr_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_rr_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `ecg_summary_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `manual_data`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_temperature`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_temperature`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `rawPPGDataHistory`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `rawAccelerometerDataHistory`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sedentary_data`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_stress`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_stress`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_spo2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_spo2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_hrv`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_hrv`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `daily_ambient_sound_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `hourly_ambient_sound_table`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_summary`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_summary_details`");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (CoveAppDatabase_Impl.this.mCallbacks != null) {
                int size = CoveAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) CoveAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            CoveAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            CoveAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (CoveAppDatabase_Impl.this.mCallbacks != null) {
                int size = CoveAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) CoveAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(10);
            hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1));
            hashMap.put("date", new TableInfo.Column("date", "TEXT", false, 0));
            hashMap.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
            hashMap.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
            hashMap.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
            hashMap.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
            hashMap.put("ligth_sleep", new TableInfo.Column("ligth_sleep", "INTEGER", true, 0));
            hashMap.put("deep_sleep", new TableInfo.Column("deep_sleep", "INTEGER", true, 0));
            hashMap.put("awake", new TableInfo.Column("awake", "INTEGER", true, 0));
            hashMap.put("intervalValue", new TableInfo.Column("intervalValue", "INTEGER", true, 0));
            TableInfo tableInfo = new TableInfo("hourlysleepdata", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "hourlysleepdata");
            if (tableInfo.equals(read)) {
                HashMap hashMap2 = new HashMap(12);
                hashMap2.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                hashMap2.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                hashMap2.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                hashMap2.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                hashMap2.put("interval_value", new TableInfo.Column("interval_value", "INTEGER", true, 0));
                hashMap2.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                hashMap2.put("distance_code_value", new TableInfo.Column("distance_code_value", "TEXT", false, 0));
                hashMap2.put("calorie_code_value", new TableInfo.Column("calorie_code_value", "TEXT", false, 0));
                hashMap2.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                hashMap2.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0));
                hashMap2.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0));
                hashMap2.put("active_time", new TableInfo.Column("active_time", "INTEGER", false, 0));
                TableInfo tableInfo2 = new TableInfo("hourlywalkdata", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "hourlywalkdata");
                if (tableInfo2.equals(read2)) {
                    HashMap hashMap3 = new HashMap(9);
                    hashMap3.put("mDate", new TableInfo.Column("mDate", "TEXT", true, 2));
                    hashMap3.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                    hashMap3.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0));
                    hashMap3.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0));
                    hashMap3.put("calories", new TableInfo.Column("calories", "REAL", true, 0));
                    hashMap3.put("pace", new TableInfo.Column("pace", "REAL", true, 0));
                    hashMap3.put("steps_target", new TableInfo.Column("steps_target", "INTEGER", true, 0));
                    hashMap3.put("active_time", new TableInfo.Column("active_time", "INTEGER", false, 0));
                    hashMap3.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                    TableInfo tableInfo3 = new TableInfo("dailywalkdata", hashMap3, new HashSet(0), new HashSet(0));
                    TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "dailywalkdata");
                    if (tableInfo3.equals(read3)) {
                        HashMap hashMap4 = new HashMap(18);
                        hashMap4.put("date", new TableInfo.Column("date", "TEXT", true, 0));
                        hashMap4.put("serial_no", new TableInfo.Column("serial_no", "TEXT", false, 0));
                        hashMap4.put("_id", new TableInfo.Column("_id", "TEXT", true, 1));
                        hashMap4.put("deep_sleep", new TableInfo.Column("deep_sleep", "INTEGER", true, 0));
                        hashMap4.put("light_sleep", new TableInfo.Column("light_sleep", "INTEGER", true, 0));
                        hashMap4.put("awake", new TableInfo.Column("awake", "INTEGER", true, 0));
                        hashMap4.put(TypedValues.AttributesType.S_TARGET, new TableInfo.Column(TypedValues.AttributesType.S_TARGET, "INTEGER", true, 0));
                        hashMap4.put("sleepScore", new TableInfo.Column("sleepScore", "INTEGER", true, 0));
                        hashMap4.put("maxHr", new TableInfo.Column("maxHr", "INTEGER", true, 0));
                        hashMap4.put("minHr", new TableInfo.Column("minHr", "INTEGER", true, 0));
                        hashMap4.put("maxStress", new TableInfo.Column("maxStress", "INTEGER", true, 0));
                        hashMap4.put("minStress", new TableInfo.Column("minStress", "INTEGER", true, 0));
                        hashMap4.put("minAmbientSound", new TableInfo.Column("minAmbientSound", "INTEGER", true, 0));
                        hashMap4.put("maxAmbientSound", new TableInfo.Column("maxAmbientSound", "INTEGER", true, 0));
                        hashMap4.put("breathQuality", new TableInfo.Column("breathQuality", "INTEGER", true, 0));
                        hashMap4.put("isMinMaxUpdated", new TableInfo.Column("isMinMaxUpdated", "INTEGER", true, 0));
                        hashMap4.put("value", new TableInfo.Column("value", "INTEGER", true, 0));
                        hashMap4.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                        TableInfo tableInfo4 = new TableInfo("dailysleepdata", hashMap4, new HashSet(0), new HashSet(0));
                        TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "dailysleepdata");
                        if (tableInfo4.equals(read4)) {
                            HashMap hashMap5 = new HashMap(21);
                            hashMap5.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 1));
                            hashMap5.put("is_active", new TableInfo.Column("is_active", "INTEGER", true, 0));
                            hashMap5.put("last_sync_timestamp", new TableInfo.Column("last_sync_timestamp", "INTEGER", true, 0));
                            hashMap5.put("last_sync_date_walk", new TableInfo.Column("last_sync_date_walk", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_spo2", new TableInfo.Column("last_sync_date_spo2", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_raw_ppg", new TableInfo.Column("last_sync_date_raw_ppg", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_accelerometer", new TableInfo.Column("last_sync_date_accelerometer", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_sedentary", new TableInfo.Column("last_sync_date_sedentary", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_sleep", new TableInfo.Column("last_sync_date_sleep", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_hr", new TableInfo.Column("last_sync_date_hr", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_bp", new TableInfo.Column("last_sync_date_bp", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_temperature", new TableInfo.Column("last_sync_date_temperature", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_sens_ai_summary", new TableInfo.Column("last_sync_date_sens_ai_summary", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_distance_data", new TableInfo.Column("last_sync_date_distance_data", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_calorie_data", new TableInfo.Column("last_sync_date_calorie_data", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_rr", new TableInfo.Column("last_sync_date_rr", "TEXT", false, 0));
                            hashMap5.put("last_server_sync_steps", new TableInfo.Column("last_server_sync_steps", "TEXT", false, 0));
                            hashMap5.put("last_server_sync_sleep", new TableInfo.Column("last_server_sync_sleep", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_periodic_spo2", new TableInfo.Column("last_sync_date_periodic_spo2", "TEXT", false, 0));
                            hashMap5.put("last_server_sync_date_periodic_spo2", new TableInfo.Column("last_server_sync_date_periodic_spo2", "TEXT", false, 0));
                            hashMap5.put("last_sync_date_ambient_sound", new TableInfo.Column("last_sync_date_ambient_sound", "TEXT", false, 0));
                            TableInfo tableInfo5 = new TableInfo("device_info", hashMap5, new HashSet(0), new HashSet(0));
                            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "device_info");
                            if (tableInfo5.equals(read5)) {
                                HashMap hashMap6 = new HashMap(10);
                                hashMap6.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                hashMap6.put("start_time", new TableInfo.Column("start_time", "TEXT", true, 2));
                                hashMap6.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                hashMap6.put("date", new TableInfo.Column("date", "TEXT", true, 3));
                                hashMap6.put("min_heart_rate", new TableInfo.Column("min_heart_rate", "INTEGER", true, 0));
                                hashMap6.put("max_heart_rate", new TableInfo.Column("max_heart_rate", "INTEGER", true, 0));
                                hashMap6.put("avg_heart_rate", new TableInfo.Column("avg_heart_rate", "REAL", true, 0));
                                hashMap6.put("rest_heart_rate", new TableInfo.Column("rest_heart_rate", "INTEGER", true, 0));
                                hashMap6.put("time_stamp", new TableInfo.Column("time_stamp", "INTEGER", true, 0));
                                hashMap6.put("coded_values", new TableInfo.Column("coded_values", "TEXT", false, 0));
                                TableInfo tableInfo6 = new TableInfo("hourly_heart_rate_table", hashMap6, new HashSet(0), new HashSet(0));
                                TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "hourly_heart_rate_table");
                                if (tableInfo6.equals(read6)) {
                                    HashMap hashMap7 = new HashMap(5);
                                    hashMap7.put("date", new TableInfo.Column("date", "TEXT", true, 1));
                                    hashMap7.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                    hashMap7.put("systolic_bp", new TableInfo.Column("systolic_bp", "INTEGER", true, 0));
                                    hashMap7.put("diastolic_bp", new TableInfo.Column("diastolic_bp", "INTEGER", true, 0));
                                    hashMap7.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                    TableInfo tableInfo7 = new TableInfo("daily_bp", hashMap7, new HashSet(0), new HashSet(0));
                                    TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "daily_bp");
                                    if (tableInfo7.equals(read7)) {
                                        HashMap hashMap8 = new HashMap(7);
                                        hashMap8.put("date", new TableInfo.Column("date", "TEXT", true, 1));
                                        hashMap8.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                        hashMap8.put("start_hour", new TableInfo.Column("start_hour", "TEXT", true, 3));
                                        hashMap8.put("end_hour", new TableInfo.Column("end_hour", "TEXT", true, 0));
                                        hashMap8.put("systolic_bp", new TableInfo.Column("systolic_bp", "INTEGER", true, 0));
                                        hashMap8.put("diastolic_bp", new TableInfo.Column("diastolic_bp", "INTEGER", true, 0));
                                        hashMap8.put("codevalues", new TableInfo.Column("codevalues", "TEXT", false, 0));
                                        TableInfo tableInfo8 = new TableInfo("hourly_bp", hashMap8, new HashSet(0), new HashSet(0));
                                        TableInfo read8 = TableInfo.read(supportSQLiteDatabase, "hourly_bp");
                                        if (tableInfo8.equals(read8)) {
                                            HashMap hashMap9 = new HashMap(10);
                                            hashMap9.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                            hashMap9.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                            hashMap9.put("min_heart_rate", new TableInfo.Column("min_heart_rate", "INTEGER", true, 0));
                                            hashMap9.put("max_heart_rate", new TableInfo.Column("max_heart_rate", "INTEGER", true, 0));
                                            hashMap9.put("avg_heart_rate", new TableInfo.Column("avg_heart_rate", "INTEGER", true, 0));
                                            hashMap9.put("rest_heart_rate", new TableInfo.Column("rest_heart_rate", "INTEGER", true, 0));
                                            hashMap9.put("column_1", new TableInfo.Column("column_1", "REAL", true, 0));
                                            hashMap9.put("column_2", new TableInfo.Column("column_2", "REAL", true, 0));
                                            hashMap9.put("column_3", new TableInfo.Column("column_3", "TEXT", false, 0));
                                            hashMap9.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                            TableInfo tableInfo9 = new TableInfo("daily_heart_rate_table", hashMap9, new HashSet(0), new HashSet(0));
                                            TableInfo read9 = TableInfo.read(supportSQLiteDatabase, "daily_heart_rate_table");
                                            if (tableInfo9.equals(read9)) {
                                                HashMap hashMap10 = new HashMap(12);
                                                hashMap10.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 1));
                                                hashMap10.put(Property.ICON_TEXT_FIT_HEIGHT, new TableInfo.Column(Property.ICON_TEXT_FIT_HEIGHT, "INTEGER", true, 0));
                                                hashMap10.put("weight", new TableInfo.Column("weight", "REAL", true, 0));
                                                hashMap10.put("steps_target", new TableInfo.Column("steps_target", "INTEGER", true, 0));
                                                hashMap10.put("sleep_target", new TableInfo.Column("sleep_target", "INTEGER", true, 0));
                                                hashMap10.put("age", new TableInfo.Column("age", "INTEGER", true, 0));
                                                hashMap10.put("walkStrideLength", new TableInfo.Column("walkStrideLength", "INTEGER", true, 0));
                                                hashMap10.put("runStrideLength", new TableInfo.Column("runStrideLength", "INTEGER", true, 0));
                                                hashMap10.put("restingHr", new TableInfo.Column("restingHr", "INTEGER", true, 0));
                                                hashMap10.put("physicalActivityScore", new TableInfo.Column("physicalActivityScore", "REAL", true, 0));
                                                hashMap10.put("updatedToBand", new TableInfo.Column("updatedToBand", "INTEGER", true, 0));
                                                hashMap10.put("gender", new TableInfo.Column("gender", "INTEGER", true, 0));
                                                TableInfo tableInfo10 = new TableInfo(Scopes.PROFILE, hashMap10, new HashSet(0), new HashSet(0));
                                                TableInfo read10 = TableInfo.read(supportSQLiteDatabase, Scopes.PROFILE);
                                                if (tableInfo10.equals(read10)) {
                                                    HashMap hashMap11 = new HashMap(4);
                                                    hashMap11.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                    hashMap11.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                    hashMap11.put("avgRrvalue", new TableInfo.Column("avgRrvalue", "INTEGER", true, 0));
                                                    hashMap11.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                    TableInfo tableInfo11 = new TableInfo("daily_rr_table", hashMap11, new HashSet(0), new HashSet(0));
                                                    TableInfo read11 = TableInfo.read(supportSQLiteDatabase, "daily_rr_table");
                                                    if (tableInfo11.equals(read11)) {
                                                        HashMap hashMap12 = new HashMap(6);
                                                        hashMap12.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                        hashMap12.put("start_time", new TableInfo.Column("start_time", "TEXT", true, 2));
                                                        hashMap12.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                        hashMap12.put("date", new TableInfo.Column("date", "TEXT", true, 3));
                                                        hashMap12.put("avgRrValue", new TableInfo.Column("avgRrValue", "INTEGER", true, 0));
                                                        hashMap12.put("coded_values", new TableInfo.Column("coded_values", "TEXT", false, 0));
                                                        TableInfo tableInfo12 = new TableInfo("hourly_rr_table", hashMap12, new HashSet(0), new HashSet(0));
                                                        TableInfo read12 = TableInfo.read(supportSQLiteDatabase, "hourly_rr_table");
                                                        if (tableInfo12.equals(read12)) {
                                                            HashMap hashMap13 = new HashMap(14);
                                                            hashMap13.put(WorkoutConstants.SESSION_ID, new TableInfo.Column(WorkoutConstants.SESSION_ID, "TEXT", true, 1));
                                                            hashMap13.put("serial_no", new TableInfo.Column("serial_no", "TEXT", false, 0));
                                                            hashMap13.put("startDateTime", new TableInfo.Column("startDateTime", "TEXT", false, 0));
                                                            hashMap13.put("endDateTime", new TableInfo.Column("endDateTime", "TEXT", false, 0));
                                                            hashMap13.put("heart_rate", new TableInfo.Column("heart_rate", "INTEGER", true, 0));
                                                            hashMap13.put("hrv_value", new TableInfo.Column("hrv_value", "INTEGER", true, 0));
                                                            hashMap13.put(DeviceKey.Stress, new TableInfo.Column(DeviceKey.Stress, "INTEGER", true, 0));
                                                            hashMap13.put("high_bp", new TableInfo.Column("high_bp", "INTEGER", true, 0));
                                                            hashMap13.put("low_bp", new TableInfo.Column("low_bp", "INTEGER", true, 0));
                                                            hashMap13.put("rr_interval", new TableInfo.Column("rr_interval", "INTEGER", true, 0));
                                                            hashMap13.put("json_data_URL", new TableInfo.Column("json_data_URL", "TEXT", false, 0));
                                                            hashMap13.put("mediaID", new TableInfo.Column("mediaID", "TEXT", false, 0));
                                                            hashMap13.put("mediaURL", new TableInfo.Column("mediaURL", "TEXT", false, 0));
                                                            hashMap13.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                            TableInfo tableInfo13 = new TableInfo("ecg_summary_table", hashMap13, new HashSet(0), new HashSet(0));
                                                            TableInfo read13 = TableInfo.read(supportSQLiteDatabase, "ecg_summary_table");
                                                            if (tableInfo13.equals(read13)) {
                                                                HashMap hashMap14 = new HashMap(15);
                                                                hashMap14.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", true, 1));
                                                                hashMap14.put("source", new TableInfo.Column("source", "TEXT", true, 0));
                                                                hashMap14.put("serial_no", new TableInfo.Column("serial_no", "TEXT", false, 0));
                                                                hashMap14.put("userDeviceId", new TableInfo.Column("userDeviceId", "TEXT", false, 0));
                                                                hashMap14.put("hr", new TableInfo.Column("hr", "INTEGER", true, 0));
                                                                hashMap14.put("systolicBp", new TableInfo.Column("systolicBp", "INTEGER", true, 0));
                                                                hashMap14.put("diastolicBp", new TableInfo.Column("diastolicBp", "INTEGER", true, 0));
                                                                hashMap14.put("spo2", new TableInfo.Column("spo2", "REAL", true, 0));
                                                                hashMap14.put(DeviceKey.TempData, new TableInfo.Column(DeviceKey.TempData, "REAL", true, 0));
                                                                hashMap14.put("isSyncedWithServer", new TableInfo.Column("isSyncedWithServer", "INTEGER", true, 0));
                                                                hashMap14.put("isLevelInterpretation", new TableInfo.Column("isLevelInterpretation", "INTEGER", true, 2));
                                                                hashMap14.put("spo2Level", new TableInfo.Column("spo2Level", "TEXT", false, 0));
                                                                hashMap14.put(DeviceKey.HRV, new TableInfo.Column(DeviceKey.HRV, "INTEGER", true, 0));
                                                                hashMap14.put(DeviceKey.Stress, new TableInfo.Column(DeviceKey.Stress, "INTEGER", true, 0));
                                                                hashMap14.put(DeviceKey.VascularAging, new TableInfo.Column(DeviceKey.VascularAging, "INTEGER", true, 0));
                                                                TableInfo tableInfo14 = new TableInfo("manual_data", hashMap14, new HashSet(0), new HashSet(0));
                                                                TableInfo read14 = TableInfo.read(supportSQLiteDatabase, "manual_data");
                                                                if (tableInfo14.equals(read14)) {
                                                                    HashMap hashMap15 = new HashMap(6);
                                                                    hashMap15.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                                    hashMap15.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                    hashMap15.put("temperature_avg", new TableInfo.Column("temperature_avg", "REAL", true, 0));
                                                                    hashMap15.put("temperature_high", new TableInfo.Column("temperature_high", "REAL", true, 0));
                                                                    hashMap15.put("temperature_low", new TableInfo.Column("temperature_low", "REAL", true, 0));
                                                                    hashMap15.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                                    TableInfo tableInfo15 = new TableInfo("daily_temperature", hashMap15, new HashSet(0), new HashSet(0));
                                                                    TableInfo read15 = TableInfo.read(supportSQLiteDatabase, "daily_temperature");
                                                                    if (tableInfo15.equals(read15)) {
                                                                        HashMap hashMap16 = new HashMap(9);
                                                                        hashMap16.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                                                        hashMap16.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                                                                        hashMap16.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                                                                        hashMap16.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                                        hashMap16.put("temperature_avg", new TableInfo.Column("temperature_avg", "REAL", true, 0));
                                                                        hashMap16.put("temperature_high", new TableInfo.Column("temperature_high", "REAL", true, 0));
                                                                        hashMap16.put("temperature_low", new TableInfo.Column("temperature_low", "REAL", true, 0));
                                                                        hashMap16.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                                                                        hashMap16.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                        TableInfo tableInfo16 = new TableInfo("hourly_temperature", hashMap16, new HashSet(0), new HashSet(0));
                                                                        TableInfo read16 = TableInfo.read(supportSQLiteDatabase, "hourly_temperature");
                                                                        if (tableInfo16.equals(read16)) {
                                                                            HashMap hashMap17 = new HashMap(10);
                                                                            hashMap17.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2));
                                                                            hashMap17.put("serial_number", new TableInfo.Column("serial_number", "TEXT", true, 1));
                                                                            hashMap17.put("recordNumber", new TableInfo.Column("recordNumber", "INTEGER", true, 0));
                                                                            hashMap17.put("ppgDataFilePath", new TableInfo.Column("ppgDataFilePath", "TEXT", false, 0));
                                                                            hashMap17.put("samplingRate", new TableInfo.Column("samplingRate", "INTEGER", true, 0));
                                                                            hashMap17.put("ppgType", new TableInfo.Column("ppgType", "INTEGER", true, 0));
                                                                            hashMap17.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0));
                                                                            hashMap17.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0));
                                                                            hashMap17.put("movementLevel", new TableInfo.Column("movementLevel", "INTEGER", true, 0));
                                                                            hashMap17.put("is_sync_to_server", new TableInfo.Column("is_sync_to_server", "INTEGER", true, 0));
                                                                            TableInfo tableInfo17 = new TableInfo("rawPPGDataHistory", hashMap17, new HashSet(0), new HashSet(0));
                                                                            TableInfo read17 = TableInfo.read(supportSQLiteDatabase, "rawPPGDataHistory");
                                                                            if (tableInfo17.equals(read17)) {
                                                                                HashMap hashMap18 = new HashMap(8);
                                                                                hashMap18.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2));
                                                                                hashMap18.put("serial_number", new TableInfo.Column("serial_number", "TEXT", true, 1));
                                                                                hashMap18.put("recordNumber", new TableInfo.Column("recordNumber", "INTEGER", true, 0));
                                                                                hashMap18.put("accelerometerDataFilePath", new TableInfo.Column("accelerometerDataFilePath", "TEXT", false, 0));
                                                                                hashMap18.put("samplingRate", new TableInfo.Column("samplingRate", "INTEGER", true, 0));
                                                                                hashMap18.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0));
                                                                                hashMap18.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0));
                                                                                hashMap18.put("is_sync_to_server", new TableInfo.Column("is_sync_to_server", "INTEGER", true, 0));
                                                                                TableInfo tableInfo18 = new TableInfo("rawAccelerometerDataHistory", hashMap18, new HashSet(0), new HashSet(0));
                                                                                TableInfo read18 = TableInfo.read(supportSQLiteDatabase, "rawAccelerometerDataHistory");
                                                                                if (tableInfo18.equals(read18)) {
                                                                                    HashMap hashMap19 = new HashMap(5);
                                                                                    hashMap19.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 2));
                                                                                    hashMap19.put("serial_number", new TableInfo.Column("serial_number", "TEXT", true, 1));
                                                                                    hashMap19.put("recordNumber", new TableInfo.Column("recordNumber", "INTEGER", true, 0));
                                                                                    hashMap19.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                                                                                    hashMap19.put("is_saved_server", new TableInfo.Column("is_saved_server", "INTEGER", true, 0));
                                                                                    TableInfo tableInfo19 = new TableInfo("sedentary_data", hashMap19, new HashSet(0), new HashSet(0));
                                                                                    TableInfo read19 = TableInfo.read(supportSQLiteDatabase, "sedentary_data");
                                                                                    if (tableInfo19.equals(read19)) {
                                                                                        HashMap hashMap20 = new HashMap(10);
                                                                                        hashMap20.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                                                        hashMap20.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                                        hashMap20.put("stress_avg", new TableInfo.Column("stress_avg", "REAL", true, 0));
                                                                                        hashMap20.put("stress_high", new TableInfo.Column("stress_high", "INTEGER", true, 0));
                                                                                        hashMap20.put("stress_low", new TableInfo.Column("stress_low", "INTEGER", true, 0));
                                                                                        hashMap20.put("baseline", new TableInfo.Column("baseline", "REAL", true, 0));
                                                                                        hashMap20.put("readiness", new TableInfo.Column("readiness", "REAL", true, 0));
                                                                                        hashMap20.put("isfeedbackgiven", new TableInfo.Column("isfeedbackgiven", "INTEGER", true, 0));
                                                                                        hashMap20.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                                                        hashMap20.put("answered_questions", new TableInfo.Column("answered_questions", "TEXT", false, 0));
                                                                                        TableInfo tableInfo20 = new TableInfo("daily_stress", hashMap20, new HashSet(0), new HashSet(0));
                                                                                        TableInfo read20 = TableInfo.read(supportSQLiteDatabase, "daily_stress");
                                                                                        if (tableInfo20.equals(read20)) {
                                                                                            HashMap hashMap21 = new HashMap(9);
                                                                                            hashMap21.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                                                                            hashMap21.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                                                                                            hashMap21.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                                                                                            hashMap21.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                                                            hashMap21.put("stress_avg", new TableInfo.Column("stress_avg", "REAL", true, 0));
                                                                                            hashMap21.put("stress_high", new TableInfo.Column("stress_high", "INTEGER", true, 0));
                                                                                            hashMap21.put("stress_low", new TableInfo.Column("stress_low", "INTEGER", true, 0));
                                                                                            hashMap21.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                                                                                            hashMap21.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                                            TableInfo tableInfo21 = new TableInfo("hourly_stress", hashMap21, new HashSet(0), new HashSet(0));
                                                                                            TableInfo read21 = TableInfo.read(supportSQLiteDatabase, "hourly_stress");
                                                                                            if (tableInfo21.equals(read21)) {
                                                                                                HashMap hashMap22 = new HashMap(9);
                                                                                                hashMap22.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                                                                                hashMap22.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                                                                                                hashMap22.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                                                hashMap22.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                                                                                                hashMap22.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                                                                hashMap22.put("spo2_avg", new TableInfo.Column("spo2_avg", "REAL", true, 0));
                                                                                                hashMap22.put("spo2_high", new TableInfo.Column("spo2_high", "INTEGER", true, 0));
                                                                                                hashMap22.put("spo2_low", new TableInfo.Column("spo2_low", "INTEGER", true, 0));
                                                                                                hashMap22.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                                                                                                TableInfo tableInfo22 = new TableInfo("hourly_spo2", hashMap22, new HashSet(0), new HashSet(0));
                                                                                                TableInfo read22 = TableInfo.read(supportSQLiteDatabase, "hourly_spo2");
                                                                                                if (tableInfo22.equals(read22)) {
                                                                                                    HashMap hashMap23 = new HashMap(6);
                                                                                                    hashMap23.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                                                                    hashMap23.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                                                    hashMap23.put("spo2_avg", new TableInfo.Column("spo2_avg", "REAL", true, 0));
                                                                                                    hashMap23.put("spo2_high", new TableInfo.Column("spo2_high", "INTEGER", true, 0));
                                                                                                    hashMap23.put("spo2_low", new TableInfo.Column("spo2_low", "INTEGER", true, 0));
                                                                                                    hashMap23.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                                                                    TableInfo tableInfo23 = new TableInfo("daily_spo2", hashMap23, new HashSet(0), new HashSet(0));
                                                                                                    TableInfo read23 = TableInfo.read(supportSQLiteDatabase, "daily_spo2");
                                                                                                    if (tableInfo23.equals(read23)) {
                                                                                                        HashMap hashMap24 = new HashMap(8);
                                                                                                        hashMap24.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                                                                        hashMap24.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                                                        hashMap24.put("hrv_avg", new TableInfo.Column("hrv_avg", "REAL", true, 0));
                                                                                                        hashMap24.put("hrv_high", new TableInfo.Column("hrv_high", "REAL", true, 0));
                                                                                                        hashMap24.put("hrv_low", new TableInfo.Column("hrv_low", "REAL", true, 0));
                                                                                                        hashMap24.put("baseline", new TableInfo.Column("baseline", "REAL", true, 0));
                                                                                                        hashMap24.put("readiness", new TableInfo.Column("readiness", "REAL", true, 0));
                                                                                                        hashMap24.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                                                                        TableInfo tableInfo24 = new TableInfo("daily_hrv", hashMap24, new HashSet(0), new HashSet(0));
                                                                                                        TableInfo read24 = TableInfo.read(supportSQLiteDatabase, "daily_hrv");
                                                                                                        if (tableInfo24.equals(read24)) {
                                                                                                            HashMap hashMap25 = new HashMap(9);
                                                                                                            hashMap25.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                                                                                            hashMap25.put("date", new TableInfo.Column("date", "TEXT", false, 0));
                                                                                                            hashMap25.put("start_time", new TableInfo.Column("start_time", "TEXT", false, 0));
                                                                                                            hashMap25.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                                                                            hashMap25.put("hrv_avg", new TableInfo.Column("hrv_avg", "REAL", true, 0));
                                                                                                            hashMap25.put("hrv_high", new TableInfo.Column("hrv_high", "REAL", true, 0));
                                                                                                            hashMap25.put("hrv_low", new TableInfo.Column("hrv_low", "REAL", true, 0));
                                                                                                            hashMap25.put("codevalue", new TableInfo.Column("codevalue", "TEXT", false, 0));
                                                                                                            hashMap25.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                                                            TableInfo tableInfo25 = new TableInfo("hourly_hrv", hashMap25, new HashSet(0), new HashSet(0));
                                                                                                            TableInfo read25 = TableInfo.read(supportSQLiteDatabase, "hourly_hrv");
                                                                                                            if (tableInfo25.equals(read25)) {
                                                                                                                HashMap hashMap26 = new HashMap(7);
                                                                                                                hashMap26.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                                                                hashMap26.put("is_sync_server", new TableInfo.Column("is_sync_server", "INTEGER", true, 0));
                                                                                                                hashMap26.put("date", new TableInfo.Column("date", "TEXT", true, 2));
                                                                                                                hashMap26.put("min_ambient_sound", new TableInfo.Column("min_ambient_sound", "INTEGER", true, 0));
                                                                                                                hashMap26.put("max_ambient_sound", new TableInfo.Column("max_ambient_sound", "INTEGER", true, 0));
                                                                                                                hashMap26.put("avg_ambient_sound", new TableInfo.Column("avg_ambient_sound", "REAL", true, 0));
                                                                                                                hashMap26.put("total_time", new TableInfo.Column("total_time", "INTEGER", true, 0));
                                                                                                                TableInfo tableInfo26 = new TableInfo("daily_ambient_sound_table", hashMap26, new HashSet(0), new HashSet(0));
                                                                                                                TableInfo read26 = TableInfo.read(supportSQLiteDatabase, "daily_ambient_sound_table");
                                                                                                                if (tableInfo26.equals(read26)) {
                                                                                                                    HashMap hashMap27 = new HashMap(8);
                                                                                                                    hashMap27.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 1));
                                                                                                                    hashMap27.put("date", new TableInfo.Column("date", "TEXT", true, 3));
                                                                                                                    hashMap27.put("start_time", new TableInfo.Column("start_time", "TEXT", true, 2));
                                                                                                                    hashMap27.put("end_time", new TableInfo.Column("end_time", "TEXT", false, 0));
                                                                                                                    hashMap27.put("min_ambient_sound", new TableInfo.Column("min_ambient_sound", "INTEGER", true, 0));
                                                                                                                    hashMap27.put("max_ambient_sound", new TableInfo.Column("max_ambient_sound", "INTEGER", true, 0));
                                                                                                                    hashMap27.put("avg_ambient_sound", new TableInfo.Column("avg_ambient_sound", "REAL", true, 0));
                                                                                                                    hashMap27.put("coded_values", new TableInfo.Column("coded_values", "TEXT", false, 0));
                                                                                                                    TableInfo tableInfo27 = new TableInfo("hourly_ambient_sound_table", hashMap27, new HashSet(0), new HashSet(0));
                                                                                                                    TableInfo read27 = TableInfo.read(supportSQLiteDatabase, "hourly_ambient_sound_table");
                                                                                                                    if (tableInfo27.equals(read27)) {
                                                                                                                        HashMap hashMap28 = new HashMap(27);
                                                                                                                        hashMap28.put(WorkoutConstants.SESSION_ID, new TableInfo.Column(WorkoutConstants.SESSION_ID, "TEXT", true, 1));
                                                                                                                        hashMap28.put("client_ref_id", new TableInfo.Column("client_ref_id", "TEXT", false, 0));
                                                                                                                        hashMap28.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                                                                        hashMap28.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("activity_type", new TableInfo.Column("activity_type", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("duration_sec", new TableInfo.Column("duration_sec", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("total_steps", new TableInfo.Column("total_steps", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("total_calories", new TableInfo.Column("total_calories", "REAL", true, 0));
                                                                                                                        hashMap28.put("hand", new TableInfo.Column("hand", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("goal_type", new TableInfo.Column("goal_type", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("target_goal_value", new TableInfo.Column("target_goal_value", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("goal_completion_pct", new TableInfo.Column("goal_completion_pct", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("max_hr", new TableInfo.Column("max_hr", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("avg_hr", new TableInfo.Column("avg_hr", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("total_swings", new TableInfo.Column("total_swings", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("played", new TableInfo.Column("played", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("hit_pct", new TableInfo.Column("hit_pct", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("max_hand_speed", new TableInfo.Column("max_hand_speed", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("avg_hand_speed", new TableInfo.Column("avg_hand_speed", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("bowling_type", new TableInfo.Column("bowling_type", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("total_balls_bowled", new TableInfo.Column("total_balls_bowled", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("max_arm_speed", new TableInfo.Column("max_arm_speed", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("min_arm_speed", new TableInfo.Column("min_arm_speed", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("avg_arm_speed", new TableInfo.Column("avg_arm_speed", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("is_saved_server", new TableInfo.Column("is_saved_server", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("is_add_to_compare", new TableInfo.Column("is_add_to_compare", "INTEGER", true, 0));
                                                                                                                        hashMap28.put("is_data_aggregate_saved", new TableInfo.Column("is_data_aggregate_saved", "INTEGER", true, 0));
                                                                                                                        TableInfo tableInfo28 = new TableInfo("sensai_activity_summary", hashMap28, new HashSet(0), new HashSet(0));
                                                                                                                        TableInfo read28 = TableInfo.read(supportSQLiteDatabase, "sensai_activity_summary");
                                                                                                                        if (tableInfo28.equals(read28)) {
                                                                                                                            HashMap hashMap29 = new HashMap(12);
                                                                                                                            hashMap29.put(WorkoutConstants.SESSION_ID, new TableInfo.Column(WorkoutConstants.SESSION_ID, "TEXT", true, 1));
                                                                                                                            hashMap29.put("serial_no", new TableInfo.Column("serial_no", "TEXT", true, 2));
                                                                                                                            hashMap29.put("activity_type", new TableInfo.Column("activity_type", "INTEGER", true, 0));
                                                                                                                            hashMap29.put("details_data_num", new TableInfo.Column("details_data_num", "INTEGER", true, 0));
                                                                                                                            hashMap29.put("unix_time_stamp", new TableInfo.Column("unix_time_stamp", "INTEGER", false, 0));
                                                                                                                            hashMap29.put("hand_speed", new TableInfo.Column("hand_speed", "TEXT", false, 0));
                                                                                                                            hashMap29.put("hr", new TableInfo.Column("hr", "TEXT", false, 0));
                                                                                                                            hashMap29.put("steps", new TableInfo.Column("steps", "TEXT", false, 0));
                                                                                                                            hashMap29.put("distance", new TableInfo.Column("distance", "TEXT", false, 0));
                                                                                                                            hashMap29.put("calories", new TableInfo.Column("calories", "TEXT", false, 0));
                                                                                                                            hashMap29.put("hit_miss", new TableInfo.Column("hit_miss", "TEXT", false, 0));
                                                                                                                            hashMap29.put("is_feedback_saved", new TableInfo.Column("is_feedback_saved", "INTEGER", true, 0));
                                                                                                                            TableInfo tableInfo29 = new TableInfo("sensai_activity_summary_details", hashMap29, new HashSet(0), new HashSet(0));
                                                                                                                            TableInfo read29 = TableInfo.read(supportSQLiteDatabase, "sensai_activity_summary_details");
                                                                                                                            if (tableInfo29.equals(read29)) {
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            throw new IllegalStateException("Migration didn't properly handle sensai_activity_summary_details(com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails).\n Expected:\n" + tableInfo29 + "\n Found:\n" + read29);
                                                                                                                        }
                                                                                                                        throw new IllegalStateException("Migration didn't properly handle sensai_activity_summary(com.coveiot.covedb.sensai.entity.SensAIActivitySummary).\n Expected:\n" + tableInfo28 + "\n Found:\n" + read28);
                                                                                                                    }
                                                                                                                    throw new IllegalStateException("Migration didn't properly handle hourly_ambient_sound_table(com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData).\n Expected:\n" + tableInfo27 + "\n Found:\n" + read27);
                                                                                                                }
                                                                                                                throw new IllegalStateException("Migration didn't properly handle daily_ambient_sound_table(com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData).\n Expected:\n" + tableInfo26 + "\n Found:\n" + read26);
                                                                                                            }
                                                                                                            throw new IllegalStateException("Migration didn't properly handle hourly_hrv(com.coveiot.covedb.hrv.entity.HourlyHRV).\n Expected:\n" + tableInfo25 + "\n Found:\n" + read25);
                                                                                                        }
                                                                                                        throw new IllegalStateException("Migration didn't properly handle daily_hrv(com.coveiot.covedb.hrv.entity.DailyHRV).\n Expected:\n" + tableInfo24 + "\n Found:\n" + read24);
                                                                                                    }
                                                                                                    throw new IllegalStateException("Migration didn't properly handle daily_spo2(com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2).\n Expected:\n" + tableInfo23 + "\n Found:\n" + read23);
                                                                                                }
                                                                                                throw new IllegalStateException("Migration didn't properly handle hourly_spo2(com.coveiot.covedb.spo2.entity.EntityHourlySpo2).\n Expected:\n" + tableInfo22 + "\n Found:\n" + read22);
                                                                                            }
                                                                                            throw new IllegalStateException("Migration didn't properly handle hourly_stress(com.coveiot.covedb.stress.entity.HourlyStress).\n Expected:\n" + tableInfo21 + "\n Found:\n" + read21);
                                                                                        }
                                                                                        throw new IllegalStateException("Migration didn't properly handle daily_stress(com.coveiot.covedb.stress.entity.DailyStress).\n Expected:\n" + tableInfo20 + "\n Found:\n" + read20);
                                                                                    }
                                                                                    throw new IllegalStateException("Migration didn't properly handle sedentary_data(com.coveiot.covedb.sedentary.entities.EntitySedentary).\n Expected:\n" + tableInfo19 + "\n Found:\n" + read19);
                                                                                }
                                                                                throw new IllegalStateException("Migration didn't properly handle rawAccelerometerDataHistory(com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData).\n Expected:\n" + tableInfo18 + "\n Found:\n" + read18);
                                                                            }
                                                                            throw new IllegalStateException("Migration didn't properly handle rawPPGDataHistory(com.coveiot.covedb.ppg.entities.EntityRawPPGData).\n Expected:\n" + tableInfo17 + "\n Found:\n" + read17);
                                                                        }
                                                                        throw new IllegalStateException("Migration didn't properly handle hourly_temperature(com.coveiot.covedb.temperature.entity.HourlyTemperature).\n Expected:\n" + tableInfo16 + "\n Found:\n" + read16);
                                                                    }
                                                                    throw new IllegalStateException("Migration didn't properly handle daily_temperature(com.coveiot.covedb.temperature.entity.DailyTemperature).\n Expected:\n" + tableInfo15 + "\n Found:\n" + read15);
                                                                }
                                                                throw new IllegalStateException("Migration didn't properly handle manual_data(com.coveiot.covedb.manualdata.entities.EntityManualData).\n Expected:\n" + tableInfo14 + "\n Found:\n" + read14);
                                                            }
                                                            throw new IllegalStateException("Migration didn't properly handle ecg_summary_table(com.coveiot.covedb.ecg.EntityECGSummaryData).\n Expected:\n" + tableInfo13 + "\n Found:\n" + read13);
                                                        }
                                                        throw new IllegalStateException("Migration didn't properly handle hourly_rr_table(com.coveiot.covedb.rr.entity.EntityHourlyRrData).\n Expected:\n" + tableInfo12 + "\n Found:\n" + read12);
                                                    }
                                                    throw new IllegalStateException("Migration didn't properly handle daily_rr_table(com.coveiot.covedb.rr.entity.EntityDailyRrData).\n Expected:\n" + tableInfo11 + "\n Found:\n" + read11);
                                                }
                                                throw new IllegalStateException("Migration didn't properly handle profile(com.coveiot.covedb.profile.entities.EntityProfile).\n Expected:\n" + tableInfo10 + "\n Found:\n" + read10);
                                            }
                                            throw new IllegalStateException("Migration didn't properly handle daily_heart_rate_table(com.coveiot.covedb.heartrate.EntityDailyHeartRateData).\n Expected:\n" + tableInfo9 + "\n Found:\n" + read9);
                                        }
                                        throw new IllegalStateException("Migration didn't properly handle hourly_bp(com.coveiot.covedb.bp.entities.EntityHourlyBp).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
                                    }
                                    throw new IllegalStateException("Migration didn't properly handle daily_bp(com.coveiot.covedb.bp.entities.EntityDailyBp).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
                                }
                                throw new IllegalStateException("Migration didn't properly handle hourly_heart_rate_table(com.coveiot.covedb.heartrate.EntityHourlyHeartRateData).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
                            }
                            throw new IllegalStateException("Migration didn't properly handle device_info(com.coveiot.covedb.deviceinfo.EntityDeviceInfo).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
                        }
                        throw new IllegalStateException("Migration didn't properly handle dailysleepdata(com.coveiot.covedb.sleep.DailySleepData).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                    }
                    throw new IllegalStateException("Migration didn't properly handle dailywalkdata(com.coveiot.covedb.walk.entities.DailyWalkData).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                throw new IllegalStateException("Migration didn't properly handle hourlywalkdata(com.coveiot.covedb.walk.entities.HourlyWalkData).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            throw new IllegalStateException("Migration didn't properly handle hourlysleepdata(com.coveiot.covedb.sleep.HourlySleepData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public AmbientSoundDao ambientSoundDao() {
        AmbientSoundDao ambientSoundDao;
        if (this.S != null) {
            return this.S;
        }
        synchronized (this) {
            if (this.S == null) {
                this.S = new AmbientSoundDao_Impl(this);
            }
            ambientSoundDao = this.S;
        }
        return ambientSoundDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public BpDataDao bpDataDao() {
        BpDataDao bpDataDao;
        if (this.G != null) {
            return this.G;
        }
        synchronized (this) {
            if (this.G == null) {
                this.G = new BpDataDao_Impl(this);
            }
            bpDataDao = this.G;
        }
        return bpDataDao;
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `hourlysleepdata`");
            writableDatabase.execSQL("DELETE FROM `hourlywalkdata`");
            writableDatabase.execSQL("DELETE FROM `dailywalkdata`");
            writableDatabase.execSQL("DELETE FROM `dailysleepdata`");
            writableDatabase.execSQL("DELETE FROM `device_info`");
            writableDatabase.execSQL("DELETE FROM `hourly_heart_rate_table`");
            writableDatabase.execSQL("DELETE FROM `daily_bp`");
            writableDatabase.execSQL("DELETE FROM `hourly_bp`");
            writableDatabase.execSQL("DELETE FROM `daily_heart_rate_table`");
            writableDatabase.execSQL("DELETE FROM `profile`");
            writableDatabase.execSQL("DELETE FROM `daily_rr_table`");
            writableDatabase.execSQL("DELETE FROM `hourly_rr_table`");
            writableDatabase.execSQL("DELETE FROM `ecg_summary_table`");
            writableDatabase.execSQL("DELETE FROM `manual_data`");
            writableDatabase.execSQL("DELETE FROM `daily_temperature`");
            writableDatabase.execSQL("DELETE FROM `hourly_temperature`");
            writableDatabase.execSQL("DELETE FROM `rawPPGDataHistory`");
            writableDatabase.execSQL("DELETE FROM `rawAccelerometerDataHistory`");
            writableDatabase.execSQL("DELETE FROM `sedentary_data`");
            writableDatabase.execSQL("DELETE FROM `daily_stress`");
            writableDatabase.execSQL("DELETE FROM `hourly_stress`");
            writableDatabase.execSQL("DELETE FROM `hourly_spo2`");
            writableDatabase.execSQL("DELETE FROM `daily_spo2`");
            writableDatabase.execSQL("DELETE FROM `daily_hrv`");
            writableDatabase.execSQL("DELETE FROM `hourly_hrv`");
            writableDatabase.execSQL("DELETE FROM `daily_ambient_sound_table`");
            writableDatabase.execSQL("DELETE FROM `hourly_ambient_sound_table`");
            writableDatabase.execSQL("DELETE FROM `sensai_activity_summary`");
            writableDatabase.execSQL("DELETE FROM `sensai_activity_summary_details`");
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
        return new InvalidationTracker(this, "hourlysleepdata", "hourlywalkdata", "dailywalkdata", "dailysleepdata", "device_info", "hourly_heart_rate_table", "daily_bp", "hourly_bp", "daily_heart_rate_table", Scopes.PROFILE, "daily_rr_table", "hourly_rr_table", "ecg_summary_table", "manual_data", "daily_temperature", "hourly_temperature", "rawPPGDataHistory", "rawAccelerometerDataHistory", "sedentary_data", "daily_stress", "hourly_stress", "hourly_spo2", "daily_spo2", "daily_hrv", "hourly_hrv", "daily_ambient_sound_table", "hourly_ambient_sound_table", "sensai_activity_summary", "sensai_activity_summary_details");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(29), "51f62816e58ed08a404eb944af274113", "18c8ba4c622f06c34c430d80914b494f")).build());
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public DaoProfile daoProfile() {
        DaoProfile daoProfile;
        if (this.H != null) {
            return this.H;
        }
        synchronized (this) {
            if (this.H == null) {
                this.H = new DaoProfile_Impl(this);
            }
            daoProfile = this.H;
        }
        return daoProfile;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public DeviceInfoDao deviceInfoDao() {
        DeviceInfoDao deviceInfoDao;
        if (this.E != null) {
            return this.E;
        }
        synchronized (this) {
            if (this.E == null) {
                this.E = new DeviceInfoDao_Impl(this);
            }
            deviceInfoDao = this.E;
        }
        return deviceInfoDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public ECGDao ecgDao() {
        ECGDao eCGDao;
        if (this.J != null) {
            return this.J;
        }
        synchronized (this) {
            if (this.J == null) {
                this.J = new ECGDao_Impl(this);
            }
            eCGDao = this.J;
        }
        return eCGDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public HeartRateDao heartRateDao() {
        HeartRateDao heartRateDao;
        if (this.F != null) {
            return this.F;
        }
        synchronized (this) {
            if (this.F == null) {
                this.F = new HeartRateDao_Impl(this);
            }
            heartRateDao = this.F;
        }
        return heartRateDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public HRVDao hrvDao() {
        HRVDao hRVDao;
        if (this.R != null) {
            return this.R;
        }
        synchronized (this) {
            if (this.R == null) {
                this.R = new HRVDao_Impl(this);
            }
            hRVDao = this.R;
        }
        return hRVDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public WalkDataDao mFiveMinuteWData() {
        WalkDataDao walkDataDao;
        if (this.D != null) {
            return this.D;
        }
        synchronized (this) {
            if (this.D == null) {
                this.D = new WalkDataDao_Impl(this);
            }
            walkDataDao = this.D;
        }
        return walkDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public SleepDataDao mSleepDataDao() {
        SleepDataDao sleepDataDao;
        if (this.C != null) {
            return this.C;
        }
        synchronized (this) {
            if (this.C == null) {
                this.C = new SleepDataDao_Impl(this);
            }
            sleepDataDao = this.C;
        }
        return sleepDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public ManualDataDao manualDataDao() {
        ManualDataDao manualDataDao;
        if (this.L != null) {
            return this.L;
        }
        synchronized (this) {
            if (this.L == null) {
                this.L = new ManualDataDao_Impl(this);
            }
            manualDataDao = this.L;
        }
        return manualDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public RawAccelerometerDao rawAccelerometerDao() {
        RawAccelerometerDao rawAccelerometerDao;
        if (this.N != null) {
            return this.N;
        }
        synchronized (this) {
            if (this.N == null) {
                this.N = new RawAccelerometerDao_Impl(this);
            }
            rawAccelerometerDao = this.N;
        }
        return rawAccelerometerDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public RawPPGDao rawPPGDao() {
        RawPPGDao rawPPGDao;
        if (this.M != null) {
            return this.M;
        }
        synchronized (this) {
            if (this.M == null) {
                this.M = new RawPPGDao_Impl(this);
            }
            rawPPGDao = this.M;
        }
        return rawPPGDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public RrDataDao rrDataDao() {
        RrDataDao rrDataDao;
        if (this.I != null) {
            return this.I;
        }
        synchronized (this) {
            if (this.I == null) {
                this.I = new RrDataDao_Impl(this);
            }
            rrDataDao = this.I;
        }
        return rrDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public SedentaryDataDao sedentaryDataDao() {
        SedentaryDataDao sedentaryDataDao;
        if (this.P != null) {
            return this.P;
        }
        synchronized (this) {
            if (this.P == null) {
                this.P = new SedentaryDataDao_Impl(this);
            }
            sedentaryDataDao = this.P;
        }
        return sedentaryDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public Spo2Dao spo2Dao() {
        Spo2Dao spo2Dao;
        if (this.Q != null) {
            return this.Q;
        }
        synchronized (this) {
            if (this.Q == null) {
                this.Q = new Spo2Dao_Impl(this);
            }
            spo2Dao = this.Q;
        }
        return spo2Dao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public SensAIDataDao stanceBeamDataDao() {
        SensAIDataDao sensAIDataDao;
        if (this.T != null) {
            return this.T;
        }
        synchronized (this) {
            if (this.T == null) {
                this.T = new SensAIDataDao_Impl(this);
            }
            sensAIDataDao = this.T;
        }
        return sensAIDataDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public StressDao stressDao() {
        StressDao stressDao;
        if (this.O != null) {
            return this.O;
        }
        synchronized (this) {
            if (this.O == null) {
                this.O = new StressDao_Impl(this);
            }
            stressDao = this.O;
        }
        return stressDao;
    }

    @Override // com.coveiot.covedb.CoveAppDatabase
    public TemperatureDao temperatureDao() {
        TemperatureDao temperatureDao;
        if (this.K != null) {
            return this.K;
        }
        synchronized (this) {
            if (this.K == null) {
                this.K = new TemperatureDao_Impl(this);
            }
            temperatureDao = this.K;
        }
        return temperatureDao;
    }
}
