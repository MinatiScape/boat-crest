package com.coveiot.khidodb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa;
import com.coveiot.khidodb.activities.KHIDOHealthActivityV3Doa_Impl;
import com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao;
import com.coveiot.khidodb.heartrate.KHIDOHealthHeartRateDataDao_Impl;
import com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao;
import com.coveiot.khidodb.noise.KHIDOHealthNoiseDataDao_Impl;
import com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao;
import com.coveiot.khidodb.sleep.KHIDOHealthSleepV3DataDao_Impl;
import com.coveiot.khidodb.spo2.KHHealthSpo2DataDao;
import com.coveiot.khidodb.spo2.KHHealthSpo2DataDao_Impl;
import com.coveiot.khidodb.stress.KHHealthPressureDataDao;
import com.coveiot.khidodb.stress.KHHealthPressureDataDao_Impl;
import com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao;
import com.coveiot.khidodb.walk.KHIDOHealthSportV3DataDao_Impl;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class KHIDOAppDatabase_Impl extends KHIDOAppDatabase {
    public volatile KHIDOHealthSportV3DataDao b;
    public volatile KHIDOHealthSleepV3DataDao c;
    public volatile KHIDOHealthHeartRateDataDao d;
    public volatile KHIDOHealthActivityV3Doa e;
    public volatile KHHealthPressureDataDao f;
    public volatile KHHealthSpo2DataDao g;
    public volatile KHIDOHealthNoiseDataDao h;

    /* loaded from: classes8.dex */
    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `health_sport_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `start_time` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hour` INTEGER NOT NULL, `minute` INTEGER NOT NULL, `second` INTEGER NOT NULL, `per_minute` INTEGER NOT NULL, `total_step` INTEGER NOT NULL, `total_rest_activity_calories` INTEGER NOT NULL, `total_distances` INTEGER NOT NULL, `total_active_time` INTEGER NOT NULL, `total_activity_calories` INTEGER NOT NULL, `item_count` INTEGER NOT NULL, `wear_flag_array` TEXT, `type` TEXT, `items` TEXT, `walk_goal_time` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `start_time`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `health_sleep_v3` (`get_up_day` INTEGER NOT NULL, `get_up_month` INTEGER NOT NULL, `get_up_year` INTEGER NOT NULL, `get_up_hour` INTEGER NOT NULL, `get_up_minte` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `data_type` INTEGER NOT NULL, `fall_asleep_year` INTEGER NOT NULL, `fall_asleep_month` INTEGER NOT NULL, `fall_asleep_day` INTEGER NOT NULL, `fall_asleep_hour` INTEGER NOT NULL, `fall_asleep_minte` INTEGER NOT NULL, `total_sleep_time_mins` INTEGER NOT NULL, `wake_mins` INTEGER NOT NULL, `light_mins` INTEGER NOT NULL, `rem_mins` INTEGER NOT NULL, `deep_mins` INTEGER NOT NULL, `wake_count` INTEGER NOT NULL, `light_count` INTEGER NOT NULL, `rem_count` INTEGER NOT NULL, `deep_count` INTEGER NOT NULL, `awrr_status` INTEGER NOT NULL, `sleep_score` INTEGER NOT NULL, `breath_quality` INTEGER NOT NULL, `items` TEXT, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`get_up_year`, `get_up_month`, `get_up_day`, `get_up_hour`, `get_up_minte`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `health_heartrate_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hrInterval` TEXT, `items` TEXT, `hr_data` TEXT, `silentHR` INTEGER NOT NULL, `dataId` INTEGER, `dId` INTEGER NOT NULL, `date` TEXT, `five_min_max_data` INTEGER NOT NULL, `five_min_min_data` INTEGER NOT NULL, `five_min_avg_data` INTEGER NOT NULL, `five_min_data` TEXT, `hr_data_count` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `startTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_activity_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `hour` INTEGER NOT NULL, `minute` INTEGER NOT NULL, `second` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hr_data_interval_minute` INTEGER NOT NULL, `type` INTEGER NOT NULL, `step` INTEGER NOT NULL, `durations` INTEGER NOT NULL, `calories` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `avg_hr_value` INTEGER NOT NULL, `max_hr_value` INTEGER NOT NULL, `min_hr_value` INTEGER NOT NULL, `warmUpMins` INTEGER NOT NULL, `warm_up_time` INTEGER NOT NULL, `burn_fat_mins` INTEGER NOT NULL, `fat_burning_time` INTEGER NOT NULL, `aerobic_mins` INTEGER NOT NULL, `aerobic_exercise_time` INTEGER NOT NULL, `anaerobicMins` INTEGER NOT NULL, `anaerobic_exercise_time` INTEGER NOT NULL, `limit_mins` INTEGER NOT NULL, `extreme_exercise_time` INTEGER NOT NULL, `hr_data_vlaue` TEXT, `items` TEXT, `fast_km_speed` INTEGER NOT NULL, `items_km_speed` TEXT, `frequency_items` TEXT, `items_mi_speed` TEXT, `km_speed` INTEGER NOT NULL, `avg_speed` INTEGER NOT NULL, `max_speed` INTEGER NOT NULL, `avg_step_frequency` INTEGER NOT NULL, `max_step_frequency` INTEGER NOT NULL, `avg_step_stride` INTEGER NOT NULL, `max_step_stride` INTEGER NOT NULL, `sport_start_type` INTEGER NOT NULL, `connect_app` INTEGER NOT NULL, `avg_pace_speed` INTEGER NOT NULL, `fast_pace_speed` INTEGER NOT NULL, `training_effect` INTEGER NOT NULL, `vO2max` INTEGER NOT NULL, `recovery_time_year` INTEGER NOT NULL, `recovery_time_mon` INTEGER NOT NULL, `recovery_time_day` INTEGER NOT NULL, `recovery_time_hour` INTEGER NOT NULL, `recovery_time_min` INTEGER NOT NULL, `recovery_time_s` INTEGER NOT NULL, `pace_speed_items` TEXT, `paddle_number_items` TEXT, `paddle_frequency_items` TEXT, `tread_frequency_items` TEXT, `paddle_number_count` INTEGER NOT NULL, `paddle_frequency_count` INTEGER NOT NULL, `tread_frequency_count` INTEGER NOT NULL, `end_month` INTEGER NOT NULL, `end_day` INTEGER NOT NULL, `end_hour` INTEGER NOT NULL, `end_minute` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `hour`, `minute`, `second`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_health_pressure` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `date` TEXT, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, `items` TEXT, PRIMARY KEY(`day`, `month`, `year`, `startTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_health_spo2` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `date` TEXT, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, `items` TEXT, PRIMARY KEY(`day`, `month`, `year`, `startTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `entity_swim_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `hour` INTEGER NOT NULL, `minute` INTEGER NOT NULL, `second` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `type` INTEGER NOT NULL, `durations` INTEGER NOT NULL, `calories` INTEGER NOT NULL, `distance` INTEGER NOT NULL, `trips` INTEGER NOT NULL, `averageSWOLF` INTEGER NOT NULL, `totalStrokesNumber` INTEGER NOT NULL, `swimmingPosture` INTEGER NOT NULL, `poolDistance` INTEGER NOT NULL, `confirmDistance` INTEGER NOT NULL, `items` TEXT, `avg_speed` INTEGER NOT NULL, `avg_frequency` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `hour`, `minute`, `second`, `macAddress`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `health_noise_v3` (`day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `macAddress` TEXT NOT NULL, `hour` INTEGER NOT NULL, `minute` INTEGER NOT NULL, `second` INTEGER NOT NULL, `avgNoise` INTEGER NOT NULL, `maxNoise` INTEGER NOT NULL, `minNoise` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `items` TEXT, `timestamp` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`year`, `month`, `day`, `startTime`, `macAddress`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '10cb576087150116e33d2aca06786055')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `health_sport_v3`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `health_sleep_v3`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `health_heartrate_v3`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_activity_v3`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_health_pressure`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_health_spo2`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `entity_swim_v3`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `health_noise_v3`");
            if (KHIDOAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHIDOAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHIDOAppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (KHIDOAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHIDOAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHIDOAppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            KHIDOAppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            KHIDOAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (KHIDOAppDatabase_Impl.this.mCallbacks != null) {
                int size = KHIDOAppDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) KHIDOAppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
            HashMap hashMap = new HashMap(21);
            hashMap.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 3, null, 1));
            hashMap.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap.put("year", new TableInfo.Column("year", "INTEGER", true, 1, null, 1));
            hashMap.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 4, null, 1));
            hashMap.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 5, null, 1));
            hashMap.put(WeatherCriteria.UNIT_TYPE_HOUR, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_HOUR, "INTEGER", true, 0, null, 1));
            hashMap.put("minute", new TableInfo.Column("minute", "INTEGER", true, 0, null, 1));
            hashMap.put("second", new TableInfo.Column("second", "INTEGER", true, 0, null, 1));
            hashMap.put("per_minute", new TableInfo.Column("per_minute", "INTEGER", true, 0, null, 1));
            hashMap.put("total_step", new TableInfo.Column("total_step", "INTEGER", true, 0, null, 1));
            hashMap.put("total_rest_activity_calories", new TableInfo.Column("total_rest_activity_calories", "INTEGER", true, 0, null, 1));
            hashMap.put("total_distances", new TableInfo.Column("total_distances", "INTEGER", true, 0, null, 1));
            hashMap.put("total_active_time", new TableInfo.Column("total_active_time", "INTEGER", true, 0, null, 1));
            hashMap.put("total_activity_calories", new TableInfo.Column("total_activity_calories", "INTEGER", true, 0, null, 1));
            hashMap.put("item_count", new TableInfo.Column("item_count", "INTEGER", true, 0, null, 1));
            hashMap.put("wear_flag_array", new TableInfo.Column("wear_flag_array", "TEXT", false, 0, null, 1));
            hashMap.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, 1));
            hashMap.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap.put("walk_goal_time", new TableInfo.Column("walk_goal_time", "INTEGER", true, 0, null, 1));
            hashMap.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("health_sport_v3", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "health_sport_v3");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "health_sport_v3(com.coveiot.khidodb.walk.EntityHealthSportV3).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(27);
            hashMap2.put("get_up_day", new TableInfo.Column("get_up_day", "INTEGER", true, 3, null, 1));
            hashMap2.put("get_up_month", new TableInfo.Column("get_up_month", "INTEGER", true, 2, null, 1));
            hashMap2.put("get_up_year", new TableInfo.Column("get_up_year", "INTEGER", true, 1, null, 1));
            hashMap2.put("get_up_hour", new TableInfo.Column("get_up_hour", "INTEGER", true, 4, null, 1));
            hashMap2.put("get_up_minte", new TableInfo.Column("get_up_minte", "INTEGER", true, 5, null, 1));
            hashMap2.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 6, null, 1));
            hashMap2.put("data_type", new TableInfo.Column("data_type", "INTEGER", true, 0, null, 1));
            hashMap2.put("fall_asleep_year", new TableInfo.Column("fall_asleep_year", "INTEGER", true, 0, null, 1));
            hashMap2.put("fall_asleep_month", new TableInfo.Column("fall_asleep_month", "INTEGER", true, 0, null, 1));
            hashMap2.put("fall_asleep_day", new TableInfo.Column("fall_asleep_day", "INTEGER", true, 0, null, 1));
            hashMap2.put("fall_asleep_hour", new TableInfo.Column("fall_asleep_hour", "INTEGER", true, 0, null, 1));
            hashMap2.put("fall_asleep_minte", new TableInfo.Column("fall_asleep_minte", "INTEGER", true, 0, null, 1));
            hashMap2.put("total_sleep_time_mins", new TableInfo.Column("total_sleep_time_mins", "INTEGER", true, 0, null, 1));
            hashMap2.put("wake_mins", new TableInfo.Column("wake_mins", "INTEGER", true, 0, null, 1));
            hashMap2.put("light_mins", new TableInfo.Column("light_mins", "INTEGER", true, 0, null, 1));
            hashMap2.put("rem_mins", new TableInfo.Column("rem_mins", "INTEGER", true, 0, null, 1));
            hashMap2.put("deep_mins", new TableInfo.Column("deep_mins", "INTEGER", true, 0, null, 1));
            hashMap2.put("wake_count", new TableInfo.Column("wake_count", "INTEGER", true, 0, null, 1));
            hashMap2.put("light_count", new TableInfo.Column("light_count", "INTEGER", true, 0, null, 1));
            hashMap2.put("rem_count", new TableInfo.Column("rem_count", "INTEGER", true, 0, null, 1));
            hashMap2.put("deep_count", new TableInfo.Column("deep_count", "INTEGER", true, 0, null, 1));
            hashMap2.put("awrr_status", new TableInfo.Column("awrr_status", "INTEGER", true, 0, null, 1));
            hashMap2.put("sleep_score", new TableInfo.Column("sleep_score", "INTEGER", true, 0, null, 1));
            hashMap2.put("breath_quality", new TableInfo.Column("breath_quality", "INTEGER", true, 0, null, 1));
            hashMap2.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap2.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("health_sleep_v3", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "health_sleep_v3");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "health_sleep_v3(com.coveiot.khidodb.sleep.EntityHealthSleepV3).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(19);
            hashMap3.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 3, null, 1));
            hashMap3.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap3.put("year", new TableInfo.Column("year", "INTEGER", true, 1, null, 1));
            hashMap3.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 4, null, 1));
            hashMap3.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 5, null, 1));
            hashMap3.put("hrInterval", new TableInfo.Column("hrInterval", "TEXT", false, 0, null, 1));
            hashMap3.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap3.put("hr_data", new TableInfo.Column("hr_data", "TEXT", false, 0, null, 1));
            hashMap3.put("silentHR", new TableInfo.Column("silentHR", "INTEGER", true, 0, null, 1));
            hashMap3.put("dataId", new TableInfo.Column("dataId", "INTEGER", false, 0, null, 1));
            hashMap3.put("dId", new TableInfo.Column("dId", "INTEGER", true, 0, null, 1));
            hashMap3.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap3.put("five_min_max_data", new TableInfo.Column("five_min_max_data", "INTEGER", true, 0, null, 1));
            hashMap3.put("five_min_min_data", new TableInfo.Column("five_min_min_data", "INTEGER", true, 0, null, 1));
            hashMap3.put("five_min_avg_data", new TableInfo.Column("five_min_avg_data", "INTEGER", true, 0, null, 1));
            hashMap3.put("five_min_data", new TableInfo.Column("five_min_data", "TEXT", false, 0, null, 1));
            hashMap3.put("hr_data_count", new TableInfo.Column("hr_data_count", "INTEGER", true, 0, null, 1));
            hashMap3.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap3.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("health_heartrate_v3", hashMap3, new HashSet(0), new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "health_heartrate_v3");
            if (!tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(false, "health_heartrate_v3(com.coveiot.khidodb.heartrate.EntityHealthHeartRateSecond).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
            }
            HashMap hashMap4 = new HashMap(64);
            hashMap4.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 3, null, 1));
            hashMap4.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap4.put("year", new TableInfo.Column("year", "INTEGER", true, 1, null, 1));
            hashMap4.put(WeatherCriteria.UNIT_TYPE_HOUR, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_HOUR, "INTEGER", true, 4, null, 1));
            hashMap4.put("minute", new TableInfo.Column("minute", "INTEGER", true, 5, null, 1));
            hashMap4.put("second", new TableInfo.Column("second", "INTEGER", true, 6, null, 1));
            hashMap4.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 7, null, 1));
            hashMap4.put("hr_data_interval_minute", new TableInfo.Column("hr_data_interval_minute", "INTEGER", true, 0, null, 1));
            hashMap4.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
            hashMap4.put(DeviceKey.Step, new TableInfo.Column(DeviceKey.Step, "INTEGER", true, 0, null, 1));
            hashMap4.put("durations", new TableInfo.Column("durations", "INTEGER", true, 0, null, 1));
            hashMap4.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, 1));
            hashMap4.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap4.put("avg_hr_value", new TableInfo.Column("avg_hr_value", "INTEGER", true, 0, null, 1));
            hashMap4.put("max_hr_value", new TableInfo.Column("max_hr_value", "INTEGER", true, 0, null, 1));
            hashMap4.put("min_hr_value", new TableInfo.Column("min_hr_value", "INTEGER", true, 0, null, 1));
            hashMap4.put("warmUpMins", new TableInfo.Column("warmUpMins", "INTEGER", true, 0, null, 1));
            hashMap4.put("warm_up_time", new TableInfo.Column("warm_up_time", "INTEGER", true, 0, null, 1));
            hashMap4.put("burn_fat_mins", new TableInfo.Column("burn_fat_mins", "INTEGER", true, 0, null, 1));
            hashMap4.put("fat_burning_time", new TableInfo.Column("fat_burning_time", "INTEGER", true, 0, null, 1));
            hashMap4.put("aerobic_mins", new TableInfo.Column("aerobic_mins", "INTEGER", true, 0, null, 1));
            hashMap4.put("aerobic_exercise_time", new TableInfo.Column("aerobic_exercise_time", "INTEGER", true, 0, null, 1));
            hashMap4.put("anaerobicMins", new TableInfo.Column("anaerobicMins", "INTEGER", true, 0, null, 1));
            hashMap4.put("anaerobic_exercise_time", new TableInfo.Column("anaerobic_exercise_time", "INTEGER", true, 0, null, 1));
            hashMap4.put("limit_mins", new TableInfo.Column("limit_mins", "INTEGER", true, 0, null, 1));
            hashMap4.put("extreme_exercise_time", new TableInfo.Column("extreme_exercise_time", "INTEGER", true, 0, null, 1));
            hashMap4.put("hr_data_vlaue", new TableInfo.Column("hr_data_vlaue", "TEXT", false, 0, null, 1));
            hashMap4.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap4.put("fast_km_speed", new TableInfo.Column("fast_km_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("items_km_speed", new TableInfo.Column("items_km_speed", "TEXT", false, 0, null, 1));
            hashMap4.put("frequency_items", new TableInfo.Column("frequency_items", "TEXT", false, 0, null, 1));
            hashMap4.put("items_mi_speed", new TableInfo.Column("items_mi_speed", "TEXT", false, 0, null, 1));
            hashMap4.put("km_speed", new TableInfo.Column("km_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("avg_speed", new TableInfo.Column("avg_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("max_speed", new TableInfo.Column("max_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("avg_step_frequency", new TableInfo.Column("avg_step_frequency", "INTEGER", true, 0, null, 1));
            hashMap4.put("max_step_frequency", new TableInfo.Column("max_step_frequency", "INTEGER", true, 0, null, 1));
            hashMap4.put("avg_step_stride", new TableInfo.Column("avg_step_stride", "INTEGER", true, 0, null, 1));
            hashMap4.put("max_step_stride", new TableInfo.Column("max_step_stride", "INTEGER", true, 0, null, 1));
            hashMap4.put("sport_start_type", new TableInfo.Column("sport_start_type", "INTEGER", true, 0, null, 1));
            hashMap4.put("connect_app", new TableInfo.Column("connect_app", "INTEGER", true, 0, null, 1));
            hashMap4.put("avg_pace_speed", new TableInfo.Column("avg_pace_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("fast_pace_speed", new TableInfo.Column("fast_pace_speed", "INTEGER", true, 0, null, 1));
            hashMap4.put("training_effect", new TableInfo.Column("training_effect", "INTEGER", true, 0, null, 1));
            hashMap4.put("vO2max", new TableInfo.Column("vO2max", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_year", new TableInfo.Column("recovery_time_year", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_mon", new TableInfo.Column("recovery_time_mon", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_day", new TableInfo.Column("recovery_time_day", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_hour", new TableInfo.Column("recovery_time_hour", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_min", new TableInfo.Column("recovery_time_min", "INTEGER", true, 0, null, 1));
            hashMap4.put("recovery_time_s", new TableInfo.Column("recovery_time_s", "INTEGER", true, 0, null, 1));
            hashMap4.put("pace_speed_items", new TableInfo.Column("pace_speed_items", "TEXT", false, 0, null, 1));
            hashMap4.put("paddle_number_items", new TableInfo.Column("paddle_number_items", "TEXT", false, 0, null, 1));
            hashMap4.put("paddle_frequency_items", new TableInfo.Column("paddle_frequency_items", "TEXT", false, 0, null, 1));
            hashMap4.put("tread_frequency_items", new TableInfo.Column("tread_frequency_items", "TEXT", false, 0, null, 1));
            hashMap4.put("paddle_number_count", new TableInfo.Column("paddle_number_count", "INTEGER", true, 0, null, 1));
            hashMap4.put("paddle_frequency_count", new TableInfo.Column("paddle_frequency_count", "INTEGER", true, 0, null, 1));
            hashMap4.put("tread_frequency_count", new TableInfo.Column("tread_frequency_count", "INTEGER", true, 0, null, 1));
            hashMap4.put("end_month", new TableInfo.Column("end_month", "INTEGER", true, 0, null, 1));
            hashMap4.put("end_day", new TableInfo.Column("end_day", "INTEGER", true, 0, null, 1));
            hashMap4.put("end_hour", new TableInfo.Column("end_hour", "INTEGER", true, 0, null, 1));
            hashMap4.put("end_minute", new TableInfo.Column("end_minute", "INTEGER", true, 0, null, 1));
            hashMap4.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo4 = new TableInfo("entity_activity_v3", hashMap4, new HashSet(0), new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "entity_activity_v3");
            if (!tableInfo4.equals(read4)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_activity_v3(com.coveiot.khidodb.activities.EntityHealthActivityV3).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
            HashMap hashMap5 = new HashMap(9);
            hashMap5.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 1, null, 1));
            hashMap5.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap5.put("year", new TableInfo.Column("year", "INTEGER", true, 3, null, 1));
            hashMap5.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 4, null, 1));
            hashMap5.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 5, null, 1));
            hashMap5.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap5.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap5.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap5.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            TableInfo tableInfo5 = new TableInfo("entity_health_pressure", hashMap5, new HashSet(0), new HashSet(0));
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "entity_health_pressure");
            if (!tableInfo5.equals(read5)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_health_pressure(com.coveiot.khidodb.stress.EntityHealthPressure).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
            HashMap hashMap6 = new HashMap(9);
            hashMap6.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 1, null, 1));
            hashMap6.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap6.put("year", new TableInfo.Column("year", "INTEGER", true, 3, null, 1));
            hashMap6.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 4, null, 1));
            hashMap6.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 5, null, 1));
            hashMap6.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, 1));
            hashMap6.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap6.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            hashMap6.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            TableInfo tableInfo6 = new TableInfo("entity_health_spo2", hashMap6, new HashSet(0), new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "entity_health_spo2");
            if (!tableInfo6.equals(read6)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_health_spo2(com.coveiot.khidodb.spo2.EntityHealthSpo2).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
            }
            HashMap hashMap7 = new HashMap(22);
            hashMap7.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 3, null, 1));
            hashMap7.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap7.put("year", new TableInfo.Column("year", "INTEGER", true, 1, null, 1));
            hashMap7.put(WeatherCriteria.UNIT_TYPE_HOUR, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_HOUR, "INTEGER", true, 4, null, 1));
            hashMap7.put("minute", new TableInfo.Column("minute", "INTEGER", true, 5, null, 1));
            hashMap7.put("second", new TableInfo.Column("second", "INTEGER", true, 6, null, 1));
            hashMap7.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 7, null, 1));
            hashMap7.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
            hashMap7.put("durations", new TableInfo.Column("durations", "INTEGER", true, 0, null, 1));
            hashMap7.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, 1));
            hashMap7.put("distance", new TableInfo.Column("distance", "INTEGER", true, 0, null, 1));
            hashMap7.put("trips", new TableInfo.Column("trips", "INTEGER", true, 0, null, 1));
            hashMap7.put("averageSWOLF", new TableInfo.Column("averageSWOLF", "INTEGER", true, 0, null, 1));
            hashMap7.put("totalStrokesNumber", new TableInfo.Column("totalStrokesNumber", "INTEGER", true, 0, null, 1));
            hashMap7.put("swimmingPosture", new TableInfo.Column("swimmingPosture", "INTEGER", true, 0, null, 1));
            hashMap7.put("poolDistance", new TableInfo.Column("poolDistance", "INTEGER", true, 0, null, 1));
            hashMap7.put("confirmDistance", new TableInfo.Column("confirmDistance", "INTEGER", true, 0, null, 1));
            hashMap7.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap7.put("avg_speed", new TableInfo.Column("avg_speed", "INTEGER", true, 0, null, 1));
            hashMap7.put("avg_frequency", new TableInfo.Column("avg_frequency", "INTEGER", true, 0, null, 1));
            hashMap7.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap7.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo7 = new TableInfo("entity_swim_v3", hashMap7, new HashSet(0), new HashSet(0));
            TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "entity_swim_v3");
            if (!tableInfo7.equals(read7)) {
                return new RoomOpenHelper.ValidationResult(false, "entity_swim_v3(com.coveiot.khidodb.activities.EntityHealthSwimV3).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
            }
            HashMap hashMap8 = new HashMap(15);
            hashMap8.put(WeatherCriteria.UNIT_TYPE_DAY, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_DAY, "INTEGER", true, 3, null, 1));
            hashMap8.put("month", new TableInfo.Column("month", "INTEGER", true, 2, null, 1));
            hashMap8.put("year", new TableInfo.Column("year", "INTEGER", true, 1, null, 1));
            hashMap8.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 4, null, 1));
            hashMap8.put(DeviceKey.MacAddress, new TableInfo.Column(DeviceKey.MacAddress, "TEXT", true, 5, null, 1));
            hashMap8.put(WeatherCriteria.UNIT_TYPE_HOUR, new TableInfo.Column(WeatherCriteria.UNIT_TYPE_HOUR, "INTEGER", true, 0, null, 1));
            hashMap8.put("minute", new TableInfo.Column("minute", "INTEGER", true, 0, null, 1));
            hashMap8.put("second", new TableInfo.Column("second", "INTEGER", true, 0, null, 1));
            hashMap8.put("avgNoise", new TableInfo.Column("avgNoise", "INTEGER", true, 0, null, 1));
            hashMap8.put("maxNoise", new TableInfo.Column("maxNoise", "INTEGER", true, 0, null, 1));
            hashMap8.put("minNoise", new TableInfo.Column("minNoise", "INTEGER", true, 0, null, 1));
            hashMap8.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
            hashMap8.put(FirebaseAnalytics.Param.ITEMS, new TableInfo.Column(FirebaseAnalytics.Param.ITEMS, "TEXT", false, 0, null, 1));
            hashMap8.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
            hashMap8.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo8 = new TableInfo("health_noise_v3", hashMap8, new HashSet(0), new HashSet(0));
            TableInfo read8 = TableInfo.read(supportSQLiteDatabase, "health_noise_v3");
            if (!tableInfo8.equals(read8)) {
                return new RoomOpenHelper.ValidationResult(false, "health_noise_v3(com.coveiot.khidodb.noise.EntityHealthNoise).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
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
            writableDatabase.execSQL("DELETE FROM `health_sport_v3`");
            writableDatabase.execSQL("DELETE FROM `health_sleep_v3`");
            writableDatabase.execSQL("DELETE FROM `health_heartrate_v3`");
            writableDatabase.execSQL("DELETE FROM `entity_activity_v3`");
            writableDatabase.execSQL("DELETE FROM `entity_health_pressure`");
            writableDatabase.execSQL("DELETE FROM `entity_health_spo2`");
            writableDatabase.execSQL("DELETE FROM `entity_swim_v3`");
            writableDatabase.execSQL("DELETE FROM `health_noise_v3`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "health_sport_v3", "health_sleep_v3", "health_heartrate_v3", "entity_activity_v3", "entity_health_pressure", "entity_health_spo2", "entity_swim_v3", "health_noise_v3");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(4), "10cb576087150116e33d2aca06786055", "ac39308df440203d77fe86fb621b1ace")).build());
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHIDOHealthActivityV3Doa getActivityV3Data() {
        KHIDOHealthActivityV3Doa kHIDOHealthActivityV3Doa;
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e == null) {
                this.e = new KHIDOHealthActivityV3Doa_Impl(this);
            }
            kHIDOHealthActivityV3Doa = this.e;
        }
        return kHIDOHealthActivityV3Doa;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHIDOHealthHeartRateDataDao getHealthHeartRateDao() {
        KHIDOHealthHeartRateDataDao kHIDOHealthHeartRateDataDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new KHIDOHealthHeartRateDataDao_Impl(this);
            }
            kHIDOHealthHeartRateDataDao = this.d;
        }
        return kHIDOHealthHeartRateDataDao;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHIDOHealthNoiseDataDao getHealthNoiseData() {
        KHIDOHealthNoiseDataDao kHIDOHealthNoiseDataDao;
        if (this.h != null) {
            return this.h;
        }
        synchronized (this) {
            if (this.h == null) {
                this.h = new KHIDOHealthNoiseDataDao_Impl(this);
            }
            kHIDOHealthNoiseDataDao = this.h;
        }
        return kHIDOHealthNoiseDataDao;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHHealthPressureDataDao getHealthPressureData() {
        KHHealthPressureDataDao kHHealthPressureDataDao;
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = new KHHealthPressureDataDao_Impl(this);
            }
            kHHealthPressureDataDao = this.f;
        }
        return kHHealthPressureDataDao;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHIDOHealthSleepV3DataDao getHealthSleepV3Dao() {
        KHIDOHealthSleepV3DataDao kHIDOHealthSleepV3DataDao;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new KHIDOHealthSleepV3DataDao_Impl(this);
            }
            kHIDOHealthSleepV3DataDao = this.c;
        }
        return kHIDOHealthSleepV3DataDao;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHHealthSpo2DataDao getHealthSpo2Data() {
        KHHealthSpo2DataDao kHHealthSpo2DataDao;
        if (this.g != null) {
            return this.g;
        }
        synchronized (this) {
            if (this.g == null) {
                this.g = new KHHealthSpo2DataDao_Impl(this);
            }
            kHHealthSpo2DataDao = this.g;
        }
        return kHHealthSpo2DataDao;
    }

    @Override // com.coveiot.khidodb.KHIDOAppDatabase
    public KHIDOHealthSportV3DataDao getHealthSportV3Dao() {
        KHIDOHealthSportV3DataDao kHIDOHealthSportV3DataDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new KHIDOHealthSportV3DataDao_Impl(this);
            }
            kHIDOHealthSportV3DataDao = this.b;
        }
        return kHIDOHealthSportV3DataDao;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(KHIDOHealthSportV3DataDao.class, KHIDOHealthSportV3DataDao_Impl.getRequiredConverters());
        hashMap.put(KHIDOHealthSleepV3DataDao.class, KHIDOHealthSleepV3DataDao_Impl.getRequiredConverters());
        hashMap.put(KHIDOHealthHeartRateDataDao.class, KHIDOHealthHeartRateDataDao_Impl.getRequiredConverters());
        hashMap.put(KHIDOHealthActivityV3Doa.class, KHIDOHealthActivityV3Doa_Impl.getRequiredConverters());
        hashMap.put(KHHealthPressureDataDao.class, KHHealthPressureDataDao_Impl.getRequiredConverters());
        hashMap.put(KHHealthSpo2DataDao.class, KHHealthSpo2DataDao_Impl.getRequiredConverters());
        hashMap.put(KHIDOHealthNoiseDataDao.class, KHIDOHealthNoiseDataDao_Impl.getRequiredConverters());
        return hashMap;
    }
}
