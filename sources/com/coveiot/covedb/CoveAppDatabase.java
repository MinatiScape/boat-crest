package com.coveiot.covedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.covedb.accelerometer.RawAccelerometerDao;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import com.coveiot.covedb.ambientsound.AmbientSoundDao;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.covedb.bp.BpDataDao;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.deviceinfo.DeviceInfoDao;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.ecg.ECGDao;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.heartrate.HeartRateDao;
import com.coveiot.covedb.hrv.HRVDao;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.manualdata.dao.ManualDataDao;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.ppg.RawPPGDao;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import com.coveiot.covedb.profile.dao.DaoProfile;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.covedb.rr.RrDataDao;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.sedentary.SedentaryDataDao;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.covedb.sensai.SensAIDataDao;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.sleep.SleepDataDao;
import com.coveiot.covedb.spo2.Spo2Dao;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.stress.StressDao;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.TemperatureDao;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.WalkDataDao;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.maps.style.layers.Property;
@TypeConverters({Convertors.class, ConvertorsMediaList.class, TimestampConverter.class})
@Database(entities = {HourlySleepData.class, HourlyWalkData.class, DailyWalkData.class, DailySleepData.class, EntityDeviceInfo.class, EntityHourlyHeartRateData.class, EntityDailyBp.class, EntityHourlyBp.class, EntityDailyHeartRateData.class, EntityProfile.class, EntityDailyRrData.class, EntityHourlyRrData.class, EntityECGSummaryData.class, EntityManualData.class, DailyTemperature.class, HourlyTemperature.class, EntityRawPPGData.class, EntityRawAccelerometerData.class, EntitySedentary.class, DailyStress.class, HourlyStress.class, EntityHourlySpo2.class, DailyPeriodicSpo2.class, DailyHRV.class, HourlyHRV.class, EntityDailyAmbientSoundData.class, EntityHourlyAmbientSoundData.class, SensAIActivitySummary.class, SensAIActivitySummaryDetails.class}, exportSchema = false, version = 29)
/* loaded from: classes8.dex */
public abstract class CoveAppDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static CoveAppDatabase f6933a = null;
    public static final String covedatabase = "covedatabase";
    public static final Migration b = new k(6, 7);
    public static final Migration c = new t(7, 8);
    public static final Migration d = new u(8, 9);
    public static final Migration e = new v(9, 10);
    public static final Migration f = new w(10, 11);
    public static final Migration g = new x(11, 12);
    public static final Migration h = new y(12, 13);
    public static final Migration i = new z(13, 14);
    public static final Migration j = new a0(14, 15);
    public static final Migration k = new a(15, 16);
    public static final Migration l = new b(17, 18);
    public static final Migration m = new c(12, 16);
    public static final Migration n = new d(16, 17);
    public static final Migration o = new e(12, 17);
    public static final Migration p = new f(12, 18);
    public static final Migration q = new g(18, 19);
    public static final Migration r = new h(19, 20);
    public static final Migration s = new i(20, 21);
    public static final Migration t = new j(21, 22);
    public static final Migration u = new l(22, 23);
    public static final Migration v = new m(23, 24);
    public static final Migration w = new n(24, 25);
    public static final Migration x = new o(25, 26);
    public static final Migration y = new p(26, 27);
    public static final Migration z = new q(27, 28);
    public static final Migration A = new r(28, 29);
    public static final Migration B = new s(11, 29);

    /* loaded from: classes8.dex */
    public static class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class a0 extends Migration {
        public a0(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` REAL NOT NULL, `stress_low` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class b extends Migration {
        public b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sedentary_data' ADD COLUMN 'is_saved_server' INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sedentary_data' ADD COLUMN 'date' TEXT DEFAULT NULL ");
        }
    }

    /* loaded from: classes8.dex */
    public static class c extends Migration {
        public c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_raw_ppg", "");
                    contentValues.put("last_sync_date_spo2", "");
                    contentValues.put("last_sync_date_accelerometer", "");
                    contentValues.put("last_sync_date_sedentary", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class d extends Migration {
        public d(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, `last_sync_date_periodic_spo2` TEXT, `last_server_sync_date_periodic_spo2` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_raw_ppg", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_raw_ppg"))));
                    contentValues.put("last_sync_date_spo2", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_spo2"))));
                    contentValues.put("last_sync_date_accelerometer", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_accelerometer"))));
                    contentValues.put("last_sync_date_sedentary", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_sedentary"))));
                    contentValues.put("last_sync_date_periodic_spo2", "");
                    contentValues.put("last_server_sync_date_periodic_spo2", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class e extends Migration {
        public e(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, `last_sync_date_periodic_spo2` TEXT, `last_server_sync_date_periodic_spo2` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_raw_ppg", "");
                    contentValues.put("last_sync_date_spo2", "");
                    contentValues.put("last_sync_date_accelerometer", "");
                    contentValues.put("last_sync_date_sedentary", "");
                    contentValues.put("last_sync_date_periodic_spo2", "");
                    contentValues.put("last_server_sync_date_periodic_spo2", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL,  PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL,  PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class f extends Migration {
        public f(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, `last_sync_date_periodic_spo2` TEXT, `last_server_sync_date_periodic_spo2` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_raw_ppg", "");
                    contentValues.put("last_sync_date_spo2", "");
                    contentValues.put("last_sync_date_accelerometer", "");
                    contentValues.put("last_sync_date_sedentary", "");
                    contentValues.put("last_sync_date_periodic_spo2", "");
                    contentValues.put("last_server_sync_date_periodic_spo2", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `date` TEXT   , `is_saved_server` INTEGER  , PRIMARY KEY(`serial_number`, `timestamp`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_stress` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_stress` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `date` TEXT   , `is_saved_server` INTEGER  , PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class g extends Migration {
        public g(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'daily_stress' ADD COLUMN 'baseline' REAL NOT NULL DEFAULT 0.0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'daily_stress' ADD COLUMN 'readiness' REAL NOT NULL DEFAULT 0.0");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_hrv` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `baseline` REAL NOT NULL, `readiness` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_hrv` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class h extends Migration {
        public h(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'daily_stress' ADD COLUMN 'isfeedbackgiven' INTEGER NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes8.dex */
    public static class i extends Migration {
        public i(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'daily_stress' ADD COLUMN 'answered_questions' TEXT  DEFAULT NULL");
        }
    }

    /* loaded from: classes8.dex */
    public static class j extends Migration {
        public j(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'sleepScore' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'maxHr' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'minHr' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'minStress' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'maxStress' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'isMinMaxUpdated' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailywalkdata' ADD COLUMN 'active_time' INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN 'active_time' INTEGER  DEFAULT NULL");
        }
    }

    /* loaded from: classes8.dex */
    public static class k extends Migration {
        public k(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` INTEGER NOT NULL, `temperature` INTEGER NOT NULL, `isSyncedWithServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `source`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class l extends Migration {
        public l(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_ambient_sound_table` (`serial_no` TEXT NOT NULL, `date` TEXT NOT NULL, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_ambient_sound_table` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `coded_values` TEXT, PRIMARY KEY(`serial_no`, `start_time`, `date`))");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN 'last_sync_date_ambient_sound' TEXT  DEFAULT NULL");
        }
    }

    /* loaded from: classes8.dex */
    public static class m extends Migration {
        public m(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'minAmbientSound' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'maxAmbientSound' INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN 'breathQuality' INTEGER  NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes8.dex */
    public static class n extends Migration {
        public n(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'daily_ambient_sound_table' ADD COLUMN 'total_time' INTEGER  NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes8.dex */
    public static class o extends Migration {
        public o(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_batting_summary` (`session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `duration_sec` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `hand` INTEGER NOT NULL, `goal_type` INTEGER NOT NULL, `target_goal_value` INTEGER NOT NULL, `goal_completion_pct` INTEGER NOT NULL,  `max_hr` INTEGER NOT NULL,  `avg_hr` INTEGER NOT NULL,  `total_swings` INTEGER NOT NULL,  `played` INTEGER NOT NULL,  `hit_pct` INTEGER NOT NULL,  `max_hand_speed` INTEGER NOT NULL,  `avg_hand_speed` INTEGER NOT NULL, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_bowling_summary` (`session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `duration_sec` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `hand` INTEGER NOT NULL, `goal_type` INTEGER NOT NULL, `target_goal_value` INTEGER NOT NULL, `goal_completion_pct` INTEGER NOT NULL,  `max_hr` INTEGER NOT NULL,  `avg_hr` INTEGER NOT NULL,  `bowling_type` INTEGER NOT NULL,  `total_bowls_allowed` INTEGER NOT NULL,  `max_arm_speed` INTEGER NOT NULL,  `min_arm_speed` INTEGER NOT NULL,  `avg_arm_speed` INTEGER NOT NULL, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_batting_summary_details` ( `session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `details_data_num` INTEGER NOT NULL, `unix_time_stamp` INTEGER NOT NULL, `hand_speed` TEXT , `hr` TEXT , `steps` TEXT , `distance` TEXT , `calories` TEXT ,  `hit_miss` TEXT , PRIMARY KEY( `session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_bowling_summary_details` ( `session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `details_data_num` INTEGER NOT NULL, `unix_time_stamp` INTEGER NOT NULL, `arm_speed` TEXT , `hr` TEXT , `steps` TEXT , `distance` TEXT , `calories` TEXT , PRIMARY KEY( `session_id`, `serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class p extends Migration {
        public p(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_sens_ai_summary` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_batting_summary`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_bowling_summary`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary` (`session_id` TEXT NOT NULL, `client_ref_id` TEXT, `serial_no` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `activity_type` INTEGER NOT NULL, `duration_sec` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `hand` INTEGER NOT NULL, `goal_type` INTEGER NOT NULL, `target_goal_value` INTEGER NOT NULL, `goal_completion_pct` INTEGER NOT NULL,  `max_hr` INTEGER NOT NULL,  `avg_hr` INTEGER NOT NULL,  `total_swings` INTEGER NOT NULL DEFAULT 0,  `played` INTEGER NOT NULL DEFAULT 0,  `hit_pct` INTEGER NOT NULL DEFAULT 0,  `max_hand_speed` INTEGER NOT NULL DEFAULT 0,  `avg_hand_speed` INTEGER NOT NULL DEFAULT 0,  `bowling_type` INTEGER NOT NULL DEFAULT 0,  `total_balls_bowled` INTEGER NOT NULL DEFAULT 0,  `max_arm_speed` INTEGER NOT NULL DEFAULT 0,  `min_arm_speed` INTEGER NOT NULL DEFAULT 0,  `avg_arm_speed` INTEGER NOT NULL DEFAULT 0,  `is_saved_server` INTEGER NOT NULL DEFAULT 0,  `is_add_to_compare` INTEGER NOT NULL DEFAULT 0,  `is_data_aggregate_saved` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_batting_summary_details`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_bowling_summary_details`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary_details` ( `session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `details_data_num` INTEGER NOT NULL, `unix_time_stamp` INTEGER , `hand_speed` TEXT , `hr` TEXT , `steps` TEXT , `distance` TEXT , `calories` TEXT ,  `hit_miss` TEXT DEFAULT NULL, PRIMARY KEY( `session_id`, `serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class q extends Migration {
        public q(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_distance_data` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_calorie_data` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN `distance_code_value` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN `calorie_code_value` TEXT DEFAULT NULL");
        }
    }

    /* loaded from: classes8.dex */
    public static class r extends Migration {
        public r(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'sensai_activity_summary_details' ADD COLUMN 'is_feedback_saved' INTEGER NOT NULL DEFAULT 0");
        }
    }

    /* loaded from: classes8.dex */
    public static class s extends Migration {
        public s(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'manual_data' ADD COLUMN `hrv` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'manual_data' ADD COLUMN `stress` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'manual_data' ADD COLUMN `vascularAging` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_spo2` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_raw_ppg` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_accelerometer` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_sedentary` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_sens_ai_summary` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_distance_data` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_calorie_data` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_periodic_spo2` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_server_sync_date_periodic_spo2` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'device_info' ADD COLUMN `last_sync_date_ambient_sound` TEXT  DEFAULT NULL");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawPPGDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `ppgDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `ppgType` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `movementLevel` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `rawAccelerometerDataHistory` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL, `accelerometerDataFilePath` TEXT, `samplingRate` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `is_sync_to_server` INTEGER NOT NULL, PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS daily_stress (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL,`baseline` REAL NOT NULL DEFAULT 0.0,`readiness` REAL NOT NULL DEFAULT 0.0,`isfeedbackgiven` INTEGER NOT NULL DEFAULT 0,`answered_questions` TEXT  DEFAULT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS hourly_stress (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `stress_avg` REAL NOT NULL, `stress_high` INTEGER NOT NULL, `stress_low` INTEGER NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sedentary_data` (`timestamp` INTEGER NOT NULL, `serial_number` TEXT NOT NULL, `recordNumber` INTEGER NOT NULL,`is_saved_server` INTEGER NOT NULL DEFAULT 0,`date` TEXT DEFAULT NULL , PRIMARY KEY(`serial_number`, `timestamp`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_spo2` (`date` TEXT NOT NULL,  `serial_no` TEXT NOT NULL, `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL,  `spo2_low` INTEGER NOT NULL, `is_sync_server` INTEGER NOT NULL , PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_spo2` (`id` TEXT NOT NULL, `date` TEXT , `serial_no` TEXT NOT NULL, `start_time` TEXT ,`end_time` TEXT , `spo2_avg` REAL NOT NULL, `spo2_high` INTEGER NOT NULL, `spo2_low` INTEGER NOT NULL, `codevalue` TEXT ,  PRIMARY KEY(`id`,`serial_no`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_hrv` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `baseline` REAL NOT NULL, `readiness` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_hrv` (`id` TEXT NOT NULL, `date` TEXT, `start_time` TEXT, `end_time` TEXT, `hrv_avg` REAL NOT NULL, `hrv_high` REAL NOT NULL, `hrv_low` REAL NOT NULL, `codevalue` TEXT, `serial_no` TEXT NOT NULL, PRIMARY KEY(`id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `sleepScore` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `maxHr` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `minHr` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `minStress` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `maxStress` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `isMinMaxUpdated` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `minAmbientSound` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `maxAmbientSound` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailysleepdata' ADD COLUMN `breathQuality` INTEGER  NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'dailywalkdata' ADD COLUMN `active_time` INTEGER DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN `active_time` INTEGER  DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN `distance_code_value` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'hourlywalkdata' ADD COLUMN `calorie_code_value` TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `daily_ambient_sound_table` (`serial_no` TEXT NOT NULL, `date` TEXT NOT NULL, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `is_sync_server` INTEGER NOT NULL,`total_time` INTEGER  NOT NULL DEFAULT 0, PRIMARY KEY(`serial_no`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `hourly_ambient_sound_table` (`date` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT, `min_ambient_sound` INTEGER NOT NULL, `max_ambient_sound` INTEGER NOT NULL, `avg_ambient_sound` REAL NOT NULL, `coded_values` TEXT, PRIMARY KEY(`serial_no`, `start_time`, `date`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary` (`session_id` TEXT NOT NULL, `client_ref_id` TEXT, `serial_no` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `activity_type` INTEGER NOT NULL, `duration_sec` INTEGER NOT NULL, `total_steps` INTEGER NOT NULL, `total_calories` REAL NOT NULL, `hand` INTEGER NOT NULL, `goal_type` INTEGER NOT NULL, `target_goal_value` INTEGER NOT NULL, `goal_completion_pct` INTEGER NOT NULL,  `max_hr` INTEGER NOT NULL,  `avg_hr` INTEGER NOT NULL,  `total_swings` INTEGER NOT NULL DEFAULT 0,  `played` INTEGER NOT NULL DEFAULT 0,  `hit_pct` INTEGER NOT NULL DEFAULT 0,  `max_hand_speed` INTEGER NOT NULL DEFAULT 0,  `avg_hand_speed` INTEGER NOT NULL DEFAULT 0,  `bowling_type` INTEGER NOT NULL DEFAULT 0,  `total_balls_bowled` INTEGER NOT NULL DEFAULT 0,  `max_arm_speed` INTEGER NOT NULL DEFAULT 0,  `min_arm_speed` INTEGER NOT NULL DEFAULT 0,  `avg_arm_speed` INTEGER NOT NULL DEFAULT 0,  `is_saved_server` INTEGER NOT NULL DEFAULT 0,  `is_add_to_compare` INTEGER NOT NULL DEFAULT 0,  `is_data_aggregate_saved` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`session_id`, `serial_no`))");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_batting_summary_details`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `sensai_activity_bowling_summary_details`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `sensai_activity_summary_details` ( `session_id` TEXT NOT NULL, `serial_no` TEXT NOT NULL, `activity_type` INTEGER NOT NULL, `details_data_num` INTEGER NOT NULL, `unix_time_stamp` INTEGER , `hand_speed` TEXT , `hr` TEXT , `steps` TEXT , `distance` TEXT , `calories` TEXT ,  `hit_miss` TEXT DEFAULT NULL,  `is_feedback_saved` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY( `session_id`, `serial_no`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class t extends Migration {
        public t(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `manual_data`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` REAL NOT NULL, `temperature` REAL NOT NULL, `isSyncedWithServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `source`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class u extends Migration {
        public u(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `manual_data`");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` REAL NOT NULL, `temperature` REAL NOT NULL, `spo2Level` TEXT, `isLevelInterpretation` INTEGER NOT NULL,`isSyncedWithServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `source`))");
        }
    }

    /* loaded from: classes8.dex */
    public static class v extends Migration {
        public v(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data_temp` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` REAL NOT NULL, `temperature` REAL NOT NULL, `spo2Level` TEXT, `isLevelInterpretation` INTEGER NOT NULL,`isSyncedWithServer` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `isLevelInterpretation`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM manual_data_temp");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("timestamp", Long.valueOf(query.getLong(query.getColumnIndex("timestamp"))));
                    contentValues.put("source", query.getString(query.getColumnIndex("source")));
                    contentValues.put("serial_no", query.getString(query.getColumnIndex("serial_no")));
                    contentValues.put("userDeviceId", Integer.valueOf(query.getInt(query.getColumnIndex("userDeviceId"))));
                    contentValues.put("hr", query.getString(query.getColumnIndex("hr")));
                    contentValues.put("systolicBp", query.getString(query.getColumnIndex("systolicBp")));
                    contentValues.put("diastolicBp", query.getString(query.getColumnIndex("diastolicBp")));
                    contentValues.put("spo2", Long.valueOf(query.getLong(query.getColumnIndex("spo2"))));
                    contentValues.put("spo2Level", Long.valueOf(query.getLong(query.getColumnIndex("spo2Level"))));
                    contentValues.put(DeviceKey.TempData, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.TempData))));
                    contentValues.put("isSyncedWithServer", Integer.valueOf(query.getInt(query.getColumnIndex("isSyncedWithServer"))));
                    contentValues.put("isLevelInterpretation", Integer.valueOf(query.getInt(query.getColumnIndex("isLevelInterpretation"))));
                    supportSQLiteDatabase.insert("manual_data_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE manual_data");
                supportSQLiteDatabase.execSQL("ALTER TABLE manual_data_temp RENAME TO manual_data");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE manual_data");
            supportSQLiteDatabase.execSQL("ALTER TABLE manual_data_temp RENAME TO manual_data");
        }
    }

    /* loaded from: classes8.dex */
    public static class w extends Migration {
        public w(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `profile_temp` (`timestamp` INTEGER NOT NULL, `height` INTEGER NOT NULL, `weight` REAL NOT NULL, `steps_target` INTEGER NOT NULL, `sleep_target` INTEGER NOT NULL, `age` INTEGER NOT NULL, `walkStrideLength` INTEGER NOT NULL, `runStrideLength` INTEGER NOT NULL, `restingHr` INTEGER NOT NULL, `physicalActivityScore` REAL NOT NULL, `updatedToBand` INTEGER NOT NULL, `gender` INTEGER NOT NULL, PRIMARY KEY(`timestamp`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM profile");
            if (query == null || query.getCount() <= 0) {
                return;
            }
            query.moveToFirst();
            do {
                ContentValues contentValues = new ContentValues();
                contentValues.put("timestamp", Long.valueOf(query.getLong(query.getColumnIndex("timestamp"))));
                contentValues.put(Property.ICON_TEXT_FIT_HEIGHT, query.getString(query.getColumnIndex(Property.ICON_TEXT_FIT_HEIGHT)));
                contentValues.put("weight", query.getString(query.getColumnIndex("weight")));
                contentValues.put("steps_target", Integer.valueOf(query.getInt(query.getColumnIndex("steps_target"))));
                contentValues.put("sleep_target", query.getString(query.getColumnIndex("sleep_target")));
                contentValues.put("age", query.getString(query.getColumnIndex("age")));
                contentValues.put("restingHr", query.getString(query.getColumnIndex("restingHr")));
                contentValues.put("physicalActivityScore", Long.valueOf(query.getLong(query.getColumnIndex("physicalActivityScore"))));
                contentValues.put("gender", Long.valueOf(query.getLong(query.getColumnIndex("gender"))));
                contentValues.put("updatedToBand", Long.valueOf(query.getLong(query.getColumnIndex("updatedToBand"))));
                contentValues.put("walkStrideLength", (Integer) (-1));
                contentValues.put("runStrideLength", (Integer) (-1));
                supportSQLiteDatabase.insert("profile_temp", 5, contentValues);
            } while (query.moveToNext());
            supportSQLiteDatabase.execSQL("DROP TABLE profile");
            supportSQLiteDatabase.execSQL("ALTER TABLE profile_temp RENAME TO profile");
        }
    }

    /* loaded from: classes8.dex */
    public static class x extends Migration {
        public x(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `manual_data_temp` (`timeStamp` INTEGER NOT NULL, `source` TEXT NOT NULL, `serial_no` TEXT, `userDeviceId` TEXT, `hr` INTEGER NOT NULL, `systolicBp` INTEGER NOT NULL, `diastolicBp` INTEGER NOT NULL, `spo2` REAL NOT NULL, `temperature` REAL NOT NULL, `isSyncedWithServer` INTEGER NOT NULL, `isLevelInterpretation` INTEGER NOT NULL, `spo2Level` TEXT, `hrv` INTEGER NOT NULL, `stress` INTEGER NOT NULL, `vascularAging` INTEGER NOT NULL, PRIMARY KEY(`timeStamp`, `isLevelInterpretation`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM manual_data");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("timestamp", Long.valueOf(query.getLong(query.getColumnIndex("timestamp"))));
                    contentValues.put("source", query.getString(query.getColumnIndex("source")));
                    contentValues.put("serial_no", query.getString(query.getColumnIndex("serial_no")));
                    contentValues.put("userDeviceId", Integer.valueOf(query.getInt(query.getColumnIndex("userDeviceId"))));
                    contentValues.put("hr", query.getString(query.getColumnIndex("hr")));
                    contentValues.put("systolicBp", query.getString(query.getColumnIndex("systolicBp")));
                    contentValues.put("diastolicBp", query.getString(query.getColumnIndex("diastolicBp")));
                    contentValues.put("spo2", Long.valueOf(query.getLong(query.getColumnIndex("spo2"))));
                    contentValues.put("spo2Level", Long.valueOf(query.getLong(query.getColumnIndex("spo2Level"))));
                    contentValues.put(DeviceKey.TempData, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.TempData))));
                    contentValues.put("isSyncedWithServer", Integer.valueOf(query.getInt(query.getColumnIndex("isSyncedWithServer"))));
                    contentValues.put("isLevelInterpretation", Integer.valueOf(query.getInt(query.getColumnIndex("isLevelInterpretation"))));
                    supportSQLiteDatabase.insert("manual_data_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE manual_data");
                supportSQLiteDatabase.execSQL("ALTER TABLE manual_data_temp RENAME TO manual_data");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE manual_data");
            supportSQLiteDatabase.execSQL("ALTER TABLE manual_data_temp RENAME TO manual_data");
        }
    }

    /* loaded from: classes8.dex */
    public static class y extends Migration {
        public y(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_spo2", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
        }
    }

    /* loaded from: classes8.dex */
    public static class z extends Migration {
        public z(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `device_info_temp` (`macAddress` TEXT NOT NULL, `is_active` INTEGER NOT NULL, `last_sync_timestamp` INTEGER NOT NULL, `last_sync_date_walk` TEXT, `last_sync_date_spo2` TEXT, `last_sync_date_raw_ppg` TEXT, `last_sync_date_accelerometer` TEXT, `last_sync_date_sedentary` TEXT, `last_sync_date_sleep` TEXT, `last_sync_date_hr` TEXT, `last_sync_date_bp` TEXT, `last_sync_date_temperature` TEXT, `last_sync_date_rr` TEXT, `last_server_sync_steps` TEXT, `last_server_sync_sleep` TEXT, PRIMARY KEY(`macAddress`))");
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM device_info");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DeviceKey.MacAddress, Long.valueOf(query.getLong(query.getColumnIndex(DeviceKey.MacAddress))));
                    contentValues.put("is_active", query.getString(query.getColumnIndex("is_active")));
                    contentValues.put("last_sync_timestamp", query.getString(query.getColumnIndex("last_sync_timestamp")));
                    contentValues.put("last_sync_date_walk", Integer.valueOf(query.getInt(query.getColumnIndex("last_sync_date_walk"))));
                    contentValues.put("last_sync_date_sleep", query.getString(query.getColumnIndex("last_sync_date_sleep")));
                    contentValues.put("last_sync_date_hr", query.getString(query.getColumnIndex("last_sync_date_hr")));
                    contentValues.put("last_sync_date_bp", query.getString(query.getColumnIndex("last_sync_date_bp")));
                    contentValues.put("last_sync_date_temperature", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_temperature"))));
                    contentValues.put("last_sync_date_rr", Long.valueOf(query.getLong(query.getColumnIndex("last_sync_date_rr"))));
                    contentValues.put("last_server_sync_steps", Long.valueOf(query.getLong(query.getColumnIndex("last_server_sync_steps"))));
                    contentValues.put("last_server_sync_sleep", Integer.valueOf(query.getInt(query.getColumnIndex("last_server_sync_sleep"))));
                    contentValues.put("last_sync_date_raw_ppg", "");
                    contentValues.put("last_sync_date_spo2", "");
                    contentValues.put("last_sync_date_accelerometer", "");
                    contentValues.put("last_sync_date_sedentary", "");
                    supportSQLiteDatabase.insert("device_info_temp", 5, contentValues);
                } while (query.moveToNext());
                supportSQLiteDatabase.execSQL("DROP TABLE device_info");
                supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
            }
            supportSQLiteDatabase.execSQL("DROP TABLE device_info");
            supportSQLiteDatabase.execSQL("ALTER TABLE device_info_temp RENAME TO device_info");
        }
    }

    public static CoveAppDatabase getAppDatabase(Context context) {
        if (f6933a == null) {
            f6933a = (CoveAppDatabase) Room.databaseBuilder(context.getApplicationContext(), CoveAppDatabase.class, covedatabase).allowMainThreadQueries().addMigrations(b).addMigrations(c).addMigrations(d).addMigrations(e).addMigrations(f).addMigrations(g).addMigrations(h).addMigrations(i).addMigrations(j).addMigrations(k).addMigrations(n).addMigrations(l).addMigrations(m).addMigrations(o).addMigrations(p).addMigrations(q).addMigrations(r).addMigrations(s).addMigrations(t).addMigrations(u).addMigrations(v).addMigrations(w).addMigrations(x).addMigrations(y).addMigrations(z).addMigrations(A).addMigrations(B).build();
        }
        return f6933a;
    }

    public abstract AmbientSoundDao ambientSoundDao();

    public abstract BpDataDao bpDataDao();

    public abstract DaoProfile daoProfile();

    public abstract DeviceInfoDao deviceInfoDao();

    public abstract ECGDao ecgDao();

    public abstract HeartRateDao heartRateDao();

    public abstract HRVDao hrvDao();

    public abstract WalkDataDao mFiveMinuteWData();

    public abstract SleepDataDao mSleepDataDao();

    public abstract ManualDataDao manualDataDao();

    public abstract RawAccelerometerDao rawAccelerometerDao();

    public abstract RawPPGDao rawPPGDao();

    public abstract RrDataDao rrDataDao();

    public abstract SedentaryDataDao sedentaryDataDao();

    public abstract Spo2Dao spo2Dao();

    public abstract SensAIDataDao stanceBeamDataDao();

    public abstract StressDao stressDao();

    public abstract TemperatureDao temperatureDao();
}
