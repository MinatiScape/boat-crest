package com.coveiot.khjstyledb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleEntityDeviceInfo;
import com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao;
import com.coveiot.khjstyledb.gps.KHJstyleSessionGPSData;
import com.coveiot.khjstyledb.heartrate.KHJstyleEntitySessionHeartRateData;
import com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao;
import com.coveiot.khjstyledb.walk.KHJstyleHourlyWalkData;
import com.coveiot.khjstyledb.walk.KHJstyleWalkDataDao;
@TypeConverters({Convertors.class, ConvertorsMediaList.class, TimestampConverter.class})
@Database(entities = {KHJstyleEntitySessionHeartRateData.class, KHJstyleHourlyWalkData.class, KHJstyleEntityDeviceInfo.class, KHJstyleSessionGPSData.class}, exportSchema = false, version = 2)
/* loaded from: classes8.dex */
public abstract class KHJstyleAppDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static KHJstyleAppDatabase f7115a = null;
    public static final Migration b = new a(1, 2);
    public static final String covedatabase = "khjstyledatabase";

    /* loaded from: classes8.dex */
    public static class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `history_session_gps` (`serialNo` TEXT NOT NULL, `timeStamp` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`serialNo`, `timeStamp`))");
        }
    }

    public static KHJstyleAppDatabase getAppDatabase(Context context) {
        if (f7115a == null) {
            f7115a = (KHJstyleAppDatabase) Room.databaseBuilder(context.getApplicationContext(), KHJstyleAppDatabase.class, covedatabase).allowMainThreadQueries().addMigrations(b).build();
        }
        return f7115a;
    }

    public abstract KHJstyleDeviceInfoDao deviceInfoDao();

    public abstract KHJstyleGPSDataDao gpsDataDao();

    public abstract KHJstyleHeartRateDao heartRateDao();

    public abstract KHJstyleWalkDataDao walkDataDao();
}
